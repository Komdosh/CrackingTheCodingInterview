package palindrome

import (
	"../list"
	"fmt"
)

// Palindrome: Implement a function to check if a linked list is a palindrome.

func IsPalindrome() {
	list := list.CreateLinkedList()

	list.Add(1)
	list.Add(2)
	list.Add(3)
	list.Add(4)
	list.Add(4)
	list.Add(3)
	list.Add(2)
	list.Add(1)

	list.Print()

	isPalindrome := NaiveIsPalindrome(&list)

	fmt.Printf("%v\n", isPalindrome)

	isPalindrome = NaiveIsPalindromeSize(&list)

	fmt.Printf("%v\n", isPalindrome)

	isPalindrome = AlternativeNaiveIsPalindromeReversed(list.Start)

	fmt.Printf("%v\n", isPalindrome)

	isPalindrome = OptimizedIsPalindromeStack(list.Start)

	fmt.Printf("%v\n", isPalindrome)

	_, isPalindrome = OptimizedIsPalindromeRecursion(list.Start, list.Size)

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

// OptimizedIsPalindromeStack fast pointer run through elements with 2x speed than slow pointer. When the fast pointer finish, then the slow pointer will
// in middle position. Elements in slow pointer for the first half was added to a stack, after we need to pop elements from a stack and compare
// it to current slow pointer item. If it is not equal, then a list is not a palindrome.
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
		n := len(stack) - 1
		value := stack[n]
		stack = stack[:n] // pop element

		if value != slow.Item {
			return false
		}

		slow = slow.Next
	}

	return true
}

// OptimizedIsPalindromeRecursion go through elements recursively while middle not reached.
// After that start to return next element and compare it with previous.
// So it will looks like we start to compare elements from middle to edges.
// Example
//   Input:
//     0 -> 1 -> 2 -> 3 -> 2 -> 1 -> 0
// Compare step by step:
//     3 - skip odd middle element, middle was reached
//     start to return elements from middle to edges (compare previous pointer and pointer.next elements)
//     2 == 2 - compare edges (2 -> 3 -> 2)
//     1 == 1 - compare edges (1 -> 2 -> 3 -> 2 -> 1)
//     0 == 0 - compare edges (0 -> 1 -> 2 -> 3 -> 2 -> 1 -> 0)
func OptimizedIsPalindromeRecursion(head *list.Node, size int64) (*list.Node, bool) {
	if head == nil || size <= 0 {
		return head, true
	} else if size == 1 {
		return head.Next, true
	}

	node, isPalindrome := OptimizedIsPalindromeRecursion(head.Next, size-2)

	if !isPalindrome || node == nil {
		return node, isPalindrome
	}

	return node.Next, head.Item == node.Item
}
