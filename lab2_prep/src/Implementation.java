import treeviewer.ViewableTree;
import treeviewer.ViewableNode;

public class Implementation<Key extends Comparable<? super Key>, Val> extends WeightWatchingBinarySearchTrees<Key, Val> implements Iterable<Key>, ViewableTree {
    final int N = 1000000; //choose smaller N for testing
    BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
    RandomizedBinarySearchTree<Integer, Integer> rbst = new RandomizedBinarySearchTree<>();
    WeightWatchingBinarySearchTrees<Integer, Integer> wwbst = new WeightWatchingBinarySearchTrees<>();

    // Implementation-Array
    Implementation[] implementations = new Implementation[]{
            (Implementation) bst, (Implementation) rbst, (Implementation) wwbst
    };

    

}
