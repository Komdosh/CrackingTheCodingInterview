package pro.atabakov.pro.atabakov.smallestk

import java.util.PriorityQueue
import kotlin.random.Random


fun smallestKWithHeap(original: Array<Int>, k: Int): Array<Int> {
    if (k == 0) return emptyArray()
    if (k >= original.size) return original

    val pq = PriorityQueue<Int> { o1, o2 -> o2 - o1 }

    original.take(k).forEach(pq::add)

    original.drop(k).forEach { value ->
        if (value < pq.peek()) {
            pq.poll()
            pq.add(value)
        }
    }

    return Array(k) { pq.poll() }
}

fun smallestK(original: Array<Int>, k: Int): Array<Int> {
    if (k == 0) return emptyArray()
    if (k >= original.size) return original

    val result = Array(k) { i -> original[i] }

    var currentMax = result.max()

    for (originalIndex in k..original.lastIndex) {
        val num = original[originalIndex]

        if (num < currentMax) {
            for (i in result.indices) {
                if (num < result[i]) {
                    result[i] = num
                    currentMax = result.max()
                    break
                }
            }
        }
    }

    return result
}


fun main() {
    val array = arrayOf(5, 2, 4, 9, 1, 5, 8)

    println(smallestKWithHeap(array, 3).joinToString())
    println(smallestK(array, 3).joinToString())

    println(SmallestKWithRank().smallestK(array, 3).joinToString())
}

class SmallestKWithRank {
    fun smallestK(original: Array<Int>, k: Int): Array<Int> {
        if (k == 0) return emptyArray()
        if (k >= original.size) return original

        val threshold = rank(original, k - 1, 0, original.lastIndex)

        // Copy elements smaller than the threshold
        val smallest = Array(k) { 0 }
        var count = 0
        for (a in original) {
            if (a < threshold) {
                smallest[count++] = a
            }
        }

        while (count < k) {
            smallest[count++] = threshold
        }

        return smallest
    }

    fun rank(array: Array<Int>, k: Int, start: Int, end: Int): Int {
        val pivot = array[Random.nextInt(start, end + 1)]
        val (leftSize, middleSize) = partition(array, start, end, pivot)

        return when {
            k < leftSize -> rank(array, k, start, start + leftSize - 1)
            k < leftSize + middleSize -> pivot
            else -> rank(array, k - leftSize - middleSize, start + leftSize + middleSize, end)
        }
    }

    fun partition(array: Array<Int>, start: Int, end: Int, pivot: Int): Pair<Int, Int> {
        var left = start
        var right = end
        var middle = start

        while (middle <= right) {
            val middleValue = array[middle]
            when {
                middleValue < pivot -> {
                    swap(array, middle, left)
                    middle++
                    left++
                }

                middleValue > pivot -> {
                    swap(array, middle, right)
                    right--
                }

                else -> middle++
            }
        }

        return (left - start) to (right - left + 1)
    }

    fun swap(array: Array<Int>, i: Int, j: Int) {
        val temp = array[i]
        array[i] = array[j]
        array[j] = temp
    }
}