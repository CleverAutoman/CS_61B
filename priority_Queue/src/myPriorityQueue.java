import java.util.*;
import java.lang.reflect.Array;

public class myPriorityQueue<T extends Comparable<T>> implements Queue<T> {

    // the first ele of the heap is always null for convenience
    // the first non-null ele is always equal to minimal ele in the heap
    private T[] heap;
    private int currentSize;
    private int size;
    private Comparator<T> comparator;
    private final int DEFAULT_SIZE = 11;
    private final double LOAD_FACTOR = 0.5;

    public myPriorityQueue() {
        size = DEFAULT_SIZE;
        heap = (T[]) Array.newInstance(Comparable.class, size + 1);
    }

    public myPriorityQueue(int inputSize) {
        size = inputSize;
        heap = (T[]) Array.newInstance(Comparable.class, size + 1);
    }

    public myPriorityQueue(int inputSize, Comparator<T> comparator) {
        size = inputSize;
        this.comparator = comparator;
        heap = (T[]) Array.newInstance(Comparable.class, size + 1);
    }

    public void showHeap() {
        System.out.println(Arrays.toString(Arrays.copyOfRange(heap, 1, currentSize + 1)));
    }


    private void heapify(int index) {
        if (index == 1) return;
        int parentIndex = getParentIndex(index);

        if (heap[parentIndex].compareTo(heap[index]) > 0) {
            swap(index, parentIndex);
        }
        heapify(parentIndex);
    }

    private void heapifyDown(int index) {
        int childMinIndex = getChildMinIndex(index);
        if (childMinIndex == -1) return;
        if (heap[index].compareTo(heap[childMinIndex]) < 0) return;
        swap(childMinIndex, index);
        heapifyDown(childMinIndex);
    }

    private int getParentIndex(int index) {
        return index / 2;
    }

    private int getChildMinIndex(int index) {
        int child1 = index * 2;
        int child2 = child1 + 1;
        if (child1 > currentSize) {
            return -1;
        } else if (child2 > currentSize) {
            return child1;
        } else {
            return heap[child1].compareTo(heap[child2]) < 0? child1: child2;
        }
    }

    private void swap(int swapper, int toSwapped) {
        T temp = heap[toSwapped];
        heap[toSwapped] = heap[swapper];
        heap[swapper] = temp;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T>{

        @Override
        public boolean hasNext() {
            return currentSize > 0;
        }

        @Override
        public T next() {
            return poll();
        }
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean add(T t) {
        if (currentSize == size) {
            int ceil = (int) Math.ceil(currentSize * (1 + LOAD_FACTOR));
            resize(ceil);
        }
        heap[++currentSize] = t;
        heapify(currentSize);
        return true;
    }

    /**
     * resize the heap array to the input length, copy all original eles to the new array
     * @param length: target length
     * @return void
     * on 7/4/24 5:31 PM
     */
    private void resize(int length) {
        T[] newHeap = (T[]) Array.newInstance(Comparable.class, length + 1);
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        size = length;
        heap = newHeap;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        currentSize = 0;
        Arrays.fill(heap, null);
    }

    @Override
    public boolean offer(T t) {
        return add(t);
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T poll() {
        if (currentSize == 0) throw new UnsupportedOperationException("empty queue");
        T t = heap[1];
        heap[1] = heap[currentSize];
        heap[currentSize] = null;
        currentSize --;
        heapifyDown(1);
//        moveForward();
        return t;
    }


    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        if (currentSize == 0) return null;
        else return heap[1];
    }
}
