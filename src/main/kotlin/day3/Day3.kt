package day3

import AdventOfCode

object Day3 : AdventOfCode() {
    private val parsed = input.lines()
    private val height = parsed.size
    private val width = parsed[0].length

    override fun part1() = slopeCount(3, 1)

    override fun part2() = 1L *
            slopeCount(1,1) *
            slopeCount(3,1) *
            slopeCount(5,1) *
            slopeCount(7,1) *
            slopeCount(1,2)

    private fun slopeCount(right: Int, down: Int) =
            generateSequence(0 to 0){ (x, y) -> (x + right) % width to y + down }
                .takeWhile { (_, y) -> y < height }
                .count { (x, y) -> parsed[y][x] == '#' }

}