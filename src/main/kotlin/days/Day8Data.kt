package days

import utils.SolutionData

fun main() = with(Day8Data()) {
  solvePart1()
  solvePart2()
}

class Day8Data : SolutionData(inputFile = "inputs/day8.txt") {
  val grid = rawData.map { line -> line.map { it.toString().toInt() } }
  val borders = rawData.first().length * 2 + (rawData.size - 2) * 2

  fun processGrid(from: Int, to: Int, action: (row: Int, col: Int) -> Unit) {
	(from..to).forEach { row ->
	  (from..to).forEach { column ->
		action(row, column)
	  }
	}
  }

  fun getScenicScore(target: Int, path: List<Int>): Int {
	var score = 0
	run lit@{
	  path.forEach {
		score += 1
		if (it >= target) return@lit
	  }
	}
	return score.coerceAtLeast(1)
  }
}

fun Day8Data.solvePart1() {
  var visibleTrees = 0
  processGrid(from = 1, to = grid.size - 2) { row, column ->
	val tree = grid[row][column]
	val treeCol = grid[row].indices.map { grid[it][column] }
	if (
	  grid[row].subList(0, column).max() < tree ||
	  grid[row].subList(column + 1, grid.size).max() < tree ||
	  treeCol.subList(0, row).max() < tree ||
	  treeCol.subList(row + 1, grid.size).max() < tree
	) {
	  visibleTrees += 1
	}
  }
  println(visibleTrees + borders)
}

fun Day8Data.solvePart2() {
  val scenicScoreList = mutableListOf<Int>()
  processGrid(from = 1, to = grid.size - 2) { row, column ->
	val tree = grid[row][column]
	val treeCol = grid[row].indices.map { grid[it][column] }
	scenicScoreList.add(
	  getScenicScore(tree, grid[row].subList(0, column).reversed()) *
		getScenicScore(tree, grid[row].subList(column + 1, grid.size)) *
		getScenicScore(tree, treeCol.subList(0, row).reversed()) *
		getScenicScore(tree, treeCol.subList(row + 1, grid.size))
	)
  }
  println(scenicScoreList.maxOrNull())
}