private val input = readResourceFileAsLines("2022-day3.txt").map { it }

fun main() {
    var sharedItems = mutableListOf<Char>()
    for (rucksack in input) {
        val len = rucksack.length
        val compartment1 = rucksack.slice(0 until len / 2).toSet()
        val compartment2 = rucksack.slice(len / 2 until len).toSet()
        val sharedItem = compartment1.intersect(compartment2)
        sharedItems.add(sharedItem.first())
    }
    var prioritySum = 0;
    for (item in sharedItems) {
        val charValue = item.toInt()
        prioritySum += if (charValue in 65..90) {
            charValue - 38
        } else {
            charValue - 96
        }
    }
    println(prioritySum)
}