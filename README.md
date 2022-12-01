
# Advent Of Code 2022

Advent of Code solutions in Kotlin.

## Structure of solutions

For each day you can extend [SolutionData](src/main/kotlin/utils/SolutionData.kt) to get the raw data like below.

```kotlin
class Day1: SolutionData(inputFile = "inputs/day1.txt")
```

If you need data to be processed further, you can do so in the Solution class itself. 

See solution for [Day1](src/main/kotlin/days/Day1.kt) as reference.

Each solution is an extension function from the related Solution class. 
