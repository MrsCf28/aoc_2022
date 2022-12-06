private val input = readResourceFile("2022-day5.txt").split("\n\n").map { it.lines() }

fun main() {
    val stacks = createStacksList(input[0])
    val stacks2 = createStacksList(input[0])
    val instructions = input[1]

    for (instruction in instructions) {
        moveOneByOne(instruction, stacks)
        moveStacks(instruction, stacks2)
    }

    println(getTopOfStacks(stacks))
    println(getTopOfStacks(stacks2))
}

fun getTopOfStacks(stacks: List<MutableList<Char>>): String {
    var topOfStacks = ""
    for (crate in stacks) {
        topOfStacks += crate[0]
    }
    return topOfStacks
}

fun moveOneByOne(instruction: String, stacks: List<MutableList<Char>>) {
    val (numOfBoxes, start, end) = interpretInstruction(instruction)
    repeat(numOfBoxes) {
        stacks[end - 1].add(0, stacks[start - 1][0])
        stacks[start - 1].removeAt(0)
    }
}

fun moveStacks(instruction: String, stacks: List<MutableList<Char>>) {
    val (numOfBoxes, start, end) = interpretInstruction(instruction)
    stacks[end - 1].addAll(0, stacks[start - 1].subList(0, numOfBoxes))
    repeat(numOfBoxes) {
        stacks[start - 1].removeAt(0)
    }
}

fun interpretInstruction(instruction: String): List<Int> {
    return Regex("[0-9]+").findAll(instruction).map(MatchResult::value).map { it.toInt() }.toList()
}

fun createStacksList(stackInput: List<String>): List<MutableList<Char>> {
    val stackSize = stackInput.size - 1
    val numOfCrates = stackInput[stackInput.size - 1].last().digitToInt()
    val stacks: List<MutableList<Char>> = List(numOfCrates) { mutableListOf() }

    for (i in 0 until stackSize) {
        for ((crate, j) in (1 until 4 * numOfCrates step 4).withIndex()) {
            val charAt = stackInput[i].getOrNull(j)
            if (charAt != ' ' && charAt != null) {
                stacks[crate].add(charAt)
            }
        }
    }
    return stacks
}