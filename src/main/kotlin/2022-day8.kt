private val input = readResourceFileAsLines("2022-day8.txt")

fun main() {
    val widthOfForest = input[0].length
    val lengthOfForest = input.size
    val numOfOutsideTrees = 2 * (widthOfForest + lengthOfForest - 2)

    var visibleTrees = 0

    for (row in 1 until lengthOfForest - 1) {
        for (column in 1 until widthOfForest - 1) {
            if (isVisible(row, column, input, input[row][column].digitToInt())) {
                visibleTrees++
            }
        }
    }
    println(visibleTrees + numOfOutsideTrees)
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