import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestRedBlackTree {

    @Test
    public void testBasicRotateRight() {
        RedBlackTree<Integer> rbtree = new TestableRedBlackTree();
        assertNull(rbtree.root, "Root should be null initially");

        RedBlackTree.RBTreeNode<Integer> node1 = new RedBlackTree.RBTreeNode<>(true, 10, null, null);
        RedBlackTree.RBTreeNode<Integer> node2 = new RedBlackTree.RBTreeNode<>(false, 9, null, null);
        RedBlackTree.RBTreeNode<Integer> node3 = new RedBlackTree.RBTreeNode<>(false, 8, null, null);
        node1.left = node2;
        node2.left = node3;

        RedBlackTree.RBTreeNode<Integer> newRoot = rbtree.rotateRight(node1);
        assertEquals(9, newRoot.item, "Root item should be 9 after rotate right");
        assertEquals(10, newRoot.right.item, "Right child should be 10 after rotate right");
        assertEquals(8, newRoot.left.item, "Left child should be 8 after rotate right");
    }

    @Test
    public void testInsertSimple() {
        RedBlackTree<Integer> rbtree = new TestableRedBlackTree();
        assertNull(rbtree.root, "Root should be null initially");

        rbtree.insert(10);
        assertNotNull(rbtree.root, "Root should not be null after insertion");
        assertTrue(rbtree.root.isBlack, "Root should be black");
        assertEquals(10, rbtree.root.item, "Root item should be 10");

        assertNull(rbtree.root.left, "Left child of root should be null");
        assertNull(rbtree.root.right, "Right child of root should be null");

        rbtree.insert(5);
        assertNotNull(rbtree.root.left, "Left child of root should not be null after inserting 5");
        assertFalse(rbtree.root.left.isBlack, "Left child should be red");
        assertEquals(5, rbtree.root.left.item, "Left child item should be 5");
    }





    // ...转换其他测试用例，确保所有的assertThat调用都被替换为相应的JUnit 5断言

    /*
     * Helper class to test the number of calls to fixing operations in your RedBlackTree implementation.
     */
    class TestableRedBlackTree extends RedBlackTree<Integer> {
        @Override
        void flipColors(RBTreeNode<Integer> node) {
            callsToFlipColors++;
            super.flipColors(node);
        }

        @Override
        RBTreeNode<Integer> rotateRight(RBTreeNode<Integer> node) {
            callsToRotateRight++;
            return super.rotateRight(node);
        }

        @Override
        RBTreeNode<Integer> rotateLeft(RBTreeNode<Integer> node) {
            callsToRotateLeft++;
            return super.rotateLeft(node);
        }
    }

    private int callsToFlipColors = 0;
    private int callsToRotateRight = 0;
    private int callsToRotateLeft = 0;
}
