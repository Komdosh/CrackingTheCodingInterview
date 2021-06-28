package queue;

import java.util.EmptyStackException;
import java.util.Stack;

public class MyQueue {

    private final Stack<Integer> oldest = new Stack<>();
    private final Stack<Integer> newest = new Stack<>();

    public void push(int value) {
        newest.push(value);
    }

    public int size(){
        return newest.size() + oldest.size();
    }

    public void add(int value){
        newest.push(value);
    }

    private void shiftStacks(){
        if(oldest.empty()) {
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
