package days

import utils.SolutionData

fun main() = with(Day9Data()) {
    solvePart1()
    solvePart2()
}

data class Vector(val x: Int = 0, val y: Int = 0) {
    operator fun plus(v: Vector): Vector = Vector(this.x + v.x, this.y + v.y)
    operator fun minus(v: Vector): Vector = Vector(this.x - v.x, this.y - v.y)
}

class Day9Data : SolutionData(inputFile = "inputs/day9.txt") {
    val inputList = rawData
        .map { it.split(" ") }
        .map { it.first().toString() to it.last().toString().toInt() }

    fun getDirection(input: String): Vector = when (input) {
        "D" -> Vector(x = 0, y = -1)
        "U" -> Vector(x = 0, y = 1)
        "R" -> Vector(x = 1, y = 0)
        else -> Vector(x = -1, y = 0)

    }

    fun tailMovement(input: Vector): Vector = when {
        input.x == 2 -> Vector(x = 1, y = input.y)
        input.x == -2 -> Vector(x = -1, y = input.y)
        input.y == 2 -> Vector(x = input.x, y = 1)
        input.y == -2 -> Vector(x = input.x, y = -1)
        else -> Vector()
    }

    fun ropeMovement(input: Vector): Vector = when {
        input.x == 2 && input.y == 2 -> Vector(x = 1, y = 1)
        input.x == -2 && input.y == 2 -> Vector(x = -1, y = 1)
        input.x == 2 && input.y == -2 -> Vector(x = 1, y = -1)
        input.x == -2 && input.y == -2 -> Vector(x = -1, y = -1)
        else -> tailMovement(input)
    }
}

fun Day9Data.solvePart1() {
    var head = Vector()
    var tail = Vector()
    val seen = mutableListOf<Vector>()
    inputList.forEach { pair ->
        repeat(pair.second) {
            head += getDirection(pair.first)
            val tailMove = tailMovement(head - tail)
            tail += tailMove
            seen.add(tail)
        }
    }
    println(seen.toSet().count())
}

fun Day9Data.solvePart2() {
    var head = Vector()
    var tail = MutableList(9){ Vector() }
    val seen = mutableListOf<Vector>()
    inputList.forEach { pair ->
        repeat(pair.second) {
            head += getDirection(pair.first)
            var tempHead = head
            (0 until 9).forEach {
                val newTailMove = ropeMovement(tempHead - tail[it])
                tail[it] += newTailMove
                tempHead = tail[it]
            }
            seen.add(tail.last())
        }
    }
    println(seen.toSet().count())
}
