package set.of.stack.naive;

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
    void pushRandomMin() {

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
}
