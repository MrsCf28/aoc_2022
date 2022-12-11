private val input = readResourceFile("2022-day11-sample.txt").split("\n\n")

fun main() {
    // parse input
    val monkeyList = mutableListOf<Monkey>()
    for (monkey in input) {
        monkeyList.add(readInput(monkey.split("\n")))
    }

    val monkeyList2 = mutableListOf<Monkey>()
    for (monkey in input) {
        monkeyList2.add(readInput(monkey.split("\n")))
    }

    // PART 1 20 rounds of Monkey Business
    repeat(20) {
        for (monkey in monkeyList) {
            for (item in monkey.itemsHeld) {
                val itemToInspect = monkey.newItemValue(item)
                monkey.itemsInspected++
                monkeyList[monkey.throwTo(itemToInspect)].itemsHeld.add(itemToInspect)
            }
            monkey.itemsHeld.clear()
        }
//        println("cycle ${it + 1}")
//        for (monkey in monkeyList) {
//            println("monkey ${monkey.num} is holding ${monkey.itemsHeld}")
//        }
    }

    // PART 1 calculations with number of items inspected
    val numOfItemsInspected = mutableListOf<Int>()
    for (monkey in monkeyList) {
        numOfItemsInspected.add(monkey.itemsInspected)
        println("monkey ${monkey.num} inspected ${monkey.itemsInspected} items")
    }
    val numOfItemsDesc = numOfItemsInspected.sortedDescending()
    val monkeyBusiness = numOfItemsDesc[0] * numOfItemsDesc[1]
    println("monkey Business: $monkeyBusiness")

    // PART 2 10000 rounds of Monkey Business
    repeat(10000) {
        for (monkey in monkeyList) {
            for (item in monkey.itemsHeld) {
                val worryItem = monkey.operate(item)
                monkey.itemsInspected++
                monkeyList[monkey.throwTo(worryItem)].itemsHeld.add(worryItem)
            }
            monkey.itemsHeld.clear()
        }
        println("cycle ${it + 1}")
        for (monkey in monkeyList) {
            println("monkey ${monkey.num} is holding ${monkey.itemsHeld}")
        }
    }
}

data class Monkey(
    val num: Int,
    val itemsHeld: MutableList<Long>,
    val operator: Char,
    val operand: Int?,
    val divisibleTest: Int,
    val trueMonkey: Int,
    val falseMonkey: Int
) {
    var itemsInspected = 0
    fun operate(item: Long): Long {
        val operands = operand?.toLong() ?: item
        return when (operator) {
            '*' -> item * operands
            '+' -> item + operands
            else -> error("unknown operation")
        }
    }

    fun newItemValue(item: Long): Long {
        return operate(item) / 3
    }
    fun throwTo(item: Long): Int {
        return if (item % divisibleTest == 0L) {
            trueMonkey
        } else {
            falseMonkey
        }
    }
}

fun readInput(lines: List<String>): Monkey {
    val num: Int = lines[0][7].digitToInt()
    val itemsHeld: MutableList<Long> = lines[1].substring(18).split(", ").map {it.toLong()}.toMutableList()
    val operator: Char = lines[2][23]

    val operand: Int? = lines[2].substring(25).trim().toIntOrNull()
    val divisibleTest: Int = lines[3].substring(21).toInt()
    val trueMonkey: Int = lines[4].substring(29).toInt()
    val falseMonkey: Int = lines[5].substring(30).toInt()
    return Monkey(num, itemsHeld, operator, operand, divisibleTest, trueMonkey, falseMonkey)
}
