# Linked Lists `Go`

Completed tasks:

![50%](https://progress-bar.dev/50)

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
that the 1 's digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list. Suppose the
digits are stored in forward order. Repeat the above problem.

### Example

```
Input: 
    (7 -> 1 -> 6) + (5 -> 9 -> 2). That is, 617 + 295.
Output: 
    2 -> 1 -> 9. That is, 912. 
    
---

Input:
    (6 -> 1 -> 7) + (2 -> 9 -> 5).That is, 617 + 295.
Output: 
    9 -> 1 -> 2. That is, 912. 
```

<hr/>

## 6. Palindrome

Implement a function to check if a linked list is a palindrome.

<hr/>

## 7. Intersection

Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting node. Note that the intersection is defined
based on reference, not value. That is, if the `kth` node of the first linked list is the exact same node (by reference) as the jth node of
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
