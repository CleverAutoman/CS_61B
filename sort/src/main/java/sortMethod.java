import java.util.*;

public class sortMethod {

    /**
     * Find the smallest item.
     * Swap that item to the front.
     * Repeat until all items are fixed (there are no inversions).
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> void selectionSort(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            int minIndex = findSmallestRange(i, list);
            T min = list.get(minIndex);
            list.set(minIndex, list.get(i));
            list.set(i, min);
        }
    }

    private static <T extends Comparable<T>> int findSmallestRange(int first, List<T> list) {
        int index = first;
        T min = list.get(first);
        for (int i = first + 1; i < list.size(); i++) {
            if (min.compareTo(list.get(i)) > 0) {
                min = list.get(i);
                index = i;
            }
        }
        return index;
    }


    /**
     * Then, to heapsort N items, we can insert all the items into a max heap and create and output array.
     * Then, we repeatedly delete the largest item from the max heap
     * and put the largest item at the end part of the output array.
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> void heapSort(List<T> list) {
        PriorityQueue<T> pq = new PriorityQueue<>();
        for (T t : list) {
            pq.add(t);
        }
        int index = 0;
        while (!pq.isEmpty()) {
            list.set(index++, pq.poll());
        }
    }

    /**
     *
     * Split the items into half.
     * Mergesort each half.
     * Merge the two sorted halves to form the final result.
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> List<T> mergeSort(List<T> list) {
        if (list.size() == 2) {
            T t1 = list.get(0);
            T t2 = list.get(1);
            if (t1.compareTo(t2) < 0)
                return new ArrayList<>(Arrays.asList(t1, t2));
            else
                return new ArrayList<>(Arrays.asList(t2, t1));
        }
        if (list.size() == 1) {
            return new ArrayList<>(Arrays.asList(list.get(0)));
        }

        List<T> list1 = list.subList(0, list.size() / 2);
        List<T> list2 = list.subList(list.size() / 2, list.size());
        return merge(mergeSort(list1), mergeSort(list2));
    }
    private static List<Object> result;

    private static <T extends Comparable<T>> List<T> merge(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<>();
        int i, j;
        for (i = 0, j = 0; i < list1.size() && j < list2.size();) {
            T t1 = list1.get(i);
            T t2 = list2.get(j);
            if (t1.compareTo(t2) < 0) {
                list.add(t1);
                i++;
            } else if (t1.compareTo(t2) > 0) {
                list.add(t2);
                j++;
            } else {
                list.add(t1);
                list.add(t2);
                i++;
                j++;
            }
        }
        while (i < list1.size()) {
            list.add(list1.get(i++));
        }
        while (j < list2.size()) {
            list.add(list2.get(j++));
        }
        return list;
    }


    /**
     * Essentially, we move from left to right in the array, selecting an item to be swapped each time.
     * Then, we swap that item as far to the front as it can go.
     * The front of our array gradually becomes sorted until the entire array is sorted.
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> void insertionSort(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            T tobeSwapped = list.get(i);
            int j = i;

            while (j > 0) {
                T Ti = list.get(j - 1);
                T Tj = list.get(j);
                if (Ti.compareTo(Tj) > 0) {
                    T temp = Ti;
                    list.set(j - 1, Tj);
                    list.set(j, temp);
                    j--;
                } else {
                    j--;
                    continue;
                }
            }
        }
    }

    /**
     * Essentially, we move from left to right in the array, selecting an item to be swapped each time.
     * Then, we swap that item as far to the front as it can go.
     * The front of our array gradually becomes sorted until the entire array is sorted.
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> void quickSort(List<T> list) {
        if (list.size() == 2) {
            T t1 = list.get(0);
            T t2 = list.get(1);
            if (t1.compareTo(t2) > 0) {
                list.set(0, t2);
                list.set(1, t1);
            } else {
                return;
            }
        }
        if (list.size() == 1 || list.isEmpty()) {
            return;
        }

        int pivotIndex = locatePivot(findPivot(list), list);
        quickSort(list.subList(0, pivotIndex));
        quickSort(list.subList(pivotIndex, list.size()));
    }

    // locate the pivot to the right place
    private static <T extends Comparable<T>> int locatePivot(T pivot, List<T> list) {
        int num = 0;
        for (int i = 0, j = list.size() - 1; i <= j;) {
            if (list.get(i).compareTo(pivot) >= 0 && list.get(j).compareTo(pivot) <= 0) {
                T temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
                i++; j--;
            } else if (list.get(i).compareTo(pivot) < 0) {
                i++;
            } else if (list.get(j).compareTo(pivot) > 0) {
                j--;
            }
            num = i;
        }
        return num;
    }

    // find the pivot, default is the first element
    // or shuffle the list to find the random element
    private static<T extends Comparable<T>> T findPivot(List<T> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }


    public static List<Integer> radixSort(List<Integer> list) {
        // get the currency and the biggest value
        int biggest = list.stream().max(Comparable::compareTo).get();
        for (int exp = 1; biggest / exp > 0; exp *= 10) {
            list = countSort(list, exp);
        }
        return list;
    }

    private static List<Integer> countSort(List<Integer> list, int exp) {
        Integer[] output = new Integer[list.size()];
        Arrays.fill(output, 0);
        int[] count = new int[10];

        for (Integer i : list) {
             count[(i / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = list.size() - 1; i >= 0 ; i--) {
            output[count[(list.get(i) / exp) % 10] - 1] = list.get(i);
            // move to the previous socket for the duplicate one
            count[(list.get(i) / exp) % 10]--;
        }
        return new ArrayList<>(Arrays.asList(output));
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

