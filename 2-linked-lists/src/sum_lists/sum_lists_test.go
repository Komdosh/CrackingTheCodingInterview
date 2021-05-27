package sum_lists

import (
	"../list"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestSumList(t *testing.T)  {
	l1 := list.CreateLinkedList()

	l1.Add(7)
	l1.Add(1)
	l1.Add(6)

	l2 := list.CreateLinkedList()

	l2.Add(5)
	l2.Add(9)
	l2.Add(2)

	result := list.CreateLinkedList()

	result.Add(2)
	result.Add(1)
	result.Add(9)

	res, _ := SumList(&l1, &l2)

	assert.NotNil(t, res)
	assert.Equal(t, true, list.IsEqual(result.Start, res.Start))
}

func TestRevertSumList(t *testing.T)  {
	l1 := list.CreateLinkedList()

	l1.Add(6)
	l1.Add(1)
	l1.Add(7)

	l2 := list.CreateLinkedList()

	l2.Add(2)
	l2.Add(9)
	l2.Add(5)

	result := list.CreateLinkedList()

	result.Add(9)
	result.Add(1)
	result.Add(2)

	res, _ := RevertSumList(&l1, &l2)

	assert.NotNil(t, res)
	assert.Equal(t, true, list.IsEqual(result.Start, res))
}
