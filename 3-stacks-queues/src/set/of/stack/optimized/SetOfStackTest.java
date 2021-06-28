package set.of.stack.optimized;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetOfStackTest {

    @Test
    void pushRandom() {
        SetOfStack setOfStack = new SetOfStack(3);

        setOfStack.push( 1);
        setOfStack.push( 2);
        setOfStack.push( 3);
        setOfStack.push( 4);
        setOfStack.push( 5);
        setOfStack.push( 6);
        setOfStack.push( 7);
        setOfStack.push( 1);
        setOfStack.push( 0);

        assertEquals(0, setOfStack.pop());
        assertEquals(1, setOfStack.pop());
        assertEquals(7, setOfStack.pop());
        assertEquals(6, setOfStack.pop());

    }

    @Test
    void pushRandom2() {

        SetOfStack setOfStack = new SetOfStack(3);

        setOfStack.push( 3);
        setOfStack.push( 4);
        setOfStack.push( 5);
        setOfStack.push( 6);
        setOfStack.push( 7);
        setOfStack.push( 1);
        setOfStack.push( 0);

        assertEquals(0, setOfStack.pop());
        assertEquals(1, setOfStack.pop());
        assertEquals(7, setOfStack.pop());
        assertEquals(6, setOfStack.pop());

    }

    @Test
    void popAt() {

        SetOfStack setOfStack = new SetOfStack(3);

        setOfStack.push( 3);
        setOfStack.push( 4);
        setOfStack.push( 5);
        setOfStack.push( 6);
        setOfStack.push( 7);
        setOfStack.push( 1);
        setOfStack.push( 0);
        setOfStack.push( 4);
        setOfStack.push( 5);
        setOfStack.push( 1);
        setOfStack.push( 9);

        assertEquals(9, setOfStack.pop());
        assertEquals(5, setOfStack.popAt(2));
        assertEquals(1, setOfStack.popAt(2));
        assertEquals(4, setOfStack.popAt(2));
        assertEquals(0, setOfStack.pop());
        assertEquals(1, setOfStack.pop());
        assertEquals(7, setOfStack.pop());

    }
}
