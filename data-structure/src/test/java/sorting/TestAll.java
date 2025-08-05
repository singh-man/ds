package sorting;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Created by M.Singh on 20/02/2018.
 */
@Suite
@SelectClasses({
        BogoSort.class,
        BubbleSort.class,
        BucketSort.class,
        CountingSort.class,
        HeapSort.class,
        InsertionSort.class,
        MergeSort.class,
        MergeSortEfficient.class,
        MergeSortStraightForward.class,
        QuickSort.class,
        RadixSort.class,
        SelectionSort.class
})
public class TestAll {

    public static void main(String args[]) {

    }

}

