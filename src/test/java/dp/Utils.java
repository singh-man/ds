package dp;

import java.util.*;

public class Utils {

    // Generic types should be replaced with appropriate types for method type and method parameter types. (String[], String, int, etc.).

    public static <T,U> T sumOfPrimes(U input) {
        if (!(input instanceof Integer)) {
            throw new IllegalArgumentException("Input must be an integer.");
        }

        int n = (Integer) input;
        return (T) (Integer) calculateSumOfPrimes(n);
    }

    private static int calculateSumOfPrimes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        int sum = 0;
        for (int p = 2; p <= n; p++) {
            if (isPrime[p]) {
                sum += p;
            }
        }
        return sum;
    }


    /**
     * @see <a href="https://en.wikipedia.org/wiki/Title_case">Title_case</a>
     */
    public static <T> T convertToTitleCase(T input) {
        if (!(input instanceof String)) {
            throw new IllegalArgumentException("Input must be of type String.");
        }

        String inputStr = (String) input;
        String resultStr = toEnhancedTitleCase(inputStr);
        return (T) resultStr;
    }

    private static String toEnhancedTitleCase(String input) {
        if (input == null || input.isEmpty()) return input;

        input = input.trim().replaceAll("\\s+", " "); // Remove extra whitespaces
        StringBuilder titleCase = new StringBuilder();
        boolean capitalizeNext = true; // Always capitalize the first character

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (Character.isWhitespace(currentChar)) {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                currentChar = Character.toTitleCase(currentChar);
                capitalizeNext = false;
            } else if (!Character.isLetter(currentChar)) {
                // Keep the case of the character as-is, following a non-letter.
                capitalizeNext = false;
            } else if (Character.isLetter(currentChar)) {
                // Ensure other characters in words are lower case
                currentChar = Character.toLowerCase(currentChar);
            }

            titleCase.append(currentChar);
        }

        return titleCase.toString();
    }

    public static <T, U> T  groupAnagrams(U arr) {
        if (!(arr instanceof String[])) {
            throw new IllegalArgumentException("Expecting an array of strings");
        }

        String[] inputArray = (String[]) arr;
        Map<String, List<String>> anagrams = new HashMap<>();

        for (String str : inputArray) {
            String key = sortString(str);

            anagrams.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        // Unsafe cast, but justified by our specific usage pattern.
        @SuppressWarnings("unchecked")
        T result = (T) anagrams;

        return result;
    }

    private static String sortString(String input) {
        char[] characters = input.toCharArray();
        Arrays.sort(characters);
        return new String(characters);
    }
}

