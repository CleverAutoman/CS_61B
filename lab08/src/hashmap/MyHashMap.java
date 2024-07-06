package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }

        @Override
        public boolean equals(Object obj) {
            Node obj1 = (Node) obj;
            return key.equals(obj1.key) && value.equals(obj1.value);
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size = 0;
    private int capacity;
    private double factor;
    private final int INITIAL_CAPACITY = 16;
    private final double LOAD_FACTOR = 0.75;

    /** Constructors */
    public MyHashMap() {
        this.buckets = new Collection[INITIAL_CAPACITY];
        capacity = INITIAL_CAPACITY;
        factor = LOAD_FACTOR;
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    public MyHashMap(int initialCapacity) {
        this.buckets = new Collection[initialCapacity];
        capacity = initialCapacity;
        factor = LOAD_FACTOR;
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.buckets = new Collection[initialCapacity];
        capacity = initialCapacity;
        factor = loadFactor;
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new HashSet<>();
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    private int getHashIndex(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    @Override
    public void put(K key, V value) {


        int hashIndex = getHashIndex(key);
        Collection<Node> bucket = this.buckets[hashIndex];
        if (containsKey(key)) {
            bucket.remove(new Node(key, get(key)));
        }
        bucket.add(new Node(key, value));
        size += 1;
    }

    @Override
    public V get(K key) {
        int hashIndex = getHashIndex(key);
        Collection<Node> bucket = this.buckets[hashIndex];
        Iterator<Node> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            Node next = iterator.next();
            if (next.key.equals(key)) {
                return next.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int hashIndex = getHashIndex(key);
        Collection<Node> bucket = this.buckets[hashIndex];
        Iterator<Node> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            Node next = iterator.next();
            if (next.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.buckets.length; i++) {
            this.buckets[i] = createBucket();
        }
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> set = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            Iterator<Node> iterator = bucket.iterator();
            while (iterator.hasNext()) {
                set.add(iterator.next().key);
            }
        }
        return set;
    }

    @Override
    public V remove(K key) {
        int index = getHashIndex(key);
        Collection<Node> bucket = this.buckets[index];
        V v = get(key);
        bucket.removeIf(b -> b.key.equals(key));
        return v;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
