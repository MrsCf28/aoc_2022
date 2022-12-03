private val input = readResourceFileAsLines("2022-day3.txt")

fun main() {
    // part 1
    var prioritySum = 0
    for (rucksack in input) {
        val len = rucksack.length
        val compartment1 = rucksack.substring(0, len / 2).toSet()
        val compartment2 = rucksack.substring(len / 2).toSet()
        val sharedItem = compartment1.intersect(compartment2)
        prioritySum += priorityValue(sharedItem.first().code)
    }
    println(prioritySum)

    // part 2
    val chunkedInput = input.chunked(3)
    var badgeSum = 0
    for (group in chunkedInput) {
        val sack1 = group[0].toSet()
        val sack2 = group[1].toSet()
        val sack3 = group[2].toSet()
        val badgeItem = sack1.intersect(sack2.intersect(sack3))
        badgeSum += priorityValue(badgeItem.first().code)
    }
    println(badgeSum)
}

fun priorityValue(charValue: Int): Int {
    return if (charValue in 65..90) {
        charValue - 38
    } else {
        charValue - 96
    }
}