package main

import (
	"../../src/list"
	"fmt"
)

// Return K'th to Last: Implement an algorithm to find the kth to last element of a singly linked list.

func main() {
	l := list.CreateLinkedList()

	l.Add(0)
	l.Add(1)
	l.Add(2)
	l.Add(3)
	l.Add(4)
	l.Add(5)

	if n, e := NaiveGetLastKthItem(1, l.Start, l.Size); e == nil {
		fmt.Printf("%d\n", n.Item)
	} else {
		fmt.Printf("%s\n", e.Error())
	}

	if n, e := OptimizedGetLastKthItem(1, l.Start); e == nil {
		fmt.Printf("%d\n", n.Item)
	} else {
		fmt.Printf("%s\n", e.Error())
	}

	if n, _, e := AlternativeNaiveGetLastKthItem(1, l.Start); e == nil {
		fmt.Printf("%d\n", n.Item)
	} else {
		fmt.Printf("%s\n", e.Error())
	}

}

// NaiveGetLastKthItem get last k'th item, loop on current while counter is not equal position
// Assumptions:
//  - List size is available
func NaiveGetLastKthItem(position int, start *list.Node, size int64) (*list.Node, error) {
	if int64(position) >= size {
		return nil, fmt.Errorf("position is out of list size")
	}

	var current = start
	i := size - int64(position)
	for current != nil && i > 0 {

		i-=1

		current = current.Next
	}

	return current, nil
}

// AlternativeNaiveGetLastKthItem get last k'th item, recursive method, go through all items, when end is reached,
//return nodes until index not equal to position. After that return same node till the end.
// Assumptions:
//  - List size is unknown
func AlternativeNaiveGetLastKthItem(position int, node *list.Node) (*list.Node, int, error) {
	if node == nil {
		return node, 0, nil
	}

	if n, i, e := AlternativeNaiveGetLastKthItem(position, node.Next); e == nil {
		if i == position{
			return n, i, nil
		} else {
			return node, i+1, nil
		}
	}

	return nil, 0, fmt.Errorf("shouldn't be called")
}

// OptimizedGetLastKthItem get last k'th item, there is two pointers, one point to current item,
// another lagging for position number from current. When current will finish, lagging pointer will point to k'th last item
// Assumptions:
//  - List size is unknown
func OptimizedGetLastKthItem(position int, start *list.Node) (*list.Node, error) {
	var current = start
	var laggingNode = start
	i := 0
	for current != nil {

		if i < position {
			i += 1
		} else {
			laggingNode = laggingNode.Next
		}

		current = current.Next
	}

	if i != 0 && laggingNode == start {
		return nil, fmt.Errorf("position is out of list size")
	}

	return laggingNode, nil
}
