package main

import (
	"../../src/list"
	"fmt"
)

// Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.
//
// Definition: Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node,
//so as to make a loop in the linked list.
//
// Example:
// Input:
//    A -> B -> C -> D -> E -> C [the same C as earlier]
// Output:
//    C

func main() {
	l1 := list.CreateLinkedList()

	l1.Add(1)
	l1.Add(2)
	l1.Add(3)
	l1.Add(4)
	l1.Add(5)
	l1.Add(6)

	l1.Print()

	l1.End.Next, _ = l1.Get(3)

	node := NaiveLoopDetection(&l1)

	if node != nil {
		fmt.Printf("Loop detected on node with value: %d\n", node.Item)
	} else {
		fmt.Printf("No loops")
	}

	node = OptimizedLoopDetection(&l1)

	if node != nil {
		fmt.Printf("Loop detected on node with value: %d\n", node.Item)
	} else {
		fmt.Printf("No loops")
	}
}

// NaiveLoopDetection go through all nodes, if it exists on set, then it is loop, otherwise add node to set.
func NaiveLoopDetection(l1 *list.LinkedList) *list.Node {
	if l1 == nil {
		return nil
	}
	set := map[*list.Node]bool{}

	node := l1.Start

	for node != nil {

		if _, exists := set[node]; !exists {
			set[node] = true
		} else {
			return node
		}

		node = node.Next
	}

	return nil
}

// OptimizedLoopDetection faster pointer has speed 2x. If loop exist, fast and slow pointers will met at point `k % LOOP_SIZE`, where k is
// size of list that not in loop. So after slow and fast pointer met, we need to move slow pointer to start. After that fast and slow pointers
// need to go further node by node, then they met in start loop (CollisionSpot) point.
func OptimizedLoopDetection(l1 *list.LinkedList) *list.Node {
	slow := l1.Start
	fast := l1.Start

	for fast != nil && fast.Next != nil {
		slow = slow.Next
		fast = fast.Next.Next
		if slow == fast {
			break
		}
	}

	if fast == nil || fast.Next == nil {
		return nil
	}

	slow = l1.Start
	for slow != fast {
		slow = slow.Next
		fast = fast.Next
	}

	return slow
}

