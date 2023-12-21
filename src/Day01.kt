// First part answer
// 55816

fun main() {
    fun part1(input: List<String>): Int {
        val digitPattern = Regex("\\d")

        return input
            .map { (digitPattern.find(it)?.value + digitPattern.find(it.reversed())?.value).toInt() }
            .sumOf { it }
    }

    fun part2(input: List<String>): Int {
        val spellingToDigit = mapOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9",
        )

        val firstDigitPattern = Regex("\\d|one|two|three|four|five|six|seven|eight|nine")
        val secondDigitPattern = Regex("\\d|eno|owt|eerht|ruof|evif|xis|neves|thgie|enin")
        val result = input
            .map {
                var firstDigit = firstDigitPattern.find(it)?.value
                firstDigit = spellingToDigit[firstDigit] ?: firstDigit

                var secondDigit = secondDigitPattern.find(it.reversed())?.value
                secondDigit = spellingToDigit[secondDigit?.reversed()] ?: secondDigit

                (firstDigit + secondDigit).toInt()
            }
            .sumOf { it }

        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val input =
//        readInput("Day01")
        readInput("Day01")

//    part1(input).println()
    part2(input).println()
}
