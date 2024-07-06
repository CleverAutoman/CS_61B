import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class testSort {

    private List<Integer> list = new ArrayList<> (Arrays.asList(9, 4, 100, 150, 701, 102, 99, 87));

    @Test
    public void testSelection() {
        sortMethod.selectionSort(list);
        assertEquals(new ArrayList<>(Arrays.asList(4, 9, 87, 99, 100, 102, 150, 701)), list);
    }

    @Test
    public void testHeapSort() {
        sortMethod.heapSort(list);
        assertEquals(new ArrayList<>(Arrays.asList(4, 9, 87, 99, 100, 102, 150, 701)), list);
    }

    @Test
    public void testMergeSort() {
        List<Integer> mergeSort = sortMethod.mergeSort(list);
        assertEquals(new ArrayList<>(Arrays.asList(4, 9, 87, 99, 100, 102, 150, 701)), mergeSort);
    }

    @Test
    public void testQuickSort() {
        sortMethod.quickSort(list);
        assertEquals(new ArrayList<>(Arrays.asList(4, 9, 87, 99, 100, 102, 150, 701)), list);
    }

    @Test
    public void testRadixSort() {
        List<Integer> integers = sortMethod.radixSort(list);
        assertEquals(new ArrayList<>(Arrays.asList(4, 9, 87, 99, 100, 102, 150, 701)), integers);
    }
}
