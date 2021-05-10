/***
 * Compare elements of two matrices.
 *
 * @param first - first matrix of int
 * @param second - second matrix of int
 * @return true if elements and it's positions of two matrix are equals, false - otherwise
 */
fun matrixEqual(first: Array<Array<Int>>, second: Array<Array<Int>>): Boolean {
    val firstCollections = first.asSequence().flatMap { it.asSequence() }.toCollection(arrayListOf())
    val secondCollections = second.asSequence().flatMap { it.asSequence() }.toCollection(arrayListOf())
    return firstCollections == secondCollections
}

/***
 * Print matrix in stdout.
 *
 * @param matrix - matrix of int
 */
fun printMatrix(matrix: Array<Array<Int>>) {
    matrix.forEach {
        it.forEach { i ->
            print(i)
            print("  ")
        }
        println()
    }
}
