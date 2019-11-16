import treeviewer.BinaryTreeViewer;

import java.util.Arrays;
import java.util.Random;

public class AD_Lab2Prep {
    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        displayDataBinaryTree(bst, "Binary Search Tree");

        RandomizedBinarySearchTree<Integer, Integer> rbst = new RandomizedBinarySearchTree<>();
        displayDataBinaryTree(rbst, "Randomized Binary Search Tree");

        WeightWatchingBinarySearchTrees<Integer, Integer> wwbst = new WeightWatchingBinarySearchTrees<>();
        displayDataBinaryTree(wwbst, "Weight Watching Binary Search Trees");

        evaluatePerformancePutAndGet();
    }

    private static void evaluatePerformancePutAndGet() {
        final int N = 1; //choose smaller N for testing

        // Implementation-Array
        BinarySearchTree[] bsts = new BinarySearchTree[]{
                new BinarySearchTree<>(),
                new RandomizedBinarySearchTree<>(),
                new WeightWatchingBinarySearchTrees<>()
        };

        for (BinarySearchTree bst : bsts) {
            for (int K = 1; K <= 10; K++) {
                int[] allNumbers = new int[10 * N];
                for (int i = 0; i < 10 * N; i++) allNumbers[i] = i + 1;

                shuffle(allNumbers);
            }
        }
    }

    private static void shuffle(int[] array){
        Random random = new Random();
        for( int i = 0; i < array.length; i++){
            int randomIndexToSwap = random.nextInt(array.length);
            int temp =  array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
        //Test shuffle with small N:
//        System.out.println(Arrays.toString(array));
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