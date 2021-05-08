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

    assertEquals(
        true, matrixEqual(
            processedMatrix, naiveZeroMatrix(
                getMatrix()
            )
        )
    )
    assertEquals(
        true, matrixEqual(
            processedMatrix, traditionalZeroMatrix(
                getMatrix()
            )
        )
    )

    assertEquals(
        true, matrixEqual(
            processedMatrix, optimizedZeroMatrix(
                getMatrix()
            )
        )
    )

    assertEquals(
        true, matrixEqual(
            processedMatrix, zeroMatrixKotlinWay(
                getMatrix()
            )
        )
    )
}

fun getMatrix(): Array<Array<Int>>{
    return arrayOf(
        arrayOf(0, 1, 1, 1),
        arrayOf(1, 1, 1, 1),
        arrayOf(1, 1, 1, 1),
        arrayOf(0, 1, 0, 1),
        arrayOf(1, 1, 1, 1),
    )
}

/***
 * Set 0 to row and column if it is present on row or column
 *
 * For store rows and columns position hash sets are used
 *
 * @param matrix - MxN int matrix
 * @return same matrix reference
 */
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

/***
 * Set 0 to row and column if it is present on row or column
 *
 * For store rows and columns position boolean arrays are used
 *
 * @param matrix - MxN int matrix
 * @return same matrix reference
 */
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
        if (row[i]) {
            nullifyRow(matrix, i)
        }
        ++i
    }

    i = 0
    while (i < matrix[0].size) {
        if (column[i]) {
            nullifyColumn(matrix, i)
        }
        ++i
    }

    return matrix
}

/***
 * Set 0 to row and column if it is present on row or column
 *
 * For store rows and columns position first row and column are used
 *
 * @param matrix - MxN int matrix
 * @return same matrix reference
 */
fun optimizedZeroMatrix(matrix: Array<Array<Int>>): Array<Array<Int>> {
    var rowHasZero = false
    var columnHasZero = false

    var i = 0
    while (i < matrix[0].size) {
        if (matrix[0][i] == 0) {
            rowHasZero = true
            break
        }
        ++i
    }

    i = 0
    while (i < matrix.size) {
        if (matrix[i][0] == 0) {
            columnHasZero = true
            break
        }
        ++i
    }


    i = 1
    while (i < matrix.size) {
        var j = 0
        while (j < matrix[0].size) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0
                matrix[0][j] = 0
            }
            ++j
        }
        ++i
    }

    i = 1
    while (i < matrix.size) {
        if (matrix[i][0] == 0) {
            nullifyRow(matrix, i)
        }
        ++i
    }

    i = 1
    while (i < matrix[0].size) {
        if (matrix[0][i] == 0) {
            nullifyColumn(matrix, i)
        }
        ++i
    }

    if (rowHasZero) {
        nullifyRow(matrix, 0)
    }

    if (columnHasZero) {
        nullifyColumn(matrix, 0)
    }

    return matrix
}

/***
 * Set 0 to row
 *
 * @param matrix - MxN int matrix
 * @param row - to replace with 0
 */
fun nullifyRow(matrix: Array<Array<Int>>, row: Int) {
    var j = 0
    while (j < matrix[0].size) {
        matrix[row][j] = 0
        ++j
    }
}

/***
 * Set 0 to column
 *
 * @param matrix - MxN int matrix
 * @param column - to replace with 0
 */
fun nullifyColumn(matrix: Array<Array<Int>>, column: Int) {
    var i = 0
    while (i < matrix.size) {
        matrix[i][column] = 0
        ++i
    }
}


/***
 * Set 0 to row and column if it is present on row or column
 *
 * For store rows and columns position hash sets are used
 *
 * @param matrix - MxN int matrix
 * @return same matrix reference
 */
fun zeroMatrixKotlinWay(matrix: Array<Array<Int>>): Array<Array<Int>> {

    val rows = hashSetOf<Int>()
    val columns = hashSetOf<Int>()

    matrix.mapIndexed { rowId, row ->
        row.mapIndexed { columnId, value ->
            if(value == 0){
                rows.add(rowId)
                columns.add(columnId)
            }
        }
    }

    rows.forEach {
        nullifyRow(matrix, it)
    }

    columns.forEach {
        nullifyColumn(matrix, it)
    }

    return matrix
}
