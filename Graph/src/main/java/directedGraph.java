import com.sun.jdi.IntegerType;

import java.util.*;

/*  The directed graph is implemented to test shortest path
 *
 *
 *
 *  */
public class directedGraph {

    // the value of the map is the edge with the weight
    private Map<String, Map<String, Integer>> adjList;
    private Map<Integer, String> dictionary;

    public directedGraph(int vertices) {
        adjList = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            adjList.put(Character.toString((char)(i + 65)), new HashMap<>());
            System.out.println(Character.toString((char)(i + 65)));
        }

        penalty.put("A", 1);
        penalty.put("B", 3);
        penalty.put("C", 15);
        penalty.put("D", 1);
        penalty.put("E", 1);
        penalty.put("F", Integer.MAX_VALUE);
        penalty.put("G", 0);
    }

    public void addEdge(String v, String w, int weight) {
        adjList.get(v).put(w, weight);

    }

    // the lowest distance from start to the key
    private Map<String, Integer> distanceMap = new HashMap<>();

    private PriorityQueue<Pair<String, Integer>> pq = new PriorityQueue<>((a, b) -> a.getSecond()- b.getSecond());
    public int Dijkstra(String start, String target) {
        // update the nears of the first node
        Map<String, Integer> firstMap = adjList.get(start);
        distanceMap.put(start, 0);
        for (String s : adjList.keySet()) {
            if (!firstMap.containsKey(s)) {
                pq.add(new Pair<>(s, Integer.MAX_VALUE));
            } else {
                pq.add(new Pair<>(s, firstMap.get(s)));
                distanceMap.put(s, firstMap.get(s));
            }
        }

        while (distanceMap.size() != adjList.size()) {
            Pair<String, Integer> polled = pq.poll();
            String key = polled.getFirst();
            Integer value = polled.getSecond();

            // get the neighbours and update the information
            Map<String, Integer> nextMap = adjList.get(key);
            for (Map.Entry<String, Integer> entry : nextMap.entrySet()) {
                String nextKey = entry.getKey();
                Integer nextWeight = entry.getValue();
                if (distanceMap.containsKey(nextKey)) {
                    if (distanceMap.get(nextKey) > value + nextWeight) {
                        distanceMap.put(nextKey, value + nextWeight);
                    } else {
                        continue;
                    }
                } else {
                    distanceMap.put(nextKey, value + nextWeight);
                }
                pq.add(new Pair<>(nextKey, value + nextWeight));
            }
        }

        return distanceMap.get(target);
    }


    private Map<String, Integer> penalty = new HashMap<>();
    /**
     * same idea as Dijkstra but with penalty map
     * @param start
     * @param target
     * @return
     */
    public int AStar(String start, String target) {
        // update the nears of the first node
        Map<String, Integer> firstMap = adjList.get(start);
        distanceMap.put(start, 0);
        for (String s : adjList.keySet()) {
            if (!firstMap.containsKey(s)) {
                pq.add(new Pair<>(s, Integer.MAX_VALUE));
            } else {
                pq.add(new Pair<>(s, firstMap.get(s)));
                distanceMap.put(s, firstMap.get(s));
            }
        }

        while (distanceMap.size() != adjList.size()) {
            Pair<String, Integer> polled = pq.poll();
            String key = polled.getFirst();
            Integer value = distanceMap.get(key);
            if (key.equals(target)) {
                return value;
            }

            // get the neighbours and update the information
            Map<String, Integer> nextMap = adjList.get(key);
            for (Map.Entry<String, Integer> entry : nextMap.entrySet()) {
                String nextKey = entry.getKey();
                Integer nextWeight = entry.getValue();
                if (distanceMap.containsKey(nextKey)) {
                    if (distanceMap.get(nextKey) > value + nextWeight) {
                        distanceMap.put(nextKey, value + nextWeight);
                    } else {
                        continue;
                    }
                } else {
                    distanceMap.put(nextKey, value + nextWeight);
                }
                pq.add(new Pair<>(nextKey, value + nextWeight + penalty.get(nextKey)));
            }
        }

        return distanceMap.get(target);
    }
    /**
     *  print the output results in list
     */
    private List<String> outputList = new ArrayList<>();
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
