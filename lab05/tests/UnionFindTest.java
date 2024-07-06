import org.junit.Test;

//import static com.google.common.truth.Truth.assertThat;
//import static com.google.common.truth.Truth.assertWithMessage;
import java.util.Arrays;

import static org.junit.Assert.*;

public class UnionFindTest {

    /**
     * Checks that the initial state of the disjoint sets are correct (this will pass with the skeleton
     * code, but ensure it still passes after all parts are implemented).
     */
    @Test
    public void initialStateTest() {
        UnionFind uf = new UnionFind(4);
//        assertThat(uf.connected(0, 1)).isFalse();
//        assertThat(uf.connected(0, 2)).isFalse();
//        assertThat(uf.connected(0, 3)).isFalse();
//        assertThat(uf.connected(1, 2)).isFalse();
//        assertThat(uf.connected(1, 3)).isFalse();
//        assertThat(uf.connected(2, 3)).isFalse();

        assertFalse(uf.connected(0, 1));
        assertFalse(uf.connected(0, 2));
        assertFalse(uf.connected(0, 3));
        assertFalse(uf.connected(1, 2));
        assertFalse(uf.connected(1, 3));
        assertFalse(uf.connected(2, 3));
    }

    /**
     * Checks that invalid inputs are handled correctly.
     */
    @Test
    public void illegalFindTest() {
        UnionFind uf = new UnionFind(4);
        try {
            uf.find(10);
            fail("Cannot find an out of range vertex!");
        } catch (IllegalArgumentException e) {
            return;
        }
        try {
            uf.union(1, 10);
            fail("Cannot union with an out of range vertex!");
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    /**
     * Checks that union is done correctly (including the tie-breaking scheme).
     */
//    @Test
//    public void basicUnionTest() {
//        UnionFind uf = new UnionFind(10);
//        uf.union(0, 1);
//        assertThat(uf.find(0)).isEqualTo(1);
//        uf.union(2, 3);
//        assertThat(uf.find(2)).isEqualTo(3);
//        uf.union(0, 2);
//        assertThat(uf.find(1)).isEqualTo(3);
//
//        uf.union(4, 5);
//        uf.union(6, 7);
//        uf.union(8, 9);
//        uf.union(4, 8);
//        uf.union(4, 6);
//
//        assertThat(uf.find(5)).isEqualTo(9);
//        assertThat(uf.find(7)).isEqualTo(9);
//        assertThat(uf.find(8)).isEqualTo(9);
//
//        uf.union(9, 2);
//        assertThat(uf.find(3)).isEqualTo(9);
//    }
    @Test
    public void basicUnionTest() {
        UnionFind uf = new UnionFind(10);
        uf.union(0, 1);
        System.out.println(Arrays.toString(uf.arr));
        assertEquals(1, uf.find(0));
        uf.union(2, 3);
        System.out.println(Arrays.toString(uf.arr));
        assertEquals(3, uf.find(2));
        uf.union(0, 2);
        System.out.println(Arrays.toString(uf.arr));
        assertEquals(3, uf.find(1));

        uf.union(4, 5);
        System.out.println(Arrays.toString(uf.arr));
        uf.union(6, 7);
        System.out.println(Arrays.toString(uf.arr));
        uf.union(8, 9);
        System.out.println(Arrays.toString(uf.arr));
        uf.union(4, 8);
        System.out.println(Arrays.toString(uf.arr));
        uf.union(4, 6);
        System.out.println(Arrays.toString(uf.arr));


        assertEquals(9, uf.find(5));
        assertEquals(9, uf.find(7));
        assertEquals(9, uf.find(8));

        uf.union(9, 2);
        assertEquals(9, uf.find(3));
    }


    /**
     * Unions the same item with itself. Calls on find and checks that the outputs are correct.
     */
//    @Test
//    public void sameUnionTest() {
//        UnionFind uf = new UnionFind(4);
//        uf.union(1, 1);
//        for (int i = 0; i < 4; i += 1) {
//            assertThat(uf.find(i)).isEqualTo(i);
//        }
//    }
    @Test
    public void sameUnionTest() {
        UnionFind uf = new UnionFind(4);
        uf.union(1, 1);
        for (int i = 0; i < 4; i++) {
            assertEquals(i, uf.find(i));
        }
    }

    /**
     * Write your own tests below here to verify for correctness. The given tests are not comprehensive.
     * Specifically, you may want to write a test for path compression and to check for the correctness
     * of all methods in your implementation.
     */

    @Test
    public void testParentAndSize() {
        UnionFind uf = new UnionFind(10);
        uf.union(0, 1);
        System.out.println(Arrays.toString(uf.arr));
        assertEquals(1, uf.find(0));
        uf.union(2, 3);
        System.out.println(Arrays.toString(uf.arr));
        assertEquals(3, uf.find(2));
        uf.union(0, 2);
        System.out.println(Arrays.toString(uf.arr));
        assertEquals(3, uf.find(1));

        uf.union(4, 5);
        System.out.println(Arrays.toString(uf.arr));
        uf.union(6, 7);
        System.out.println(Arrays.toString(uf.arr));
        uf.union(8, 9);
        System.out.println(Arrays.toString(uf.arr));
        uf.union(4, 8);
        System.out.println(Arrays.toString(uf.arr));
        uf.union(4, 6);
        System.out.println(Arrays.toString(uf.arr));


        assertEquals(9, uf.find(5));
        assertEquals(9, uf.find(7));
        assertEquals(9, uf.find(8));

        uf.union(9, 2);
        assertEquals(9, uf.find(3));

//        assertEquals(6, uf.sizeOf(5));
//        assertEquals(6, uf.sizeOf(4));

        assertEquals(10, uf.parent(9));
        assertEquals(10, uf.parent(7));
        assertEquals(10, uf.parent(8));

    }

}


