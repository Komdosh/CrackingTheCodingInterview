# Arrays and Strings `Kotlin`

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
        val symbolCode = testStr[i].toInt()
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
        val symbolCode = testStr[i].toInt() - 'a'.toInt()
        if ((a and (1 shl symbolCode)) > 0) {
            return false
        }

        a = a or (1 shl symbolCode)
    }
    return true
}
   ```

</details>
<hr/>

## 2. Check Permutation

Given two strings, write a method to decide if one is a permutation of the other

### Assumptions

- String case sensitive
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
        val symbolCode = first[i].toInt()

        counter[symbolCode] = (counter[symbolCode] ?: 0) + 1
    }

    val secondCounter = HashMap<Int, Int>()

    for (i in IntRange(0, first.length - 1)) {
        val symbolCode = second[i].toInt()

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
        val symbolCode = first[i].toInt()

        counter[symbolCode] = (counter[symbolCode] ?: 0) + 1
    }

    for (i in IntRange(0, first.length - 1)) {
        val symbolCode = second[i].toInt()

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

- Space Complexity: `O(1)`

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

<hr/>

## 3. URLify

Write a method to replace all spaces in a string with '%20'. You may assume that the string has sufficient space at the end to hold the
additional characters, and that you are given the "true"
length of the string. (Note: If implementing in Java, please use a character array so that you can perform this operation in place.)


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

- Space Complexity: `O(1)`

#### Implementation

   ```kotlin
    fun naiveIsPalindromePermutation(testStr: String): Boolean {
        val preparedTestString = testStr.toLowerCase().filter { it != ' ' }.toCharArray()
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

- Space Complexity: `O(1)`

#### Implementation

   ```kotlin
    fun alternativeNaiveIsPalindromePermutation(testStr: String): Boolean {
        val charsMap = HashMap<Char, Int>()
        var countOdd = 0
        testStr.toLowerCase().toCharArray().filter { it != ' ' }.forEach {
            charsMap[it] = (charsMap[it] ?: 0) + 1
            if(charsMap[it]!! % 2 == 1){
                ++countOdd
            } else{
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
            return char.toLowerCase().toInt() - 'a'.toInt()
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
        val preparedTestString = testStr.toLowerCase().filter { it != ' ' }.toCharArray()
        val charsMap = preparedTestString.toTypedArray().groupingBy { it }.eachCount()
    
        return charsMap.values.filter { it % 2 != 0 }.count() <= 1
    }
   ```

</details>

<hr/>

## 5. One Away

There are three types of edits that can be performed on strings: insert a character, remove a character, or replace a character. Given two
strings, write a function to check if they are one edit (or zero edits) away.

<hr/>

## 6. String Compression

Implement a method to perform basic string compression using the counts of repeated characters. If the "compressed" string would not become
smaller than the original string, your method should return the original string. You can assume the string has only uppercase and lowercase
letters (a - z).

#### Example

```
Input:
    aabcccccaaa
Output:
    a2blc5a3
```

<hr/>

## 7. Rotate Matrix

Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate the image by 90 degrees. Can
you do this in place?

<hr/>

## 8. Zero Matrix

Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.

<hr/>

## 9. String Rotation

Assume you have a method isSubstring which checks if one word is a substring of another. Given two strings, s1 and s2, write code to check
if s2 is a rotation of s1 using only one call to isSubstring (e.g., `waterbottle` is a rotation of `erbottlewat`).

<hr/>
