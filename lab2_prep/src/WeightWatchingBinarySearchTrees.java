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

        RNode<Key, Val> left, right;

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
        RNode<Key, Val> x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }

    private RNode<Key, Val> rotL(RNode<Key, Val> h) {
        RNode<Key, Val> x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }

    private void updateW(RNode<Key, Val> x) {
        x.W = 1; // no recursive descent !
        if (x.left != null) x.W += ((RNode<Key, Val>) x.left).W;
        if (x.right != null) x.W += ((RNode<Key, Val>) x.right).W;
    }

    @Override
    public void put(Key key, Val val) {
        root = putWWBST(((RNode<Key, Val>) root), key, val);

    }

//    private boolean isParent(Node<Key, Val> x) {
//        return x != null && (x.left != null || x.right != null);
//    }
//
//    private RNode<Key, Val> comparedWeight(RNode<Key, Val> x, RNode<Key, Val> y){
//        return x.W > y.W
//                ? x
//                : y;
//    }

    private RNode<Key, Val> putWWBST(RNode<Key, Val> x, Key key, Val val) {
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
        if (cmp < 0) {
            x.left = putWWBST(((RNode<Key, Val>) x.left), key, val);
            if (x.right != null && x.left.left != null) {
                if (x.left.left.W > x.right.W) {
                    rotR(x.left);
                }
            }
        } else {
            x.right = putWWBST(((RNode<Key, Val>) x.right), key, val);
            if (x.left != null && x.right.right != null) {
                if (x.right.right.W > x.left.W) {
                    rotL(x.right);
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
            x = (RNode<Key, Val>) rotR(x);
        } else {
            x.right = putRoot(((RNode<Key, Val>) x.right), key, val);
            x = (RNode<Key, Val>) rotL(x);
        }
        return x;
    }
}
