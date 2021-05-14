package list

import (
	"fmt"
)

type LinkedList struct {
	Start *Node
	End   *Node
	Size  int64
}

type Node struct {
	Item int32
	Next *Node
}

func CreateLinkedList() LinkedList {
	return LinkedList{
		Start: nil,
		End:   nil,
		Size:  0,
	}
}

func (l *LinkedList) Get(position int64) (*Node, error) {

	if position >= l.Size {
		return nil, fmt.Errorf("position greater than size of list")
	}

	var current = l.Start
	for current != nil {
		if position == 0 {
			return current, nil
		}
		position--
		current = current.Next
	}

	return nil, fmt.Errorf("shouldn't be called")
}

func (l *LinkedList) Add(item int32) error {
	node := &Node{
		item,
		nil,
	}

	if l.End == nil {
		l.End = node
		l.Start = node
		l.Size = 1
	} else {
		l.End.Next = node
		l.End = node
		l.Size++
	}
	return nil
}

func (l *LinkedList) Remove(node *Node) error {
	var current = l.Start
	for current != nil {

		if current.Next == node {
			current.Next = current.Next.Next
			break
		}

		current = current.Next
	}
	return nil
}

func (l *LinkedList) Print() error {
	var node = l.Start
	for node != nil {
		fmt.Printf("%d", node.Item)

		if node.Next != nil {
			fmt.Printf(" -> ")
		}

		node = node.Next
	}
	fmt.Printf("\n")
	return nil
}
