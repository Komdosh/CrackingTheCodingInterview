package three.in.one.naive;

import java.lang.reflect.Array;

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

    private T peek(StackPointer sp){
        if (sp.end < 0) {
            throw new IllegalStateException("Stack is empty");
        }
        int index = sp.end;
        return this.arr[index - 1];
    }

    private T pop(StackPointer sp){
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
