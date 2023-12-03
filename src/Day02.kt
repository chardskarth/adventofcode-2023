fun main() {
    val lineRegex = """Game (?<gameNo>\d+): (?<games>.*)""".toRegex(RegexOption.DOT_MATCHES_ALL)
    val mapOfColors = mapOf(
        "blue" to null, "green" to null, "red" to null
    ).mapValues { (color, _) -> """(?<number>\d+) $color""".toRegex() }

    val gameSetOfCubes = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14,
    )

    fun part1(input: List<String>) = input
        .mapNotNull { currentLine ->
            with(lineRegex.matchEntire(currentLine)!!) {
                val allGamesIsValid = groups["games"]!!.value.split(";").map {currentGame ->
                    mapOfColors.mapValues {(_, colorRegex) ->
                        colorRegex.find(currentGame, 0)?.groups?.get("number")?.value?.toInt() ?: 0
                    }.entries.all {(color, numberInGame)  ->
                        numberInGame <= gameSetOfCubes[color]!!
                    }
                }.all { it }

                groups["gameNo"]!!.value.toInt().letWhenTrue(!allGamesIsValid) {
                    null
                }
            }
        }.sum()

    readInput("Day02_test1").also {
        check(part1(it) == 8)
    }

    readInput("Day02").also {
        part1(it).println()
    }
}
