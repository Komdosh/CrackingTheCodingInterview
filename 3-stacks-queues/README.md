# Stacks and Queues `Java`

Completed tasks:

![100%](https://progress-bar.dev/100)

## 1. Three in One

Describe how you could use a single array to implement three stacks.

<details>
<summary>Naive Solution</summary>

#### Assumptions

- Stacks with fixed size

#### Complexity

- Time Complexity:
    - Insert: `O(1)`
    - Delete: `O(1)`
    - Peek: `O(1)`
- Space Complexity: `O(1)`

#### Implementation

   ```java
public class NaiveThreeInOne<T> implements ThreeInOneStack<T> {

    private final T[] arr;
    private final StackPointer first = new StackPointer();
    private final StackPointer second = new StackPointer();
    private final StackPointer third = new StackPointer();
    private final int capacity;
    private int size = 0;

    public NaiveThreeInOne(Class<T> clazz, int size) {
        arr = (T[]) Array.newInstance(clazz, size);
        this.capacity = size;
    }

    public int getFirstSize() {
        return first.getSize();
    }

    public int getSecondSize() {
        return second.getSize();
    }

    public int getThirdSize() {
        return third.getSize();
    }

    @Override
    public T pushFirst(T item) {
        if (size + 1 > capacity) {
            throw new IllegalStateException("Exceed max size");
        }
        if (!first.isInitiated()) {
            first.init(0);
        }

        size++;

        if (first.end < capacity || second.end == -1) {
            return addItem(item, first);
        } else {
            throw new IllegalStateException("Exceed max size");
        }
    }

    @Override
    public T popFirst() {
        return pop(first);
    }

    @Override
    public T peekFirst() {
        return peek(first);
    }

    @Override
    public T pushSecond(T item) {
        if (size + 1 > capacity) {
            throw new IllegalStateException("Exceed max size");
        }
        if (!second.isInitiated()) {
            second.init(Math.max(first.end, (capacity / 3)));
        }
        size++;
        if (second.end < third.end || third.end == -1) {
            return addItem(item, second);
        } else {
            throw new IllegalStateException("Exceed max size");
        }
    }

    @Override
    public T popSecond() {
        return pop(second);
    }

    @Override
    public T peekSecond() {
        return peek(second);
    }

    @Override
    public T pushThird(T item) {
        if (size + 1 > capacity) {
            throw new IllegalStateException("Exceed max size");
        }
        if (!third.isInitiated()) {
            third.init(Math.max(second.end, (capacity / 3) * 2));
        }
        size++;
        if (third.end < capacity) {
            return addItem(item, third);
        } else {
            throw new IllegalStateException("Exceed max size");
        }
    }

    @Override
    public T popThird() {
        return pop(third);
    }

    @Override
    public T peekThird() {
        return peek(third);
    }

    private T peek(StackPointer sp) {
        if (sp.end < 0) {
            throw new IllegalStateException("Stack is empty");
        }
        int index = sp.end;
        return this.arr[index - 1];
    }

    private T pop(StackPointer sp) {
        if (sp.end < 0) {
            throw new IllegalStateException("Stack is empty");
        }
        size--;
        return this.arr[--sp.end];
    }

    private T addItem(T item, StackPointer sp) {
        this.arr[sp.end] = item;
        ++sp.end;
        return this.arr[sp.end - 1];
    }
}
   ```

</details>

<details>
<summary>Alternative Naive Solution</summary>

#### Assumptions

- Stacks with fixed size

#### Complexity

- Time Complexity:
    - Insert: `O(1)`
    - Delete: `O(1)`
    - Peek: `O(1)`
- Space Complexity: `O(1)`

#### Implementation

   ```java
public class FixedMultiStack {
    private final int numberOfStacks = 3;
    private final int stackCapacity;
    private final int[] values;
    private final int[] sizes;

    public FixedMultiStack(int stackSize) {
        stackCapacity = stackSize;
        values = new int[stackSize * numberOfStacks];
        sizes = new int[numberOfStacks];
    }

    public void push(int stackNum, int value) throws IllegalStateException {
        if (isFull(stackNum)) {
            throw new IllegalStateException("Exceed max size");
        }

        sizes[stackNum]++;
        values[indexOfTop(stackNum)] = value;
    }

    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException();
        }

        int topIndex = indexOfTop(stackNum);
        int value = values[topIndex];
        values[topIndex] = 0;
        sizes[stackNum]--; // Shrink
        return value;
    }

    public int peek(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException();
        }
        return values[indexOfTop(stackNum)];
    }

    public boolean isEmpty(int stackNum) {
        return sizes[stackNum] == 0;
    }

    public boolean isFull(int stackNum) {
        return sizes[stackNum] == stackCapacity;
    }

    private int indexOfTop(int stackNum) {
        int offset = stackNum * stackCapacity;
        int size = sizes[stackNum];
        return offset + size - 1;
    }
}
   ```

</details>

<details>
<summary>Optimized Solution</summary>

#### Complexity

- Time Complexity:
    - Insert: `O(N)` - in case of shift
    - Delete: `O(1)`
    - Peek: `O(1)`
- Space Complexity: `O(1)`

#### Implementation

   ```java
public class MultiStack {
    private final int[] values;
    private final StackInfo[] info;

    public MultiStack(int numberOfStacks, int defaultSize) {
        info = new StackInfo[numberOfStacks];
        for (int i = 0; i < numberOfStacks; ++i) {
            info[i] = new StackInfo(defaultSize * i, defaultSize);
        }
        values = new int[numberOfStacks * defaultSize];
    }

    public void push(int stackNum, int value) {
        if (allStacksAreFull()) {
            throw new IllegalStateException("Exceed max size");
        }

        StackInfo stack = info[stackNum];
        if (stack.isFull()) {
            expand(stackNum);
        }

        ++stack.size;
        values[stack.lastElementIndex()] = value;
    }

    public int pop(int stackNum) {
        StackInfo stack = info[stackNum];
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }

        int value = values[stack.lastElementIndex()];
        values[stack.lastElementIndex()] = 0;
        --stack.size;
        return value;
    }

    public int peek(int stackNum) {
        StackInfo stack = info[stackNum];
        return values[stack.lastElementIndex()];
    }

    private boolean allStacksAreFull() {
        return numberOfElements() == values.length;
    }

    private int numberOfElements() {
        int size = 0;
        for (StackInfo sd : info) {
            size += sd.size;
        }
        return size;
    }

    private int nextIndex(int index) {
        return adjustIndex(index + 1);
    }

    private int previousIndex(int index) {
        return adjustIndex(index - 1);
    }

    private void expand(int stackNum) {
        shift((stackNum + 1) % info.length);
        ++info[stackNum].capacity;
    }

    private void shift(int stackNum) {
        StackInfo stack = info[stackNum];
        if (stack.size >= stack.capacity) {
            int nextStack = (stackNum + 1) % info.length;
            shift(nextStack);
            ++stack.capacity;
        }

        int index = stack.lastCapacity();
        while (stack.isWithinStackCapacity(index)) {
            values[index] = values[previousIndex(index)];
            index = previousIndex(index);
        }

        values[stack.start] = 0;
        stack.start = nextIndex(stack.start);
        --stack.capacity;
    }

    private int adjustIndex(int index) {
        int max = values.length;
        return ((index % max) + max) % max;
    }

    private class StackInfo {
        public int start, size, capacity;

        public StackInfo(int start, int capacity) {
            this.start = start;
            this.capacity = capacity;
        }

        public boolean isWithinStackCapacity(int index) {
            if (index < 0 || index >= values.length) {
                return false;
            }
            int contiguousIndex = index < start ? index + values.length : index;
            int end = start + capacity;
            return start <= contiguousIndex && contiguousIndex < end;
        }

        public int lastCapacity() {
            return adjustIndex(start + capacity - 1);
        }

        public int lastElementIndex() {
            return adjustIndex(start + size - 1);
        }

        public boolean isFull() {
            return size == capacity;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }
}
   ```

</details>

<hr/>

## 2. Stack Min

How would you design a stack which, in addition to push and pop, has a function min which returns the minimum element?
Push, pop and min should all operate in 0(1) time.

<details>
<summary>Naive Solution</summary>

#### Complexity

- Time Complexity: `O(1)`

- Space Complexity: `O(N)`

#### Implementation

   ```java
public class StackMin extends Stack<StackMin.NodeWithMin> {

    public void push(int value) {

        super.push(new NodeWithMin(value, Math.min(value, min())));

    }

    public int popValue() {
        return super.pop().value;
    }

    public int min() {
        if (isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return peek().min;
        }
    }

    public static class NodeWithMin {
        public int value;
        public int min;

        public NodeWithMin(int value, int min) {
            this.value = value;
            this.min = min;
        }
    }

}
   ```

</details>

<details>
<summary>Optimized Solution</summary>

This method is better because it stores smaller number of min elements.

#### Complexity

- Time Complexity: `O(1)`

- Space Complexity: `O(N)`

#### Implementation

   ```java
public class StackMin extends Stack<Integer> {

    private final Stack<Integer> min = new Stack<>();

    public void push(int value) {
        super.push(value);
        if (value <= min()) {
            min.push(value);
        }
    }

    @Override
    public synchronized Integer pop() {
        int value = super.pop();
        if (value == min()) {
            min.pop();
        }
        return value;
    }

    public int min() {
        if (min.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return min.peek();
        }
    }
}
   ```

</details>

<hr/>

## 3. Stack of Plates

Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore, in real life, we would
likely start a new stack when the previous stack exceeds some threshold. Implement a data structure `SetOfStacks` that
mimics this. `SetOfStacks` should be composed of several stacks and should create a new stack once the previous one
exceeds capacity. `SetOfStacks.push()` and `SetOfStacks.pop()`
should behave identically to a single stack (that is, `pop()` should return the same values as it would if there were
just a single stack).

Additional: Implement a function `popAt(int index)` which performs a pop operation on a specific sub-stack.

<details>
<summary>Naive Solution</summary>

#### Assumptions

No need to implement `popAt` method

#### Complexity

- Time Complexity: `O(1)`

- Space Complexity: `O(N)`

#### Implementation

   ```java
public class SetOfStack {

    private final int capacity;
    private final Stack<Stack<Integer>> stacks = new Stack<>();

    public SetOfStack(int capacity) {
        this.capacity = capacity;
    }

    public void push(int value) {
        if (stacks.empty() || stacks.peek().size() < capacity) {
            stacks.push(new Stack<>());
        }
        stacks.peek().push(value);
    }

    public int pop() {
        if (stacks.empty()) {
            throw new EmptyStackException();
        }
        if (stacks.peek().empty()) {
            stacks.pop();
        }
        if (stacks.empty()) {
            throw new EmptyStackException();
        }
        return stacks.peek().pop();
    }
}

   ```

</details>

<details>
<summary>Optimized Solution</summary>

#### Complexity

- Time Complexity: `O(1)`; `popAt`: `O(N)`

- Space Complexity: `O(N)`

#### Implementation

   ```java
public class SetOfStack {

    private final ArrayList<Stack> stacks = new ArrayList<>();
    private final int capacity;

    public SetOfStack(int capacity) {
        this.capacity = capacity;
    }

    public Stack getLastStack() {
        if (stacks.size() == 0) {
            return null;
        }
        return stacks.get(stacks.size() - 1);
    }

    public void push(int v) {
        Stack last = getLastStack();
        if (last != null && !last.isFull()) {
            last.push(v);
        } else {
            Stack stack = new Stack(capacity);
            stack.push(v);
            stacks.add(stack);
        }
    }

    public int pop() {
        Stack last = getLastStack();
        if (last == null) {
            throw new EmptyStackException();
        }
        int v = last.pop();
        if (last.size == 0) {
            stacks.remove(stacks.size() - 1);
        }
        return v;
    }

    public int popAt(int index) {
        return leftShift(index, true);
    }

    public int leftShift(int index, boolean removeTop) {
        Stack stack = stacks.get(index);
        int removedItem;
        if (removeTop) removedItem = stack.pop();
        else removedItem = stack.removeBottom();
        if (stack.isEmpty()) {
            stacks.remove(index);
        } else if (stacks.size() > index + 1) {
            int v = leftShift(index + 1, false);
            stack.push(v);
        }
        return removedItem;
    }

    public boolean isEmpty() {
        Stack last = getLastStack();
        return last == null || last.isEmpty();
    }
}
   ```

</details>

<hr/>

## 4. Queue via Stacks

Implement a MyQueue class which implements a queue using two stacks.

<details>
<summary>The Only Solution</summary>

#### Complexity

- Time Complexity: `O(1)`

- Space Complexity: `O(N)`

#### Implementation

   ```java
public class MyQueue {

    private final Stack<Integer> oldest = new Stack<>();
    private final Stack<Integer> newest = new Stack<>();

    public void push(int value) {
        newest.push(value);
    }

    public int size() {
        return newest.size() + oldest.size();
    }

    public void add(int value) {
        newest.push(value);
    }

    private void shiftStacks() {
        if (oldest.empty()) {
            while (!newest.empty()) {
                oldest.push(newest.pop());
            }
        }
    }

    public int pop() {
        shiftStacks();
        return oldest.pop();
    }

    public int peek() {
        shiftStacks();
        return oldest.peek();
    }
}
   ```

</details>

<hr/>

## 5. Sort Stack

Write a program to sort a stack such that the smallest items are on the top. You can use an additional temporary stack,
but you may not copy the elements into any other data structure (such as an array). The stack supports the following
operations: `push`, `pop`, `peek`, and `isEmpty`.

<details>
<summary>Optimized Solution</summary>

#### Complexity

- Time Complexity: `O(N^2)`

- Space Complexity: `O(N)`

#### Implementation

   ```java
public class SortingStack {

    private Stack<Integer> stack = new Stack<>();

    public void push(int value) {
        stack.push(value);
    }

    public void sort() {
        final Stack<Integer> tempStack = new Stack<>();

        while (!stack.isEmpty()) {
            int tmp = stack.pop();
            while (!tempStack.isEmpty() && tempStack.peek() < tmp) {
                stack.push(tempStack.pop());
            }
            tempStack.push(tmp);
        }
        stack = tempStack;
    }

    public int size() {
        return stack.size();
    }

    public int pop() {
        return stack.pop();
    }

    public int peek() {
        return stack.peek();
    }
}
   ```

</details>

<details>
<summary>Ordered Stack Solution</summary>

#### Complexity

- Time Complexity: `O(N)`

- Space Complexity: `O(N)`

#### Implementation

   ```java
public class SortingStack {

    private final Stack<Integer> stack = new Stack<>();

    public void push(int value) {
        if (stack.isEmpty() || stack.peek() > value) {
            stack.push(value);
            return;
        }
        final Stack<Integer> tempStack = new Stack<>();
        while (!stack.isEmpty() && stack.peek() < value) {
            tempStack.push(stack.pop());
        }
        stack.push(value);
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }

    public int size() {
        return stack.size();
    }

    public int pop() {
        return stack.pop();
    }

    public int peek() {
        return stack.peek();
    }
}
   ```

</details>
<hr/>

## 6. Animal Shelter

An animal shelter, which holds only dogs and cats, operates on a strictly "first in, first out" basis. People must adopt
either the "oldest" (based on arrival time) of all animals at the shelter, or they can select whether they would prefer
a dog or a cat (and will receive the oldest animal of that type). They cannot select which specific animal they would
like. Create the data structures to maintain this system and implement operations such as enqueue, dequeueAny,
dequeueDog, and dequeueCat. You may use the built-in `LinkedList` data structure.

<details>
<summary>Naive Solution</summary>

#### Assumptions

Age of animal is set by user. If there will be insert order only, insertion time complexity will be `O(1)`.

#### Complexity

- Time Complexity:
    - Insert: `O(N)`
    - Delete: `O(N)`

- Space Complexity: `O(N)`

#### Implementation

   ```java
public class AnimalShelter {

    enum AnimalType {
        Cat, Dog
    }

    static class Animal {
        AnimalType type;

        int age = 0;

        public Animal(AnimalType type, int age) {
            this.type = type;
            this.age = age;
        }
    }


    private final LinkedList<Animal> animals = new LinkedList<>();

    public void enqueue(Animal animal) {

        Optional<Animal> animalToIndex = animals.stream().filter(a -> animal.age > a.age).findFirst();

        if (animalToIndex.isEmpty()) {
            animals.addLast(animal);
            return;
        }

        animals.add(animals.indexOf(animalToIndex.get()), animal);
    }

    public Animal dequeueAny() {
        return animals.pollFirst();
    }

    public Animal dequeueDog() {
        return dequeueAnimal(AnimalType.Dog);
    }

    private Animal dequeueAnimal(AnimalType animal) {
        Optional<Animal> optionalAnimal = animals.stream().filter(i -> i.type == animal).findFirst();
        if (optionalAnimal.isPresent()) {
            Animal a = optionalAnimal.get();
            animals.remove(a);
            return a;
        } else {
            return null;
        }
    }

    public Animal dequeueCat() {
        return dequeueAnimal(AnimalType.Cat);
    }
}
   ```

</details>

<details>
<summary>Optimized Solution</summary>

#### Complexity

- Time Complexity:
    - Insert: `O(1)`
    - Delete: `O(1)`

- Space Complexity: `O(N)`

#### Implementation

   ```java
public class AnimalShelter {
    static sealed class Animal permits AnimalShelter.Cat, AnimalShelter.Dog {
        private int order;

        public void setOrder(int ord) {
            order = ord;
        }

        public int getOrder() {
            return order;
        }

        public boolean isOlderThan(animals.optimized.naive.Animal a) {
            return this.order < a.getOrder();
        }
    }

    static final class Cat extends Animal {

    }

    static final class Dog extends Animal {
    }


    LinkedList<Dog> dogs = new LinkedList<>();
    LinkedList<Cat> cats = new LinkedList<>();
    private int order = 0;

    public void enqueue(Animal a) {
        a.setOrder(order);
        order++;
        if (a instanceof Dog d) {
            dogs.addLast(d);
        } else if (a instanceof Cat c) {
            cats.addLast(c);
        }
    }

    public Animal dequeueAny() {
        if (dogs.size() == 0) {
            return dequeueCat();
        } else if (cats.size() == 0) {
            return dequeueDog();
        }
        Dog dog = dogs.peek();
        Cat cat = cats.peek();
        if (dog.isOlderThan(cat)) {
            return dogs.poll();
        } else {
            return cats.poll();
        }
    }

    public Animal peek() {
        if (dogs.size() == 0) {
            return cats.peek();
        } else if (cats.size() == 0) {
            return dogs.peek();
        }
        Dog dog = dogs.peek();
        Cat cat = cats.peek();
        if (dog.isOlderThan(cat)) {
            return dog;
        } else {
            return cat;
        }
    }

    public int size() {
        return dogs.size() + cats.size();
    }

    public Dog dequeueDog() {
        return dogs.poll();
    }

    public Dog peekDogs() {
        return dogs.peek();
    }

    public Cat dequeueCat() {
        return cats.poll();
    }

    public Cat peekCats() {
        return cats.peek();
    }
}
   ```

</details>

<hr/>
