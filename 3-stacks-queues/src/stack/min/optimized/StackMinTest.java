package stack.min.optimized;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StackMinTest {

    @Test
    void pushRandom() {
        StackMin stackMin = new StackMin();

        stackMin.push( 1);
        stackMin.push( 2);
        stackMin.push( 3);
        stackMin.push( 4);
        stackMin.push( 5);
        stackMin.push( 6);
        stackMin.push( 7);
        stackMin.push( 1);
        stackMin.push( 0);

        assertEquals(0, stackMin.pop());
        assertEquals(1, stackMin.min());
        assertEquals(1, stackMin.pop());
        assertEquals(1, stackMin.min());
        assertEquals(7, stackMin.pop());
        assertEquals(6, stackMin.pop());
    }

    @Test
    void pushRandomMin() {
        StackMin stackMin = new StackMin();

        stackMin.push( 3);
        stackMin.push( 4);
        stackMin.push( 5);
        stackMin.push( 6);
        stackMin.push( 7);
        stackMin.push( 1);
        stackMin.push( 0);

        assertEquals(0, stackMin.pop());
        assertEquals(1, stackMin.min());
        assertEquals(1, stackMin.pop());
        assertEquals(3, stackMin.min());
        assertEquals(7, stackMin.pop());
        assertEquals(6, stackMin.pop());
    }
}
