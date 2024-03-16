package number;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

/**
 * Perfect Number | Sum of its Divisors
 * 6              | 1 + 2 + 3
 * 28             | 1 + 2 + 4 + 7 + 14
 * 496            | 1 + 2 + 4 + 8 + 16 + 31 + 62 + 124 + 248
 */
public class PerfectNumber {

    private boolean isPerfect(long n) {
        if (n == 1) return false;
        long divisorSum = 1; // 1 is divisor of every number
        for (long i = 2; i < n; i++) {
            if (n % i == 0) {
                divisorSum += i;
            }
        }
        return divisorSum == n;
    }

    @Test
    public void test_6() {
        long n = 6;
        System.out.println(String.format("Is this a perfect number %d :: %s", n, isPerfect(n)));
    }

    @Test
    public void test_12() {
        long n = 12;
        System.out.println(String.format("Is this a perfect number %d :: %s", n, isPerfect(n)));
    }

    @Test
    public void test_496() {
        long n = 496;
        System.out.println(String.format("Is this a perfect number %d :: %s", n, isPerfect(n)));
    }

    @Test
    @Order(Integer.MAX_VALUE)
    public void test_33550336() {
        long n = 137438691328l;
        System.out.println(String.format("Is this a perfect number %d :: %s", n, isPerfect(n)));
    }

    private boolean isPerfect_sqRoot(long n) {
        if (n == 1) return false;
        int divisorSum = 1; // 1 is divisor of every number
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                // n is a perfect square
                // let's take 25
                // we need to add 5 only once
                // divisorSum += i + n / i will add it twice
                if (i * i == n) divisorSum += i;
                else divisorSum += i + (n / i);
            }
        }
        return divisorSum == n;
    }

    @Test
    public void test_12_sqRoot() {
        long n = 12;
        System.out.println(String.format("Is this a perfect number %d :: %s", n, isPerfect_sqRoot(n)));
    }

    @Test
    @Order(9999)
    public void test_33550336_sqRoot() {
        long n = 137438691328l;
        System.out.println(String.format("Is this a perfect number %d :: %s", n, isPerfect_sqRoot(n)));
    }
}
