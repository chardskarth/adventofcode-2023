fun main() {
    val listOfPairsOfNumbers = listOf(
        ("one" to "1"), ("two" to "2"), ("three" to "3"),
        ("four" to "4"), ("five" to "5"), ("six" to "6"),
        ("seven" to "7"), ("eight" to "8"), ("nine" to "9"), ("zero" to "0")
    )

    fun part1(input: List<String>) = input
        .map {
            it.toCharArray().filter(Character::isDigit)
        }.map {
            when {
                it.isEmpty() -> "0"
                else -> "${it.first()}${it.last()}"
            }.toInt()
        }.alsoForEach{
//            it.println()
        }.sum()

    fun part1Try2(input: List<String>) = input
        .map {
            it.lowercase()
                .replace("one", "1")
                .replace("two", "2")
                .replace("three", "3")
                .replace("four", "4")
                .replace("five", "5")
                .replace("six", "6")
                .replace("seven", "7")
                .replace("eight", "8")
                .replace("nine", "9")
                .replace("zero", "0")
        }.let {
            part1(it)
        }

    fun part1Try3(input: List<String>) = input
        .map(String::lowercase)
        .map { currentLine ->
            listOfPairsOfNumbers.fold(currentLine) { acc, (numberString, number) ->
                acc.letWhenTrue(acc.indexOf(numberString) != -1) {
                    acc.replace(numberString, number)
                }
            }
        }.let {
            it.println()
            part1(it)
        }

    fun part1Try4(input: List<String>) = input
        .map(String::lowercase)
        .map { currentLine ->
            listOfPairsOfNumbers.map {
                it to currentLine.indexOf(it.first)
            }.sortedBy { (_, index) -> index }
            .map { it.first }
            .fold(currentLine) { acc, (numberString, number) ->
                acc.letWhenTrue(acc.indexOf(numberString) != -1) {
                    acc.replace(numberString, number)
                }
            }
        }.let {
//            it.println()
            part1(it)
        }

    fun part1Try5(input: List<String>) = input
        .map(String::lowercase)
        .map { currentLine ->
            listOfPairsOfNumbers.map {
                it to currentLine.indexOf(it.first)
            }
                .filter { (_, index) -> index != -1}
                .sortedByDescending { (_, index) -> index }
                .map { it.first }
                .fold(currentLine) { acc, (numberString, number) ->
                    acc.replace(numberString, number)
                }
        }.let {
//            it.println()
            part1(it)
        }

    listOf("Day01_test01","Day01").forEach { inputFileName ->
        part1Try5(
            readInput(inputFileName)
        ).println()
    }
}
