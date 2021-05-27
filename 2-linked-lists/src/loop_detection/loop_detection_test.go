package loop

import (
	"../list"
	"fmt"
	"github.com/stretchr/testify/assert"
	"testing"
)


func TestNaiveLoopDetection(t *testing.T)  {
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

	assert.Equal(t, int32(4), node.Item)

	if node != nil {
		fmt.Printf("Loop detected on node with value: %d\n", node.Item)
	} else {
		fmt.Printf("No loops")
	}
}

func TestOptimizedLoopDetection(t *testing.T)  {
	l1 := list.CreateLinkedList()

	l1.Add(1)
	l1.Add(2)
	l1.Add(3)
	l1.Add(4)
	l1.Add(5)
	l1.Add(6)

	l1.Print()

	l1.End.Next, _ = l1.Get(3)

	node := OptimizedLoopDetection(&l1)

	assert.Equal(t, int32(4), node.Item)

	if node != nil {
		fmt.Printf("Loop detected on node with value: %d\n", node.Item)
	} else {
		fmt.Printf("No loops")
	}
}
