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

func (l *LinkedList) Copy() *LinkedList {
	list := CreateLinkedList()

	var current = l.Start
	for current != nil {

		list.Add(current.Item)

		current = current.Next
	}
	return &list
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

// Print list to stdout like: 1 -> 2 -> 3 -> 4
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

// PadList add nodes to head with zeroes
func PadList(node *Node, padding int64) *Node {
	head := node
	for padding > 0{
		head = InsertBefore(head, 0)
		padding -= 1
	}
	return head
}

// InsertBefore insert new node to head
func InsertBefore(node *Node, data int32) *Node {
	n := Node{
		Item: data,
		Next: nil,
	}

	if node != nil {
		n.Next = node
	}

	return &n
}

// IsEqual check that two lists have same items
func IsEqual(one *Node, two *Node) bool{
	head1 := one
	head2 := two

	for head1 != nil && head2 != nil{
		if head1.Item != head2.Item {
			return false
		}
		head1 = head1.Next
		head2 = head2.Next
	}

	return head1 == nil && head2 == nil
}

// Reverse list recursively
func Reverse(head *Node) (*Node, *Node){
	if head == nil {
		return nil, nil
	}

	h, t := Reverse(head.Next)
	clonedHead := &Node{
		Item: head.Item,
		Next: nil,
	}

	if h == nil && t == nil {
		return clonedHead, clonedHead
	}

	t.Next = clonedHead
	return h, clonedHead
}


