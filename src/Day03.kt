fun main() {
    val number = """(\d+)""".toRegex()
    val symbols = """[*#$+@/&=%-]""".toRegex() //listOf("*", "+", "$", "#")
    val symbolsPart2 = """[*]""".toRegex() //listOf("*", "+", "$", "#")

    fun part1(input: List<String>) = run {

        val numberAndPositions = input.mapIndexed { ii, currentLine ->
            currentLine.println()
            number.findAll(currentLine).map {
                ii to it.range andTo it.value
            }.toList()
        }.flatten()

        val symbolsAndPositions = input.mapIndexed { ii, currentLine ->
            symbols.findAll(currentLine).map {
                check(it.range.first == it.range.last)
                ii to it.range.first
            }.toList()
        }.flatten()

        numberAndPositions.filter { (numberRow, numberPositionRange) ->
            symbolsAndPositions.any { (symbolRow, symbolPositionColumn) ->
                val isNumberWithinRowOfSymbol = numberRow in (symbolRow-1)..(symbolRow+1)
                val isNumberWithinColumnOfSymbol = symbolPositionColumn in (numberPositionRange.first - 1)..(numberPositionRange.last + 1)

                isNumberWithinRowOfSymbol && isNumberWithinColumnOfSymbol
            }
        }.sumOf {
            it.third.toInt()
        }
    }

    fun part2(input: List<String>) = run {

        val numberAndPositions = input.mapIndexed { ii, currentLine ->
            currentLine.println()
            number.findAll(currentLine).map {
                ii to it.range andTo it.value
            }.toList()
        }.flatten()

        val symbolsAndPositions = input.mapIndexed { ii, currentLine ->
            symbolsPart2.findAll(currentLine).map {
                check(it.range.first == it.range.last)
                ii to it.range.first
            }.toList()
        }.flatten()

        symbolsAndPositions.map {
            val (symbolRow, symbolPositionColumn) = it
            it to numberAndPositions.filter { (numberRow, numberPositionRange) ->
                val isNumberWithinRowOfSymbol = numberRow in (symbolRow - 1)..(symbolRow + 1)
                val isNumberWithinColumnOfSymbol =
                    symbolPositionColumn in (numberPositionRange.first - 1)..(numberPositionRange.last + 1)

                isNumberWithinRowOfSymbol && isNumberWithinColumnOfSymbol
            }
        }.filter { (symbol, matchedNumber) ->
            matchedNumber.size == 2
        }.sumOf { (_, matchedNumber) ->
            matchedNumber[0].third.toInt() * matchedNumber[1].third.toInt()
        }
    }

    readInput("Day03_test1").also {
        check(part1(it) == 4361)
        check(part2(it) == 467835)
    }
    readInput("Day03").also {
        part2(it).println()
    }
}
