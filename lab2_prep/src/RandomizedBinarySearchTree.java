import java.util.*;

public class RandomizedBinarySearchTree<Key extends Comparable<? super Key>, Val> extends AbstractST<Key, Val> implements Iterable<Key> {

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

    protected class RNode extends Node {
        int W; // weight of the tree

        RNode(Key key, Val val) {
            super(key, val);
            W = 1;
        }
    }

    private Node rotR(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }

    private Node rotL(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }

    private void updateW(RNode x) {
        x.W = 1; // no recursive descent !
        if (x.left != null) x.W += ((RNode) x.left).W;
        if (x.right != null) x.W += ((RNode) x.right).W;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void put(Key key, Val val) {
        root = putRBST(((RNode) root), key, val);
    }

    private RNode putRBST(RNode x, Key key, Val val) {
        if (x == null) return new RNode(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            x.val = val;
            return x;
        }
        // flip coin whether to put it as root
        if (Math.random() * ((x).W + 1) < 1)
            return putRoot(x, key, val);
        // ok -- lost : does not become the root
        if (cmp < 0)
            x.left = putRBST(((RNode) x.left), key, val);
        else
            x.right = putRBST(((RNode) x.right), key, val);
        updateW(x); // update the weights
        return x;
    }

    private RNode putRoot(RNode x, Key key, Val val) {
        if (x == null) return new RNode(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp == 0) x.val = val;
        else if (cmp < 0) {
            x.left = putRoot(((RNode) x.left), key, val);
            x = (RNode) rotR(x);
        } else {
            x.right = putRoot(((RNode) x.right), key, val);
            x = (RNode) rotL(x);
        }
        return x;
    }

    @Override
    public Val get(Key key) {
        return null;
    }

    @Override
    public Iterator<Key> iterator() {
        return null;
    }

    @Override
    public void delete(Key key) {

    }
}