package days

import utils.SolutionData


class Day4Data : SolutionData(inputFile = "inputs/day4.txt") {
  val part1Data = rawData.map { it.split(",").map { it.split("-").map { it.toInt() } } }

	fun doesListsContainEachOther(first: List<Int>, second: List<Int>): Boolean {
		return first.first() <= second.first() && first.last() >= second.last() ||
			second.first() <= first.first() && second.last() >= first.last()
	}
	fun doesListBoundariesOverlap(first: List<Int>, second: List<Int>): Boolean {
		return  doesListsContainEachOther(first, second) ||
			(first.last() >= second.first() && first.first() <= second.first() && second.last() >= first.last() ||
			first.first() >= second.first() && first.last() >= second.last() && second.last() >= first.first())
	}
}

fun Day4Data.solvePart1() {
  part1Data.count { doesListsContainEachOther(it.first(), it.last()) }.also { println(it) }
}

fun Day4Data.solvePart2() {
	part1Data.count { doesListBoundariesOverlap(it.first(), it.last()) }.also { println(it) }
}

