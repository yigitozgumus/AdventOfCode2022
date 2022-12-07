package days

import utils.SolutionData

fun main() = with(Day7Data()) {
	solvePart1()
	solvePart2()
}

sealed interface FileSystem {
	data class Dir(val name: String, val contents: MutableList<FileSystem> = mutableListOf()): FileSystem
	data class File(val name: String, val size: Int): FileSystem
}

class Day7Data: SolutionData(inputFile = "inputs/day7.txt") {
	private lateinit var output : FileSystem.Dir
	fun processCommands() {
		output = FileSystem.Dir(name = rawData.first().split(" ").last())
		val current = output
		rawData.drop(1).map { it.split(" ") }.forEach {
				
		}
	}
}

fun Day7Data.solvePart1() {
	processCommands()
}

fun Day7Data.solvePart2() {

}