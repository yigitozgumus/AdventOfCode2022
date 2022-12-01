package utils

import java.io.File

abstract class SolutionData(private val inputFile: String) {

	private val rawData = File(inputFile).readLines()
	open val processedData = rawData

}