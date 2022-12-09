import kotlin.math.abs

private val input = readResourceFileAsLines("2022-day9.txt")

fun main() {
    val h = Coordinate(0, 0)
    val middle = mutableListOf<Coordinate>()
    repeat(8) {
        middle.add(Coordinate(0,0))
    }
    val t = Coordinate(0,0)

    val tailPositions = mutableSetOf<Coordinate>()
    val tailPositions2 = mutableSetOf<Coordinate>()

    for (line in input) {
        val (direction, amount) = line.split(" ")
        repeat(amount.toInt()) {
            h.move(direction)
            middle[0].moveNext(h)
            for (i in 1 .. 7) {
                middle[i].moveNext(middle[i - 1])
            }
            t.moveNext(middle[7])
            tailPositions.add(Coordinate(middle[0].x, middle[0].y))
            tailPositions2.add(Coordinate(t.x, t.y))

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

        if (xDiff == 0 || yDiff == 0) {
            if (abs(xDiff) == 2) {
                this.x += xDiff / 2
            } else if (abs(yDiff) == 2) {
                this.y += yDiff / 2
            }
        } else if (xDiff * yDiff == 2) {
            if (xDiff > 0) {
                this.x++
                this.y++
            } else {
                this.x--
                this.y--
            }
        } else if (xDiff * yDiff == -2) {
            if (xDiff > 0) {
                this.x++
                this.y--
            } else {
                this.x--
                this.y++
            }
        } else if (xDiff * yDiff == 4) {
            if (xDiff > 0) {
                this.x++
                this.y++
            } else {
                this.x--
                this.y--
            }
        } else if (xDiff * yDiff == -4) {
            if (xDiff > 0) {
                this.x++
                this.y--
            } else {
                this.x--
                this.y++
            }
        }
    }
}