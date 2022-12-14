package days

import utils.SolutionData

fun main() {
    println(" --- Part 1 --- ")
    Day14Data().solvePart1()
    println(" --- Part 2 --- ")
    Day14Data().solvePart2()
}

class Day14Data : SolutionData(inputFile = "inputs/day14.txt") {
    val pointLists = rawData.map {
        it.split("->").map { it.trim().split(',').map { it.trim().toInt() } }.map { it.first() to it.last() }
    }
    val linePoints = pointLists.flatMap { pointList ->
        pointList.windowed(2, 1).flatMap {
            val xMin = minOf(it.first().first, it.last().first)
            val xMax = maxOf(it.first().first, it.last().first)
            val yMin = minOf(it.first().second, it.last().second)
            val yMax = maxOf(it.first().second, it.last().second)
            (xMin..xMax).flatMap { lhs -> (yMin..yMax).map { rhs -> lhs to rhs } }.toSet()
        }
    }.toMutableSet()
}

fun Day14Data.solvePart1() {
    var currentSand = 500 to 0
    val lowestRock = linePoints.maxBy { it.second }
    var rockCount = 0
    while (true) {
        val down = currentSand.first to currentSand.second + 1
        val left = currentSand.first - 1 to currentSand.second + 1
        val right = currentSand.first + 1 to currentSand.second + 1

        if (currentSand.second > lowestRock.second) break

        if (!linePoints.contains(down)) {
            currentSand = down
        } else if (!linePoints.contains(left)) {
            currentSand = left
        } else if (!linePoints.contains(right)) {
            currentSand = right
        } else {
            rockCount += 1
            linePoints.add(currentSand)
            currentSand = 500 to 0
        }
    }
    println(rockCount)

}

fun Day14Data.solvePart2() {

    var currentSand = 500 to 0
    val lowestHeight = linePoints.maxBy { it.second }.second + 2
    var rockCount = 0
    while (true) {
        if (linePoints.contains(500 to 0)) break
        val down = currentSand.first to currentSand.second + 1
        val left = currentSand.first - 1 to currentSand.second + 1
        val right = currentSand.first + 1 to currentSand.second + 1
        if (currentSand.second + 1 == lowestHeight) {
            rockCount += 1
            linePoints.add(currentSand)
            currentSand = 500 to 0
        }else if (!linePoints.contains(down)) {
             currentSand = down

        } else if (!linePoints.contains(left)) {
             currentSand = left
        } else if (!linePoints.contains(right)) {
             currentSand = right
        } else {
            rockCount += 1
            linePoints.add(currentSand)
            currentSand = 500 to 0
        }
    }
    println(rockCount)
}