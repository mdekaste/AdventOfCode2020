import java.io.File
import java.lang.Exception
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

abstract class AdventOfCode(
    val name: String? = null,
) {
    val input = File(javaClass.getResource("input").path).readText()

    abstract fun part1() : Any?
    abstract fun part2() : Any?

    fun printSolutions(measureTime: Int? = null){
        val part1Solution = part1()
        println("Solution to part1: $part1Solution")
        val part2Solution = part2()
        println("Solution to part2: $part2Solution")
        if(measureTime != null) {
            val timeToSolve1OrException = measureNanoTime { repeat(measureTime) { part1() } } / measureTime.toDouble()
            println("Average time to solve part1: $timeToSolve1OrException nanoseconds")
            val timeToSolve2OrException = measureNanoTime { repeat(measureTime) { part2() } } / measureTime.toDouble()
            println("Average time to solve part2: $timeToSolve2OrException nanoseconds")
        }
    }
}