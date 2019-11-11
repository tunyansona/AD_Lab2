import java.util.*;

public class BinarySearchTree<Key extends Comparable<? super Key>, Val> extends AbstractST<Key, Val> implements Iterable<Key> {

    protected class Node {
        Key key;
        Val val;
        Node left, right;

        Node(Key key, Val val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node root;

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void put(Key key, Val val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Val val) {
        if (x == null)
            return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            x.val = val;
        else if (cmp < 0)
            x.left = put(x.left, key, val);
        else
            x.right = put(x.right, key, val);
        return x;
    }

    @Override
    public Val get(Key key) {
        Node x = find(key);
        if (x != null) return x.val;
        return null;
    }

    private Node find(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x;
            else if (cmp < 0) x = x.left;
            else x = x.right;
        }
        return null;
    }

    @Override
    public Iterator<Key> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator
            implements Iterator<Key> {
        private Stack<Node>
                stack = new Stack<>();

        private void pushLeft(Node x) {
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
            return x.key;
        }
    }

    @Override
    public void delete(Key key) {

    }
}
