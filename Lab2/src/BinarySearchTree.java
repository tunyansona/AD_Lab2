import java.util.*;

import treeviewer.ViewableNode;
import treeviewer.ViewableTree;

public class BinarySearchTree<Key extends Comparable<? super Key>, Val>
        extends AbstractST<Key, Val>
        implements Iterable<Key>, ViewableTree {

    protected static class Node<Key, Val> implements ViewableNode {
        Key key;
        Val val;
        Node<Key, Val> left, right;

        Node(Key key, Val val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public Object key() {
            return key;
        }

        @Override
        public ViewableNode left() {
            return left;
        }

        @Override
        public ViewableNode right() {
            return right;
        }
    }

    private class BSTIterator implements Iterator<Key> {
        private Stack<Node<Key, Val>> stack = new Stack<>();

        private void pushLeft(Node<Key, Val> x) {
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
        }

        BSTIterator() {
            pushLeft(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public void remove() {
        }

        public Key next() {
            Node<Key, Val> x = stack.pop();
            pushLeft(x.right);
            return x.key;
        }
    }

    Node<Key, Val> root;

    BinarySearchTree() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void put(Key key, Val val) {
        root = put(root, key, val);
    }

    private Node<Key, Val> put(Node<Key, Val> x, Key key, Val val) {
        if (x == null)
            return new Node<>(key, val);
        int cmp = x.key.compareTo(key);
        if (cmp == 0)
            x.val = val;
        else if (cmp > 0)
            x.left = put(x.left, key, val);
        else
            x.right = put(x.right, key, val);
        return x;
    }

    @Override
    public Val get(Key key) {
        Node<Key, Val> x = root;
        while (x != null) {
            int cmp = x.key.compareTo(key);
            if (cmp == 0) return x.val;
            else if (cmp > 0) x = x.left;
            else x = x.right;
        }
        return null;
    }

    @Override
    public Iterator<Key> iterator() {
        return new BSTIterator();
    }

    @Override
    public void delete(Key key) {
        // Can be omitted
    }

    @Override
    public ViewableNode root() {
        return root;
    }

    int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node<Key, Val> x) {
        return x == null || isLeaf(x)
                ? 0
                : Integer.max(getHeight(x.left), getHeight(x.right)) + 1;
    }

    private boolean isLeaf(Node<Key, Val> x) {
        return x != null && x.left == null && x.right == null;
    }

    float getAverageDistance() {
        return getAverageDistance(root);
    }

    private float getAverageDistance(Node<Key, Val> x) {
        return x == null || isLeaf(x)
                ? 0
                : (weightedAverageDistance(x.left) + weightedAverageDistance(x.right)) / getWeight(x);
    }

    private float weightedAverageDistance(Node<Key, Val> x) {
        return (getAverageDistance(x) + 1) * getWeight(x);
    }

    private int getWeight(Node<Key, Val> x) {
        if (x == null) {
            return 0;
        }
        return isLeaf(x)
                ? 1
                : getWeight(x.left) + getWeight(x.right) + 1;
    }
}