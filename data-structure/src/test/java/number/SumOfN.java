package number;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class SumOfN {

    private int sumOfN(int num) {
        if (num <= 0) return 0;
        return num + sumOfN(num - 1);
    }

    // Functional Style Recursion
    final Function<Integer, Integer> sumN = i -> i == 0 ? i : i + this.sumN.apply(i - 1);

    private long sumOfNConstTime(long num) {
        return ((1 + num) * num) / 2;
    }

    @Test
    public void testSumOfN() {
        Assertions.assertEquals(55, sumOfN(10));
        Assertions.assertEquals(Integer.valueOf(55), sumN.apply(10));
        Assertions.assertEquals(sumOfN(10), sumOfNConstTime(10));
    }

    @Test
    public void testSumOfNConst() {
        System.out.println(sumOfNConstTime(Integer.MAX_VALUE));
    }
}