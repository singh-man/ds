package sorting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by M.Singh on 07/02/2018.
 * Bubble Sort is the simplest sorting algorithm that works by
 * repeatedly swapping the adjacent elements if they are in wrong order.
 */
public class AllSorting implements ISort {

    /**
     * Created by M.Singh on 07/02/2018.
     * BogoSort also known as permutation sort, stupid sort, slow sort, shotgun sort or monkey sort
     * is a particularly ineffective algorithm based on generate and test paradigm.
     * The algorithm successively generates permutations of its input until it finds one that is sorted.(Wiki)
     * For example, if bogosort is used to sort a deck of cards,
     * it would consist of checking if the deck were in order,
     * and if it were not, one would throw the deck into the air,
     * pick the cards up at random, and repeat the process until the deck is sorted.
     */
    @Test
    public void sort() {
        final Random generator = new Random();
        while (!isSorted(data)) {
            for (int i = 0; i < data.length; i++) {
                int randomPosition = generator.nextInt(data.length);
                swap(data, i, randomPosition);
            }
        }
        Arrays.stream(data).forEach(System.out::println);
    }

    //    @Test
    public int[] mergeSort(int[] arr) {
        int[] array = new int[]{0, 5, 6, 3, 7, 0, 3, 7, 8, 9, 2, 5};
        if (array.length > 1) {
            int elementsInA1 = array.length / 2;
            int elementsInA2 = array.length - elementsInA1;
            int arr1[] = new int[elementsInA1];
            int arr2[] = new int[elementsInA2];

            for (int i = 0; i < elementsInA1; i++)
                arr1[i] = array[i];

            for (int i = elementsInA1; i < elementsInA1 + elementsInA2; i++)
                arr2[i - elementsInA1] = array[i];

            arr1 = mergeSort(arr1);
            arr2 = mergeSort(arr2);

            int i = 0, j = 0, k = 0;

            while (arr1.length != j && arr2.length != k) {
                if (arr1[j] <= arr2[k]) {
                    array[i] = arr1[j];
                    i++;
                    j++;
                } else {
                    array[i] = arr2[k];
                    i++;
                    k++;
                }
            }

            while (arr1.length != j) {
                array[i] = arr1[j];
                i++;
                j++;
            }
            while (arr2.length != k) {
                array[i] = arr2[k];
                i++;
                k++;
            }
        }
        return array;
    }

    /**
     * Created by M.Singh on 07/02/2018.
     * Bubble Sort is the simplest sorting algorithm that works by
     * repeatedly swapping the adjacent elements if they are in wrong order.
     */
    @Test
    public void testBubbleSort() {
        for (int k = 0; k < data.length - 1; k++) {
            boolean isSorted = true;

            for (int i = 1; i < data.length - k; i++) {
                if (data[i] < data[i - 1]) {
                    swap(data, i, i - 1);

                    isSorted = false;
                }
            }
            if (isSorted)
                break;
        }
        Arrays.stream(data).forEach(System.out::println);
    }
}
