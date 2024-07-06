package hashmap;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests by Brendan Hu, Spring 2015
 * Revised for 2016 by Josh Hug
 * Revised for 2021 by Neil Kulkarni
 * Revised for 2023 by Aram Kazorian and Noah Adhikari
 */
public class TestMyHashMap {

    @DisplayName("generics")
    @Test
    public void testGenerics() {
        assertDoesNotThrow(() -> {
            MyHashMap<String, String> a = new MyHashMap<>();
            MyHashMap<String, Integer> b = new MyHashMap<>();
            MyHashMap<Integer, String> c = new MyHashMap<>();
            MyHashMap<Boolean, Integer> d = new MyHashMap<>();
        });
    }

    @DisplayName("clear")
    @Test
    public void testClear() {
        sanityClearTest(new MyHashMap<>());
    }

    public static void sanityClearTest(MyHashMap<String, Integer> b) {
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, i);
            assertEquals(i, b.get("hi" + i));
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

    @DisplayName("containsKey")
    @Test
    public void testContainsKey() {
        containsKeyTest(new MyHashMap<>());
    }

    public static void containsKeyTest(MyHashMap<String, Integer> b) {
        assertFalse(b.containsKey("waterYouDoingHere"));
        b.put("waterYouDoingHere", 0);
        assertTrue(b.containsKey("waterYouDoingHere"));
        b.put("hashBrowns", null);
        assertTrue(b.containsKey("hashBrowns"));
    }

    @DisplayName("get")
    @Test
    public void testGet() {
        sanityGetTest(new MyHashMap<>());
    }

    public static void sanityGetTest(MyHashMap<String, Integer> b) {
        assertNull(b.get("starChild"));
        b.put("starChild", 5);
        assertEquals(5, b.get("starChild"));
        b.put("KISS", 5);
        assertEquals(5, b.get("KISS"));
    }

    @DisplayName("size")
    @Test
    public void testSize() {
        sanitySizeTest(new MyHashMap<>());
    }

    public static void sanitySizeTest(MyHashMap<String, Integer> b) {
        assertEquals(0, b.size());
        b.put("hi", 1);
        assertEquals(1, b.size());
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
        }
        assertEquals(456, b.size());
    }

    @DisplayName("put")
    @Test
    public void testPut() {
        sanityPutTest(new MyHashMap<>());
    }

    public static void sanityPutTest(MyHashMap<String, Integer> b) {
        b.put("hi", 1);
        assertTrue(b.containsKey("hi"));
        assertEquals(1, b.get("hi"));
    }

    @DisplayName("functionality")
    @Test
    public void testFunctionality() {
        functionalityTest(new MyHashMap<>(), new MyHashMap<>());
    }

    public static void functionalityTest(MyHashMap<String, String> dictionary, MyHashMap<String, Integer> studentIDs) {
        assertEquals(0, dictionary.size());

        dictionary.put("hello", "world");
        assertTrue(dictionary.containsKey("hello"));
        assertEquals("world", dictionary.get("hello"));
        assertEquals(1, dictionary.size());

        dictionary.put("hello", "kevin");
        assertEquals("kevin", dictionary.get("hello"));

        studentIDs.put("sarah", 12345);
        assertEquals(1, studentIDs.size());
        assertEquals(12345, studentIDs.get("sarah"));
        studentIDs.put("alan", 345);
        assertEquals(2, studentIDs.size());
        assertEquals(12345, studentIDs.get("sarah"));
        assertEquals(345, studentIDs.get("alan"));
        studentIDs.put("alan", 345);

        studentIDs.put("evil alan", 345);
        assertEquals(345, studentIDs.get("evil alan"));
        assertEquals(studentIDs.get("alan"), studentIDs.get("evil alan"));
    }

//    @DisplayName("resize")
//    @Test
//    public void testResize() {
//        sanityResizeTest(new MyHashMap<>(), 16, 0.75);
//        sanityResizeTest(new MyHashMap<>(32), 32, 0.75);
//        sanityResizeTest(new MyHashMap<>(64, 0.5), 64, 0.5);
//    }
//
//    public static void sanityResizeTest(MyHashMap<String, Integer> m, int initialCapacity, double loadFactor) {
//        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
//            int backingArrayCapacity = sizeOfBackingArray(m);
//            assertEquals(initialCapacity, backingArrayCapacity);
//            for (int i = 0; i < 100000; i++) {
//                m.put("hi" + i, i);
//                if (1.0 * i / backingArrayCapacity > loadFactor) {
//                    assertTrue(sizeOfBackingArray(m) > backingArrayCapacity);
//                    backingArrayCapacity = sizeOfBackingArray(m);
//                }
//            }
//        });
//    }
//
//    @DisplayName("edge cases")
//    @Test
//    public void testEdgeCases() {
//        edgeCasesTest(new MyHashMap<>());
//    }
//
//    static void edgeCasesTest(MyHashMap<Bee, Integer> map) {
//        Map<Bee, Integer> ref = new HashMap<>();
//
//        Bee b1 = new Bee(1);
//        map.put(b1, 1);
//        ref.put(b1, 1);
//        assertEquals(ref.containsKey(b1), map.containsKey(b1));
//
//        Bee b2 = new Bee(2);
//        assertFalse(map.containsKey(b2));
//
//        map.put(b2, 2);
//        ref.put(b2, 2);
//        assertEquals(ref.get(b1), map.get(b1));
//        assertEquals(ref.get(b2), map.get(b2));
//
//        Bee b61 = new Bee(-61);
//        assertNull(map.get(b61));
//
//        map.put(b61, -61);
//        ref.put(b61, -61);
//        assertEquals(ref.get(b61), map.get(b61));
//
//        for (int m = 3; m <= 61; m++) {
//            Bee bm = new Bee(m * 61);
//            assertFalse(map.containsKey(bm));
//            map.put(bm, m * 61);
//            ref.put(bm, m * 61);
//            assertEquals(ref.get(bm), map.get(bm));
//            assertEquals(ref.get(b61), map.get(b61));
//        }
//
//        for (int n = 0; n < 61; n++) {
//            Bee bn = new Bee(n);
//            map.put(bn, n);
//            ref.put(bn, n);
//            assertEquals(ref.containsKey(b1), map.containsKey(b1));
//            assertEquals(ref.get(b1), map.get(b1));
//        }
//    }
}
