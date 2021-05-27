package sum_lists

import (
	"../list"
	"fmt"
)

// Sum Lists: You have two numbers represented by a linked list, where each node contains a single digit.
// The digits are stored in reverse order, such that the 1 's digit is at the head of the list. Write a function that adds
// the two numbers and returns the sum as a linked list.
//
// Additional: Suppose the digits are stored in forward order. Repeat the above problem.
//
// Example:
//   Input:
//       (7 -> 1 -> 6) + (5 -> 9 -> 2). That is, 617 + 295.
//   Output:
//       2 -> 1 -> 9. That is, 912.
//
//   ---
//   Additional:
//
//   Input:
//       (6 -> 1 -> 7) + (2 -> 9 -> 5). That is, 617 + 295.
//   Output:
//       9 -> 1 -> 2. That is, 912.

func SumLists() {
	l1 := list.CreateLinkedList()

	l1.Add(7)
	l1.Add(1)
	l1.Add(6)

	l1.Print()
	fmt.Printf("+\n")

	l2 := list.CreateLinkedList()

	l2.Add(5)
	l2.Add(9)
	l2.Add(2)

	l2.Print()

	fmt.Printf("=\n")

	if res, err := SumList(&l1, &l2); err != nil {
		fmt.Printf("%s\n", err.Error())
	} else {
		res.Print()
	}

	fmt.Printf("---\n")

	if res, err := AlternativeSumList(l1.Start, l2.Start, 0); err != nil {
		fmt.Printf("%s\n", err.Error())
	} else {
		l := list.CreateLinkedList()
		l.Start = res
		l.Print()
	}

	fmt.Printf("---\n")

	l3 := list.CreateLinkedList()

	l3.Add(6)
	l3.Add(1)
	l3.Add(7)

	l3.Print()
	fmt.Printf("+\n")

	l4 := list.CreateLinkedList()

	l4.Add(2)
	l4.Add(2)
	l4.Add(9)
	l4.Add(5)

	l4.Print()

	fmt.Printf("=\n")

	if res, err := RevertSumList(&l3, &l4); err != nil {
		fmt.Printf("%s", err.Error())
	} else {
		l := list.CreateLinkedList()
		l.Start = res
		l.Print()
	}
}

// SumList run through two lists, sum value. If value greater than 9, then it has carry, we should add it on next.
func SumList(l1 *list.LinkedList, l2 *list.LinkedList) (*list.LinkedList, error) {
	var greatest *list.LinkedList
	var smallest *list.LinkedList
	if l1.Size > l2.Size {
		greatest = l1
		smallest = l2
	} else {
		greatest = l2
		smallest = l1
	}

	smallestRun := smallest.Start
	greatestRun := greatest.Start

	carry := false

	result := list.CreateLinkedList()

	for smallestRun != nil {
		if smallestRun.Item > 9 || smallestRun.Item < 0 || greatestRun.Item > 9 || greatestRun.Item < 0 {
			return nil, fmt.Errorf("number item should be in range 0..9")
		}

		value := smallestRun.Item + greatestRun.Item

		if carry {
			value += 1
		}

		result.Add(value % 10)

		carry = value/10 > 0

		smallestRun = smallestRun.Next
		greatestRun = greatestRun.Next
	}

	for greatestRun != nil {

		value := greatestRun.Item

		if carry {
			value += 1
		}

		carry = value/10 > 0

		result.Add(value % 10)

		greatestRun = greatestRun.Next
	}

	return &result, nil
}

// AlternativeSumList run through two lists with recursion, If value greater than 9, then it has carry, we should add it on next.
// Sum items before recursive call, add it to the tail of list.
func AlternativeSumList(l1 *list.Node, l2 *list.Node, carry int32) (*list.Node, error) {
	if l1 == nil && l2 == nil && carry == 0 {
		return nil, nil
	}

	result := list.Node{
		Item: 0,
		Next: nil,
	}

	value := carry
	if l1 != nil {
		if l1.Item > 9 || l1.Item < 0 {
			return nil, fmt.Errorf("number item should be in range 0..9")
		}
		value += l1.Item
	}
	if l2 != nil {
		if l2.Item > 9 || l2.Item < 0 {
			return nil, fmt.Errorf("number item should be in range 0..9")
		}
		value += l2.Item
	}

	result.Item = value % 10

	if l1 != nil || l2 != nil {
		var nextL1 *list.Node
		if l1 == nil {
			nextL1 = nil
		} else {
			nextL1 = l1.Next
		}
		var nextL2 *list.Node
		if l2 == nil {
			nextL2 = nil
		} else {
			nextL2 = l2.Next
		}

		var c int32

		if value/10 > 0 {
			c = 1
		} else {
			c = 0
		}

		var err error
		if result.Next, err = AlternativeSumList(nextL1, nextL2, c); err != nil {
			return nil, err
		}
	}

	return &result, nil
}

// RevertSumList fill smaller list with zeroes, to size be equal. Start sum items recursively from end.
func RevertSumList(l1 *list.LinkedList, l2 *list.LinkedList) (*list.Node, error) {

	if l1.Size < l2.Size {
		l1.Start = list.PadList(l1.Start, l2.Size-l1.Size)
		l1.Size = l2.Size
	} else {
		l2.Start = list.PadList(l2.Start, l1.Size-l2.Size)
		l2.Size = l1.Size
	}

	if sum, carry, err := sumListHelper(l1.Start, l2.Start); err != nil {
		return nil, err
	} else {
		if carry == 0 {
			return sum, nil
		} else {
			return list.InsertBefore(sum, carry), nil
		}
	}
}

// sumListHelper Recursively go deep to the end. If previous value has carry,
//then add it to the current. Sum items after recursive call, add it to the head of list.
func sumListHelper(l1 *list.Node, l2 *list.Node) (*list.Node, int32, error) {
	if l1 == nil && l2 == nil {
		return nil, 0, nil
	}

	sum, carry, err := sumListHelper(l1.Next, l2.Next)

	if err != nil {
		return nil, 0, err
	}

	val := carry + l1.Item + l2.Item

	full := list.InsertBefore(sum, val%10)

	return full, val / 10, nil
}
