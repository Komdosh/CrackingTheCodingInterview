# Sorting and Searching

Completed tasks:

![0%](https://progress-bar.xyz/0)

## 1. Sorted Merge

You are given two sorted arrays, A and B, where A has a large enough buffer at the end to hold B. Write a method to merge B into A in sorted
order.
<hr/>

## 2. Group Anagrams

Write a method to sort an array of strings so that all the anagrams are next to each other.
<hr/>

## 3. Search in Rotated Array

Given a sorted array of n integers that has been rotated an unknown number of times, write code to find an element in the array. You may
assume that the array was originally sorted in increasing order.

<hr/>

## 4. Sorted Search, No Size

You are given an array like data structure `Listy` which lacks a size method. It does, however, have an `elementAt(i)` method that returns
the element at index `i` in `0(1)` time. If `i` is beyond the bounds of the data structure, it returns `-1`. (For this reason, the data
structure only supports positive integers.) Given a Listy which contains sorted, positive integers, find the index at which an element `x`
occurs. If `x`
occurs multiple times, you may return any index.

<hr/>

## 5. Sparse Search

Given a sorted array of strings that is interspersed with empty strings, write a method to find the location of a given string.

### Example

```
Input:
    ball, {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "",""}
Output: 
    4
```

<hr/>

## 6. Sort Big File

Imagine you have a 20 GB file with one string per line. Explain how you would sort the file.

<hr/>

## 7. Missing Int

Given an input file with four billion non-negative integers, provide an algorithm to generate an integer that is not contained in the file.
Assume you have 1 GB of memory available for this task. What if you have only 10 MB of memory? Assume that all the values are distinct and
we now have no more than one billion non-negative integers.

<hr/>

## 8. Find Duplicates

You have an array with all the numbers from `1 to N`, where `N` is at most 32,000. The array may have duplicate entries, and you do not know
what N is. With only 4 kilobytes of memory available, how would you print all duplicate elements in the array?

<hr/>

## 9. Sorted Matrix Search

Given an M x N matrix in which each row and each column is sorted in ascending order, write a method to find an element.

<hr/>

## 10. Rank from Stream

Imagine you are reading in a stream of integers. Periodically, you wish to be able to look up the rank of a number `x` (the number of values
less than or equal to x). Implement the data structures and algorithms to support these operations. That is, implement the method `track
(int x)`, which is called when each number is generated, and the method `getRankOfNumber(int x)`, which returns the number of values less
than or equal to x (not including x itself).

### Example

```
Input:
    Stream (in order of appearance): 5, 1, 4, 4, 5, 9, 7, 13, 3
Output: 
    getRankOfNumber(1) = 0
    getRankOfNumber(3) = 1
    getRankOfNumber(4) = 3
```

<hr/>

## 11. Peaks and Valleys

In an array of integers, a "peak" is an element which is greater than or equal to the adjacent integers, and a "valley" is an element which
is less than or equal to the adjacent integers. For example, in the array `{5, 8, 6, 2, 3, 4, 6}`, `{8, 6}` are peaks and `{5, 2}` are
valleys. Given an array of integers, sort the array into an alternating sequence of peaks and valleys.

### Example

```
Input:
    {5, 3, 1, 2, 3} 
Output: 
    {5, 1, 3, 2, 3} 
```

<hr/>
