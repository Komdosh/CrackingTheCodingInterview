# Trees and Graphs `C/C++`

Completed tasks:

![41%](https://progress-bar.xyz/41)

## 1. Route Between Nodes

Given a directed graph, design an algorithm to find out whether there is a route between two nodes.

<details>
<summary>Naive Solution</summary>

#### Complexity

- Time Complexity: `O(|V|+|E|)` - where `|V|` - nodes (vertices), `|E|` - edges
- Space Complexity: `O(|V|)` - where `|V|` - nodes (vertices)

#### Implementation

```cpp
bool search(Node *startNode, Node *finishNode) {
        if(startNode == finishNode){
            return true;
        }

        std::queue<Node *> q;
        std::unordered_set<Node *> visitedNodes;
        std::vector<Node *> *currentConnectedNodes;

        Node *current = startNode;
        while (current != nullptr) {
            if (current == finishNode) {
                return true;
            }

            currentConnectedNodes = &current->connectedNodes;

            for (auto node : *currentConnectedNodes){
                if (!visitedNodes.contains(node)) {
                    q.push(node);
                }
            }

            visitedNodes.insert(currentConnectedNodes->begin(), currentConnectedNodes->end());

            if (!q.empty()) {
                current = q.front();
                q.pop();
            } else {
                current = nullptr;
            }
        }

        return false;
    }
```

</details>

<details>
<summary>Optimized Solution</summary>

#### Complexity

- Time Complexity: `O(|V|+|E|)` - where `|V|` - nodes (vertices), `|E|` - edges
- Space Complexity: `O(|V|)` - where `|V|` - nodes (vertices)

#### Implementation

```cpp
bool search(Node *startNode, Node *finishNode) {
        if (startNode == finishNode) {
            return true;
        }

        std::queue<Node *> q;
        std::unordered_set<Node *> visitedNodes;

        visitedNodes.insert(startNode);
        q.push(startNode);

        Node *current;
        std::vector<Node *> *currentConnectedNodes;

        while (!q.empty()) {
            current = q.front();
            q.pop();

            if (current != nullptr) {
                currentConnectedNodes = &current->connectedNodes;

                for (auto node : *currentConnectedNodes){
                    if (!visitedNodes.contains(node)) {
                        if (node == finishNode) {
                            return true;
                        } else {
                            visitedNodes.insert(node);
                            q.push(node);
                        }
                    }
                }

                visitedNodes.insert(current);
            }
        }

        return false;
    }
```

</details>

<hr/>

## 2. Minimal Tree

Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a binary search tree
with minimal height.

<details>
<summary>Solution</summary>

#### Assumptions

- Array contains no more than 9999 elements, we want to avoid stackoverflow for this recursive algorithm

#### Complexity

- Time Complexity: `O(N)`
- Space Complexity: `O(log N)` - algorithm doesn't use memory for store temporary results, but recursion use call stack.
  Result tree will consume `O(|V|)` memory.

#### Implementation

```cpp
Node *createMinimalBST(int *orderedNumbers, int start, int end, int level) {
        if (level > 9999) {
            throw "Too deep, consider to use stack instead of recursion";
        }

        if (end < start) {
            return nullptr;
        }

        int middleId = (end + start) / 2;

        Node *node = new Node(orderedNumbers[middleId], level);
        Node *left = createMinimalBST(orderedNumbers, start, middleId - 1, level + 1);
        if (left != nullptr) {
            node->connect(left);
        }
        Node *right = createMinimalBST(orderedNumbers, middleId + 1, end, level + 1);
        if (right != nullptr) {
            node->connect(right);
        }
        return node;
    }
```

</details>

<hr/>

## 3. List of Depths

Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth (e.g., if you have a
tree with depth D, you'll have D linked lists).

<details>
<summary>Naive Solution</summary>

#### Complexity

- Time Complexity: `O(N)`
- Space Complexity: `O(N)`

#### Implementation

```cpp
std::vector<std::vector<Node *>> levelsAsList(Tree tree) {
    if (tree.root() == nullptr) {
        return std::vector<std::vector<Node *>>();
    }
    std::queue<Node *> nodes;
    
    std::vector<std::vector<Node *>> levelsList;
    std::vector<Node *> level;
    
    int currentLevelChildren = 1;
    int nextLevelChildren = 0;
    int depth = 0;
    
    levelsList.push_back(std::vector<Node *>());
    tree.breadthFirstTraverse([&](Node *current) {
    --currentLevelChildren;
    
    levelsList.at(depth).push_back(current);
    std::vector<Node *> *currentConnectedNodes = &current->connectedNodes;
    
    nextLevelChildren += currentConnectedNodes->size();
    
    if (currentLevelChildren == 0) {
      ++depth;
      levelsList.push_back(std::vector<Node *>());
      currentLevelChildren = nextLevelChildren;
      nextLevelChildren = 0;
    }
    
     return false;
    });
    
    return levelsList;
}
```

</details>

<details>
<summary>Optimized Solution</summary>

#### Complexity

- Time Complexity: `O(N)`
- Space Complexity: `O(N)`

#### Implementation

```cpp
void createLevelLinkedListDepth(Node *root, std::vector<std::vector<Node *> *> *lists, int level) {
    if (root == nullptr) {
        return;
    }

    if (lists->size() == level) {
        lists->push_back(new std::vector<Node *>());
    }
    auto list = (*lists)[level];

    list->push_back(root);

    for (Node *child: root->connectedNodes) {
        createLevelLinkedListDepth(child, lists, level + 1);
    }
}

std::vector<std::vector<Node *> *> *createLevelLinkedListBreadth(Node *root) {
    std::vector<std::vector<Node *> *> *result = new std::vector<std::vector<Node *> *>();
    std::vector<Node *> *current = new std::vector<Node *>();
    if (root != nullptr) {
        current->push_back(root);
    }

    while (current->size() > 0) {
        result->push_back(current);
        std::vector<Node *> *parents = current;
        current = new std::vector<Node *>();
        for (Node *parent: *parents) {
            for (Node *child: parent->connectedNodes) {
                current->push_back(child);
            }
        }
    }

    return result;
}
```

</details>

<hr/>

## 4. Check Balanced

Implement a function to check if a binary tree is balanced. For the purposes of this question, a balanced tree is
defined to be a tree such that the heights of the two subtrees of any node never differ by more than one.

<details>
<summary>Naive Solution</summary>

#### Complexity

- Time Complexity: `O(N)`
- Space Complexity: `O(log N) - recursion`

#### Implementation

```cpp
int maxDepth = -1; // private member
int secondDepth = 0; // private member

bool isBalanced(Node *current, int level) {
    for (auto c: current->connectedNodes) {
        if (level > maxDepth) {
            if (abs(secondDepth - level) > 1) {
                return false;
            }
            secondDepth = maxDepth;
            maxDepth = level;
        }
        if (!isBalanced(c, level + 1)) {
            return false;
        }
    }

    return true;
};
```

</details>

<details>
<summary>Alternative Naive Solution</summary>

#### Complexity

- Time Complexity: `O(N log N)`
- Space Complexity: `O(N)`

#### Implementation

```cpp
int maxDepth = -1; // private member
int secondDepth = 0; // private member

bool isBalanced(Node *current, int level) {
    for (auto c: current->connectedNodes) {
        if (level > maxDepth) {
            if (abs(secondDepth - level) > 1) {
                return false;
            }
            secondDepth = maxDepth;
            maxDepth = level;
        }
        if (!isBalanced(c, level + 1)) {
            return false;
        }
    }

    return true;
};
```

</details>

<details>
<summary>Optimized Solution</summary>

#### Complexity

- Time Complexity: `O(N)`
- Space Complexity: `O(log N)`

#### Implementation

```cpp
int checkHeight(Node *root){
    if(root == nullptr) return -1;
    int max = -1;
    int min = INT_MAX;
    for (auto c: root->connectedNodes) {
        int value = checkHeight(c);
        if(value == INT_MIN){
            return INT_MIN;
        }
        if (value > max) {
            max = value;
        }
        if (value < min) {
            min = value;
        }
    }
    if(abs(max - min)>1){
        return INT_MIN;
    }
    return max + 1;
}

bool isBalanced(Node *root){
    return checkHeight(root) != INT_MIN;
}
```

</details>

<hr/>

## 5. Validate BST

Implement a function to check if a binary tree is a binary search tree.

<details>
<summary>Notes</summary>

We have some weird cases with duplicates

```
7         9
 \       /
  10    8
 /       \
7         9
```

On the one hand, this tree is BST because every left value is lower or equal with parent value and right values are
bigger than the parent value. On the other hand, the right subtree can't hold a value smaller or equal to the parent. It fit
Binary Search Tree conditions. For example, `checkBSTMinMax` implementation can not detect this case.

Moreover, in the book, I have found a mistake in `checkBST`.

Book code:

`if (lastValue != nullptr && n->getId() <= (*lastValue))`

My code:

`if (lastValue != nullptr && (*lastValue) > n->getId())`

Why? - With this compare `n->getId() <= (*lastValue)`, we think that left values can't be equal to parent, but it is
wrong. I think that this mistake was admitted because of the wrong position of values, if we move `lastValue` to the left of
comparison, then it will be clear.

</details>

<br/>

<details>
<summary>Naive Solution</summary>

#### Complexity

- Time Complexity: `O(N)`
- Space Complexity: `O(log N) - recursion`

#### Implementation

```cpp
bool isBinarySearchTree(BinaryTreeNode *node) {
    if (node == nullptr) {
        return true;
    }
    if (node->left() != nullptr) {
        if (node->left()->getId() > node->getId()) {
            return false;
        }
    }
    if (node->right() != nullptr) {
        if (node->right()->getId() <= node->getId()) {
            return false;
        }
    }
    bool good = isBinarySearchTree(node->left());
    if (!good) {
        return false;
    }
    good = isBinarySearchTree(node->right());
    return good;
}
```

</details>

<details>
<summary>Optimized Solution</summary>

#### Complexity

- Time Complexity: `O(N)`
- Space Complexity: `O(log N) - recursion`

#### Implementation

```cpp
// pretty close to mine
bool checkBST(BinaryTreeNode *n) {
  if (n == nullptr) return true;
  
  if (!checkBST(n->left())) {
    return false;
  }
  if (lastValue != nullptr && (*lastValue) > n->getId()) {
    return false;
  }
  int data = n->getId();
  
  lastValue = &data;
  
  if (!checkBST(n->right())) {
    return false;
  }
  return true;
}
```

</details>

<details>
<summary>Alternative Optimized Solution</summary>

#### Complexity

- Time Complexity: `O(N)`
- Space Complexity: `O(log N) - recursion`

#### Implementation

```cpp
bool checkBSTMinMax(BinaryTreeNode *n) {
    return checkBSTMinMax(n, nullptr, nullptr);
}

bool checkBSTMinMax(BinaryTreeNode *n, int *min, int *max) {
   if (n == nullptr) {
       return true;
   }
   if ((min != nullptr && n->getId() <= (*min)) || (max != nullptr && n->getId() > (*max))) {
       return false;
   }
   int data = n->getId();
   if (!checkBSTMinMax(n->left(), min, &data) || !checkBSTMinMax(n->right(), &data, max)) {
       return false;
   }
   return true;
}
```

</details>

<hr/>

## 6. Successor

Write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a binary search tree. You may
assume that each node has a link to its parent.

<details>
<summary>Solution</summary>

#### Complexity

- Time Complexity: `O(N) - worst case that right subtree contains almost all elements`
- Space Complexity: `O(1)`

#### Implementation

```cpp
Node *inorderSuccessor(BiDirectedBinaryTreeNode *n) {
    if (n == nullptr) {
        return nullptr;
    }
    
    if (n->right() != nullptr) {
        return leftMostChild(n);
    } 
    
    BiDirectedBinaryTreeNode *q = n;
    BiDirectedBinaryTreeNode *x = n->parent;
    while (x != nullptr && x->left() != q) {
        q = x;
        x = x->parent;
    }
    return x;
}

BiDirectedBinaryTreeNode *leftMostChild(BiDirectedBinaryTreeNode *node) {
    if (node == nullptr) {
        return nullptr;
    }
    auto current = node;
    while (current->left() != nullptr) {
        current = current->left();
    }
    return current;
}
```
</details>

<hr/>

## 7. Build Order

You are given a list of projects and a list of dependencies (which is a list of pairs of projects, where the second
project is dependent on the first project). All of a project's dependencies must be built before the project is. Find a
build order that will allow the projects to be built. If there is no valid build order, return an error.

### Example

```
Input:
    projects: a, b, c, d, e, f
    dependencies: (a, d), (f, b), (b, d), (f, a), (d, c) 
Output: 
    f, e, a, b, d, c
```

<hr/>

## 8. First Common Ancestor

Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree. Avoid storing
additional nodes in a data structure. NOTE: This is not necessarily a binary search tree.

<hr/>

## 9. BST Sequences

A binary search tree was created by traversing through an array from left to right and inserting each element. Given a
binary search tree with distinct elements, print all possible arrays that could have led to this tree.

### Example

```
Input:
    Binary search tree where root 2, left node 1, right node 3
    
Output:
     {2, 1, 3}, {2, 3, 1} 
```

<hr/>

## 10. Check Subtree

T1 and T2 are two very large binary trees, with T1 much bigger than T2. Create an algorithm to determine if T2 is a
subtree of T1. A tree T2 is a subtree of T1 if there exists a node n in Tl such that the subtree of n is identical to
T2. That is, if you cut off the tree at node n, the two trees would be identical.

<hr/>

## 11. Random Node

You are implementing a binary tree class from scratch which, in addition to insert, find, and delete, has a method
getRandomNode() which returns a random node from the tree. All nodes should be equally likely to be chosen. Design and
implement an algorithm for getRandomNode, and explain how you would implement the rest of the methods.

<hr/>

## 12. Paths with Sum

You are given a binary tree in which each node contains an integer value (which might be positive or negative). Design
an algorithm to count the number of paths that sum to a given value. The path does not need to start or end at the root
or a leaf, but it must go downwards
(traveling only from parent nodes to child nodes).
<hr/>
