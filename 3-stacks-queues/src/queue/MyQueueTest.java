package queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyQueueTest {

    @Test
    void pushRandom() {
        MyQueue myQueue = new MyQueue();

        myQueue.push( 1);
        myQueue.push( 2);
        myQueue.push( 3);
        myQueue.push( 4);
        myQueue.push( 5);

        assertEquals(1, myQueue.pop());
        assertEquals(2, myQueue.pop());
        assertEquals(3, myQueue.pop());
        assertEquals(4, myQueue.pop());

        myQueue.push(1);
        myQueue.push(2);

        assertEquals(5, myQueue.pop());
        assertEquals(1, myQueue.pop());

    }

    @Test
    void pushRandom2() {

        MyQueue myQueue = new MyQueue();

        myQueue.push( 3);
        myQueue.push( 4);
        myQueue.push( 5);
        myQueue.push( 6);
        myQueue.push( 7);
        myQueue.push( 1);
        myQueue.push( 0);

        assertEquals(3, myQueue.pop());
        assertEquals(4, myQueue.pop());
        assertEquals(5, myQueue.pop());
        assertEquals(6, myQueue.pop());

    }
}
