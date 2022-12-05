package days

import utils.SolutionData

class Day5Data: SolutionData(inputFile = "inputs/day5.txt") {
	private val separation = rawData.indexOf("")
	val data = rawData.subList(0, separation-1)
	val numOfStacks = rawData[separation-1].split(" ").last().toInt()
	val moves = rawData.subList(separation+1, rawData.size)

}

fun Day5Data.solvePart1() {
	println(data)
	println(numOfStacks)
	println(moves)
	println(data.first().length)
}

fun Day5Data.solvePart2() {

}
