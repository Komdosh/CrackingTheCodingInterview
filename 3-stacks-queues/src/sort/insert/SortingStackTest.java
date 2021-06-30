package sort.insert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortingStackTest {

    @Test
    void pushRandom() {
        SortingStack sortingStack = new SortingStack();

        sortingStack.push( 1);
        sortingStack.push( 2);
        sortingStack.push( 3);
        sortingStack.push( 4);
        sortingStack.push( 5);

        assertEquals(1, sortingStack.pop());
        assertEquals(2, sortingStack.pop());
        assertEquals(3, sortingStack.pop());
        assertEquals(4, sortingStack.pop());

        sortingStack.push(1);
        sortingStack.push(2);

        assertEquals(1, sortingStack.pop());
        assertEquals(2, sortingStack.pop());
        assertEquals(5, sortingStack.pop());

    }

    @Test
    void pushRandom2() {

        SortingStack sortingStack = new SortingStack();

        sortingStack.push( 3);
        sortingStack.push( 4);
        sortingStack.push( 5);
        sortingStack.push( 6);
        sortingStack.push( 7);
        sortingStack.push( 1);
        sortingStack.push( 0);

        assertEquals(0, sortingStack.pop());
        assertEquals(1, sortingStack.pop());
        assertEquals(3, sortingStack.pop());
        assertEquals(4, sortingStack.pop());

    }
}
