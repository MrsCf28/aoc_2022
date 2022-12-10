import kotlin.math.abs
import kotlin.math.sign

private val input = readResourceFileAsLines("2022-day9.txt")

fun main() {
    val head = Coordinate(0, 0)
    val tail = MutableList(9) { Coordinate(0, 0) }

    val tailPositions = mutableSetOf<Coordinate>()
    val tailPositions2 = mutableSetOf<Coordinate>()

    for (line in input) {
        val (direction, amount) = line.split(" ")
        repeat(amount.toInt()) {
            head.move(direction)
            for (i in tail.indices) {
                if (i == 0) {
                    tail[0].moveNext(head)
                } else {
                    tail[i].moveNext(tail[i - 1])
                }
            }
            tailPositions.add(Coordinate(tail[0].x, tail[0].y))
            tailPositions2.add(Coordinate(tail[8].x, tail[8].y))
        }
    }

    println(tailPositions.size)
    println(tailPositions2.size)
}

data class Coordinate(var x: Int, var y: Int) {
    fun move(dir: String) {
        when(dir) {
            "L" -> this.x--
            "R" -> this.x++
            "D" -> this.y--
            "U" -> this.y++
            else -> error("unknown move")
        }
    }

    fun moveNext(prev: Coordinate) {
        val xDiff = prev.x - this.x
        val yDiff = prev.y - this.y

        if (xDiff * yDiff == 0) {
            if (abs(xDiff) == 2) {
                this.x += xDiff.sign
            } else if (abs(yDiff) == 2) {
                this.y += yDiff.sign
            }
        } else if (xDiff * yDiff == 2 || xDiff * yDiff == 4) {
            this.x += xDiff.sign
            this.y += yDiff.sign
        } else if (xDiff * yDiff == -2 || xDiff * yDiff == -4) {
            this.x += xDiff.sign
            this.y -= xDiff.sign
        }
    }
}