public class RedBlackTree<T extends Comparable<T>> {

    /* Root of the tree. */
    RBTreeNode<T> root;

    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /**
         * Creates a RBTreeNode with item ITEM and color depending on ISBLACK
         * value.
         * @param isBlack
         * @param item
         */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /**
         * Creates a RBTreeNode with item ITEM, color depending on ISBLACK
         * value, left child LEFT, and right child RIGHT.
         * @param isBlack
         * @param item
         * @param left
         * @param right
         */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Creates an empty RedBlackTree.
     */
    public RedBlackTree() {
        root = null;
    }

    /**
     * Flips the color of node and its children. Assume that NODE has both left
     * and right children
     * @param node
     */
    void flipColors(RBTreeNode<T> node) {
        if (node == null) return;
        node.isBlack = !node.isBlack;
        flipColors(node.left);
        flipColors(node.right);
        // TODO: YOUR CODE HERE
    }

    /**
     * Rotates the given node to the right. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * @param node
     * @return
     */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        assert node != null;
        if (node.left == null) throw new IllegalArgumentException("node.left should not be null");

        RBTreeNode<T> newHead = node.left;
        node.left = newHead.right;
        newHead.right = node;
        node.left = null;
        return newHead;
    }

    /**
     * Rotates the given node to the left. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * @param node
     * @return
     */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        assert node != null;
        if (node.right == null) throw new IllegalArgumentException("node.right should not be null");

        RBTreeNode<T> newHead = node.right;
        node.right = newHead.left;
        newHead.left = node;
        return newHead;
    }

    /**
     * Helper method that returns whether the given node is red. Null nodes (children or leaf
     * nodes) are automatically considered black.
     * @param node
     * @return
     */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

    /**
     * Inserts the item into the Red Black Tree. Colors the root of the tree black.
     * @param item
     */
    public void insert(T item) {
        root = insert(root, item);
        root.isBlack = true;
    }

    /**
     * Inserts the given node into this Red Black Tree. Comments have been provided to help break
     * down the problem. For each case, consider the scenario needed to perform those operations.
     * Make sure to also review the other methods in this class!
     * @param node
     * @param item
     * @return
     */
    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
        // TODO: Insert (return) new red leaf node.
        RBTreeNode<T> rbTreeNode = insertBST(node, item); // new root node
        // TODO: Handle normal binary search tree insertion.

        // TODO: Rotate left operation
        RBTreeNode<T> grandParent = findGrandParent(rbTreeNode, item);
        RBTreeNode<T> uncle = findUncle(rbTreeNode, item);
        RBTreeNode<T> parent = findParent(rbTreeNode, item);

        if (uncle == null || uncle.isBlack) {
            if (parent != null && item.compareTo(parent.item) > 0) {
                rotateLeft(parent);
            } else if (parent != null && item.compareTo(parent.item) < 0 && grandParent != null) {
                rotateRight(grandParent);
                flipColors(grandParent);
            }
        } else {
            flipColors(grandParent);
        }
        // TODO: Rotate right operation
//        rotateRight(node);
        // TODO: Color flip
//        flipColors(node);
        return rbTreeNode; //fix this return statement
    }

    /**
     * all the node imputed should be red
     * @param node target node to add the item
     * @param item to be added
     * @return RedBlackTree.RBTreeNode<T>
     * on 7/3/24 6:34 PM
     */
    private RBTreeNode<T> insertBST(RBTreeNode<T> node, T item) {
        if (node == null) {
            return new RBTreeNode<>(false, item);
        }
        T currentItem = node.item;

        if (currentItem.compareTo(item) < 0) {
            node.right = insertBST(node.right, item);
        } else if (currentItem.compareTo(item) > 0) {
            node.left = insertBST(node.left, item);
        }
        return node;
    }

    /**
     * find the uncle node of item
     * @param node  target tree
     * @param item  target node as inputted
     * @return RedBlackTree.RBTreeNode<T>
     * on 7/3/24 7:13 PM
     */
    private RBTreeNode<T> findUncle(RBTreeNode<T> node, T item) {
        // findUncle is not supported
        if (node == null || node.right == null && node.left == null) {
            return null;
        }

        // record the parent node and the grandparent node
        RBTreeNode<T> grandParent = findGrandParent(node, item);
        if (grandParent == null) return null;
        return getUncle(grandParent, item);
    }

    private RBTreeNode<T> findParent(RBTreeNode<T> node, T item) {
        // findUncle is not supported
        if (node == null || node.right == null && node.left == null) {
            return null;
        }

        RBTreeNode<T> left = node.left;
        RBTreeNode<T> right = node.right;
        if (left == null || right == null) return null;
        if (left.left.item.equals(item) || left.right.item.equals(item)) {
            return left;
        } else {
            return right;
        }
    }

    private RBTreeNode<T> getUncle(RBTreeNode<T> node, T item) {
        RBTreeNode<T> left = node.left;
        RBTreeNode<T> right = node.right;
        if (left == null && right != null || left != null && right == null) return null;
        if (left.left.item.equals(item) || left.right.item.equals(item)) {
            return right;
        } else {
            return left;
        }
    }

    private RBTreeNode<T> findGrandParent(RBTreeNode<T> node, T item) {
        if (node.left != null && node.left.right != null && node.left.right.item.equals(item)
        || node.left != null && node.left.left != null && node.left.left.item.equals(item)
        || node.right != null && node.right.left != null && node.right.left.item.equals(item)
        || node.right != null && node.right.right != null && node.right.right.item.equals(item)) {
            return node;
        }
        if (node.left != null) return findUncle(node.left, item);
        if (node.right != null) return findUncle(node.right, item);
        return null;
    }


}
