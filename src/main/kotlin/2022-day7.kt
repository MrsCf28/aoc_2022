private val input = readResourceFileAsLines("2022-day7.txt")

sealed class Item {
    abstract val name: String
}
class Directory(
    override val name: String,
    val parentDir: Directory? = null,
    val subDirectories: MutableList<Directory> = mutableListOf(),
    private val files: MutableList<File> = mutableListOf()
) : Item() {

    fun addSubDirectory(child: Directory) {
        subDirectories.add(child)
    }

    fun addFile(child: File) {
        files.add(child)
    }

    fun getSize(): Int = files.sumOf { it.size } + subDirectories.sumOf {it.getSize()}

    fun getAllSubDirectories(): List<Directory> = subDirectories + subDirectories.flatMap { it.getAllSubDirectories() }
}

class File(override val name: String, val size: Int) : Item() {
}

fun main() {
    val homeDir = Directory("/")
    var currentDir = homeDir

    for (line in input) {
        if (line.startsWith("$ ls")) {
            continue
        }
        if (line.startsWith("$ cd")) {
            if (line == "$ cd ..") {
                currentDir = currentDir.parentDir ?: homeDir
            } else {
                for (child in currentDir.subDirectories) {
                    if (child.name == line.substring(5)) {
                        currentDir = child
                    }
                }
            }
        }
        if (line.startsWith("dir")) {
            val name = line.substring(4)
            val parentDir = currentDir
            val newDir = Directory(name, parentDir)
            currentDir.addSubDirectory(newDir)
        }
        if (line[0].isDigit()) {
            val (size, name) = line.split(" ")
            val newFile = File(name, size.toInt())
            currentDir.addFile(newFile)
        }
    }

    println(homeDir.getAllSubDirectories().filter { it.getSize() <= 100000 }.sumOf { it.getSize() })

    val directoriesSizesAsc = homeDir.getAllSubDirectories().map{ it.getSize()}.sorted()
    val neededSpace = homeDir.getSize() - 40000000
    for (dirSize in directoriesSizesAsc) {
        if (dirSize >= neededSpace) {
            println(dirSize)
            break
        }
    }







}