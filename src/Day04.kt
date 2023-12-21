fun main() {
    fun part1(input: List<String>): Int {
        val cardAndPoints = mutableMapOf<Int, Int>()

        input.forEach { card ->
            val cardNumber = card.split(":")[0].split(Regex("\\s+"))[1].toInt()
            val winningNumbersSet = card.split(":")[1].split(" | ")[0].trim().split(Regex("\\s+")).toSet()
            val numbersYouHave = card.split(":")[1].split(" | ")[1].trim().split(Regex("\\s+"))
//            println("$cardNumber $winningNumbersSet")
//            println(numbersYouHave)

            var points = 0
            var ifAnyPointWon = false
            numbersYouHave.forEach {
                if (winningNumbersSet.contains(it)) {
                    if (!ifAnyPointWon) {
                        ifAnyPointWon = true
                        points = 1
                    } else {
                        points *= 2
                    }
                }
            }

            cardAndPoints[cardNumber] = points
            points = 0
            ifAnyPointWon = false
        }

        return cardAndPoints.values.sumOf { it }
    }

    fun part2(input: List<String>): Int {


        return 0
    }


    val input =
        readInput("Day04")
//        readInput("Day04_test")

    part1(input).println()
//    part2(input).println()
}
