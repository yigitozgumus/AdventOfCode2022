package days

import utils.SolutionData
import utils.Utils

class Day2Data: SolutionData(inputFile = "inputs/day2.txt") {
	val processedData = rawData.map { Utils.calculateScore(it) }
	val opponentData = rawData.map { Utils.calculateOpponentAndScore(it) }
}

fun Day2Data.solvePart1() {
	println(processedData.sum())
}

fun Day2Data.solvePart2() {
	println(opponentData.sum())
}

