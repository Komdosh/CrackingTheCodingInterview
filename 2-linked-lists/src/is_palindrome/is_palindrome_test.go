package palindrome

import (
	"../list"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestNaiveIsPalindrome_true(t *testing.T) {
	list := list.CreateLinkedList()

	list.Add(1)
	list.Add(2)
	list.Add(3)
	list.Add(4)
	list.Add(4)
	list.Add(3)
	list.Add(2)
	list.Add(1)

	isPalindrome := NaiveIsPalindrome(&list)

	assert.Equal(t, true, isPalindrome)
}

func TestNaiveIsPalindrome_false(t *testing.T) {
	list := list.CreateLinkedList()

	list.Add(1)
	list.Add(2)
	list.Add(3)
	list.Add(4)
	list.Add(4)
	list.Add(2)
	list.Add(2)
	list.Add(1)

	isPalindrome := NaiveIsPalindrome(&list)

	assert.Equal(t, false, isPalindrome)
}

func TestNaiveIsPalindromeSize_true(t *testing.T) {
	list := list.CreateLinkedList()

	list.Add(1)
	list.Add(2)
	list.Add(3)
	list.Add(4)
	list.Add(4)
	list.Add(3)
	list.Add(2)
	list.Add(1)

	isPalindrome := NaiveIsPalindromeSize(&list)

	assert.Equal(t, true, isPalindrome)
}

func TestNaiveIsPalindromeSize_false(t *testing.T) {
	list := list.CreateLinkedList()

	list.Add(1)
	list.Add(2)
	list.Add(3)
	list.Add(4)
	list.Add(4)
	list.Add(2)
	list.Add(2)
	list.Add(1)

	isPalindrome := NaiveIsPalindromeSize(&list)

	assert.Equal(t, false, isPalindrome)
}

func TestAlternativeNaiveIsPalindromeReversed_true(t *testing.T) {
	list := list.CreateLinkedList()

	list.Add(1)
	list.Add(2)
	list.Add(3)
	list.Add(4)
	list.Add(4)
	list.Add(3)
	list.Add(2)
	list.Add(1)

	isPalindrome := AlternativeNaiveIsPalindromeReversed(list.Start)

	assert.Equal(t, true, isPalindrome)
}

func TestAlternativeNaiveIsPalindromeReversed_false(t *testing.T) {
	list := list.CreateLinkedList()

	list.Add(1)
	list.Add(2)
	list.Add(3)
	list.Add(4)
	list.Add(4)
	list.Add(2)
	list.Add(2)
	list.Add(1)

	isPalindrome := AlternativeNaiveIsPalindromeReversed(list.Start)

	assert.Equal(t, false, isPalindrome)
}

func TestOptimizedIsPalindromeStack_true(t *testing.T) {
	list := list.CreateLinkedList()

	list.Add(1)
	list.Add(2)
	list.Add(3)
	list.Add(4)
	list.Add(4)
	list.Add(3)
	list.Add(2)
	list.Add(1)

	isPalindrome := OptimizedIsPalindromeStack(list.Start)

	assert.Equal(t, true, isPalindrome)
}

func TestOptimizedIsPalindromeStack_false(t *testing.T) {
	list := list.CreateLinkedList()

	list.Add(1)
	list.Add(2)
	list.Add(3)
	list.Add(4)
	list.Add(4)
	list.Add(2)
	list.Add(2)
	list.Add(1)

	isPalindrome := OptimizedIsPalindromeStack(list.Start)

	assert.Equal(t, false, isPalindrome)
}

func TestOptimizedIsPalindromeRecursion_true(t *testing.T) {
	list := list.CreateLinkedList()

	list.Add(1)
	list.Add(2)
	list.Add(3)
	list.Add(4)
	list.Add(4)
	list.Add(3)
	list.Add(2)
	list.Add(1)

	_, isPalindrome := OptimizedIsPalindromeRecursion(list.Start, list.Size)

	assert.Equal(t, true, isPalindrome)
}

func TestOptimizedIsPalindromeRecursion_false(t *testing.T) {
	list := list.CreateLinkedList()

	list.Add(1)
	list.Add(2)
	list.Add(3)
	list.Add(4)
	list.Add(4)
	list.Add(2)
	list.Add(2)
	list.Add(1)

	_, isPalindrome := OptimizedIsPalindromeRecursion(list.Start, list.Size)

	assert.Equal(t, false, isPalindrome)
}
