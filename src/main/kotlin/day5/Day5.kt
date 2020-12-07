package day5

import AdventOfCode
import java.util.*

object Day5 : AdventOfCode() {
    private val parsed = input.lines().map(Seat::of).associateByTo(TreeMap(), Seat::seatID)

    override fun part1() = parsed.lastKey()

    override fun part2(): Any? {
        var counter = parsed.firstKey()
        for(e in parsed.keys){
            if(counter != e){
                return counter
            }
            counter++
        }
        error("no seat found")
    }
}

data class Seat(val row: Int, val col: Int){
    val seatID = row * 8 + col
    companion object{
        fun of(input: String, rowL: Int = 0, rowH: Int = 128, colL: Int = 0, colH: Int = 8): Seat = when(input.firstOrNull()){
            null -> Seat(row = rowL, col = colL)
            'F' -> of(input.drop(1), rowL, (rowL + rowH) / 2, colL, colH)
            'B' -> of(input.drop(1), (rowL + rowH) / 2, rowH, colL, colH)
            'L' -> of(input.drop(1), rowL, rowH, colL, (colL + colH) / 2)
            'R' -> of(input.drop(1), rowL, rowH, (colL + colH) / 2, colH)
            else -> error("input mismatch")
        }
    }
}
