package main

import (
	"../src/list"
	"github.com/stretchr/testify/assert"
	"testing"
)

// Remove Dups: Write code to remove duplicates from an unsorted linked list. How would you solve this problem if a temporary buffer is not allowed?
func TestRemoveDups(t *testing.T) {
	l := list.CreateLinkedList()

	l.Add(0)
	l.Add(1)
	l.Add(2)
	l.Add(0)
	l.Add(1)
	l.Add(2)
	l.Add(3)
	assert.Equal(t, int64(4), l.Size)

	l2 := list.CreateLinkedList()
	l2.Add(0)
	l2.Add(1)
	l2.Add(2)
	l2.Add(3)
	assert.Equal(t, int64(4), l2.Size)
}
