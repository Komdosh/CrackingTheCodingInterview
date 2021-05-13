package list

import "fmt"

type LinkedList struct {
	Start *Node
	End   *Node
	Size  int64
}

type Node struct {
	item int32
	next *Node
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
		current = current.next
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
		l.End.next = node
		l.End = node
		l.Size++
	}
	return nil
}

func (l *LinkedList) Remove(node *Node) error {
	var current = l.Start
	var prev *Node = nil
	for current != nil {

		if current == node {
			if prev == nil {
				l.Start = current.next
			} else {
				prev.next = current.next
			}
			l.Size--
			break
		}

		prev = current
		current = current.next
	}
	return nil
}

func (l *LinkedList) Print() error {
	var node = l.Start
	for node != nil {
		fmt.Printf("%d\n", node.item)

		node = node.next
	}
	return nil
}
