package days

import utils.SolutionData

fun main() = with(Day10Data()) {
  println(" --- Part 1 --- ")
  solvePart1()
  println(" --- Part 2 --- ")
  solvePart2()
}

sealed class Command(val name: String, val effect: Int = 0, val cycle: Int) {
  class NOOP(): Command("noop", 0, 1)
  class ADDX(effect: Int): Command("addx", effect, 2)
}

class Day10Data: SolutionData(inputFile = "inputs/day10.txt") {
  val snapshotList = mutableListOf<Pair<Int, Int>>()
  val commandList = rawData.map {
    if (it.startsWith("no")) Command.NOOP()
    else Command.ADDX(it.split(" ").last().toInt())
  }
  fun isDrawn(reg: Int, cycle: Int) = listOf(reg-1, reg, reg+1).contains(cycle % 40)
}

fun Day10Data.solvePart1() {
  var registerX = 1
  var totalCycle = 0
  val signalStrengthList = listOf(20, 60, 100, 140, 180, 220)
  commandList.forEach { command ->
    registerX += command.effect
    totalCycle += command.cycle
    snapshotList.add(totalCycle to registerX)
  }
  signalStrengthList.sumOf { test ->
    (snapshotList.findLast { it.first < test }?.second?.times(test) ?: 0)
  }.also { println(it) }
}

fun Day10Data.solvePart2() {
  var screen: String = ""
  val breakList = listOf(39, 79, 119, 159, 199, 239)
  (0 until 240).forEach { cycle ->
    val position = snapshotList.findLast { it.first <= cycle }?.second ?: 0
    screen += if (isDrawn(position, cycle)) "#" else "."
    if (breakList.contains(cycle)) screen += "\n"
  }
  println(screen)
}

