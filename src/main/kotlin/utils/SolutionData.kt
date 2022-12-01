package utils

import java.io.File

abstract class SolutionData(private val inputFile: String) {
	val rawData = File(inputFile).readLines()
}