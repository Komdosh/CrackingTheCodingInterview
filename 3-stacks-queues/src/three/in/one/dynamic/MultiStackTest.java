package three.in.one.dynamic;

import org.junit.jupiter.api.Test;
import three.in.one.fixed.FixedMultiStack;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiStackTest {

    @Test
    void pushRandom() {
        MultiStack tio = new MultiStack(10, 4);

        tio.push(0, 0);
        tio.push(1, 1);
        tio.push(2, 2);
        tio.push(2, 3);
        tio.push(1, 4);
        tio.push(0, 5);
        tio.push(2, 6);
        tio.push(1, 7);
        tio.push(0, 1);

        assertEquals(1, tio.pop(0));
        assertEquals(7, tio.pop(1));
        assertEquals(6, tio.pop(2));
    }

    @Test
    void pushFirstThen() {
        MultiStack tio = new MultiStack(10, 4);

        tio.push(0, 0);
        tio.push(0, 1);
        tio.push(0, 2);
        tio.push(0, 3);
        tio.push(1, 4);
        tio.push(1, 5);
        tio.push(1, 6);
        tio.push(2, 7);
        tio.push(2, 8);

        assertEquals(3, tio.pop(0));
        assertEquals(6, tio.pop(1));
        assertEquals(8, tio.pop(2));
    }
}
