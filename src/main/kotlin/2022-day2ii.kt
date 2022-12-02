private val input = readResourceFileAsLines("2022-day2.txt").map { it.split(" ") }
fun main() {
    var score: Int = 0
    for (game in input) {
        val elf = when (game[0]) {
            "A" -> 1 // "rock"
            "B" -> 2 // "paper"
            "C" -> 3 // "scissors"
            else -> error("invalid input")
        }
        score += if (game[1] == "Y") { //draw
            3 + elf
        } else if (game[1] == "Z") { // win
            6 + elf % 3 + 1
        } else { //lose
            (elf + 1) % 3 + 1
        }
    }
    println(score)
}