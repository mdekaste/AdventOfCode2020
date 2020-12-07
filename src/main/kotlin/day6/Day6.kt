package day6

import AdventOfCode
import java.util.*

object Day6 : AdventOfCode(){
    private val parsed = input.split("\r\n\r\n")
            .map {
                it.lines().map {
                    it.fold(BitSet()){ acc: BitSet, c: Char ->
                        acc.apply { set(c - 'a') }
                    }
                }
            }

    override fun part1() = parsed.sumBy { it.reduce { acc, bitSet -> acc.apply { or(bitSet) } }.cardinality() }
    override fun part2() = parsed.sumBy { it.reduce { acc, bitSet -> acc.apply { and(bitSet) } }.cardinality() }
}