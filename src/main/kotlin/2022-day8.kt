private val input = readResourceFileAsLines("2022-day8.txt")

fun main() {
    val widthOfForest = input[0].length
    val lengthOfForest = input.size
    val numOfOutsideTrees = 2 * (widthOfForest + lengthOfForest - 2)

    var visibleTrees = numOfOutsideTrees
    var maxScenicScore = 0

    for (row in 1 until lengthOfForest - 1) {
        for (column in 1 until widthOfForest - 1) {
            if (isVisible(row, column, input, input[row][column].digitToInt())) {
                visibleTrees++
            }
            val currentScenicScore = getScenicScore(row, column, input, input[row][column].digitToInt())
            if (currentScenicScore > maxScenicScore) {
                maxScenicScore = currentScenicScore
            }
        }
    }
    println("number of visible trees: $visibleTrees")
    println("max scenic score $maxScenicScore")
}

fun isVisible(row: Int, column: Int, input: List<String>, thisTree: Int): Boolean {
    val directions = mutableListOf<Boolean>(true, true, true, true)
    // (above, below, left, right)
    // check above
    for (i in 0 until row) {
        if (thisTree <= input[i][column].digitToInt()) {
            directions[0] = false
            break
        }
    }

    // check below
    for (i in row + 1 until input.size) {
        if (thisTree <= input[i][column].digitToInt()) {
            directions[1] = false
            break
        }
    }

    // check left
    for (i in 0 until column) {
        if (thisTree <= input[row][i].digitToInt()) {
            directions[2] = false
            break
        }
    }

    // check right
    for (i in column + 1 until input[0].length) {
        if (thisTree <= input[row][i].digitToInt()) {
            directions[3] = false
            break
        }
    }

    return (directions[0] || directions[1] || directions[2] || directions[3])

}

fun getScenicScore(row: Int, column: Int, input: List<String>, thisTree: Int): Int {
    var countAbove = 0
    var countBelow = 0
    var countLeft = 0
    var countRight = 0

    // score above
    for (i in row - 1 downTo 0) {
        countAbove++
        if (thisTree <= input[i][column].digitToInt()) {
            break
        }
    }

    // score below
    for (i in row + 1 until input.size) {
        countBelow++
        if (thisTree <= input[i][column].digitToInt()) {
            break
        }
    }

    // score left
    for (i in column - 1 downTo 0) {
        countLeft++
        if (thisTree <= input[row][i].digitToInt()) {
            break
        }
    }

    // score right
    for (i in column + 1 until input[0].length) {
        countRight++
        if (thisTree <= input[row][i].digitToInt()) {
            break
        }
    }

    return (countAbove * countBelow * countLeft * countRight)

}