package sort.optimized;

import java.util.Stack;

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
