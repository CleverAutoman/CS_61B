package hashmap;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/** Tests of optional parts of lab 8. */
public class TestMyHashMapExtra {

    @Test
    public void testRemove() {
        MyHashMap<String, String> q = new MyHashMap<>();
        q.put("c", "a");
        q.put("b", "a");
        q.put("a", "a");
        q.put("d", "a");
        q.put("e", "a"); // a b c d e
        assertNotNull(q.remove("c"));
        assertNull(q.remove("f"));
        assertFalse(q.containsKey("c"));
        assertTrue(q.containsKey("a"));
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("d"));
        assertTrue(q.containsKey("e"));
    }

    /**
     * Remove Test 2
     * Test the 3 different cases of remove
     */
    @Test
    public void testRemoveThreeCases() {
        MyHashMap<String, String> q = new MyHashMap<>();
        q.put("c", "a");
        q.put("b", "a");
        q.put("a", "a");
        q.put("d", "a");
        q.put("e", "a");                         // a b c d e
        assertNotNull(q.remove("e"));             // a b c d
        assertTrue(q.containsKey("a"));
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("c"));
        assertTrue(q.containsKey("d"));
        assertNotNull(q.remove("c"));            // a b d
        assertTrue(q.containsKey("a"));
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("d"));
        q.put("f", "a");                        // a b d f
        assertNotNull(q.remove("d"));            // a b f
        assertTrue(q.containsKey("a"));
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("f"));
    }

    @Test
    public void sanityKeySetTest() {
        sanityKeySetTest(new MyHashMap<>());
    }

    public static void sanityKeySetTest(MyHashMap<String, Integer> b) {
        HashSet<String> values = new HashSet<String>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
            values.add("hi" + i);
        }
        assertEquals(455, b.size()); //keys are there
        Set<String> keySet = b.keySet();
        assertEquals(values, keySet); // directly compare sets
    }
}
