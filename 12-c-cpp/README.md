# C and C++

Completed tasks:

![0%](https://progress-bar.dev/0)

## 1. Last K Lines

Write a method to print the last K lines of an input file using C++.

<hr/>

## 2. Reverse String

Implement a function `void reverse( char* str)` in C or C++ which reverses a null-terminated string.

<hr/>

## 3. Hash Table vs. STL Map

Compare and contrast a hash table and an STL map. How is a hash table implemented? If the number of inputs is small, which data structure
options can be used instead of a hash table?

<hr/>

## 4. Virtual Functions

How do virtual functions work in C++?

<hr/>

## 5. Shallow vs. Deep Copy

What is the difference between deep copy and shallow copy? Explain how you would use each.

<hr/>

## 6. Volatile

What is the significance of the keyword "volatile" in C?

<hr/>

## 7. Virtual Base Class

Why does a destructor in base class need to be declared virtual?

<hr/>

## 8. Copy Node

Write a method that takes a pointer to a Node structure as a parameter and returns a complete copy of the passed in data structure. The Node
data structure contains two pointers to other Nodes.

<hr/>

## 9. Smart Pointer

Write a smart pointer class. A smart pointer is a data type, usually implemented with templates, that simulates a pointer while also
providing automatic garbage collection. It automatically counts the number of references to a SmartPointer<T*> object and frees the object
of type T when the reference count hits zero.

<hr/>

## 10. Malloc

Write an aligned malloc and free function that supports allocating memory such that the memory address returned is divisible by a specific
power of two.

#### Example

```
align_malloc(1000, 128) - will return a memory address that is a multiple of 128 and that points
to memory of size 1000 bytes. 

aligned_ free() - will free memory allocated by align_malloc.
```

<hr/>

## 11. 2D Alloc

Write a function in C called `my2DAlloc` which allocates a two-dimensional array. Minimize the number of calls to malloc and make sure that
the memory is accessible by the notation `arr[i][j]`.

<hr/>
