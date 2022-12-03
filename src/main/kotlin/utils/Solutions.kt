package utils

import days.*

object Solutions {

	fun solveDay1() {
		Day1().run {
			solvePart1()
			solvePart2()
		}
	}

	fun solveDay2() = with(Day2Data()) {
		solvePart1()
		solvePart2()
	}

	fun solveDay3() = with(Day3Data()) {
		solvePart1()
		solvePart2()
	}
}