# Linked Lists `Go`

Completed tasks:

![62%](https://progress-bar.dev/62)

## 1. Remove Dups

Write code to remove duplicates from an unsorted linked list. How would you solve this problem if a temporary buffer is not allowed?

<details>
<summary>Naive Solution</summary>

#### Complexity

- Time Complexity: `O(N^2)`

- Space Complexity: `O(1)`

#### Implementation

   ```go
func NaiveRemoveDups (l * list.LinkedList) {
    node: = l.Start

    for node != nil {
        var current = node

        for current.Next != nil {
            if current.Next.Item == node.Item {
                current.Next = current.Next.Next
            } else {
                current = current.Next
            }
        }
        node = node.Next
    }
}
   ```

</details>

<details>
<summary>Optimized Solution</summary>

### Assumptions

- We can use temporary buffer

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(N)`

#### Implementation

   ```go
func OptimizeRemoveDups (l * list.LinkedList) {
    existed: = map[int32]bool{}

    node: = l.Start

    for node != nil {
        if _, s: = existed[node.Item]; s {
        if node.Next != nil {
            node.Next = node.Next.Next
            l.Size--
        }
    } else {
        existed[node.Item] = true
    }

        node = node.Next
    }
}
   ```

</details>

<hr/>

## 2. Return K'th to Last

Implement an algorithm to find the kth to last element of a singly linked list.

<details>
<summary>Naive Solution</summary>

### Assumptions

- List size is available

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(1)`

#### Implementation

   ```go
func NaiveGetLastKthItem(position int, start *list.Node, size int64) (*list.Node, error) {
    if int64(position) >= size {
        return nil, fmt.Errorf("position is out of list size")
    }

    var current = start
    i := size - int64(position)
    for current != nil && i > 0 {

        i-=1

        current = current.Next
    }

    return current, nil
}
   ```

</details>

<details>
<summary>Alternative Naive Solution</summary>

### Assumptions

- List size is unknown

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(N)`

#### Implementation

   ```go
func AlternativeNaiveGetLastKthItem(position int, node *list.Node) (*list.Node, int, error) {
    if node == nil {
        return node, 0, nil
    }

    if n, i, e := AlternativeNaiveGetLastKthItem(position, node.Next); e == nil {
    if i == position{
        return n, i, nil
    } else {
        return node, i+1, nil
    }
}

    return nil, 0, fmt.Errorf("shouldn't be called")
}
   ```

</details>

<details>
<summary>Optimized Solution</summary>

### Assumptions

- List size is unknown

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(1)`

#### Implementation

   ```kotlin
func OptimizedGetLastKthItem(position int, start *list.Node) (*list.Node, error) {
    var current = start
    var laggingNode = start
    i := 0
    for current != nil {

        if i < position {
            i += 1
        } else {
            laggingNode = laggingNode.Next
        }

        current = current.Next
    }

    if i != 0 && laggingNode == start {
        return nil, fmt.Errorf("position is out of list size")
    }

    return laggingNode, nil
}
   ```

</details>

<hr/>

## 3. Delete Middle Node

Implement an algorithm to delete a node in the middle (i.e., any node but the first and last node, not necessarily the exact middle) of a
singly linked list, given only access to that node.

### Example

```
Input:
    the node c from the linked list a->b->c->d->e->f
Result:
    nothing is returned, but the new linked list looks like a->b->d->e->f
```

<details>
<summary>Solution</summary>

### Assumptions

- We can't delete last element

#### Complexity

- Time Complexity: `O(1)`

- Space Complexity: `O(1)`

#### Implementation

   ```go
func DeleteMiddle(n *list.Node) error {
	if n == nil || n.Next == nil {
		return fmt.Errorf("can't delete nil or last element")
	}

	n.Item = n.Next.Item
	n.Next = n.Next.Next
	return nil
}
   ```

</details>

<hr/>

## 4. Partition

Write code to partition a linked list around a value `x`, such that all nodes less than x come before all nodes greater than or
equal to `x`. If `x` is contained within the list, the values of `x` only need to be after the elements less than `x` (see below). The 
partition element x can appear anywhere in the "right partition"; it does not need to appear between the left and right partitions.

### Example

```
Input:
    3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition = 5]
Output:
    3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
```

<details>
<summary>Naive Solution</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(N)`

#### Implementation

   ```go
func NaivePartition(l *list.LinkedList, n int32) *list.LinkedList {
    left := list.CreateLinkedList()
    right := list.CreateLinkedList()

    node := l.Start

    for node != nil {

        if node.Item < n {
            left.Add(node.Item)
        } else {
            right.Add(node.Item)
        }

        node = node.Next
    }

    if left.Start == nil {
        return &right
    }
    left.End.Next = right.Start
    left.Size = left.Size + right.Size
    return &left
}
   ```

</details>

<details>
<summary>Alternative Naive Solution</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(N)`

#### Implementation

   ```go
func AlternativeNaivePartition(l *list.LinkedList, n int32) *list.LinkedList {
    left := list.CreateLinkedList()
    right := list.CreateLinkedList()

    node := l.Copy().Start

    for node != nil {
        next := node.Next
        node.Next = nil
        if node.Item < n {
            if left.Start == nil {
                left.Start = node
                left.End = left.Start
            } else {
                left.End.Next = node
                left.End = node
            }
        } else {
            if right.Start == nil {
                right.Start = node
                right.End = right.Start
            } else {
                right.End.Next = node
                right.End = node
            }
        }

        node = next
    }

    if left.Start == nil {
        return &right
    }
    left.End.Next = right.Start
    left.Size = left.Size + right.Size
    return &left
}
   ```

</details>

<details>
<summary>Optimized Naive Solution</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(1)`

#### Implementation

   ```go
func OptimizedPartition(l *list.LinkedList, n int32) *list.LinkedList {
    l1 := l.Copy()

    head := l1.Start
    tail := l1.Start

    node := l1.Start

    for node != nil {
        next := node.Next

        if node.Item < n {
            node.Next = head
            head = node
        } else {
            tail.Next = node
            tail = node
        }

        node = next
    }
    tail.Next = nil

    l2 := list.CreateLinkedList()
    l2.Start = head
    l2.End = tail
    l2.Size = l1.Size
    return &l2
}
   ```

</details>

<hr/>

## 5. Sum Lists

You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in reverse order, such
that the 1 's digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list. 

Additional: Suppose the digits are stored in forward order. Repeat the above problem.

### Example

```
Input: 
    (7 -> 1 -> 6) + (5 -> 9 -> 2). That is, 617 + 295.
Output: 
    2 -> 1 -> 9. That is, 912. 
    
---
Additional:

Input:
    (6 -> 1 -> 7) + (2 -> 9 -> 5).That is, 617 + 295.
Output: 
    9 -> 1 -> 2. That is, 912. 
```

<details>
<summary>Solution</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(1)`

#### Implementation

   ```go
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
   ```

</details>

<details>
<summary>Alternative Solution with Recursion</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(1)`

#### Implementation

   ```go
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
   ```

</details>

<details>
<summary>Additional Solution with Recursion</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(1)`

#### Implementation

   ```go
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
   ```

</details>

<hr/>

## 6. Palindrome

Implement a function to check if a linked list is a palindrome.

<hr/>

## 7. Intersection

Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting node. Note that the intersection is defined
based on reference, not value. That is, if the `kth` node of the first linked list is the exact same node (by reference) as the `jth` node of
the second linked list, then they are intersecting.

<hr/>

## 8. Loop Detection

Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.

#### Definition

Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so as to make a loop in the linked
list.

### Example

```
Input:
    A -> B -> C -> D -> E -> C [the same C as earlier] 
Output: 
    C
```

<hr/>
