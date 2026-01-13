package pro.atabakov.pro.atabakov.circus

data class HtWt(
    val height: Int,
    val weight: Int
) : Comparable<HtWt> {

    override fun compareTo(other: HtWt): Int {
        return if (height != other.height) {
            height.compareTo(other.height)
        } else {
            weight.compareTo(other.weight)
        }
    }

    fun isBefore(other: HtWt): Boolean {
        return height < other.height && weight < other.weight
    }

    override fun toString(): String {
        return "($height, $weight)"
    }
}

fun longestIncreasingSeqRecursion(items: MutableList<HtWt>): List<HtWt> {
    items.sort()
    return bestSeqAtIndex(items, ArrayList(), 0)
}

fun canAppend(
    solution: List<HtWt>,
    value: HtWt
): Boolean {
    if (solution.isEmpty()) return true

    val last = solution[solution.size - 1]
    return last.isBefore(value)
}

fun bestSeqAtIndex(
    array: MutableList<HtWt>,
    sequence: List<HtWt>,
    index: Int
): List<HtWt> {
    if (index >= array.size) return sequence

    val value = array[index]

    var bestWith: List<HtWt>? = null

    if (canAppend(sequence, value)) {
        val sequenceWith = ArrayList(sequence)
        sequenceWith.add(value)
        bestWith = bestSeqAtIndex(array, sequenceWith, index + 1)
    }

    val bestWithout = bestSeqAtIndex(array, sequence, index + 1)

    return if (bestWith == null || bestWithout.size > bestWith.size) {
        bestWithout
    } else {
        bestWith
    }
}