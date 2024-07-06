import static org.junit.Assert.*;
import org.junit.Test;

/** Tests by Brendan Hu, Spring 2015, revised for 2016 by Josh Hug and for 2023 by Noah Adhikari */
public class TestBSTMap {

    @Test
    public void sanityGenericsTest() {
        try {
            BSTMap<String, String> a = new BSTMap<>();
            BSTMap<String, Integer> b = new BSTMap<>();
            BSTMap<Integer, String> c = new BSTMap<>();
            BSTMap<Boolean, Integer> e = new BSTMap<>();
        } catch (Exception e) {
            fail(); // Fails if any exception is thrown
        }
    }

    @Test
    public void sanityClearTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1+i);
            // Make sure put is working via containsKey and get
            assertEquals(Integer.valueOf(1 + i), b.get("hi" + i));
            assertTrue(b.containsKey("hi" + i));
        }
        assertEquals(455, b.size());
        b.clear();
        assertEquals(0, b.size());
        for (int i = 0; i < 455; i++) {
            assertNull(b.get("hi" + i));
            assertFalse(b.containsKey("hi" + i));
        }
    }

    @Test
    public void sanityContainsKeyTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        assertFalse(b.containsKey("waterYouDoingHere"));
        b.put("waterYouDoingHere", 0);
        assertTrue(b.containsKey("waterYouDoingHere"));
    }

    @Test
    public void sanityGetTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        assertNull(b.get("starChild"));
        assertEquals(0, b.size());
        b.put("starChild", 5);
        assertEquals(Integer.valueOf(5), b.get("starChild"));
        assertEquals(1, b.size());
        b.put("KISS", 5);
        assertEquals(Integer.valueOf(5), b.get("KISS"));
        assertNotNull(b.get("starChild"));
        assertEquals(2, b.size());
    }

    @Test
    public void sanitySizeTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        assertEquals(0, b.size());
        b.put("hi", 1);
        assertEquals(1, b.size());
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
            System.out.println(i);
        }
        assertEquals(456, b.size());
    }

    @Test
    public void sanityPutTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        b.put("hi", 1);
        assertTrue(b.containsKey("hi"));
        assertEquals(Integer.valueOf(1), b.get("hi"));
    }

    @Test
    public void containsKeyNullTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        b.put("hi", null);
        assertNull(b.get("hi"));
        assertTrue(b.containsKey("hi"));
    }

    @Test
    public void treeTest() {
        BSTMap<String, String> b = new BSTMap<>();
        b.put("d", "parmesan");
        b.put("a", "mozzarella");
        b.put("c", "swiss");
        b.put("b", "pepper jack");
        b.put("e", "gouda");

        assertEquals(5, b.size());
        assertEquals("parmesan", b.get("d"));
        assertEquals("mozzarella", b.get("a"));
        assertEquals("swiss", b.get("c"));
        assertEquals("pepper jack", b.get("b"));
        assertEquals("gouda", b.get("e"));

        b.put("b", "provolone");
        assertEquals(5, b.size());
        assertEquals("provolone", b.get("b"));
    }
}
