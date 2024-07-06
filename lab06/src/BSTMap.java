import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

    int size;
    private Entry entry;

    
    /**
     * compare
     * @param other: the another BSTMap tp be compared with
     * @return int
     * on 7/2/24 8:59 PM
     */
    public int compareKey(BSTMap<K, V> other) {
        return this.entry.key.compareTo(other.entry.key);
    }

    public BSTMap() {
        size = 0;
    }

    public BSTMap(int n) {
        size = n;
    }


    @Override
    public void put(K key, V value) {
        if (entry == null)  {
            entry = new Entry(key, value, null, null);
            size += 1;
        }
        entry.put(key, value);
    }


    @Override
    public V get(K key) {
        if (entry == null) return null;
        Entry entryWithKey = entry.get(key);
        if (entryWithKey == null) throw new IllegalArgumentException("No Such Key in hte TreeMap");

        return entryWithKey.value;
    }

    @Override
    public boolean containsKey(K key) {
        if (entry == null) return false;
        K currentKey = this.entry.key;
        Entry entryWithKey = entry.get(key);
        return entryWithKey != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        this.entry = null;
        this.size = 0;
    }

    @Override
    public Set<K> keySet() {
        Iterator<K> iterator = iterator();
        Set<K> set = new HashSet<>();
        while (iterator.hasNext()) {
            set.add(iterator.next());
        }
        return set;
    }

    @Override
    public V remove(K key) {
        Entry entryWithKey = entry.get(key);
        if (entryWithKey == null) return null;
        // check which side continuing to find the replacement
        Entry entryParent = entry.getParent(key);
        return null;
    }

    public K getParent(K key) {
        return entry.getParent(key).key;
    }

    private Entry findReplacement(Entry entry) {
        size -= 1;
        if (entry.leftEntry == null && entry.rightEntry == null) {
            return null;
        }
        if (entry.leftEntry == null) {
            Entry right = entry.rightEntry;
            if (right.leftEntry == null) {
                right.leftEntry = entry.leftEntry;
                return right;
            } else {
                while (right.leftEntry.leftEntry != null) {
                    right = right.leftEntry;
                }
                Entry e = right.leftEntry;
                right.leftEntry = e.rightEntry;
                return e;
            }
        } else {
            Entry left = entry.leftEntry;
            if (left.rightEntry == null) {
                left.rightEntry = entry.rightEntry;
                return left;
            } else {
                while (left.rightEntry.rightEntry != null) {
                    left = left.rightEntry;
                }
                Entry e = left.rightEntry;
                left.rightEntry = e.leftEntry;
                return e;
            }
        }
    }


    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    /**
     * iterate over the key
     * @param
     * @return
     * on 7/2/24 9:40 PM
     */
   private class BSTMapIterator implements Iterator<K>{
        private Stack<Entry> stack;
//        Entry currentEntry;
        public BSTMapIterator() {
            stack = new Stack<>();
            while (entry != null) {
                stack.push(entry);
                entry = entry.leftEntry;
            }
        }

       @Override
       public boolean hasNext() {
           return !stack.empty();
       }

       @Override
       public K next() {
           Entry pop = stack.pop();
           Entry rightEntry = pop.rightEntry;
           while (rightEntry != null) {
               stack.push(rightEntry);
               rightEntry = rightEntry.leftEntry;
           }
           return pop.key;
       }
   }

    private class Entry {
        K key;
        V value;
        Entry leftEntry;
        Entry rightEntry;

        public Entry(K k, V v, Entry leftE, Entry rightE) {
            key = k;
            value = v;
            leftEntry = leftE;
            rightEntry = rightE;
        }

        /**
         *  get the entry with the K key
         * @param k: key to search
         * @return BSTMap<K,V>.Entry: the entry to return
         * on 7/2/24 9:03 PM
         */
        public Entry get(K targetKey) {
            if (key.compareTo(targetKey) == 0) {
                return this;
            }

            // target is larger
            if (key.compareTo(targetKey) < 0) {
                if (rightEntry != null) return rightEntry.get(targetKey);
            // target is smaller.search leftEntry
            } else if (key.compareTo(targetKey) > 0) {
                if (leftEntry != null) return leftEntry.get(targetKey);
            }
            return null;
        }

        public void put(K targetK, V targetV) {
            if (targetK.compareTo(key) > 0) {
                if (rightEntry == null) {
                    rightEntry = new Entry(targetK, targetV, null, null);
                    size += 1;
                } else {
                    this.rightEntry.put(targetK, targetV);
                }
            } else if (targetK.compareTo(key) < 0) {
                if (leftEntry == null) {
                    leftEntry = new Entry(targetK, targetV, null, null);
                    size += 1;
                } else {
                    this.leftEntry.put(targetK, targetV);
                }
            } else {
                value = targetV;
            }
        }

        public void delete(K targetKey) {
            if (rightEntry != null && rightEntry.key == targetKey) rightEntry = null;
            if (leftEntry != null && leftEntry.key == targetKey) leftEntry = null;

            if (targetKey.compareTo(key) > 0) {
                if (rightEntry != null) rightEntry.delete(targetKey);
            } else if(targetKey.compareTo(key) < 0) {
                if (leftEntry != null) leftEntry.delete(targetKey);
            }
        }

        public Entry getParent(K targetKey) {
            if ((leftEntry != null && this.leftEntry.key.equals(targetKey)) || (rightEntry != null && this.rightEntry.key.equals(targetKey))) {
                return this;
            } else {
                if (targetKey.compareTo(key) > 0) {
                    if (rightEntry != null) return rightEntry.getParent(targetKey);
                } else if(targetKey.compareTo(key) < 0) {
                    if (leftEntry != null) return leftEntry.getParent(targetKey);
                }
                return null;
            }
        }
    }
}
