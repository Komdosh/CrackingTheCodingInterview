package three.in.one.fixed;

import java.util.EmptyStackException;

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
