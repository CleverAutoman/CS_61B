import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class unDirectedGraphTest {

    private directedGraph d;

    {
        d = new directedGraph(7);
        d.addEdge("A", "B", 2);
        d.addEdge("A", "C", 1);
        d.addEdge("B", "C", 5);
        d.addEdge("B", "E", 3);
        d.addEdge("E", "C", 1);
        d.addEdge("B", "D", 11);
        d.addEdge("C", "F", 15);
        d.addEdge("D", "E", 2);
        d.addEdge("E", "F", 4);
        d.addEdge("G", "F", 1);
        d.addEdge("E", "G", 5);
        d.addEdge("G", "D", 1);
    }
    @Test
    public void testDijkstra() {
        assertEquals(d.Dijkstra("A", "A"), 0);
        assertEquals(d.Dijkstra("A", "B"), 2);
        assertEquals(d.Dijkstra("A", "C"), 1);
        assertEquals(d.Dijkstra("A", "D"), 13);
        assertEquals(d.Dijkstra("A", "E"), 5);
        assertEquals(d.Dijkstra("A", "F"), 9);
        assertEquals(d.Dijkstra("A", "G"), 10);
    }

    @Test
    public void testAStar() {
        assertEquals(d.AStar("A", "G"), 10);
    }

    
}
