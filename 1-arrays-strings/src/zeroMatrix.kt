import kotlin.test.assertEquals

/***
 * Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.
 */
fun main() {
    val processedMatrix = arrayOf(
        arrayOf(0, 0, 0, 0),
        arrayOf(0, 1, 0, 1),
        arrayOf(0, 1, 0, 1),
        arrayOf(0, 0, 0, 0),
        arrayOf(0, 1, 0, 1),
    )

    assertEquals(true, matrixEqual(processedMatrix, naiveZeroMatrix(arrayOf(
        arrayOf(0, 1, 1, 1),
        arrayOf(1, 1, 1, 1),
        arrayOf(1, 1, 1, 1),
        arrayOf(0, 1, 0, 1),
        arrayOf(1, 1, 1, 1),
    ))))
    assertEquals(true, matrixEqual(processedMatrix, traditionalZeroMatrix(arrayOf(
        arrayOf(0, 1, 1, 1),
        arrayOf(1, 1, 1, 1),
        arrayOf(1, 1, 1, 1),
        arrayOf(0, 1, 0, 1),
        arrayOf(1, 1, 1, 1),
    ))))
}

fun naiveZeroMatrix(matrix: Array<Array<Int>>): Array<Array<Int>> {

    val rows = hashSetOf<Int>()
    val columns = hashSetOf<Int>()

    var i = 0
    while (i < matrix.size) {

        val row = matrix[i]
        var j = 0
        while (j < row.size) {

            if (row[j] == 0) {
                rows.add(i)
                columns.add(j)
            }
            ++j
        }
        ++i
    }

    rows.forEach {
        nullifyRow(matrix, it)
    }

    columns.forEach {
        nullifyColumn(matrix, it)
    }

    return matrix
}


fun traditionalZeroMatrix(matrix: Array<Array<Int>>): Array<Array<Int>> {
    val row = Array(matrix.size) { false }
    val column = Array(matrix[0].size) { false }

    var i = 0
    while (i < matrix.size) {
        var j = 0
        while (j < matrix[0].size) {
            if (matrix[i][j] == 0) {
                row[i] = true
                column[j] = true
            }
            ++j
        }
        ++i
    }

    i = 0
    while (i < matrix.size) {
        if(row[i]) {
            nullifyRow(matrix, i)
        }
        ++i
    }

    i = 0
    while (i < matrix[0].size) {
        if(column[i]){
            nullifyColumn(matrix, i)
        }
        ++i
    }

    return matrix
}

fun nullifyRow(matrix: Array<Array<Int>>, row: Int) {
    var j = 0
    while (j < matrix[0].size) {
        matrix[row][j] = 0
        ++j
    }
}

fun nullifyColumn(matrix: Array<Array<Int>>, column: Int) {
    var i = 0
    while (i < matrix.size) {
        matrix[i][column] = 0
        ++i
    }
}
