package main

import (
	"../../src/list"
	"fmt"
)

// Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting node.
// Note that the intersection is defined based on reference, not value. That is, if the `kth` node of the first linked list is
// the exact same node (by reference) as the `jth` node of the second linked list, then they are intersecting.
//
// Intersected Lists Example:
//
//   1 -> 2 -> 3
//               \
//                 6 -> 7 -> 8
//               /
//        4 -> 5

func main() {
	l1 := list.CreateLinkedList()

	l1.Add(1)
	l1.Add(2)
	l1.Add(3)
	l1.Add(6)
	l1.Add(7)
	l1.Add(8)

	l1.Print()

	l2 := list.CreateLinkedList()

	l2.Add(4)
	l2.Add(5)

	l2.End.Next, _ = l1.Get(3)

	l2.End = l1.End

	l2.Size = 5

	l2.Print()

	node := NaiveIntersected(&l1, &l2)
	for node != nil{
		fmt.Printf("%d", node.Item)
		if node.Next != nil {
			fmt.Printf(" -> ")
		}
		node = node.Next
	}
}

func NaiveIntersected(l1 *list.LinkedList, l2 *list.LinkedList) *list.Node {
	if l1 == nil || l2 == nil || l1.End != l2.End {
		return nil
	}

	var nodeMax *list.Node
	var nodeMin *list.Node

	if l1.Size > l2.Size{
		nodeMax = l1.Start
		nodeMin = l2.Start
	} else {
		nodeMax = l2.Start
		nodeMin = l1.Start
	}

	for nodeMax != nil && nodeMin != nil {

		nodeMaxRunner := nodeMax

		for nodeMaxRunner != nil{

			if nodeMin == nodeMaxRunner {
				return nodeMin
			}

			nodeMaxRunner = nodeMaxRunner.Next
		}

		nodeMin = nodeMin.Next
		nodeMax = nodeMax.Next
	}

	return nil
}



