package utils

import days.*
import days.Day6Data

object Solutions {

  fun solveDay1() {
	Day1().run {
	  solvePart1()
	  solvePart2()
	}
  }

  fun solveDay2() = with(Day2Data()) {
	solvePart1()
	solvePart2()
  }

  fun solveDay3() = with(Day3Data()) {
	solvePart1()
	solvePart2()
  }

  fun solveDay4() = with(Day4Data()) {
	solvePart1()
	solvePart2()
  }

	fun solveDay5() {
		Day5Data().solvePart1()
		Day5Data().solvePart2()
	}

	fun solveDay6() {
		Day6Data().solvePart1()
		Day6Data().solvePart2()
	}
}