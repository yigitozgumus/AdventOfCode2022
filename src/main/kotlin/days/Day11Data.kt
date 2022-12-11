package days

import utils.SolutionData
import utils.Utils.createPart1Monkey
import utils.Utils.createPart2Monkey

fun main() = with(Day11Data()) {
  println(" --- Part 1 --- ")
  solvePart1()
  println(" --- Part 2 --- ")
  solvePart2()
}

data class Item(var worry: Long, val part1: Boolean = true) {
  operator fun plus(value: Long) = this.apply {
	worry += value
	if (part1) worry /= 3
  }

  operator fun times(value: Long) = this.apply {
	worry *= value
	if (part1) worry /= 3
  }
}

data class Monkey(
  val index: Int,
  val items: MutableList<Item>,
  val inspect: (Item) -> Item,
  val test: (target: Item) -> Int,
  var inspectTimes: Long = 0L,
  val testNumber: Long
)

class Day11Data : SolutionData(inputFile = "inputs/day11.txt") {
  private val processedData = rawData.windowed(size = 6, step = 7)
  val part1MonkeyList = processedData.map { createPart1Monkey(it) }
  val part2MonkeyList = processedData.map { createPart2Monkey(it) }
}

fun Day11Data.solvePart1() {
  repeat(20) {
	part1MonkeyList.forEach { monkey ->
	  val sendList = mutableListOf<Pair<Item, Int>>()
	  monkey.items.forEach { item ->
		monkey.inspect(item)
		val monkeyToSend = monkey.test(item)
		sendList.add(item to monkeyToSend)
	  }
	  monkey.inspectTimes += monkey.items.size
	  sendList.forEach { (item, monkeyIndex) ->
		part1MonkeyList[monkeyIndex].items.add(item)
	  }
	  monkey.items.clear()
	}
  }
  part1MonkeyList
	.map { it.inspectTimes }.sortedDescending().take(2)
	.also { println(it.first().times(it.last())) }
}

fun Day11Data.solvePart2() {
  val totalModulo = part2MonkeyList.map { it.testNumber }.reduce { acc, l -> acc * l }
  repeat(10_000) {
	part2MonkeyList.forEach { monkey ->
	  val sendList = mutableListOf<Pair<Item, Int>>()
	  monkey.items.forEach { item ->
		monkey.inspect(item)
		val monkeyToSend = monkey.test(item)
		val itemWithUpdatedWorry = item.copy(worry = item.worry % totalModulo)
		sendList.add(itemWithUpdatedWorry to monkeyToSend)
	  }
	  monkey.inspectTimes += monkey.items.size
	  sendList.forEach { (item, monkeyIndex) ->
		part2MonkeyList[monkeyIndex].items.add(item)
	  }
	  monkey.items.clear()
	}
  }
  println(part2MonkeyList)
  part2MonkeyList
	.map { it.inspectTimes }
	.sortedDescending().take(2)
	.also { println(it.first().times(it.last())) }
}