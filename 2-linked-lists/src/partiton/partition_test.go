package partiton

import (
	"../list"
	"github.com/stretchr/testify/assert"
	"testing"
)


func TestNaivePartition(t *testing.T)  {
	l := list.CreateLinkedList()

	l.Add(3)
	l.Add(5)
	l.Add(8)
	l.Add(5)
	l.Add(10)
	l.Add(2)
	l.Add(1)

	result := list.CreateLinkedList()

	result.Add(3)
	result.Add(2)
	result.Add(1)
	result.Add(5)
	result.Add(8)
	result.Add(5)
	result.Add(10)

	partitioned := NaivePartition(&l, 5)

	eq := list.IsEqual(partitioned.Start, result.Start)
	assert.Equal(t, true, eq)
}

func TestAlternativeNaivePartition(t *testing.T)  {
	l := list.CreateLinkedList()

	l.Add(3)
	l.Add(5)
	l.Add(8)
	l.Add(5)
	l.Add(10)
	l.Add(2)
	l.Add(1)

	result := list.CreateLinkedList()

	result.Add(3)
	result.Add(2)
	result.Add(1)
	result.Add(5)
	result.Add(8)
	result.Add(5)
	result.Add(10)

	partitioned := AlternativeNaivePartition(&l, 5)

	eq := list.IsEqual(partitioned.Start, result.Start)
	assert.Equal(t, true, eq)
}

func TestOptimizedPartition(t *testing.T)  {
	l := list.CreateLinkedList()

	l.Add(3)
	l.Add(5)
	l.Add(8)
	l.Add(5)
	l.Add(10)
	l.Add(2)
	l.Add(1)

	result := list.CreateLinkedList()

	result.Add(1)
	result.Add(2)
	result.Add(3)
	result.Add(5)
	result.Add(8)
	result.Add(5)
	result.Add(10)

	partitioned := OptimizedPartition(&l, 5)

	eq := list.IsEqual(partitioned.Start, result.Start)
	assert.Equal(t, true, eq)
}
