package stack.min.optimized;

import java.util.Stack;

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
        if(value == min()){
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
