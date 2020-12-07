package day7

import AdventOfCode

object Day7 : AdventOfCode() {
    private val parsed: Map<String, Map<String, Int>> = input.lines().map { line ->
        val (main, rest) = line.split(" bags contain ")
        main to when (rest) {
            "no other bags." -> emptyMap()
            else -> rest.split(", ").map {
                it.substringAfter(" ").substringBefore(" bag") to it.substringBefore(" ").toInt()
            }.toMap()
        }
    }.toMap()

    private const val GOLD = "shiny gold"

    override fun part1() = mutableMapOf(GOLD to true).let { mem -> parsed.keys.count { containsShinyGold(mem, it) } - 1 }

    private fun containsShinyGold(memory: MutableMap<String, Boolean>, currentColor: String) : Boolean = memory.getOrPut(currentColor){
        parsed.getValue(currentColor).any { containsShinyGold(memory, it.key) }
    }

    override fun part2() = countBags(mutableMapOf(), GOLD) - 1

    private fun countBags(memory: MutableMap<String, Int>, color: String) : Int = memory.getOrPut(color){
        1 + parsed.getValue(color).entries.sumBy{ (name, amount) -> amount * countBags(memory, name) }
    }
}