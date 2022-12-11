private val input = readResourceFileAsLines("2022-day10.txt")

fun main() {
    var cycle = 0
    var x = 1
    var sum = 0
    var position = 0
    for (line in input) {
        if (x == position || x + 1 == position || x - 1 == position) {
            print('#')
        } else {
            print('.')
        }
        cycle++
        position++
        if (position % 40 == 0) {
            position = 0
            println()
        }
        if (isProminentCycle(cycle)) {
            sum += x * cycle
        }
        if (line.startsWith("addx")) {
            if (x == position || x + 1 == position || x - 1 == position) {
                print('#')
            } else {
                print('.')
            }
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