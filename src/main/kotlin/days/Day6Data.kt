package days

import utils.SolutionData

fun main() {
	Day6Data().solvePart1()
	Day6Data().solvePart2()
}

class Day6Data: SolutionData(inputFile = "inputs/day6.txt") {
	val processed = rawData.first()
}

fun Day6Data.solvePart1() {
	processed
		.windowed(size = 4, step = 1)
		.indexOfFirst { it.toSet().size == 4 }
		.also { println(it + 4) }
}

fun Day6Data.solvePart2() {
	processed
		.windowed(size = 14, step = 1)
		.indexOfFirst { it.toSet().size == 14 }
		.also { println(it + 14) }
}

