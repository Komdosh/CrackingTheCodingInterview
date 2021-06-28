package set.of.stack.naive;

import java.util.EmptyStackException;
import java.util.Stack;

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
