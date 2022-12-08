package days

import utils.SolutionData

fun main() = with(Day8Data()) {
	solvePart1()
	solvePart2()
}

class Day8Data: SolutionData(inputFile = "inputs/day8.txt") {
	val grid = rawData.map { line -> line.map { it.toString().toInt() } }
	val borders = rawData.first().length * 2 + (rawData.size - 2) * 2
}

fun Day8Data.solvePart1() {
	val lastTreeIndex = grid.size - 2
	var visibleTrees: Int = 0
	(1..lastTreeIndex).forEach { row ->
		(1..lastTreeIndex).forEach { column ->
			val tree = grid[row][column]
			val treeRow = grid[row]
			val treeCol = grid[row].indices.map { grid[it][column] }
			if (
				treeRow.subList(0,row).max() < tree ||
				treeRow.subList(row+1, grid.size).max() < tree ||
				treeCol.subList(0, column).max() < tree ||
				treeCol.subList(column+1, grid.size).max() < tree
			) { visibleTrees+= 1 }
		}
	}
	println(visibleTrees)
}

fun Day8Data.solvePart2() {

}