import java.util.*;
import treeviewer.ViewableNode;
import treeviewer.ViewableTree;

public class BinarySearchTree<Key extends Comparable<? super Key>, Val> extends AbstractST<Key, Val> implements Iterable<Key>, ViewableTree {

    protected class Node<Key, Val> implements ViewableNode {
        protected Key key;
        protected Val val;
        protected Node<Key, Val> left, right;

        public Node(Key key, Val val) {
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

    protected Node<Key, Val> root;

    public BinarySearchTree() {
        root = null;
    }

    private class BSTIterator implements Iterator<Key> {
        private Stack<Node> stack = new Stack<>();

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
            Node x = stack.pop();
            pushLeft(x.right);
            return (Key) x.key;
        }
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
            return new Node(key, val);
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
}