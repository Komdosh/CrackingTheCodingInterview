package remove_dups

import (
	"../list"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestNaiveRemoveDups(t *testing.T)  {
	l := list.CreateLinkedList()

	l.Add(0)
	l.Add(1)
	l.Add(2)
	l.Add(0)
	l.Add(1)
	l.Add(2)
	l.Add(3)

	result := list.CreateLinkedList()

	result.Add(0)
	result.Add(1)
	result.Add(2)
	result.Add(3)

	NaiveRemoveDups(&l)

	eq := list.IsEqual(l.Start, result.Start)
	assert.Equal(t, true, eq)
}

func TestOptimizeRemoveDups(t *testing.T)  {
	l := list.CreateLinkedList()

	l.Add(0)
	l.Add(1)
	l.Add(2)
	l.Add(0)
	l.Add(1)
	l.Add(2)
	l.Add(3)

	result := list.CreateLinkedList()

	result.Add(0)
	result.Add(1)
	result.Add(2)
	result.Add(3)

	OptimizedRemoveDups(&l)

	eq := list.IsEqual(l.Start, result.Start)
	assert.Equal(t, true, eq)
}
