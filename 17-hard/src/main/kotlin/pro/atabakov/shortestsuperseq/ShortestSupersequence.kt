package pro.atabakov.pro.atabakov.shortestsuperseq

import java.util.*
import kotlin.math.max

data class Range(val start: Int, val end: Int)
data class HeapNode(var location: Int, val listId: Int)

fun shortestSupersequence(array: IntArray, elements: IntArray): Range? {
    val locations = getLocationsForElements(array, elements) ?: return null
    return getShortestClosure(locations)
}

/* Get list of queues containing indices for each element in small array */
fun getLocationsForElements(big: IntArray, small: IntArray): ArrayList<Queue<Int>>? {
    val allLocations = ArrayList<Queue<Int>>()

    for (e in small) {
        val locations = getLocations(big, e)
        if (locations.isEmpty()) return null
        allLocations.add(locations)
    }
    return allLocations
}

/* Get all indices where small appears in big */
fun getLocations(big: IntArray, small: Int): Queue<Int> {
    val locations: Queue<Int> = LinkedList()
    for (i in big.indices) {
        if (big[i] == small) {
            locations.add(i)
        }
    }
    return locations
}

fun getShortestClosure(queues: ArrayList<Queue<Int>>): Range {
    val minHeap = PriorityQueue(compareBy(HeapNode::location))
    var currentMax = Int.MIN_VALUE

    // Insert first element from each list
    for (i in queues.indices) {
        val head = queues[i].remove()
        minHeap.add(HeapNode(head, i))
        currentMax = max(currentMax, head)
    }

    var bestMin = minHeap.peek().location
    var bestMax = currentMax

    while (true) {
        val node = minHeap.poll()
        val min = node.location

        // Update best range
        if (currentMax - min < bestMax - bestMin) {
            bestMin = min
            bestMax = currentMax
        }

        val list = queues[node.listId]
        if (list.isEmpty()) break

        // Push next element from same list
        node.location = list.remove()
        minHeap.add(node)
        currentMax = max(currentMax, node.location)
    }

    return Range(bestMin, bestMax)
}

fun main() {
    val small = intArrayOf(1, 5, 9)
    val big = intArrayOf(7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7)

    val result = shortestSupersequence(big, small)
    println(result) // Range(start=7, end=10)
}

