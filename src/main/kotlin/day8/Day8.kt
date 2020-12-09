package day8

import AdventOfCode

object Day8 : AdventOfCode(){
    val parsed = input.lines().withIndex().map(Instruction::of)

    override fun part1() = runCode(parsed).first

    private fun runCode(program: List<Instruction>) : Pair<Int, Boolean>{
        var accumulator = 0
        var currentIndex = 0
        var currentInstruction: Instruction? = program.getOrNull(currentIndex)
        val visited = mutableSetOf<Instruction>()

        while(currentInstruction != null && visited.add(currentInstruction)){
            when(currentInstruction.type){
                "acc" -> {
                    accumulator += currentInstruction.amount
                    currentIndex += 1;
                }
                "nop" -> currentIndex += 1
                "jmp" -> currentIndex += currentInstruction.amount
            }
            currentInstruction = program.getOrNull(currentIndex)
        }
        return accumulator to (currentInstruction == null)
    }

    override fun part2() = parsed.mapIndexedNotNull{ index, value ->
            when(value.type) {
                "nop" -> parsed.take(index) + value.copy(type = "jmp") + parsed.drop(index + 1)
                "jmp" -> parsed.take(index) + value.copy(type = "nop") + parsed.drop(index + 1)
                else -> null
            }}.map(::runCode).first{ it.second }.first

}

data class Instruction(
        val id: Int,
        val type: String,
        val amount: Int
){
    companion object{
        fun of(input: IndexedValue<String>) = input.value.split(" ")
                .let { (type, amountS) -> Instruction(input.index, type, amountS.toInt()) }
    }
}