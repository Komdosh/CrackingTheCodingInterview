package pro.atabakov.pro.atabakov.circus

fun max(
    seq1: ArrayList<HtWt>?,
    seq2: ArrayList<HtWt>?
): ArrayList<HtWt> {
    return when {
        seq1 == null -> seq2 ?: ArrayList()
        seq2 == null -> seq1
        seq1.size > seq2.size -> seq1
        else -> seq2
    }
}

fun longestIncreasingSeqDP(array: ArrayList<HtWt>): ArrayList<HtWt> {
    array.sort()

    val solutions = ArrayList<ArrayList<HtWt>>()
    var bestSequence: ArrayList<HtWt>? = null

    // Поиск самой длинной подпоследовательности,
    // завершающейся на каждом элементе
    for (i in array.indices) {
        val longestAtIndex = bestSeqAtIndex(array, solutions, i)
        solutions.add(i, longestAtIndex)
        bestSequence = max(bestSequence, longestAtIndex)
    }

    return bestSequence ?: ArrayList()
}


fun bestSeqAtIndex(
    array: ArrayList<HtWt>,
    solutions: ArrayList<ArrayList<HtWt>>,
    index: Int
): ArrayList<HtWt> {

    val value = array[index]
    var bestSequence = ArrayList<HtWt>()

    // Поиск самой длинной подпоследовательности,
    // к которой можно присоединить текущий элемент
    for (i in 0 until index) {
        val solution = solutions[i]
        if (canAppend(solution, value)) {
            bestSequence = max(solution, bestSequence)
        }
    }

    // Присоединение элемента
    val best = ArrayList(bestSequence)
    best.add(value)

    return best
}