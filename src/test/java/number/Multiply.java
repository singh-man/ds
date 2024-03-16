package number;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Multiply {

    private int multiply(int x, int y) {
        if (x <= 0) return x;
        return multiply(x - 1, y) + y;
    }

    @Test
    public void testMultiply() {
        Assertions.assertEquals(6, multiply(2, 3));
        Assertions.assertEquals(27, multiply(9, 3));
    }

}