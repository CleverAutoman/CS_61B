import java.util.List;
import java.util.Map;

public interface myGraph {

    public Map<Integer, List<Integer>> getMap();

    public void addEdge(int v, int w);

    default public void printGraph() {
        for (int v : getMap().keySet()) {
            System.out.print("Vertex " + v + ":");
            for (int w : getMap().get(v)) {
                System.out.print(" " + w);
            }
            System.out.println();
        }
    }
}
