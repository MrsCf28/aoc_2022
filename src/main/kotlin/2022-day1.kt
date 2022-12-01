private val input = readResourceFileAsLines("2022-day1.txt").map { it.toIntOrNull() }

fun main() {
    val calories: MutableList<Int> = mutableListOf()
    var currentElf: Int = 0
    for (item in input) {
        if (item != null) {
            currentElf += item
        } else {
            calories.add(currentElf)
            currentElf = 0
        }
    }
    val caloriesDesc: List<Int> = calories.sortedDescending()
    val topCarrier = caloriesDesc[0]
    val top3Carriers = caloriesDesc[0] + caloriesDesc[1] + caloriesDesc[2]
    println(topCarrier)
    println(top3Carriers)
}