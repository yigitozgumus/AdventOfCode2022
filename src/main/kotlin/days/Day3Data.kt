package days

import utils.SolutionData
import utils.Utils.getPriority

fun main() = with(Day3Data()) {
	solvePart1()
	solvePart2()
}

class Day3Data : SolutionData(inputFile = "inputs/day3.txt") {
	val part1Data = rawData.map { it.toList() }.map { it.chunked(it.size / 2) }
	val part2Data = rawData.map { it.toList() }.chunked(3)
}

fun Day3Data.solvePart1() {
	val priorityList = part1Data.flatMap { lists ->
		lists.first()
			.mapNotNull { target -> lists.last().firstOrNull { target == it } }
			.distinct()
	}
	println(priorityList.sumOf { getPriority(it) })
}

fun Day3Data.solvePart2() {
	val badgeList = part2Data.flatMap {
		it.first().toSet()
			.intersect(it[1].toSet())
			.intersect(it.last().toSet())
	}
	println(badgeList.sumOf { getPriority(it) })
}

