private val input = readResourceFile("2022-day6.txt").trim()

fun main() {
    println(findDistinctSubStr(input, 4))
    println(findDistinctSubStr(input, 14))

}

fun findDistinctSubStr(input: String, len: Int): Int {
    for (i in input.indices) {
        val subStr = input.substring(i, i + len)
        val distinctSubStr = subStr.split("").distinct().joinToString("").length

        if (subStr.length == distinctSubStr) {
            return i + len
        }
    }
    return 0
}