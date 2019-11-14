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
        x.W = 1; // no recursive descent !
        if (x.left != null) x.W += ((RNode<Key, Val>) x.left).W;
        if (x.right != null) x.W += ((RNode<Key, Val>) x.right).W;
    }

}
