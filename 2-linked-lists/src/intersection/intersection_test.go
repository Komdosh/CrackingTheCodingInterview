package intersection

import (
	"../list"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestNaiveFindIntersection(t *testing.T) {
	l1 := list.CreateLinkedList()

	l1.Add(1)
	l1.Add(2)
	l1.Add(3)
	l1.Add(6)
	l1.Add(7)
	l1.Add(8)

	l2 := list.CreateLinkedList()

	l2.Add(4)
	l2.Add(5)

	l2.End.Next, _ = l1.Get(3)

	l2.End = l1.End

	l2.Size = 5

	node := NaiveFindIntersection(&l1, &l2)

	assert.NotNil(t, node)
	intersected, _ := l1.Get(3)
	right := true
	for node != nil {
		if intersected != node{
			right = false
		}
		node = node.Next
		intersected = intersected.Next
	}

	assert.Equal(t, true, right)
}


func TestOptimizedFindIntersection(t *testing.T) {
	l1 := list.CreateLinkedList()

	l1.Add(1)
	l1.Add(2)
	l1.Add(3)
	l1.Add(6)
	l1.Add(7)
	l1.Add(8)

	l2 := list.CreateLinkedList()

	l2.Add(4)
	l2.Add(5)

	l2.End.Next, _ = l1.Get(3)

	l2.End = l1.End

	l2.Size = 5

	node := OptimizedFindIntersection(&l1, &l2)

	assert.NotNil(t, node)
	intersected, _ := l1.Get(3)
	right := true
	for node != nil {
		if intersected != node{
			right = false
		}
		node = node.Next
		intersected = intersected.Next
	}

	assert.Equal(t, true, right)
}
