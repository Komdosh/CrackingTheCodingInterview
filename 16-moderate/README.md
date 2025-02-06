# Moderate

Completed tasks:

![0%](https://progress-bar.xyz/0)

## 1. Number Swapper

Write a function to swap a number in place (that is, without temporary variables).

<hr/>

## 2. Word Frequencies

Design a method to find the frequency of occurrences of any given word in a book. What if we were running this algorithm multiple times?

<hr/>

## 3. Intersection

Given two straight line segments (represented as a start point and an end point), compute the point of intersection, if any.

<hr/>

## 4. Tic Tac Win

Design an algorithm to figure out if someone has won a game of tic-tac-toe.

<hr/>

## 5. Factorial Zeros

Write an algorithm which computes the number of trailing zeros in n factorial

<hr/>

## 6. The Smallest Difference

Given two arrays of integers, compute the pair of values (one value in each array) with the smallest (non-negative) difference. Return the
difference.

### Example

```
Input: 
  {1, 3, 15, 11, 2}, {23, 127,235, 19, 8}

Output: 
  3. That is, the pair (11, 8). 
```

<hr/>

## 7. Number Max

Write a method that finds the maximum of two numbers. You should not use if-else or any other comparison operator.

<hr/>

## 8. English Int

Given any integer, print an English phrase that describes the integer (e.g., "One Thousand, Two Hundred Thirty Four").

<hr/>

## 9. Operations

Write methods to implement the multiply, subtract, and divide operations for integers. The results of all of these are integers. Use only
the add operator.

<hr/>

## 10. Living People

Given a list of people with their birth and death years, implement a method to compute the year with the most number of people alive. You
may assume that all people were born between 1900 and 2000 (inclusive). If a person was alive during any portion of that year, they should
be included in that year's count. For example, Person (birth= 1908, death= 1909) is included in the counts for both 1908 and 1909.

<hr/>

## 11. Diving Board

You are building a diving board by placing a bunch of planks of wood end-to-end. There are two types of planks, one of length shorter and
one of length longer. You must use exactly K planks of wood. Write a method to generate all possible lengths for the diving board.

<hr/>

## 12. XML Encoding

Since XML is very verbose, you are given a way of encoding it where each tag gets mapped to a pre-defined integer value. The
language/grammar is as follows:

```
Element     --> Tag Attributes END Children END
Attribute   --> Tag Value
END         --> 0
Tag         --> some predefined mapping to int
Value       --> string value
```

### Example

```
Input: 
The following XML might be converted into the compressed string below (assuming a
mapping of 
    family -> 1, person ->2, firstName -> 3, lastName -> 4, state -> 5).
    <family lastName="McDowell" state="CA">
        <person firstName="Gayle">Some Message</person>
    </family>  
Output: 
    1 4 McDowell SCA 0 2 3 Gayle 0 Some Message 0 0 
```

Write code to print the encoded version of an XML element (passed in Element and Attribute objects).

<hr/>

## 13. Bisect Squares

Given two squares on a two-dimensional plane, find a line that would cut these two squares in half. Assume that the top and the bottom sides
of the square run parallel to the x-axis.

<hr/>

## 14. Best Line

Given a two-dimensional graph with points on it, find a line which passes the most number of points.

<hr/>

## 15. Master Mind

The Game of Master Mind is played as follows:

The computer has four slots, and each slot will contain a ball that is red (R). yellow (Y). green (G) or blue (B). For example, the computer
might have RGGB (Slot #1 is red, Slots #2 and #3 are green, Slot #4 is blue). You, the user, are trying to guess the solution. You might,
for example, guess YRGB. When you guess the correct color for the correct slot, you get a "hit" If you guess a color that exists but is in
the wrong slot, you get a "pseudo-hit" Note that a slot that is a hit can never count as a pseudo-hit. For example, if the actual solution
is RGBY and you guess GGRR, you have one hit and one pseudo-hit. Write a method that, given a guess and a solution, returns the number of
hits and pseudo-hits.

<hr/>

## 16. Sub Sort

Given an array of integers, write a method to find indices m and n such that if you sorted elements `m` through `n`, the entire array would
be sorted. Minimize `n - m` (that is, find the smallest such sequence).

### Example

```
Input: 
  1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19

Output: 
  (3, 9) 
```

<hr/>

## 17. Contiguous Sequence

You are given an array of integers (both positive and negative). Find the contiguous sequence with the largest sum. Return the sum.

### Example

```
Input: 
  2, -8, 3, -2, 4, -10 

Output: 
  5 ( i. e • , { 3, -2, 4})
```

<hr/>

## 18. Pattern Matching

You are given two strings, pattern and value. The pattern string consists of just the letters `a` and `b`, describing a pattern within a
string. For example, the string `catcatgocatgo` matches the pattern `aabab` (where `cat` is `a` and `go` is `b`). It also matches patterns
like `a`, `ab`, and `b`. Write a method to determine if `value` matches `pattern`.

<hr/>

## 19. Pond Sizes

You have an integer matrix representing a plot of land, where the value at that location represents the height above sea level. A value of
zero indicates water. A pond is a region of water connected vertically, horizontally, or diagonally. The size of the pond is the total
number of connected water cells. Write a method to compute the sizes of all ponds in the matrix.

### Example

```
Input: 
    0 2 1 0
    0 1 0 1
    1 1 0 1
    0 1 0 1

Output: 
    2, 4, 1 (in any order)
```

<hr/>

## 20. T9

On old cell phones, users typed on a numeric keypad and the phone would provide a list of words that matched these numbers. Each digit
mapped to a set of O - 4 letters. Implement an algorithm to return a list of matching words, given a sequence of digits. You are provided a
list of valid words (provided in whatever data structure you'd like). The mapping is shown in the diagram below:

![T9.png](t9.png)

### Example

```
Input: 
    8733

Output: 
    tree, used 
```

<hr/>

## 21. Sum Swap

Given two arrays of integers, find a pair of values (one value from each array) that you can swap to give the two arrays the same sum.

### Example

```
Input: 
    {4, 1, 2, 1, 1, 2} and {3, 6, 3, 3}

Output: 
    {1, 3} 
```

<hr/>

## 22. Langton's Ant

An ant is sitting on an infinite grid of white and black squares. It initially faces right. At each step, it does the following:
(1) At a white square, flip the color of the square, turn 90 degrees right (clockwise), and move forward one unit.
(2) At a black square, flip the color of the square, turn 90 degrees left (counter-clockwise), and move forward one unit. Write a program to
simulate the first K moves that the ant makes and print the final board as a grid. Note that you are not provided with the data structure to
represent the grid. This is something you must design yourself. The only input to your method is K. You should print the final grid and
return nothing. The method signature might be something like `void printKMoves ( int K)`.

<hr/>

## 23. Rand7 from Rands

Implement a method `rand7()` given `rand5()`. That is, given a method that generates a random number between O and 4 (inclusive), write a
method that generates a random number between O and 6 (inclusive).

<hr/>

## 24. Pairs with Sum

Design an algorithm to find all pairs of integers within an array which sum to a specified value.

<hr/>

## 25. LRU Cache

Design and build a "least recently used" cache, which evicts the least recently used item. The cache should map from keys to values (
allowing you to insert and retrieve a value associated with a particular key) and be initialized with a max size. When it is full, it should
evict the least recently used item.

<hr/>

## 26. Calculator

Given an arithmetic equation consisting of positive integers, +, -, * and / (no parentheses), compute the result.

### Example

```
Input: 
    2*3+5/6*3+15

Output: 
    23.5
```

<hr/>
