package slidingWindow;

import org.junit.jupiter.api.Test;

/**
 * Input: arr[] = {3, 8, 9, 15}, K = 2
 * Output: 6.5
 * All subarrays of length 2 are {3, 8}, {8, 9}, {9, 15} and their averages are
 * (3+8)/2 = 5.5, (8+9)/2 = 8.5, and (9+15)/2 = 12.0 respectively.
 * Therefore, the difference between the maximum(=12.0) and minimum(=5.5) is 12.0 -5.5 = 6.5.
 */
public class MaxMinAvgDiff {

    @Test
    public void test() {
        int[] arr = {3, 8, 9, 15};
        int k = 2;
        float minAvg, maxAvg;
        // sum
        int total = 0;
        for (int i = 0; i < k; i++) {
            total += arr[i];
        }
        minAvg = maxAvg = total / 2f;

        for (int i = 1; i < arr.length-1; i++) {
            total = total - arr[i - 1] + arr[i + 1];
            minAvg = Math.min(minAvg, total / 2f);
            maxAvg = Math.max(maxAvg, total / 2f);
        }
        System.out.println(maxAvg - minAvg);
    }
}
