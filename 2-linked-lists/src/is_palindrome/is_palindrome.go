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

	isPalindrome = AlternativeNaiveIsPalindromeReversed(l1.Start)

	fmt.Printf("%v\n", isPalindrome)

	isPalindrome = OptimizedIsPalindromeStack(l1.Start)

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

// AlternativeNaiveIsPalindromeReversed reverse source list and compare it to original
func AlternativeNaiveIsPalindromeReversed(head *list.Node) bool {
	reversed, _ := list.Reverse(head)
	return list.IsEqual(head, reversed)
}

// OptimizedIsPalindromeStack fast pointer run through elements with 2x speed than slow pointer. When fast pointer finish, then slow pointer will
// in middle position. Elements in slow pointer for first half was added to stack, after that we need to pop elements from stack and compare
// it to current slow pointer item. If it is not equal, then list is not a palindrome.
func OptimizedIsPalindromeStack(head *list.Node) bool {
	fast := head
	slow := head

	var stack []int32

	for fast != nil && fast.Next != nil {
		stack = append(stack, slow.Item)
		slow = slow.Next
		fast = fast.Next.Next
	}

	if fast != nil {
		slow = slow.Next
	}

	for slow != nil {
		n := len(stack)-1
		value := stack[n]
		stack = stack[:n] // pop element

		if value != slow.Item {
			return false
		}

		slow = slow.Next
	}

	return true
}
