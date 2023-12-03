import kotlin.math.max

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
        .mapNotNull {
            lineRegex.matchEntire(it)
        }.map { matchResult ->
            with(matchResult) {
                val allGamesIsValid = groups["games"]!!.value.split(";").map { currentGame ->
                    mapOfColors.mapValues { (_, colorRegex) ->
                        colorRegex.find(currentGame, 0)?.groups?.get("number")?.value?.toInt() ?: 0
                    }.entries.all { (color, numberInGame) ->
                        numberInGame <= gameSetOfCubes[color]!!
                    }
                }.all { it }
                groups["gameNo"]!!.value.toInt() to allGamesIsValid
            }
        }.mapNotNull {(gameNo, isAllGamesValid) ->
            gameNo.letWhenNotTrue(isAllGamesValid) { null }
        }.sum()

    fun part2(input: List<String>) = input
        .mapNotNull {
            lineRegex.matchEntire(it)
        }.map { matchResult ->
            with(matchResult) {
                groups["games"]!!.value.split(";").map { currentGame ->
                    mapOfColors.mapValues { (_, colorRegex) ->
                        colorRegex.find(currentGame, 0)?.groups?.get("number")?.value?.toInt() ?: 0
                    }
                }
            }.fold(gameSetOfCubes.mapValues { 0 }) { acc, curr ->
                curr.mapValues { (currentColor, currentNumber) ->
                    max(currentNumber, acc[currentColor]!!)
                }
            }.values.reduce(Int::times)
        }.sum()

    readInput("Day02_test1").also {
        check(part2(it) == 2286)
    }

    readInput("Day02").also {
        part2(it).println()
    }
}
