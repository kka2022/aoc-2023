import kotlin.math.max

// First part answer
// 55816

fun main() {
    fun part1(input: List<String>): Int {
        val availableBalls =
            mapOf("red" to 12, "green" to 13, "blue" to 14)

        val gamesToAdd = mutableMapOf<Int, Boolean>()
        input.forEach { gameString ->
            val gameNumberAndGames = gameString.split(": ")
            val gameNumber = gameNumberAndGames[0].split(" ")[1].toInt()
            gamesToAdd[gameNumber] = true
            val games = gameNumberAndGames[1].split("; ")
            games.forEach { game ->
                val numberAndColor = game.split(", ")
                numberAndColor.forEach {
                    val color = it.split(" ")[1]
                    val number = it.split(" ")[0]
                    if (availableBalls[color]!! < number.toInt()) {
                        gamesToAdd[gameNumber] = false
                    }
                }
            }
        }

        return gamesToAdd.keys.filter { gamesToAdd[it] == true }.sumOf { it }
    }

    fun part2(input: List<String>): Int? {
        val availableBalls =
            mapOf("red" to 12, "green" to 13, "blue" to 14)
        val powerOfGames = mutableMapOf<Int, Int?>()

        var gameCount = 0
        var tryCount = 0
        input.forEach { gameString ->
            gameCount++
            val gameNumberAndGame = gameString.split(": ")
            val gameNumber = gameNumberAndGame[0].split(" ")[1].toInt()
            val tries = gameNumberAndGame[1].split("; ")
//            println(gameCount)

            val colorAndValues = mutableMapOf(
                "red" to mutableListOf<Int>(),
                "green" to mutableListOf(),
                "blue" to mutableListOf()
            )

            tries.forEach { trial ->
                tryCount++
//                println("$tryCount $trial")
                val numberAndColor = trial.split(", ")

                numberAndColor.forEach {
                    val number = it.split(" ")[0].toInt()
                    val color = it.split(" ")[1]

                    colorAndValues[color]?.add(number)

//                    println("----------- $number $color --------")
//                    println("++++++++ $colorAndMax ++++++++")
                }
            }

            val redPower = colorAndValues["red"]?.max()
            val greenPower = colorAndValues["green"]?.max()
            val bluePower = colorAndValues["blue"]?.max()

            val power = greenPower?.let {
                if (bluePower != null) {
                    redPower?.times(it)?.times(bluePower)
                } else {
                    null
                }
            }
            powerOfGames[gameNumber] = power
            tryCount = 0
        }
        println(powerOfGames)

        return powerOfGames.values.reduce { acc, i ->
            if (i != null) {
                acc?.plus(i)
            } else {
                acc
            }
        }
    }

    val input =
//        readInput("Day02_test")
        readInput("Day02")

//    part1(input).println()
    part2(input).println()
}
