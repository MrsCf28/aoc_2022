private val input = readResourceFileAsLines("2022-day10.txt")

fun main() {
    var cycle = 0
    var x = 1
    var sum = 0
    var position = 0
    for (line in input) {
        printPixel(position, x)
        cycle++
        position++
        if (position % 40 == 0) {
            position = 0
            println()
        }
        if (isProminentCycle(cycle)) {
            sum += x * cycle
        }
        if (line.startsWith("add")) {
            printPixel(position, x)
            cycle++
            position++
            if (position % 40 == 0) {
                position = 0
                println()
            }
            if (isProminentCycle(cycle)) {
                sum += x * cycle
            }
            x += line.split(" ")[1].toInt()
        }
    }
    println(sum )

}

fun isProminentCycle(cycle: Int): Boolean {
    val prominentCycles = listOf(20, 60, 100, 140, 180, 220)
    return prominentCycles.contains(cycle)
}

fun printPixel(position: Int, x: Int) {
    if (position in x-1..x+1) {
        print('#')
    } else {
        print('.')
    }
}

fun tick() {

}