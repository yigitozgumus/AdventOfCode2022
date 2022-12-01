package days

import utils.SolutionData

fun solveDay1() {
	Day1().run {
		solvePart1()
		solvePart2()
	}
}

class Day1: SolutionData(inputFile = "inputs/day1.txt") {
	val processedData = rawData.flatMapIndexed { index: Int, s: String ->
		when {
			index == 0 || index == rawData.lastIndex -> listOf(index)
			s.isEmpty() -> listOf(index - 1, index + 1)
			else -> emptyList()
		}
	}.windowed(size = 2, step = 2) { (from, to) -> rawData.slice(from..to) }
}


fun Day1.solvePart1() {
	val listOfSums = processedData.map { it.sumOf { item -> item.toInt() } }
	println(listOfSums.max())
}

fun Day1.solvePart2() {
	val listOfSums = processedData.map { it.sumOf { item -> item.toInt() } }
	println(listOfSums.sortedDescending().take(3).sum())
}