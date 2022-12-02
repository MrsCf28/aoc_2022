private val input = readResourceFileAsLines("2022-day2.txt").map { it.split(" ") }
fun main() {
    var score = 0
    for (game in input) {
        val elf = when (game[0]) {
            "A" -> "X"
            "B" -> "Y"
            "C" -> "Z"
            else -> error("invalid input")
        }
        when (game[1]) {
            "X" -> score += 1
            "Y" -> score += 2
            "Z" -> score += 3
        }
        if (elf == game[1]) {
            score += 3
        } else if (elf == "X" && game[1] == "Y" || elf == "Y" && game[1] == "Z" || elf == "Z" && game[1] == "X") {
            score += 6
        }
    }
    println(score)
}