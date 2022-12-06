fun readResourceFile(name: String) = object {}::class.java.classLoader.getResource(name)!!.readText().trimEnd()
fun readResourceFileAsLines(name: String) = readResourceFile(name).lines()