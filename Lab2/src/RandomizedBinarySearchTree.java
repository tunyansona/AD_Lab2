import treeviewer.ViewableTree;

public class RandomizedBinarySearchTree<Key extends Comparable<? super Key>, Val> extends BinarySearchTree<Key, Val> implements Iterable<Key>, ViewableTree {
    RandomizedBinarySearchTree() {
        root = null;
    }

    private static class RNode<Key, Val> extends Node<Key, Val> {
        int W; // weight of the tree

        RNode(Key key, Val val) {
            super(key, val);
            W = 1;
        }
    }

    private Node<Key, Val> rotR(Node<Key, Val> h) {
        Node<Key, Val> x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }

    private Node<Key, Val> rotL(Node<Key, Val> h) {
        Node<Key, Val> x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }

    private void updateW(RNode<Key, Val> x) {
        x.W = 1;
        if (x.left != null) x.W += ((RNode<Key, Val>) x.left).W;
        if (x.right != null) x.W += ((RNode<Key, Val>) x.right).W;
    }

    @Override
    public void put(Key key, Val val) {
        root = putRBST(((RNode<Key, Val>) root), key, val);
    }

    private RNode<Key, Val> putRBST(RNode<Key, Val> x, Key key, Val val) {
        if (x == null) return new RNode<>(key, val);
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
            x.left = putRBST(((RNode<Key, Val>) x.left), key, val);
        else
            x.right = putRBST(((RNode<Key, Val>) x.right), key, val);
        updateW(x); // update the weights
        return x;
    }

    private RNode<Key, Val> putRoot(RNode<Key, Val> x, Key key, Val val) {
        if (x == null) return new RNode<>(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp == 0) x.val = val;
        else if (cmp < 0) {
            x.left = putRoot(((RNode<Key, Val>) x.left), key, val);
            x = (RNode<Key, Val>) rotR(x);
        } else {
            x.right = putRoot(((RNode<Key, Val>) x.right), key, val);
            x = (RNode<Key, Val>) rotL(x);
        }
        return x;
    }
}