# Arrays and Strings `Kotlin`

Completed tasks:

![100%](https://progress-bar.xyz/100)

## 1. Is Unique

Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structures?

<details>
<summary>Naive Solution </summary>

#### Assumptions

- String contains only `ASCII` symbols

#### Complexity

- Time Complexity: `O(N)` - but never exceed number of symbols ```O(min(c, N)) = O(c)```
- Space Complexity: `O(1)`

#### Implementation

   ```kotlin
fun naiveIsUniqueChars(testStr: String): Boolean {
    if (testStr.length > 128) return false

    val used = Array(128) { false }

    for (i in IntRange(0, testStr.length - 1)) {
        val symbolCode = testStr[i].code
        if (used[symbolCode]) {
            return false
        }
        used[symbolCode] = true
    }
    return true
}
   ```

</details>

<details>
<summary>Optimized Solution </summary>

#### Assumptions:

- String contains only `a-z` symbols

#### Complexity

- Time Complexity: `O(N)` - but never exceed number of symbols ```O(min(c, N)) = O(c)```
- Space Complexity: `O(1)`

#### Implementation

   ```kotlin
fun optimizedIsUniqueChars(testStr: String): Boolean {
    if (testStr.length > 26) return false

    var a = 0

    for (i in IntRange(0, testStr.length - 1)) {
        val symbolCode = testStr[i].code - 'a'.code
        if ((a and (1 shl symbolCode)) > 0) {
            return false
        }

        a = a or (1 shl symbolCode)
    }
    return true
}
   ```

</details>

<details>
<summary>Naive Solution - Kotlin Way</summary>

#### Complexity

- Time Complexity: `O(N)`
- Space Complexity: `O(N)`

#### Implementation

   ```kotlin
fun isUniqueCharsKotlinWay(testStr: String): Boolean =
    testStr.toCharArray().toSet().size == testStr.length
   ```

</details>
<hr/>

## 2. Check Permutation

Given two strings, write a method to decide if one is a permutation of the other

### Assumptions

- String case-sensitive
- Whitespace is countable

<details>
<summary>Naive Solution </summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(N)`

#### Implementation

   ```kotlin
fun naiveCheckPermutation(first: String, second: String): Boolean {
    if (first.length != second.length) {
        return false
    }

    val counter = HashMap<Int, Int>()

    for (i in IntRange(0, first.length - 1)) {
        val symbolCode = first[i].code

        counter[symbolCode] = (counter[symbolCode] ?: 0) + 1
    }

    val secondCounter = HashMap<Int, Int>()

    for (i in IntRange(0, first.length - 1)) {
        val symbolCode = second[i].code

        secondCounter[symbolCode] = (secondCounter[symbolCode] ?: 0) + 1
    }

    for ((key, value) in counter) {
        if (secondCounter[key] != value) {
            return false
        }
    }

    return true
}
   ```

</details>

<details>
<summary>Optimized Solution</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(N)`

#### Implementation

   ```kotlin
fun optimizedCheckPermutation(first: String, second: String): Boolean {
    if (first.length != second.length) {
        return false
    }

    val counter = HashMap<Int, Int>()

    for (i in IntRange(0, first.length - 1)) {
        val symbolCode = first[i].code

        counter[symbolCode] = (counter[symbolCode] ?: 0) + 1
    }

    for (i in IntRange(0, first.length - 1)) {
        val symbolCode = second[i].code

        if ((counter[symbolCode] ?: 0) == 0) {
            return false
        }
        counter[symbolCode] = counter[symbolCode]!! - 1
    }

    return true
}
   ```

</details>

<details>
<summary>Clear Solution</summary>

#### Complexity

- Time Complexity: `O(N log N)`

- Space Complexity: `O(N)`

#### Implementation

   ```kotlin
fun clearCheckPermutation(first: String, second: String): Boolean {
    if (first.length != second.length) {
        return false
    }

    val firstSorted = first.toCharArray()
    val secondSorted = first.toCharArray()
    firstSorted.sort()
    secondSorted.sort()

    for (i in IntRange(0, first.length - 1)) {
        if (firstSorted[i] != secondSorted[i]) {
            return false
        }
    }

    return true
}
   ```

</details>

<details>
<summary>Naive Solution - Kotlin Way</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(N)`

#### Implementation

   ```kotlin
fun checkPermutationKotlinWay(first: String, second: String): Boolean = first.toCharArray().toTypedArray().groupingBy { it }.eachCount() ==
        second.toCharArray().toTypedArray().groupingBy { it }.eachCount()
   ```

</details>

<hr/>

## 3. URLify

Write a method to replace all spaces in a string with `%20`. You may assume that the string has sufficient space at the end to hold the
additional characters, and that you are given the `true` length of the string. (Note: If implementing in Java, please use a character array
so that you can perform this operation in place.)


<details>
<summary>Solution</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(1)`

#### Implementation

   ```kotlin
fun inplaceURlify(chars: CharArray, length: Int): CharArray {
    var whitespaceCounter = 0
    for (i in IntRange(0, length - 1)) {
        if (chars[i] == ' ') {
            ++whitespaceCounter
        }
    }
    if (whitespaceCounter == 0) {
        return chars
    }

    val replacedBy = "%20".toCharArray()
    var newLength = length + whitespaceCounter * 2 - 1
    var i = length - 1
    while (i >= 0) {
        if (chars[i] == ' ') {
            for (j in IntRange(0, replacedBy.size - 1).reversed()) {
                chars[newLength - j] = replacedBy[replacedBy.size - j - 1]
            }
            newLength -= replacedBy.size
        } else {
            chars[newLength] = chars[i]
            newLength -= 1
        }
        --i
    }
    return chars
}
   ```

</details>

<details>
<summary>Naive Solution - Kotlin Way</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(N)`

#### Implementation

   ```kotlin
fun URlifyKotlinWay(str: String): String = str.replace(" ", "%20")
   ```

</details>


<hr/>

## 4. Palindrome Permutation

Given a string, write a function to check if it is a permutation of a palindrome. A palindrome is a word or phrase that is the same forwards
and backwards. A permutation is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.

### Assumptions

- String case insensitive

<details>
<summary>Naive Solution </summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(N)`

#### Implementation

   ```kotlin
fun naiveIsPalindromePermutation(testStr: String): Boolean {
    val preparedTestString = testStr.lowercase().filter { it != ' ' }.toCharArray()
    val isStrLenEven = preparedTestString.size % 2 == 0
    val charsMap = HashMap<Char, Int>()
    preparedTestString.forEach {
        charsMap[it] = (charsMap[it] ?: 0) + 1
    }

    var hasOddNumberOfChars = false
    for (value in charsMap.values) {
        if (value % 2 != 0) {
            if (hasOddNumberOfChars || isStrLenEven) {
                return false
            }
            hasOddNumberOfChars = true
        }
    }
    return true
}
   ```

</details>

<details>
<summary>Alternative Naive Solution </summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(N)`

#### Implementation

   ```kotlin
fun alternativeNaiveIsPalindromePermutation(testStr: String): Boolean {
    val charsMap = HashMap<Char, Int>()
    var countOdd = 0
    testStr.lowercase().toCharArray().filter { it != ' ' }.forEach {
        charsMap[it] = (charsMap[it] ?: 0) + 1
        if (charsMap[it]!! % 2 == 1) {
            ++countOdd
        } else {
            --countOdd
        }
    }

    return countOdd <= 1
}
   ```

</details>

<details>
<summary>Optimized Solution</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(1)`

#### Implementation

   ```kotlin
fun optimizedIsPalindromePermutation(testStr: String): Boolean {
    val bitVector = createBitVector(testStr)
    return bitVector == 0 || checkExactlyOneBitSet(bitVector)
}

fun createBitVector(testStr: String): Int {
    var bitVector = 0
    for (char in testStr.toCharArray()) {
        val x = getCharNumber(char)
        bitVector = toggle(bitVector, x)
    }
    return bitVector
}

fun getCharNumber(char: Char): Int {
    return char.toLowerCase().code - 'a'.code
}

fun toggle(bitVector: Int, index: Int): Int {
    if (index < 0) return bitVector

    val mask = 1.shl(index)

    return if ((bitVector.and(mask)) == 0) {
        bitVector.or(mask)
    } else {
        bitVector.and(mask.inv())
    }
}

fun checkExactlyOneBitSet(bitVector: Int): Boolean {
    return bitVector.and(bitVector - 1) == 0
}
   ```

</details>

<details>
<summary>Naive Solution - Kotlin Way</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(1)`

#### Implementation

   ```kotlin
fun isPalindromePermutationKotlinWay(testStr: String): Boolean {
    val preparedTestString = testStr.lowercase().filter { it != ' ' }.toCharArray()
    val charsMap = preparedTestString.toTypedArray().groupingBy { it }.eachCount()

    return charsMap.values.filter { it % 2 != 0 }.count() <= 1
}
   ```

</details>

<hr/>

## 5. One Away

There are three types of edits that can be performed on strings: insert a character, remove a character, or replace a character. Given two
strings, write a function to check if they are one edit (or zero edits) away.

### Example

```
pale, ple -> true
pales, pale -> true
pale, bale -> true
pale, bake -> false
```

<details>
<summary>Naive Solution</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(1)`

#### Implementation

   ```kotlin
enum class SKIP {
    FIRST, SECOND, BOTH
}

fun naiveOneWay(first: String, second: String): Boolean {
    val lenDif = first.length - second.length
    if (abs(lenDif) > 1) {
        return false
    }
    val availableSkip = when (lenDif) {
        1 -> SKIP.FIRST
        -1 -> SKIP.SECOND
        else -> SKIP.BOTH
    }

    var skipUsed = false
    var firstIndex = 0
    var secondIndex = 0
    while (firstIndex < first.length && secondIndex < second.length) {

        if (first[firstIndex] != second[secondIndex]) {
            if (skipUsed) {
                return false
            }
            skipUsed = true

            when (availableSkip) {
                SKIP.BOTH -> {
                    ++firstIndex
                    ++secondIndex
                }
                SKIP.FIRST -> ++firstIndex
                SKIP.SECOND -> ++secondIndex
            }
        } else {
            ++firstIndex
            ++secondIndex
        }
    }

    return true
}
   ```

</details>

<details>
<summary>Alternative Naive Solution </summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(1)`

#### Implementation

   ```kotlin
fun alternativeNaiveOneWay(first: String, second: String): Boolean {
    return when {
        first.length == second.length -> {
            oneEditReplace(first, second)
        }
        first.length + 1 == second.length -> {
            oneEditInsert(first, second)
        }
        first.length - 1 == second.length -> {
            oneEditInsert(second, first)
        }
        else -> false
    }
}

fun oneEditReplace(first: String, second: String): Boolean {
    var foundDifference = false
    var i = 0
    while (i < first.length) {
        if (first[i] != second[i]) {
            if (foundDifference) {
                return false
            }
        }

        foundDifference = true
        ++i
    }

    return true
}

fun oneEditInsert(first: String, second: String): Boolean {
    var firstIndex = 0
    var secondIndex = 0

    while (secondIndex < second.length && firstIndex < first.length) {
        if (first[firstIndex] != second[secondIndex]) {
            if (firstIndex != secondIndex) {
                return false
            }

            ++secondIndex
        } else {
            ++firstIndex
            ++secondIndex
        }
    }
    return true
}
   ```

</details>

<details>
<summary>Optimized Naive Solution</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(1)`

#### Implementation

   ```kotlin
fun optimizedOneWay(first: String, second: String): Boolean {
    if (abs(first.length - second.length) > 1) {
        return false
    }

    val smallestStr = if (first.length < second.length) first else second
    val biggestStr = if (first.length < second.length) second else first

    var foundDifference = false
    var firstIndex = 0
    var secondIndex = 0
    while (firstIndex < first.length && secondIndex < second.length) {

        if (smallestStr[firstIndex] != biggestStr[secondIndex]) {
            if (foundDifference) {
                return false
            }
            foundDifference = true

            if (smallestStr.length == biggestStr.length) {
                ++firstIndex
            }
        } else {
            ++firstIndex
        }
        ++secondIndex
    }

    return true
}
   ```

</details>

<details>
<summary>Naive Solution - Kotlin Way</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(N)`

#### Implementation

   ```kotlin
fun oneWayKotlinWay(first: String, second: String): Boolean {
    val secondChars = second.toCharArray().toTypedArray().groupingBy { it }.eachCount()
    val firstChars = first.toCharArray().toTypedArray().groupingBy { it }.eachCount()

    var counter = 0

    return firstChars.all {
        val difference = abs((secondChars[it.key] ?: 0) - it.value)
        if (difference > 1) {
            return@all false
        } else if (difference == 1) {
            ++counter
        }
        if (counter > 1) {
            return@all false
        }

        return@all true
    }
}
   ```

</details>

<hr/>

## 6. String Compression

Implement a method to perform basic string compression using the counts of repeated characters. If the "compressed" string would not become
smaller than the original string, your method should return the original string. You can assume the string has only uppercase and lowercase
letters (a - z).

### Example

```
Input:
    aabcccccaaa
Output:
    a2bc5a3
```

<details>
<summary>Naive Solution</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(N)`

#### Implementation

   ```kotlin
fun naiveStringCompression(toCompression: String): String {
    val length = toCompression.length

    var i = 0
    val stringBuilder = StringBuilder()
    var counter = 1
    while (i < length) {
        val c = toCompression[i]

        if (i + 1 == length || toCompression[i + 1] != c) {
            stringBuilder.append(c)
            stringBuilder.append(counter)
            counter = 1
        } else {
            ++counter
        }

        ++i
    }
    val compressed = stringBuilder.toString()
    return if (compressed.length < toCompression.length) compressed else toCompression
}
   ```

</details>

<details>
<summary>Optimized Solution</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(1)`

#### Implementation

   ```kotlin
fun optimizedStringCompression(toCompression: String): String {
    val finalLength = countCompression(toCompression)
    if (finalLength >= toCompression.length) {
        return toCompression
    }

    val compressed = StringBuilder(finalLength)
    var countConsecutive = 0
    var i = 0
    while (i < toCompression.length) {
        ++countConsecutive
        if (i + 1 >= toCompression.length || toCompression[i] != toCompression[i + 1]) {
            compressed.append(toCompression[i])
            compressed.append(countConsecutive)
            countConsecutive = 0
        }
        ++i
    }
    return compressed.toString()
}

fun countCompression(toCompression: String): Int {
    var compressedLength = 0
    var countConsecutive = 0
    var i = 0
    while (i < toCompression.length) {
        ++countConsecutive
        if (i + 1 >= toCompression.length || toCompression[i] != toCompression[i + 1]) {
            compressedLength += 1 + compressedLength.toString().length
            countConsecutive = 0
        }
        ++i
    }
    return compressedLength
}
   ```

</details>

<hr/>

## 7. Rotate Matrix

Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate the image by 90 degrees. Can
you do this in place?

<details>
<summary>The Only Solution</summary>

#### Complexity

- Time Complexity: `O(N^2)`

- Space Complexity: `O(1)`

#### Implementation

   ```kotlin
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

            matrix[first][i] = matrix[last - offset][first]
            matrix[last - offset][first] = matrix[last][last - offset]
            matrix[last][last - offset] = matrix[i][last]
            matrix[i][last] = topLeft

            ++i
        }

        ++layer
    }


    return matrix
}
   ```

</details>

<hr/>

## 8. Zero Matrix

Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.

<details>
<summary>Naive Solution</summary>

#### Complexity

- Time Complexity: `O(MN)` - In the book we

- Space Complexity: `O(M+N)`

#### Implementation

   ```kotlin
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
   ```

</details>

<details>
<summary>Traditional Solution</summary>

#### Complexity

- Time Complexity: `O(MN)` - In the book we

- Space Complexity: `O(M+N)`

#### Implementation

   ```kotlin
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
   ```

</details>

<details>
<summary>Optimized Solution</summary>

#### Complexity

- Time Complexity: `O(MN)` - In the book we

- Space Complexity: `O(M+N)`

#### Implementation

   ```kotlin
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
   ```

</details>

<details>
<summary>Naive Solution - Kotlin Way</summary>

#### Complexity

- Time Complexity: `O(MN)`

- Space Complexity: `O(M+N)`

#### Implementation

   ```kotlin
fun zeroMatrixKotlinWay(matrix: Array<Array<Int>>): Array<Array<Int>> {

    val rows = hashSetOf<Int>()
    val columns = hashSetOf<Int>()

    matrix.mapIndexed { rowId, row ->
        row.mapIndexed { columnId, value ->
            if (value == 0) {
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
   ```

</details>

<hr/>

## 9. String Rotation

Assume you have a method isSubstring which checks if one word is a substring of another. Given two strings, `s1` and `s2`, write code to
check if `s2` is a rotation of s1 using only one call to isSubstring (e.g., `waterbottle` is a rotation of `erbottlewat`).

<details>
<summary>Naive Solution</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(1)`

#### Implementation

   ```kotlin
fun naiveIsStringRotation(first: String, second: String): Boolean {
    if (first.length != second.length || first.isEmpty() || second.isEmpty()) {
        return false
    }
    var i = 0
    var j = 0
    var startSequence = -1
    while (i < second.length) {
        if (first[i] == second[j]) {
            ++j
            if (startSequence == -1) {
                startSequence = i
            }
        } else if (startSequence != -1) {
            startSequence = -1
        }
        ++i
    }
    if (startSequence != -1) {
        if (first.substring(0, startSequence) == second.substring(second.length - startSequence, second.length)) {
            return true
        }
    }
    return false
}
   ```

</details>

<details>
<summary>Optimized Solution</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(N)` - N is just because we need to create new string with double length of first

#### Implementation

   ```kotlin
fun optimizedIsStringRotation(first: String, second: String): Boolean {
    if (first.length == second.length && first.isNotEmpty()) {
        return (first + first).contains(second)
    }
    return false
}
   ```

</details>

<hr/>

## P.S.

<details>
<summary>Why do you use `while` loops on i?</summary>

Kotlin doesn't support `for loop` as java does

```java
for(int i = 0; i < size; ++i){
    // some useful code
    }
```

So it's just a workaround:

```kotlin
var i = 0
while (i < size) {
    // some useful code
    ++i
}
```

What about for loop with `IntRange`?

```kotlin
for (i in IntRange(0, size - 1).step(1)) {
    // some useful code
}
```

It works perfectly, until we need dynamic stepping, like this:

```kotlin
var i = 0
while (i < size) {
    // some useful code
    i += i
}
```

So we just get a lot of control with simple `while loop`

</details>
