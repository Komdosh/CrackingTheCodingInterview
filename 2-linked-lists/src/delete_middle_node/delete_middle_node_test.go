package delete_middle

import (
	"../list"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestDeleteMiddle(t *testing.T) {
	l := list.CreateLinkedList()

	l.Add(0)
	l.Add(1)
	l.Add(2)
	l.Add(3)
	l.Add(4)
	l.Add(5)

	size := 0
	noNode := true
	if n, e := l.Get(2); e == nil {
		DeleteMiddle(n)
		node := l.Start
		for node != nil {
			size += 1
			if node.Item == 2 {
				noNode = false
			}
			node = node.Next
		}
	}

	assert.Equal(t, 5, size)
	assert.Equal(t, true, noNode)
}
