package pro.atabakov.pro.atabakov.subset

import kotlin.random.Random

val randomGenerator = Random(0)
fun randomSubset(nums: List<Int>, m: Int): List<Int> {
    if (m >= nums.size) {
        error("Subset can't be constructed")
    }

    val subset = ArrayList<Int>(m)
    val copyOfOriginalList = nums.toMutableList()

    repeat(m) {
        val randomIndex = randomGenerator.nextInt(copyOfOriginalList.size)
        subset.add(copyOfOriginalList.removeAt(randomIndex))
    }

    return subset
}

fun optimizedRandomSubset(nums: List<Int>, m: Int): List<Int> {
    if (m >= nums.size) {
        error("Subset can't be constructed")
    }

    val subset = Array(m) { i -> nums[i] }

    var i = m
    while (i < nums.size) {
        val randomIndex = randomGenerator.nextInt(i)
        if (randomIndex < m) {
            subset[randomIndex] = nums[i]
        }
        ++i
    }

    return subset.toList()
}

fun main() {
    println(randomSubset((0..<10).toList().shuffled(), 4))
    println(optimizedRandomSubset((0..<10).toList().shuffled(), 4))
}