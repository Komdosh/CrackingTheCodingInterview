package stack.min.naive;

import java.util.Stack;

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
