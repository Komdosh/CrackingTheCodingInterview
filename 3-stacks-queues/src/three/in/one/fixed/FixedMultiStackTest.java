package three.in.one.fixed;

import org.junit.jupiter.api.Test;
import three.in.one.naive.NaiveThreeInOne;
import three.in.one.naive.ThreeInOneStack;

import static org.junit.jupiter.api.Assertions.*;

class FixedMultiStackTest {

    @Test
    void fixedPushRandom() {
        FixedMultiStack tio = new FixedMultiStack(10);

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
}
