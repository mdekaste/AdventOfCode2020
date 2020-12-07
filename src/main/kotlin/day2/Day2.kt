package day2

import AdventOfCode

object Day2 : AdventOfCode(){
    private val parsed = input.lines().map(Input::of)

    override fun part1() = parsed.count { (min, max, char, text) -> text.count { it == char } in min..max }
    override fun part2() = parsed.count { (min ,max, char, text) -> (text[min - 1] == char) xor (text[max - 1] == char) }

    data class Input(val n1: Int, val n2: Int, val char: Char, val text: String){
        companion object{
            private val regex = """(\d{1,2})-(\d{1,2}) (\w): (\w+)""".toRegex()
            fun of(input: String) = regex.matchEntire(input)
                    ?.destructured
                    ?.let { (min, max, char, text) -> Input(min.toInt(), max.toInt(), char[0], text) }
                    ?: error("invalid input")
        }
    }
}