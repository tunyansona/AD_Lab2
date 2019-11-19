import treeviewer.ViewableNode;
import treeviewer.ViewableTree;

public class WeightWatchingBinarySearchTrees<Key extends Comparable<? super Key>, Val> extends RandomizedBinarySearchTree<Key, Val> implements Iterable<Key>, ViewableTree {
    WeightWatchingBinarySearchTrees() {
        root = null;
    }

    private static class RNode<Key, Val> extends Node<Key, Val> {
        int W; // weight of the tree

        RNode(Key key, Val val) {
            super(key, val);
            W = 1;
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

    private RNode<Key, Val> rotR(RNode<Key, Val> h) {
        RNode<Key, Val> x = (RNode<Key, Val>) h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }

    private RNode<Key, Val> rotL(RNode<Key, Val> h) {
        RNode<Key, Val> x = (RNode<Key, Val>) h.right;
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
        root = putWWBST(((RNode<Key, Val>) root), key, val);
    }

    private RNode<Key, Val> putWWBST(RNode<Key, Val> x, Key key, Val val) {
        if (x == null) return new RNode<>(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            x.val = val;
            return x;
        }
        // flip coin whether to put it as root
        if (Math.random() * ((x).W + 1) < 1) {
            return putRoot(x, key, val);
        }
        // ok -- lost : does not become the root
        if (cmp < 0) {
            x.left = putWWBST(((RNode<Key, Val>) x.left), key, val);
            if (x.left.left != null) {
                if (x.right == null || ((RNode<Key, Val>) x.left.left).W > ((RNode<Key, Val>) x.right).W) {
                    x = rotR(x);
                }
            }
            if (x.left.right != null) {
                if (x.right == null || ((RNode<Key, Val>) x.left.right).W > ((RNode<Key, Val>) x.right).W) {
                    x = rotR(x);
                }
            }
        } else {
            x.right = putWWBST(((RNode<Key, Val>) x.right), key, val);
            if (x.right.right != null) {
                if (x.left == null || ((RNode<Key, Val>) x.right.right).W > ((RNode<Key, Val>) x.left).W) {
                    x = rotL(x);
                }
            }
            if (x.right.left != null) {
                if (x.left == null || ((RNode<Key, Val>) x.right).W > ((RNode<Key, Val>) x.left).W) {
                    x = rotL(x);
                }
            }
        }
        updateW(x); // update the weights
        return x;
    }

    private RNode<Key, Val> putRoot(RNode<Key, Val> x, Key key, Val val) {
        if (x == null) return new RNode<>(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp == 0) x.val = val;
        else if (cmp < 0) {
            x.left = putRoot(((RNode<Key, Val>) x.left), key, val);
            x = rotR(x);
        } else {
            x.right = putRoot(((RNode<Key, Val>) x.right), key, val);
            x = rotL(x);
        }
        return x;
    }
}
