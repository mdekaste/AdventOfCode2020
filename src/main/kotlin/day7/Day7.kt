package day7

import AdventOfCode

object Day7 : AdventOfCode() {
    val parsed: Map<String, List<Pair<Int, String>>> = parseInput(input)

    override fun part1() = mutableMapOf("shiny gold" to true).let { mem -> parsed.keys.count { containsShinyGold(mem, it) } - 1 }

    private fun containsShinyGold(memory: MutableMap<String, Boolean>, currentColor: String) : Boolean = memory.getOrPut(currentColor){
        parsed.getValue(currentColor).any { containsShinyGold(memory, it.second) }
    }

    override fun part2() = countBags(mutableMapOf(), "shiny gold") - 1

    private fun countBags(memory: MutableMap<String, Int>, color: String) : Int = memory.getOrPut(color){
        1 + parsed.getValue(color).sumBy { (amount, other) -> amount * countBags(memory, other) }
    }

    private fun parseInput(input: String) : Map<String, List<Pair<Int, String>>>{
        return input.lines().map { line ->
            val (main, rest) = line.split(" bags contain ")
            main to when (rest) {
                "no other bags." -> emptyList()
                else -> rest.split(", ").map {
                    it.substringBefore(" ").toInt() to it.substringAfter(" ").substringBefore(" bag")
                }
            }
        }.toMap()
    }
}


//dark indigo bags contain 2 clear indigo bags.
//posh chartreuse bags contain 2 faded blue bags, 2 pale plum bags, 2 posh coral bags, 4 vibrant bronze bags.
//dim fuchsia bags contain 5 dark red bags, 1 muted green bag, 4 clear indigo bags.
//shiny crimson bags contain no other bags.