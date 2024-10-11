package isel.tds.galo.view

data class CommandLine(val name: String, val args: List<String>)

fun readCommand(): CommandLine {
    print("> ")

    return handleLineRead { readln() }
}

private fun handleLineRead(readLine: () -> String): CommandLine {
    val line = readLine().split(' ').filter { it.isNotBlank() }
    return if (line.isEmpty()) handleLineRead(readLine)
    else CommandLine(line.first().uppercase(), line.drop(1))
}