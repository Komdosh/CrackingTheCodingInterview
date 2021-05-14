package main

import (
	"../../src/list"
	"fmt"
)

// Remove Dups: Write code to remove duplicates from an unsorted linked list. How would you solve this problem if a temporary buffer is not allowed?

func main() {
	l := list.CreateLinkedList()

	l.Add(0)
	l.Add(1)
	l.Add(2)
	l.Add(0)
	l.Add(1)
	l.Add(2)
	l.Add(3)

	l.Print()

	fmt.Printf("Size %d\n", l.Size)

	OptimizeRemoveDups(&l)

	l.Print()

	fmt.Printf("Size %d\n", l.Size)

	l2 := list.CreateLinkedList()

	l2.Add(0)
	l2.Add(1)
	l2.Add(2)
	l2.Add(0)
	l2.Add(1)
	l2.Add(2)
	l2.Add(3)

	l2.Print()

	fmt.Printf("Size %d\n", l2.Size)

	OptimizeRemoveDups(&l2)

	l.Print()

	fmt.Printf("Size %d\n", l2.Size)
}

// NaiveRemoveDups compare elements with each other. If it is equal then remove.
func NaiveRemoveDups(l *list.LinkedList) {
	node := l.Start

	for node != nil {
		var current = node

		for current.Next != nil {
			if current.Next.Item == node.Item {
				current.Next = current.Next.Next
			} else {
				current = current.Next
			}
		}
		node = node.Next
	}
}

// OptimizeRemoveDups put visited element into hashmap. If it is already exists in map, then delete current node.
func OptimizeRemoveDups(l *list.LinkedList) {
	existed := map[int32]bool{}

	node := l.Start

	for node != nil {
		if _, s := existed[node.Item]; s {
			if node.Next != nil {
				node.Next = node.Next.Next
				l.Size--
			}
		} else {
			existed[node.Item] = true
		}

		node = node.Next
	}
}
