package utils

import days.Day12Data
import days.Item
import days.Monkey
import days.Point

object Utils {
  // region day 2
  fun calculateScore(input: String): Int {
	return when (input) {
	  "A X" -> 4
	  "B Y" -> 5
	  "C Z" -> 6
	  "A Y" -> 8
	  "B Z" -> 9
	  "C X" -> 7
	  "A Z" -> 3
	  "B X" -> 1
	  else -> 2
	}
  }

  fun calculateOpponentAndScore(input: String): Int {
	return when (input) {
	  "A X" -> 3
	  "B Y" -> 5
	  "C Z" -> 7
	  "A Y" -> 4
	  "B Z" -> 9
	  "C X" -> 2
	  "A Z" -> 8
	  "B X" -> 1
	  else -> 6
	}
  }

  // endregion

  // region Day3

  fun getPriority(value: Char): Int {
	return if (value.isUpperCase()) {
	  value.code - 38
	} else {
	  value.code - 96
	}
  }

  // endregion

  // region Day 11

  fun createPart1Monkey(list: List<String>): Monkey {
	val index = list.first().trimEnd(':').split(" ").last().toInt()
	val items = list[1].split(":").last().split(',').map { it.trim() }.map { Item(it.toLong()) }.toMutableList()
	val operationString = list[2].split('=').last()
	val opNumber = operationString.split(' ').last()
	val isSelf = opNumber.contains("old")
	val operation = when {
	  operationString.contains("*") && isSelf -> {
		{ item: Item -> item * item.worry }
	  }

	  operationString.contains("*") -> {
		{ item: Item -> item * opNumber.toLong() }
	  }

	  operationString.contains("+") && isSelf -> {
		{ item: Item -> item + item.worry }
	  }

	  operationString.contains("+") -> {
		{ item: Item -> item + opNumber.toLong() }
	  }

	  else -> { item: Item -> item }
	}
	val testNumber = list[3].split(' ').last().toInt()
	val trueMonkeyIndex = list[4].split(' ').last().toInt()
	val falseMonkeyIndex = list[5].split(' ').last().toInt()
	val test = { item: Item -> if (item.worry % testNumber == 0L) trueMonkeyIndex else falseMonkeyIndex }
	return Monkey(
	  index = index,
	  items = items,
	  inspect = operation,
	  test = test,
	  testNumber = testNumber.toLong()
	)
  }

  fun createPart2Monkey(list: List<String>): Monkey {
	val index = list.first().trimEnd(':').split(" ").last().toInt()
	val items =
	  list[1].split(":").last().split(',').map { it.trim() }.map { Item(it.toLong(), part1 = false) }.toMutableList()
	val operationString = list[2].split('=').last()
	val opNumber = operationString.split(' ').last()
	val isSelf = opNumber.contains("old")
	val operation = when {
	  operationString.contains("*") && isSelf -> {
		{ item: Item -> item * item.worry }
	  }

	  operationString.contains("*") -> {
		{ item: Item -> item * opNumber.toLong() }
	  }

	  operationString.contains("+") && isSelf -> {
		{ item: Item -> item + item.worry }
	  }

	  operationString.contains("+") -> {
		{ item: Item -> item + opNumber.toLong() }
	  }

	  else -> { item: Item -> item }
	}
	val testNumber = list[3].split(' ').last().toInt()
	val trueMonkeyIndex = list[4].split(' ').last().toInt()
	val falseMonkeyIndex = list[5].split(' ').last().toInt()
	val test = { item: Item -> if (item.worry % testNumber == 0L) trueMonkeyIndex else falseMonkeyIndex }
	return Monkey(
	  index = index,
	  items = items,
	  inspect = operation,
	  test = test,
	  testNumber = testNumber.toLong()
	)
  }

  // endregion

  // region Day 12


  fun Day12Data.getNeighbors(curr: Point): List<Point> = buildList {
	if (curr.x > 0) {
	  val element = getElement(curr.x -1,curr.y)
	  if (element.getFilteredValue() - curr.getFilteredValue() <= 1) add(element)
	}
	if (curr.x < grid.size - 1) {
	  val element = getElement(curr.x + 1,curr.y)
	  if (element.getFilteredValue() - curr.getFilteredValue() <= 1) add(element)
	}
	if (curr.y > 0) {
	  val element = getElement(curr.x, curr.y - 1)
	  if (element.getFilteredValue() - curr.getFilteredValue() <= 1) add(element)
	}
	if (curr.y < grid.first().size - 1) {
	  val element = getElement(curr.x,curr.y + 1)
	  if (element.getFilteredValue() - curr.getFilteredValue() <= 1) add(element)
	}
  }


  // endregion
}