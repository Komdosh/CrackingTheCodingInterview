package main

import (
	"../../src/list"
)

// Partition: Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes
//greater than or equal to x. If x is contained within the list, the values of x only need to be after the elements less than x (see below).
//The partition element x can appear anywhere in the "right partition"; it does not need to appear between the left and right partitions.
//
//Example:
// Input:
//    3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition= 5]
// Output:
//    3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8

func main() {
	l := list.CreateLinkedList()

	l.Add(3)
	l.Add(5)
	l.Add(8)
	l.Add(5)
	l.Add(10)
	l.Add(2)
	l.Add(1)

	l.Print()

	partitioned := NaivePartition(&l, 5)

	partitioned.Print()
	l.Print()
	altPartitioned := AlternativeNaivePartition(&l, 5)

	altPartitioned.Print()
	l.Print()

	optPartitioned := OptimizedPartition(&l, 5)

	optPartitioned.Print()
	l.Print()
}

// NaivePartition create two lists, left has elements smaller than x, right - greater
func NaivePartition(l *list.LinkedList, n int32) *list.LinkedList {
	left := list.CreateLinkedList()
	right := list.CreateLinkedList()

	node := l.Start

	for node != nil {

		if node.Item < n {
			left.Add(node.Item)
		} else {
			right.Add(node.Item)
		}

		node = node.Next
	}

	if left.Start == nil {
		return &right
	}
	left.End.Next = right.Start
	left.Size = left.Size + right.Size
	return &left
}

// AlternativeNaivePartition create two lists, left has elements smaller than x, right - greater
func AlternativeNaivePartition(l *list.LinkedList, n int32) *list.LinkedList {
	left := list.CreateLinkedList()
	right := list.CreateLinkedList()

	node := l.Copy().Start

	for node != nil {
		next := node.Next
		node.Next = nil
		if node.Item < n {
			if left.Start == nil {
				left.Start = node
				left.End = left.Start
			} else {
				left.End.Next = node
				left.End = node
			}
		} else {
			if right.Start == nil {
				right.Start = node
				right.End = right.Start
			} else {
				right.End.Next = node
				right.End = node
			}
		}

		node = next
	}

	if left.Start == nil {
		return &right
	}
	left.End.Next = right.Start
	left.Size = left.Size + right.Size
	return &left
}

// OptimizedPartition add smaller elements to start of list, and greater elements to end
func OptimizedPartition(l *list.LinkedList, n int32) *list.LinkedList {
	l1 := l.Copy()

	head := l1.Start
	tail := l1.Start

	node := l1.Start

	for node != nil {
		next := node.Next

		if node.Item < n {
			node.Next = head
			head = node
		} else {
			tail.Next = node
			tail = node
		}

		node = next
	}
	tail.Next = nil

	l2 := list.CreateLinkedList()
	l2.Start = head
	l2.End = tail
	l2.Size = l1.Size
	return &l2
}
