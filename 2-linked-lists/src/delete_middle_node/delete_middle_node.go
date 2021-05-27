package delete_middle

import (
	"../list"
	"fmt"
)

// Delete Middle Node: Implement an algorithm to delete a node in the middle
// (i.e., any node but the first and last node, not necessarily the exact middle) of a singly linked list, given only access to that node.

func DeleteMiddleNode() {
	l := list.CreateLinkedList()

	l.Add(0)
	l.Add(1)
	l.Add(2)
	l.Add(3)
	l.Add(4)
	l.Add(5)

	l.Print()

	if n, e := l.Get(2); e == nil {
		DeleteMiddle(n)

		l.Print()
	} else {
		fmt.Printf("Fetch by position error")
	}
}

// DeleteMiddle delete node in singly linked list by pointer to its node. We don't have start pointer.
func DeleteMiddle(n *list.Node) error {
	if n == nil || n.Next == nil {
		return fmt.Errorf("can't delete nil or last element")
	}

	n.Item = n.Next.Item
	n.Next = n.Next.Next
	return nil
}
