import org.junit.Test;
import static org.junit.Assert.*;

public class directedGraphTest {

    @Test
    public void testDFS() {
//        System.out.println("hello");
        undirectedGraph undirectedGraph = new undirectedGraph(9);
        undirectedGraph.addEdge(0, 1);
        undirectedGraph.addEdge(1, 2);
        undirectedGraph.addEdge(1, 4);
        undirectedGraph.addEdge(3, 4);
        undirectedGraph.addEdge(2, 5);
        undirectedGraph.addEdge(5, 6);
        undirectedGraph.addEdge(5, 8);
        undirectedGraph.addEdge(6, 7);

        undirectedGraph.printDFS(0);
//        undirectedGraph.printGraph();
    }

    @Test
    public void testBFS() {
        undirectedGraph undirectedGraph = new undirectedGraph(9);
        undirectedGraph.addEdge(0, 1);
        undirectedGraph.addEdge(1, 2);
        undirectedGraph.addEdge(1, 4);
        undirectedGraph.addEdge(3, 4);
        undirectedGraph.addEdge(2, 5);
        undirectedGraph.addEdge(5, 6);
        undirectedGraph.addEdge(5, 8);
        undirectedGraph.addEdge(6, 7);

        undirectedGraph.printBFS(0);

        assertEquals(undirectedGraph.getLayerOfBFS(2), 2);
        assertEquals(undirectedGraph.getLayerOfBFS(3), 3);
        assertEquals(undirectedGraph.getLayerOfBFS(8), 4);
        assertEquals(undirectedGraph.getLayerOfBFS(7), 5);
    }

    @Test
    public void testPrim() {
        undirectedGraphWithWeight ud = new undirectedGraphWithWeight(7);
        ud.addEdge("A", "B", 2);
        ud.addEdge("A", "C", 1);
        ud.addEdge("B", "C", 5);
        ud.addEdge("B", "E", 3);
        ud.addEdge("E", "C", 1);
        ud.addEdge("B", "D", 11);
        ud.addEdge("C", "F", 15);
        ud.addEdge("D", "E", 2);
        ud.addEdge("E", "F", 4);
        ud.addEdge("G", "F", 1);
        ud.addEdge("E", "G", 5);
        ud.addEdge("G", "D", 1);

        ud.printPrim("A");
    }

    @Test
    public void testKruskal() {
        undirectedGraphWithWeight ud = new undirectedGraphWithWeight(7);
        ud.addEdge("A", "B", 2);
        ud.addEdge("A", "C", 1);
        ud.addEdge("B", "C", 5);
        ud.addEdge("B", "E", 3);
        ud.addEdge("E", "C", 1);
        ud.addEdge("B", "D", 11);
        ud.addEdge("C", "F", 15);
        ud.addEdge("D", "E", 2);
        ud.addEdge("E", "F", 4);
        ud.addEdge("G", "F", 1);
        ud.addEdge("E", "G", 5);
        ud.addEdge("G", "D", 1);

        ud.printKruskal("A");
    }

    @Test
     public void testTrie() {
        Trie trie = new Trie();
        trie.inputString("sona");
        trie.inputString("sony");
        trie.inputString("sonyde");
        trie.inputString("sand");
        trie.inputString("sandandsans");

        assertEquals(true, trie.containsString("sona"));
        assertEquals(true, trie.containsString("sony"));
        assertEquals(true, trie.containsString("sonyde"));
        assertEquals(true, trie.containsString("sand"));
        assertEquals(true, trie.containsString("sandandsans"));

        assertEquals("sand", trie.findNearestWord("sa"));
        assertEquals("sona", trie.findNearestWord("so"));
//        assertEquals("son", trie.findNearestWord("s"));
        assertEquals("sandandsans", trie.findNearestWord("sanda"));
    }
}
