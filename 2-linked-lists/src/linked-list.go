package main

import "fmt"

type LinkedList struct {
	start *Node
	end   *Node
	size  int64
}

type Node struct {
	item int32
	next *Node
}

func createLinkedList() LinkedList {
	return LinkedList{
		start: nil,
		end:   nil,
		size:  0,
	}
}

func (l *LinkedList) GetSize() int64 {
	return l.size
}

func (l *LinkedList) Get(position int64) (*Node, error) {

	if position >= l.GetSize() {
		return nil, fmt.Errorf("position greater than size of list")
	}

	var current = l.start
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

	if l.end == nil {
		l.end = node
		l.start = node
		l.size = 1
	} else {
		l.end.next = node
		l.end = node
		l.size++
	}
	return nil
}

func (l *LinkedList) Remove(node *Node) error {
	var current = l.start
	for current != nil {

		if current == node {
			if current == l.start {
				l.start = current.next
			} else if current.next != nil {
				current.next = current.next.next
			} else {
				current.next = nil
			}
			l.size--
			break
		}

		current = current.next

	}
	return nil
}

func (l *LinkedList) Print() error {
	var node = l.start
	for node != nil {
		fmt.Printf("%d\n", node.item)

		node = node.next
	}
	return nil
}

func main() {
	l := createLinkedList()
	l.Add(0)
	l.Add(1)
	l.Add(2)

	l.Print()

	if node, err := l.Get(2); err == nil {
		l.Remove(node)
	} else {
		fmt.Printf("%s\n", err.Error())
	}

	if node, err := l.Get(1); err == nil {
		l.Remove(node)
	} else {
		fmt.Printf("%s\n", err.Error())
	}

	if node, err := l.Get(0); err == nil {
		l.Remove(node)
	} else {
		fmt.Printf("%s\n", err.Error())
	}

	l.Print()
}
