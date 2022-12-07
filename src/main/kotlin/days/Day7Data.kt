package days

import utils.SolutionData
import java.util.Stack

fun main() = with(Day7Data()) {
	solvePart1()
	solvePart2()
}

sealed interface FileSystem {
	data class Dir(
		val name: String,
		val contents: MutableList<FileSystem> = mutableListOf()
	): FileSystem
	data class File(val name: String, val size: Int): FileSystem
}

class Day7Data: SolutionData(inputFile = "inputs/day7.txt") {
	private lateinit var output : FileSystem.Dir
	val dirList = mutableListOf<FileSystem.Dir>()
	fun processCommands() {
		output = FileSystem.Dir(name = rawData.first().split(" ").last())
		val current: Stack<FileSystem.Dir> = Stack<FileSystem.Dir>().also { it.push(output) }
		dirList.add(output)
		rawData.drop(1).map { it.split(" ") }.forEach { args ->
			when {
				args[0] == "$" && args[1] == "ls" -> {}
				args[0] == "$" && args[1] == "cd" && args[2] == ".." -> current.pop()
				args[0] == "$" && args[1] == "cd" -> {
					val target = current.peek().contents.find { (it as? FileSystem.Dir)?.name == args[2] }
					current.push(target as FileSystem.Dir)
					dirList.add(target)
				}
				args[0] == "dir" -> current.peek().contents.add(FileSystem.Dir(name = args[1]))
				else -> current.peek().contents.add(FileSystem.File(name = args[1], size = args[0].toInt()))

			}
		}
	}
	fun findTotalSize(of: FileSystem.Dir): Int = of.contents.sumOf {
		if (it is FileSystem.File) it.size else if (it is FileSystem.Dir) findTotalSize(it) else 0
	}
}

fun Day7Data.solvePart1() {
	processCommands()
	dirList.map { findTotalSize(it) }.filter { it < 100000 }.sum().also { println(it) }
}

fun Day7Data.solvePart2() {
	val needed = 70_000_000 - findTotalSize(dirList.first())
	val enough = 30_000_000 - needed
	println(dirList.map { findTotalSize(it) }.filter { it > enough }.minOf { it })
}