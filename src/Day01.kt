fun main() {
    fun part1(input: List<String>) = input
        .map {
            it.toCharArray().filter(Character::isDigit)
        }.sumOf {
            when {
                it.isEmpty() -> "0"
                else -> "${it.first()}${it.last()}"
            }.toInt()
        }

    readInput("Day01").also {
        part1(it).println()
    }
}
