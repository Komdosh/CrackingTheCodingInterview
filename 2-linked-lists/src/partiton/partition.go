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
}

// NaivePartition compare elements with each other. If it is equal then remove.
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

	left.End.Next = right.Start
	left.Size = left.Size + right.Size
	return &left
}
