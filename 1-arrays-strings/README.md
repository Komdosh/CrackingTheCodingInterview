# Arrays and Strings

## 1. Is Unique
  Implement an algorithm to determine if a string has all unique characters. What if you 
   cannot use additional data structures?

   ### Naive Solution
   
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

   ### Optimized Solution:
   
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
   
<hr/>

## 2. Check Permutation
   Given two strings, write a method to decide if one is a permutation of the other

   ### Assumptions

   - String case sensitive
   - Whitespace is countable
   
   ### Naive Solution

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
   
           counter[symbolCode] = (counter[symbolCode] ?: 0)+1
       }
   
       val secondCounter = HashMap<Int, Int>()
   
       for (i in IntRange(0, first.length - 1)) {
           val symbolCode = second[i].toInt()
   
           secondCounter[symbolCode] = (secondCounter[symbolCode] ?: 0)+1
       }
   
       for ((key, value) in counter) {
           if (secondCounter[key] != value) {
               return false
           }
       }
   
       return true
   }
   ```

   ### Optimized Solution:

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
   
           counter[symbolCode] = (counter[symbolCode] ?: 0)+1
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
   
   ### Clear Solution:

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
           if(firstSorted[i] != secondSorted[i]){
               return false
           }
       }
   
       return true
   }
   ```
<hr/>

## 3. URLify
   Write a method to replace all spaces in a string with '%20'. You may assume that the string 
   has sufficient space at the end to hold the additional characters, and that you are given the "true"
   length of the string. (Note: If implementing in Java, please use a character array so that you can
   perform this operation in place.)

<hr/>

## 4. Palindrome Permutation
   Given a string, write a function to check if it is a permutation of a palindrome. A palindrome is a word or phrase that is the same 
   forwards and backwards. A permutation is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.

<hr/>

## 5. One Away
   There are three types of edits that can be performed on strings: insert a character,
   remove a character, or replace a character. Given two strings, write a function to check if they are
   one edit (or zero edits) away.

<hr/>

## 6. String Compression
   Implement a method to perform basic string compression using the counts
   of repeated characters. For example, the string aabcccccaaa would become a2blc5a3. If the
   "compressed" string would not become smaller than the original string, your method should return
   the original string. You can assume the string has only uppercase and lowercase letters (a - z).

<hr/>

## 7. Rotate Matrix
   Given an image represented by an NxN matrix, where each pixel in the image is 4
   bytes, write a method to rotate the image by 90 degrees. Can you do this in place?

<hr/>

## 8. Zero Matrix
   Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
   column are set to 0.

<hr/>

## 9. String Rotation
   Assume you have a method isSubstring which checks if one word is a substring of another.
   Given two strings, sl and s2, write code to check if s2 is a rotation of sl using only one call to isSubstring (e.g., "waterbottle" is a rotation of"erbottlewat").

<hr/>
