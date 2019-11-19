import treeviewer.BinaryTreeViewer;

import java.util.Arrays;
import java.util.Random;

public class main {
    public static void main(String[] args) {
        final int N = 1000; //choose smaller N for testing

        displayBSTs(N); // Test with N = 1000 or smaller
        evaluatePerformancePutAndGet(N);
    }

    private static void displayBSTs(int N) {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        displayDataBinaryTree(bst, "Binary Search Tree", N);

        RandomizedBinarySearchTree<Integer, Integer> rbst = new RandomizedBinarySearchTree<>();
        displayDataBinaryTree(rbst, "Randomized Binary Search Tree", N);

        WeightWatchingBinarySearchTrees<Integer, Integer> wwbst = new WeightWatchingBinarySearchTrees<>();
        displayDataBinaryTree(wwbst, "Weight Watching Binary Search Trees", N);
    }

    private static void displayDataBinaryTree(BinarySearchTree<Integer, Integer> bst, String name, int N) {
        Random rnd = new Random(42);
        System.out.println("\n" + name);
        for (int i = 0; i < N; i++) {
            int k = rnd.nextInt(N);
            bst.put(k, i);
            // System.out.println("Put (" + k + ", " + i + ")");
        }
        for (int i = 0; i < N; i++) {
            Integer v = bst.get(i);
            if (v != null) {
                // System.out.println("Node with key " + i + " has value " + v);
            }
        }

        System.out.println("Tree height: " + bst.getHeight());
        System.out.println("Average Distance: " + bst.getAverageDistance());

        BinaryTreeViewer btv = new BinaryTreeViewer();
        btv.setTree(bst);
    }

    private static void evaluatePerformancePutAndGet(int N) {
        Random rnd = new Random();

        // Implementation-Array
        BinarySearchTree[] bsts = new BinarySearchTree[]{
                new BinarySearchTree<>(),
                new RandomizedBinarySearchTree<>(),
                new WeightWatchingBinarySearchTrees<>()
        };

        for (BinarySearchTree bst : bsts) {
            double[] averageDistance = new double[10];
            int[] height = new int[10];
            double[] getAverageTime = new double[10];
            double[] putAverageTime = new double[10];
            int keyProbability = 10;

            System.out.println("\n" + bst.toString());

            for (int K = 1; K <= keyProbability; K++) {
                bst.root = null;
                int[] allNumbers = new int[keyProbability * N];
                for (int i = 0; i < keyProbability * N; i++) {
                    allNumbers[i] = i + 1;
                }
                shuffle(allNumbers);

                // 8a)
                for (int i = 0; i < K * N; i++) {
                    bst.put(allNumbers[i], allNumbers[i]);
                }

                // 8b)
                averageDistance[K - 1] = bst.getAverageDistance();
                height[K - 1] = bst.getHeight();

                // 8c)
                Stopwatch sw = new Stopwatch();

                // Timer for get
                Integer[] toGet = new Integer[N];
                for (int i = 0; i < N; i++) {
                    toGet[i] = rnd.nextInt(keyProbability * N) + 1;
                }
                sw.tic();
                for (int i = 0; i < N; i++) {
                    bst.get(toGet[i]);
                }
                getAverageTime[K - 1] = sw.toc();

                // Timer for put
                Integer[] toPut = new Integer[N];
                for (int i = 0; i < N; i++) {
                    toPut[i] = rnd.nextInt(keyProbability * N) + 1;
                }
                sw.tic();
                for (int i = 0; i < N; i++) {
                    bst.put(toPut[i], toPut[i]);
                }
                putAverageTime[K - 1] = sw.toc();
            }

            // Display to the console all results
            System.out.println("Average Distance: " + Arrays.toString(averageDistance));
            System.out.println("Average Height: " + Arrays.toString(height));
            System.out.println("Get time: " + Arrays.toString(getAverageTime));
            System.out.println("Put time: " + Arrays.toString(putAverageTime));
        }
    }

    private static void shuffle(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = random.nextInt(array.length);
            int temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
    }
}