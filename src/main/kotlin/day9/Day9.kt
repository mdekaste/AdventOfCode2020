package day9

import AdventOfCode
import java.util.*
import kotlin.collections.ArrayDeque

object Day9 : AdventOfCode(){
    val parsed = input.lines().map(String::toLong)

    override fun part1(): Any? {
        val numberWithPairs = parsed.take(25).toCollection(ArrayDeque(25))
        val allSums = numberWithPairs.flatMap { x -> numberWithPairs.map { y -> y + x } }.toMutableSet()
        for(value in parsed.drop(25)){
            if(!allSums.contains(value)){
                return value
            }
            numberWithPairs.removeFirst()
            for(numberWithPair in numberWithPairs){
                allSums.add(numberWithPair + value)
            }
            numberWithPairs.addLast(value)
        }
        return null
    }

    override fun part2(): Any? {
        val wrongNumber = part1() as Long
        val currentPos = parsed.indexOf(wrongNumber)
        val list = (0 until currentPos).reversed().mapNotNull{ index -> findUp(wrongNumber, index) }.first()
        return list.minOrNull()!! + list.maxOrNull()!!
    }

    private fun findUp(target: Long, curIndex: Int, values: MutableList<Long> = mutableListOf()) : MutableList<Long>? {
        val curValue = parsed.getOrElse(curIndex){Long.MAX_VALUE}
        values.add(curValue)
        return when{
            curValue == target -> values
            curValue < target -> findUp(target - curValue, curIndex - 1, values)
            else -> null
        }
    }
}