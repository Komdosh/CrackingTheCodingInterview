package three.in.one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreeInOneTest {

    @Test
    void naivePushRandom() {
        NaiveThreeInOne<Integer> tio = new NaiveThreeInOne<>(Integer.class, 10);

        tio.pushFirst(0);
        tio.pushSecond(1);
        tio.pushThird(2);
        tio.pushThird(3);
        tio.pushSecond(4);
        tio.pushFirst(5);
        tio.pushThird(6);
        tio.pushSecond(7);
        tio.pushFirst(1);

        assertEquals(1, tio.popFirst());
        assertEquals(7, tio.popSecond());
        assertEquals(6, tio.popThird());
    }
    @Test
    void naivePushFirstThenNext() {
        NaiveThreeInOne<Integer> tio = new NaiveThreeInOne<>(Integer.class, 10);

        tio.pushFirst(0);
        tio.pushFirst(1);
        tio.pushFirst(2);
        tio.pushFirst(3);
        tio.pushFirst(3);
        tio.pushSecond(4);
        tio.pushSecond(5);
        tio.pushSecond(6);
        tio.pushThird(7);
        tio.pushThird(8);

        assertEquals(3, tio.popFirst());
        assertEquals(6, tio.popSecond());
        assertEquals(8, tio.popThird());
    }
}
