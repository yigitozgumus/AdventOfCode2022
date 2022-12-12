package days

import utils.SolutionData
import utils.Utils.getNeighbors

fun main() = with(Day12Data()) {
  println(" --- Part 1 --- ")
  solvePart1()
  println(" --- Part 2 --- ")
  solvePart2()
}

data class Point(val x: Int, val y: Int, val value: Char) {
  fun getFilteredValue(): Char = when(value) {
      'S' -> 'a'
      'E' -> 'z'
      else -> value
  }
}

class Day12Data: SolutionData(inputFile = "inputs/day12.txt") {
  val grid = rawData.map { it.trim().toList() }
    .mapIndexed { row, ints -> ints.mapIndexed { columns, i -> Point(row, columns, i) } }
  fun getElement(x: Int, y: Int): Point = grid[x][y]

  fun findShortestBetween(start:Point, end: Point): Int {
    val visited: MutableMap<Point, Int> = mutableMapOf<Point, Int>().also { it[start] = 0 }
    val queue = mutableListOf<Point>().also { it.add(start) }
    while (queue.isNotEmpty()) {
      val current = queue.removeFirst()
      val step = visited[current]!!
      getNeighbors(current).forEach {
        if (it == end) return step.plus(1)
        if (!visited.keys.contains(it)) {
          queue.add(it)
          visited[it] = step.plus(1)
        }
      }
    }
    return Int.MAX_VALUE
  }
}

fun Day12Data.solvePart1() {
  val start = grid.flatten().sortedByDescending { it.value.code }.takeLast(2).first()
  val end = grid.flatten().sortedByDescending { it.value.code }.takeLast(2).last()
  println(findShortestBetween(start, end))
}

fun Day12Data.solvePart2() {
  val startList = grid.flatten().filter { it.value == 'a' }
  val end = grid.flatten().sortedByDescending { it.value.code }.takeLast(2).last()
  println(startList.minOfOrNull { findShortestBetween(it, end) })
}
