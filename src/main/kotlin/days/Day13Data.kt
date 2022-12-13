package days

import utils.SolutionData
import utils.Utils

fun main() = with(Day13Data()) {
  println(" --- Part 1 --- ")
  solvePart1()
  println(" --- Part 2 --- ")
  solvePart2()
}

class Day13Data: SolutionData(inputFile = "inputs/day13.txt") {
  val pairs = rawData
    .windowed(size = 2, step=3)
    .map { (left, right) -> Utils.List.from(left) to Utils.List.from(right) }
}

fun Day13Data.solvePart1() {
  pairs.mapIndexed { i, (left, right) -> if (left compareTo right < 0) i + 1 else 0 }.sum().also { println(it) }
}

fun Day13Data.solvePart2() {
  val first = Utils.List.from("[[2]]")
  val second = Utils.List.from("[[6]]")
  val sorted = (pairs.flatMap { it.toList() } + listOf(first, second)).sortedWith(Utils.List::compareTo)
  ((sorted.indexOf(first) + 1) * (sorted.indexOf(second) + 1)).also { println(it) }
}
