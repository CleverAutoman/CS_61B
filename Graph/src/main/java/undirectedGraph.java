import java.util.*;

/**
 *  The undirected graph is implemented to test DFS & BFS
 *
 *
 */
public class undirectedGraph implements myGraph{

    private Map<Integer, List<Integer>> adjList;

    public undirectedGraph(int vertices) {
        adjList = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            adjList.put(i, new ArrayList<>());
        }
    }
    @Override
    public Map<Integer, List<Integer>> getMap() {
        return adjList;
    }

    @Override
    public void addEdge(int v, int w) {
        adjList.get(v).add(w);
        adjList.get(w).add(v); // 如果是有向图，删除这行
    }


    protected List<Integer> outputList = new ArrayList<>();

    public void printDFS(int start) {
        DFS(start);
        printOutputList();
    }

    private final Set<Integer> marked = new HashSet<>();
    private void DFS(int start) {
        marked.add(start);
        outputList.add(start);
        List<Integer> integers = adjList.get(start);
        for (Integer i : integers) {
            if (!marked.contains(i)) {
                DFS(i);
            }
        }
    }

    public void printBFS(int start) {
        BFS(start);
        printOutputList();
    }

    public int getLayerOfBFS(int index) {
        return height.get(index);
    }

    private Queue<Integer> fringe = new ArrayDeque<>();
    private Map<Integer, Integer> height = new HashMap<>();

    private void BFS(int start) {
        fringe.add(start);
        marked.add(start);
        height.put(0, 0);
        while (!fringe.isEmpty()) {
            Integer polled = fringe.poll();
            outputList.add(polled);
            for (Integer i : adjList.get(polled)) {
                if (!marked.contains(i)) {
                    height.put(i, height.get(polled) + 1);
                    fringe.add(i);
                    marked.add(polled);
                }
            }
        }


    }
    protected void printOutputList() {
        Iterator<Integer> iterator = outputList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
            if (iterator.hasNext()) {
                System.out.print( " -> ");
            }
        }
    }

}
