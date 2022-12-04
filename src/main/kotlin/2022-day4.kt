private val input = readResourceFileAsLines("2022-day4.txt").map { line -> line.split(",", "-").map { it.toInt()} }

fun main() {
    val containedPairs = input.count { isFullyContained(it) }
    val overlappingPairs = input.count { isOverlapping(it) }

    println(containedPairs)
    println(overlappingPairs)
}

fun isFullyContained(section: List<Int>): Boolean {
    val (min1, max1, min2, max2) = section
    return (min2 >= min1 && max2 <= max1 || min1 >= min2 && max1 <= max2)
}

fun isOverlapping(section: List<Int>): Boolean {
    val (min1, max1, min2, max2) = section
    return (max1 >= min2 && min1 <= max2)
}