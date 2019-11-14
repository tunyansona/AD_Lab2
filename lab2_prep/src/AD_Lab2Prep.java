import treeviewer.BinaryTreeViewer;

import java.util.Random;

public class AD_Lab2Prep {
    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        displayDataBinaryTree(bst, "Binary Search Tree");

        RandomizedBinarySearchTree<Integer, Integer> rbst = new RandomizedBinarySearchTree<>();
        displayDataBinaryTree(rbst, "Randomized Binary Search Tree");

        WeightWatchingBinarySearchTrees<Integer, Integer> wwbst = new WeightWatchingBinarySearchTrees<>();
        displayDataBinaryTree(wwbst, "Weight Watching Binary Search Trees");
    }

    private static void displayDataBinaryTree(BinarySearchTree<Integer, Integer> bst, String name) {
        Random rnd = new Random(42);
        System.out.println("\n" + name);
        for (int i = 0; i < 10; i++) {
            int k = rnd.nextInt(100);
            bst.put(k, i);
            System.out.println("Put (" + k + ", " + i + ")");
        }
        for (int i = 0; i < 100; i++) {
            Integer v = bst.get(i);
            if (v != null) {
                System.out.println("Node with key " + i + " has value " + v);
            }
        }

        System.out.println("Tree height: " + bst.getHeight());
        System.out.println("Average Distance: " + bst.getAverageDistance());

        BinaryTreeViewer btv = new BinaryTreeViewer();
        btv.setTree(bst);
    }
}