fun main() {
    val listOfString = listOf(
        "one" to "1", "1" to "1",
        "two" to "2", "2" to "2",
        "three" to "3", "3" to "3",
        "four" to "4", "4" to "4",
        "five" to "5", "5" to "5",
        "six" to "6", "6" to "6",
        "seven" to "7", "7" to "7",
        "eight" to "8", "8" to "8",
        "nine" to "9", "9" to "9",
        "zero" to "0", "0" to "0",
    )

    fun part1(input: List<String>) = input
        .map {
            it.toCharArray().filter(Character::isDigit)
        }.sumOf {
            when {
                it.isEmpty() -> "0"
                else -> "${it.first()}${it.last()}"
            }.toInt()
        }
    fun part2(input: List<String>) = input
        .sumOf {currentLine ->
            val first = listOfString.minByOrNull { (numberInText, _) ->
                currentLine.indexOf(numberInText).let {
                    if(it == -1) Int.MAX_VALUE else it
                }
            }!!.second

            val last = listOfString.maxByOrNull { (numberInText, _) ->
                currentLine.lastIndexOf(numberInText)
            }!!.second

            "$first$last".toInt()
        }

    readInput("Day01_test01").also {
        check(part1(it) == 142)
    }

    readInput("Day01_test02").also {
        check(part2(it) == 281)
    }

    readInput("Day01").also {
        part1(it).println()
        part2(it).println()
    }
}
