package utils

object Utils {
	// region day 2
	fun calculateScore(input: String): Int {
		return when(input) {
			"A X" -> 4
			"B Y" -> 5
			"C Z" -> 6
			"A Y" -> 8
			"B Z" -> 9
			"C X" -> 7
			"A Z" -> 3
			"B X" -> 1
			else -> 2
		}
	}
	fun calculateOpponentAndScore(input: String): Int {
		return when(input) {
			"A X" -> 3
			"B Y" -> 5
			"C Z" -> 7
			"A Y" -> 4
			"B Z" -> 9
			"C X" -> 2
			"A Z" -> 8
			"B X" -> 1
			else -> 6
		}
	}

	// endregion

	// region Day3

	fun getPriority(value: Char): Int {
		return if(value.isUpperCase()) {
			value.code - 38
		} else {
			value.code - 96
		}
	}

	// endregion
}