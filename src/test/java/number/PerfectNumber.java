package number;


import org.junit.jupiter.api.*;

/**
 * Perfect Number | Sum of its Divisors
 * 6              | 1 + 2 + 3
 * 28             | 1 + 2 + 4 + 7 + 14
 * 496            | 1 + 2 + 4 + 8 + 16 + 31 + 62 + 124 + 248
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(10)
    public void test_6() {
        long n = 6;
        boolean perfect = isPerfect(n);
        System.out.println(String.format("Is this a perfect number %d :: %s", n, perfect));
        Assertions.assertTrue(perfect);
    }

    @Test
    @Order(20)
    public void test_12() {
        long n = 12;
        boolean perfect = isPerfect(n);
        System.out.println(String.format("Is this a perfect number %d :: %s", n, perfect));
        Assertions.assertFalse(perfect);
    }

    @Test
    @Order(30)
    public void test_496() {
        long n = 496;
        boolean perfect = isPerfect(n);
        System.out.println(String.format("Is this a perfect number %d :: %s", n, perfect));
        Assertions.assertTrue(perfect);
    }

    @Test
    @Order(1000)
    public void test_bigNumber() {
        long n = 137438691328l;
        boolean perfect = isPerfect(n);
        System.out.println(String.format("Is this a perfect number %d :: %s", n, perfect));
        Assertions.assertTrue(perfect);
    }

    private boolean isPerfect_sqRoot(long n) {
        if (n == 1) return false;
        long divisorSum = 1; // 1 is divisor of every number
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
    @Order(20)
    public void test_12_sqRoot() {
        long n = 12;
        boolean perfect = isPerfect_sqRoot(n);
        System.out.println(String.format("Is this a perfect number %d :: %s", n, perfect));
        Assertions.assertFalse(perfect);
    }

    @Test
    @Order(30)
    public void test_496_sqRoot() {
        long n = 496;
        boolean perfect = isPerfect_sqRoot(n);
        System.out.println(String.format("Is this a perfect number %d :: %s", n, perfect));
        Assertions.assertTrue(perfect);
    }

    @Test
    @Order(900)
    public void test_bigNumber_sqRoot() {
        long n = 137438691328l;
        boolean perfect = isPerfect_sqRoot(n);
        System.out.println(String.format("Is this a perfect number %d :: %s", n, perfect));
        Assertions.assertTrue(perfect);
    }

    private long isPerfect_recurse(long n, long i) {
        if (n == i) return 0;
        if (n % i == 0) {
            return i + isPerfect_recurse(n, ++i);
        }
        return isPerfect_recurse(n, ++i);
    }

    @Test
    @Order(20)
    public void test_12_recurse() {
        long n = 12;
        long divisorSum = isPerfect_recurse(n, 1);
        System.out.println(String.format("Is this a perfect number %d :: %s", n, n == divisorSum));
        Assertions.assertFalse(n == divisorSum);
    }

    @Test
    @Order(30)
    public void test_496_recurse() {
        long n = 496;
        long divisorSum = isPerfect_recurse(n, 1);
        System.out.println(String.format("Is this a perfect number %d :: %s", n, n == divisorSum));
        Assertions.assertTrue(n == divisorSum);
    }
}
