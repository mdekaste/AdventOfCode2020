package day4

import AdventOfCode
import java.lang.System.lineSeparator
import java.util.regex.Pattern

object Day4 : AdventOfCode() {
    private val parsed = input.split("\r\n\r\n").map(Passport::of)
    private val hr = """(?<=\d)(?=\D)""".toRegex()

    override fun part1() = parsed.count { it != null }
    override fun part2() = parsed.filterNotNull().filter(::correctPassport).count()

    private fun correctPassport(passport: Passport) : Boolean {
        if(passport.birthYear !in 1920..2002) return false
        if(passport.issueYear !in 2010..2020) return false
        if(passport.expirationYear !in 2020..2030) return false
        passport.height.split(hr).let { it[0].toInt() to if(it.size == 1) return false else it[1] }.let { (h, u) -> when(u){
            "cm" -> if(h !in 150..193) return false
            "in" -> if(h !in 59..76) return false
        }}
        if(!passport.hairColor.startsWith("#") || passport.hairColor.substring(1).toIntOrNull(16) == null) return false
        if(passport.eyeColor !in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")) return false
        if(passport.passportID.length != 9 || passport.passportID.toIntOrNull() == null) return false
        return true
    }
}

data class Passport(
        val birthYear: Int,
        val issueYear: Int,
        val expirationYear: Int,
        val height: String,
        val hairColor: String,
        val eyeColor: String,
        val passportID: String,
        val countryID: Int?
){
    companion object{
        private val splitRegex = """\s+""".toRegex()
        fun of(input: String) : Passport?{
            val variables = input.split(splitRegex).map { it.split(":") }.associateBy ({it[0]}, {it[1]})
            return Passport(
                    birthYear = variables["byr"]?.toInt() ?: return null,
                    issueYear = variables["iyr"]?.toInt() ?: return null,
                    expirationYear = variables["eyr"]?.toInt() ?: return null,
                    height = variables["hgt"] ?: return null,
                    hairColor = variables["hcl"] ?: return null,
                    eyeColor = variables["ecl"] ?: return null,
                    passportID = variables["pid"] ?: return null,
                    countryID = variables["cid"]?.toInt()
            )
        }
    }
}