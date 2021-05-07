import kotlin.test.assertEquals

/***
 * Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate the
 * image by 90 degrees. Can you do this in place?
 */
fun main() {
    val originalMatrix = arrayOf(
        arrayOf(0, 1, 1, 1),
        arrayOf(0, 4, 5, 2),
        arrayOf(0, 4, 5, 2),
        arrayOf(3, 3, 3, 2),
    )

    val rotatedMatrix = arrayOf(
        arrayOf(3, 0, 0, 0),
        arrayOf(3, 4, 4, 1),
        arrayOf(3, 5, 5, 1),
        arrayOf(2, 2, 2, 1),
    )

    printMatrix(originalMatrix)
    println()
    printMatrix(rotatedMatrix)

    assertEquals(true, matrixEqual(originalMatrix, originalMatrix))
}

/***
 * Compare elements of two matrices
 *
 * @param first - first matrix
 * @param second - second matrix
 * @return true if elements and it's positions of two matrix are equals, false - otherwise
 */
fun matrixEqual(first: Array<Array<Int>>, second: Array<Array<Int>>): Boolean {
    val firstCollections = first.asSequence().flatMap { it.asSequence() }.toCollection(arrayListOf())
    val secondCollections = second.asSequence().flatMap { it.asSequence() }.toCollection(arrayListOf())
    return firstCollections == secondCollections
}

/***
 * Print matrix stdin
 *
 * @param matrix - matrix
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

fun naiveRotateMatrix(matrix: Array<Array<Int>>): Array<Array<Int>> {

    return matrix

}
