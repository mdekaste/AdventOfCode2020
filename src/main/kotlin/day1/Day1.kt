package day1

import AdventOfCode


object Day1 : AdventOfCode(
    name = "--- Day 1: Report Repair ---"
) {
    val parsedInput = input.lines().map { it.toInt() }

    override fun part1(): Any {
        return parsedInput.dropLast(1).flatMap { x ->
            parsedInput.drop(1).map { y -> x to y }
        }.first { (x,y) -> x + y == 2020 }
                .let { (x,y) -> x * y }
    }

    override fun part2(): Any {
        for(x in parsedInput){
            for(y in parsedInput){
                if(x == y){
                    continue
                }
                for(z in parsedInput){
                    if(x == z || y == z) {
                        continue
                    }
                    if(x + y + z == 2020){
                        return x * y * z
                    }
                }
            }
        }
        return "no answer"
    }
}
