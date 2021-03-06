package sort.insert;

import java.util.Stack;

public class SortingStack {

    private final Stack<Integer> stack = new Stack<>();

    public void push(int value) {
        if(stack.isEmpty() || stack.peek() > value){
            stack.push(value);
            return;
        }
        final Stack<Integer> tempStack = new Stack<>();
        while(!stack.isEmpty() && stack.peek() < value){
            tempStack.push(stack.pop());
        }
        stack.push(value);
        while (!tempStack.isEmpty()){
            stack.push(tempStack.pop());
        }
    }

    public int size(){
        return stack.size();
    }

    public int pop() {
        return stack.pop();
    }

    public int peek() {
        return stack.peek();
    }
}
