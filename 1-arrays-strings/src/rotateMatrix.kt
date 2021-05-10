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

    assertEquals(true, matrixEqual(originalMatrix, originalMatrix))
    assertEquals(true, matrixEqual(rotateMatrix(originalMatrix), rotatedMatrix))
}


/***
 * Rotate matrix by 90 degrees
 *
 * Step by step layer by layer swap two elements with new positions
 *
 * Example:
 *   Given Matrix:
 *      0  1  1  1
 *      0  4  5  2
 *      0  4  5  2
 *      3  3  3  2
 *
 *   Rotation:
 *      (0, 0) -> (0, 3)
 *      (0, 3) -> (3, 3)
 *      (3, 3) -> (3, 0)
 *      (3, 0) -> (0, 0)
 *      --
 *      (0, 1) -> (1, 3)
 *      (1, 3) -> (3, 2)
 *      (3, 2) -> (2, 0)
 *      (2, 0) -> (0, 1)
 *      --
 *      (0, 2) -> (2, 3)
 *      (2, 3) -> (3, 1)
 *      (3, 1) -> (1, 0)
 *      (1, 0) -> (0, 2)
 *      --
 *      (1, 1) -> (1, 2)
 *      (1, 2) -> (2, 2)
 *      (2, 2) -> (2, 1)
 *      (2, 1) -> (1, 1)
 *
 * @param matrix - matrix of int
 * @return rotated matrix
 */
fun rotateMatrix(matrix: Array<Array<Int>>): Array<Array<Int>> {

    var layer = 0
    val n = matrix.size
    while (layer < n / 2) {
        val first = layer
        val last = n - 1 - layer

        var i = first
        while (i < last) {
            val offset = i - first
            val topLeft = matrix[first][i]

            matrix[first][i] = matrix[last-offset][first]
            matrix[last-offset][first] = matrix[last][last-offset]
            matrix[last][last-offset] = matrix[i][last]
            matrix[i][last] = topLeft

            ++i
        }

        ++layer
    }


    return matrix
}
