package days

import utils.SolutionData
import java.util.*

data class Operation(val times: Int, val from: Int, val to: Int)

class Day5Data : SolutionData(inputFile = "inputs/day5.txt") {
	private val separation = rawData.indexOf("")
	private val data = rawData.subList(0, separation - 1)
	private val numOfStacks = rawData[separation - 1].trim().split(" ").last().toInt()
	val moves = rawData.subList(separation + 1, rawData.size)
	val stackList = buildList<Stack<String>> { repeat(numOfStacks) { add(Stack()) } }.also { stackList ->
		data.reversed().forEach {
			it.chunked(4).forEachIndexed { index, line ->
				line.trim(' ', '[', ']').takeIf { it.isNotEmpty() }?.let { stackList[index].push(it) }
			}
		}
	}

	fun mapOperations(command: String): Operation = with(command.split(" ")) {
		return Operation(this[1].toInt(), this[3].toInt() - 1, this[5].toInt() - 1)
	}

	fun performOperationPart1(operation: Operation) = repeat(operation.times) {
		val element = stackList[operation.from].pop()
		stackList[operation.to].push(element)
	}
	fun performOperationPart2(operation: Operation) {
		val tempStack = Stack<String>()
		repeat(operation.times) { tempStack.push(stackList[operation.from].pop()) }
		repeat(operation.times) { stackList[operation.to].push(tempStack.pop())}
	}
}

fun Day5Data.solvePart1() {
	moves.map { mapOperations(it) }.forEach { performOperationPart1(it) }
	println(stackList.joinToString("") { it.pop() })
}

fun Day5Data.solvePart2() {
	moves.map { mapOperations(it) }.forEach { performOperationPart2(it) }
	println(stackList.joinToString("") { it.pop() })
}




