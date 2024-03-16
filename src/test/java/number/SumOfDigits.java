package number;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SumOfDigits {

    private int sumOfAllDigits(int num) {
        if (num == 0) return num;
        return num % 10 + sumOfAllDigits(num / 10);
    }

    @Test
    public void testMultiply() {
        Assertions.assertEquals(10, sumOfAllDigits(1234));
        Assertions.assertEquals(23, sumOfAllDigits(1598));
        Assertions.assertNotEquals(21, sumOfAllDigits(1598));
    }

}