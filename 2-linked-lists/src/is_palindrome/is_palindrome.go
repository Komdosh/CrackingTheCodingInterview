package main

import (
	"../../src/list"
	"fmt"
)

// Palindrome: Implement a function to check if a linked list is a palindrome.

func main() {
	l1 := list.CreateLinkedList()

	l1.Add(1)
	l1.Add(2)
	l1.Add(3)
	l1.Add(4)
	l1.Add(4)
	l1.Add(3)
	l1.Add(2)
	l1.Add(1)

	l1.Print()

	isPalindrome := NaiveIsPalindrome(&l1)

	fmt.Printf("%v\n", isPalindrome)

	isPalindrome = NaiveIsPalindromeSize(&l1)

	fmt.Printf("%v\n", isPalindrome)
}

// NaiveIsPalindrome add all elements to the array, then check first N/2 items on list and last N/2 reverse items from array
func NaiveIsPalindrome(list *list.LinkedList) bool {

	var array []int32

	node := list.Start

	for node != nil {
		array = append(array, node.Item)
		node = node.Next
	}

	node = list.Start
	lenArray := len(array)
	i := lenArray - 1
	for node != nil && i >= lenArray/2 {

		if node.Item != array[i] {
			return false
		}

		i -= 1
		node = node.Next
	}

	return true
}

// NaiveIsPalindromeSize add all elements to the array, then check first N/2 items on list and last N/2 reverse items from array
// Assumption:
//  - size is available
func NaiveIsPalindromeSize(list *list.LinkedList) bool {
	size := list.Size
	var array = make([]int32, size/2)

	node := list.Start

	i := int64(1)
	arrayId := 0
	odd := size%2 != 0
	half := size / 2
	if odd {
		half += 1
	}
	for node != nil {
		if i > half { //skip first half
			array[arrayId] = node.Item
			arrayId += 1
		}
		i += 1
		node = node.Next
	}

	node = list.Start
	lenArray := len(array)
	arrayId = lenArray - 1
	for node != nil && arrayId >= 0 {

		if node.Item != array[arrayId] {
			return false
		}

		arrayId -= 1
		node = node.Next
	}

	return true
}
