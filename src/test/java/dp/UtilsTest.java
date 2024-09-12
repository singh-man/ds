package dp;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    @Test
    public void testSumOfPrimes_test1() {
        assertEquals(Integer.valueOf(17), Utils.sumOfPrimes(10));
    }

    @Test
    public void testSumOfPrimes_test2() {
        assertEquals(Integer.valueOf(0), Utils.sumOfPrimes(1));
    }

    @Test
    public void testSumOfPrimes_test3() {
        assertEquals(Integer.valueOf(2), Utils.sumOfPrimes(2));
    }

    @Test
    public void testSumOfPrimes_test4() {
        assertEquals(Integer.valueOf(41), Utils.sumOfPrimes(13));
    }

    @Test
    public void testSumOfPrimes_test5() {
        assertEquals(Integer.valueOf(41), Utils.sumOfPrimes(15));
    }

    @Test
    public void testSumOfPrimes_test6() {
        assertEquals("Test of 0 value", 0, String.valueOf(Utils.sumOfPrimes(0)));
    }

    @Test
    public void testSumOfPrimes_test7() {
        assertEquals(Long.valueOf(279209790387276L), Utils.sumOfPrimes(100_000_000));
    }

    @Test
    public void testSumOfPrimes_negativeValues() {
        assertThrows(IllegalArgumentException.class, () -> Utils.sumOfPrimes(Integer.MIN_VALUE), "Test of the minimum integer value.");
        assertThrows(IllegalArgumentException.class, () -> Utils.sumOfPrimes(-1), "Test for negative value.");
    }

    @Test
    public void testToUpperCase_test1() {
        assertEquals("Hello World", Utils.convertToTitleCase("hello world"));
    }

    @Test
    public void testToUpperCase_test2() {
        assertEquals("Another Test Case", Utils.convertToTitleCase("AnoTher teSt CasE"));
    }

    @Test
    public void testToUpperCase_test3() {
        assertEquals("", Utils.convertToTitleCase(null));
    }

    @Test
    public void testToUpperCase_test4() {
        assertEquals("", Utils.convertToTitleCase(""));
    }

    @Test
    public void testToUpperCase_test5() {
        assertEquals("A B C D", Utils.convertToTitleCase("a b c d"));
    }

    @Test
    public void testToUpperCase_test6() {
        assertEquals("Extra Spaces Here", Utils.convertToTitleCase("  extra  spaces   here"));
    }

    @Test
    public void testToUpperCase_test7() {
        assertEquals("Test@Home", Utils.convertToTitleCase("test@home"));
    }

    @Test
    public void testToUpperCase_test8() {
        assertEquals("Hello-World", Utils.convertToTitleCase("hello-world"));
    }

    @Test
    public void testToUpperCase_test9() {
        assertEquals("Java_101 !Special", Utils.convertToTitleCase("java_101 !special"));
    }

    @Test
    public void testMethodSignature_sumOfPrimes() {
        Method method = null;
        try {
            method = Utils.class.getMethod("sumOfPrimes", int.class);
        } catch (NoSuchMethodException e) {
            // Do nothing, methodFound will be false
        }
        assertNotNull("Method \"sumOfPrimes\" has incorrect signature", String.valueOf(method));
        assertEquals(Long.TYPE, method.getReturnType(), "Incorrect return type of method \"sumOfPrimes\"");
    }

    @Test
    public void testMethodSignature_convertToTitleCase() {
        Method method = null;
        try {
            method = Utils.class.getMethod("convertToTitleCase", String.class);
        } catch (NoSuchMethodException e) {
            // Do nothing, methodFound will be false
        }
        assertNotNull("Method \"convertToTitleCase\" has incorrect signature", String.valueOf(method));
        assertEquals(String.class, method.getReturnType(), "Incorrect return type of method \"convertToTitleCase\"");
    }

    @Test
    public void testGroupAnagrams() {

        String[] input1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        Set<Set<String>> groupedAnagrams1 = Utils.groupAnagrams(input1);
        assertGroupAnagrams("", groupedAnagrams1,
                Arrays.asList("eat", "tea", "ate"),
                Arrays.asList("tan", "nat"),
                Arrays.asList("bat"));


        String[] input2 = {"", "", ""};
        Set<Set<String>> groupedAnagrams2 = Utils.groupAnagrams(input2);
        assertGroupAnagrams("All strings are empty (anagrams of each other). ", groupedAnagrams2,
                Arrays.asList("", "", ""));

        String[] input3 = {"abc", "cab", "bca", "def", "fed"};
        Set<Set<String>> groupedAnagrams3 = Utils.groupAnagrams(input3);
        assertGroupAnagrams("Some strings are anagrams, others aren't. ", groupedAnagrams3,
                Arrays.asList("abc", "cab", "bca"),
                Arrays.asList("def", "fed"));


        String[] input4 = {"a", "a", "a", "a", "a"};
        Set<Set<String>> groupedAnagrams4 = Utils.groupAnagrams(input4);
        assertGroupAnagrams("All strings are the same (anagrams of each other). ", groupedAnagrams4,
                Arrays.asList("a", "a", "a", "a", "a"));

        String[] input5 = {"", "eat", "abc", "a"};
        Set<Set<String>> groupedAnagrams5 = Utils.groupAnagrams(input5);
        assertGroupAnagrams("No anagrams. ", groupedAnagrams5,
                Arrays.asList(""),
                Arrays.asList("eat"),
                Arrays.asList("abc"),
                Arrays.asList("a"));
    }

    private void assertGroupAnagrams(String testMessage, Set<Set<String>> actual, List<String>... expectedGroups) {
        Set<Set<String>> actualSet = actual.stream()
                .map(HashSet::new)
                .collect(Collectors.toSet());

        Set<Set<String>> expectedSet = new HashSet<>();
        for (List<String> group : expectedGroups) {
            expectedSet.add(new HashSet<>(group));
        }

        assertEquals(expectedSet, actualSet, testMessage + "The grouped anagrams do not match the expected result");
    }
}
