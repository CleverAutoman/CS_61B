import com.sun.jdi.IntegerType;

import java.util.*;

public class undirectedGraphWithWeight extends undirectedGraph{

    private Map<String, Map<String, Integer>> adjList;
    private Map<String, Integer> dictionary = new HashMap<>();

    public undirectedGraphWithWeight(int vertices) {
        super(vertices);
        adjList = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            adjList.put(Character.toString((char)(i + 65)), new HashMap<>());
            System.out.println(Character.toString((char)(i + 65)));
        }

        for (int i = 0; i < 26; i++) {
            dictionary.put(Character.toString(i + 65), i);
        }
    }

    public void addEdge(String v, String w, int weight) {
        adjList.get(v).put(w, weight);
        adjList.get(w).put(v, weight);
    }

    private PriorityQueue<Pair<String, Integer>> pq = new PriorityQueue<>((a, b) -> a.getSecond()- b.getSecond());
    private Map<String, Integer> distanceMap = new HashMap<>();

    private List<String> outputList = new ArrayList<>();

    /**
     *  stimulate the Prim algorithm
     * @param start
     */
    private void Prim(String start) {
        Map<String, Integer> neiborMap = adjList.get(start);
        outputList.add(start); // first item
        for (Map.Entry<String, Integer> e : neiborMap.entrySet()) {
            pq.add(new Pair<>(e.getKey(), e.getValue()));
        }
        while (outputList.size() != adjList.size()) {
            Pair<String, Integer> polled = pq.poll();
            String key = polled.key;
            Integer value = polled.value;
            outputList.add(key);
            distanceMap.put(key, value);
            // reput all the neibors into the queue
            Map<String, Integer> nextMap = adjList.get(key);
            for (Map.Entry<String, Integer> e : nextMap.entrySet()) {
                if (! outputList.contains(e.getKey()))
                    pq.add(new Pair<>(e.getKey(), e.getValue()));
            }
        }
    }

    public void printPrim(String start) {
        Prim(start);
        printOutputList();
    }

    private class myList<T> extends ArrayList {
        public myList(List<String> list) {
            super(list);
        }

        @Override
        public boolean equals(Object o) {
            List list = (ArrayList) o;
            for (Object object : list) {
                if (!this.contains(object)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 0;
            for (Object obj : this) {
                String s = (String) obj;
                char c = s.charAt(0);
                int i = c;
                hash += 31 + i * i;
//                System.out.print(obj.hashCode() + " ");
            }
            return hash;
        }
    }

    public void printKruskal(String start) {
        Kruskal(start);
        printList();
    }

    private PriorityQueue<Pair<List<String>, Integer>> p = new PriorityQueue<>((a, b) -> a.getSecond() - b.getSecond());
    private List<List<String>> pathList = new ArrayList<>();
    public void Kruskal(String start) {
        Map<myList<String>, Integer> edgeWeight = new HashMap<>();
        for (Map.Entry<String, Map<String, Integer>> e : this.adjList.entrySet()) {
            String key = e.getKey();
            Map<String, Integer> map = e.getValue();
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key1 = entry.getKey();
                Integer value = entry.getValue();
                myList<String> list = new myList<>(Arrays.asList(key, key1));
                edgeWeight.put(list, value);
            }
        }
        for (Map.Entry<myList<String>, Integer> e : edgeWeight.entrySet()) {
            p.add(new Pair<>(e.getKey(), e.getValue()));
        }

        UnionFind set = new UnionFind(adjList.size());

        while (pathList.size() < adjList.size() - 1) {
            Pair<List<String>, Integer> polled = p.poll();
            List<String> connection = polled.getFirst();
            Integer weight = polled.getSecond();
            // check with the string with the disjoint set index
            Integer first = dictionary.get(connection.get(0));
            Integer second = dictionary.get(connection.get(1));
            if (set.connected(first, second)) {
                // do nothing
            } else {
                pathList.add(connection);
                set.union(first, second);
            }
        }
    }

    protected void printList() {
        Iterator<List<String>> iterator = pathList.iterator();
        while (iterator.hasNext()) {
            List<String> next = iterator.next();
            System.out.print(next.get(0) + " ---> " + next.get(1));
            if (iterator.hasNext()) {
                System.out.print( " -> ");
            }
        }
    }

    @Override
    protected void printOutputList() {
        Iterator<String> iterator = outputList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
            if (iterator.hasNext()) {
                System.out.print( " -> ");
            }
        }
    }

    private class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K k, V v) {
            key = k;
            value = v;
        }

        public K getFirst() {
            return key;
        }

        public V getSecond() {
            return value;
        }
    }
}
