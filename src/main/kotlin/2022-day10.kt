private val input = readResourceFileAsLines("2022-day10.txt")

class ClockCircuit(var cycle: Int, var x: Int, var sum: Int, var position: Int)

fun main() {
    val circuit = ClockCircuit(0,1,0,0)
    for (line in input) {
        tick(circuit)
        if (line.startsWith("add")) {
            tick(circuit)
            circuit.x += line.split(" ")[1].toInt()
        }
    }
    println(circuit.sum)
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

fun tick(c: ClockCircuit) {
    printPixel(c.position, c.x)
    c.cycle++
    c.position++
    if (c.position % 40 == 0) {
        c.position = 0
        println()
    }
    if (isProminentCycle(c.cycle)) {
        c.sum += c.x * c.cycle
    }
}