package three.in.one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreeInOneTest {

    @Test
    void getSize() {
        ThreeInOne tio = new ThreeInOne();
        assertEquals(0, tio.getSize());
    }
}
