package kth_item

import (
	"../list"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestNaiveGetLastKthItem(t *testing.T) {
	l := list.CreateLinkedList()

	l.Add(0)
	l.Add(1)
	l.Add(2)
	l.Add(3)
	l.Add(4)
	l.Add(5)

	n, _ := NaiveGetLastKthItem(2, l.Start, l.Size)

	assert.NotNil(t, n)
	assert.Equal(t, int32(4), n.Item)
}

func TestOptimizedGetLastKthItem(t *testing.T) {
	l := list.CreateLinkedList()

	l.Add(0)
	l.Add(1)
	l.Add(2)
	l.Add(3)
	l.Add(4)
	l.Add(5)

	n, _ := OptimizedGetLastKthItem(2, l.Start)

	assert.NotNil(t, n)
	assert.Equal(t, int32(4), n.Item)
}

func TestAlternativeNaiveGetLastKthItem(t *testing.T) {
	l := list.CreateLinkedList()

	l.Add(0)
	l.Add(1)
	l.Add(2)
	l.Add(3)
	l.Add(4)
	l.Add(5)

	n, _, _ := AlternativeNaiveGetLastKthItem(2, l.Start)

	assert.NotNil(t, n)
	assert.Equal(t, int32(4), n.Item)
}
