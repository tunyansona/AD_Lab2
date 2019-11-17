import treeviewer.BinaryTreeViewer;

import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Random;

public class AD_Lab2Prep {
    public static void main(String[] args) {
//        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
//        displayDataBinaryTree(bst, "Binary Search Tree");
//
//        RandomizedBinarySearchTree<Integer, Integer> rbst = new RandomizedBinarySearchTree<>();
//        displayDataBinaryTree(rbst, "Randomized Binary Search Tree");
//
//        WeightWatchingBinarySearchTrees<Integer, Integer> wwbst = new WeightWatchingBinarySearchTrees<>();
//        displayDataBinaryTree(wwbst, "Weight Watching Binary Search Trees");

        evaluatePerformancePutAndGet();
    }

    private static void evaluatePerformancePutAndGet() {
        final int N = 100000; //choose smaller N for testing
        Random rnd = new Random();

        // Implementation-Array
        BinarySearchTree[] bsts = new BinarySearchTree[]{
                new BinarySearchTree<>(),
                new RandomizedBinarySearchTree<>(),
                new WeightWatchingBinarySearchTrees<>()
        };

        for (BinarySearchTree bst : bsts) {
            System.out.println("\n" + bst.toString());
            double averageDistance = 0;
            int height =0;
            double getAverageTime = 0;
            double putAverageTime = 0;
            int keyProbability = 10;

            for (int K = 1; K <= keyProbability; K++) {
                int[] allNumbers = new int[10 * N];
                for (int i = 0; i < 10 * N; i++) allNumbers[i] = i + 1;
                shuffle(allNumbers);

                // 8a)
                for (int i = 0; i < K * N; i++) {
                    bst.put(allNumbers[i], allNumbers[i]);
                }

                // 8b)
                averageDistance += bst.getAverageDistance();
                height += bst.getHeight();

                // 8c)
                Stopwatch sw = new Stopwatch();

                // Timer for get
                Integer[] toGet = new Integer[N];
                for (int i = 0; i < N; i++) {
                    toGet[i] = rnd.nextInt(10 * N) + 1;
                }
                sw.tic();
                for (int i = 0; i < N; i++) {
                    bst.get(toGet[i]);
                }
                getAverageTime += sw.toc();

                // Timer for put
                Integer[] toPut = new Integer[N];
                for (int i = 0; i < N; i++) {
                    toPut[i] = rnd.nextInt(10 * N) + 1;
                }
                sw.tic();
                for (int i = 0; i < N; i++) {
                    bst.put(toPut[i], toPut[i]);
                }
                putAverageTime += sw.toc();
            }

            System.out.println("Average Distance: " + averageDistance / keyProbability);
            System.out.println("Average Height: " + height / keyProbability);
            System.out.println("Get time: " + getAverageTime / keyProbability);
            System.out.println("Put time: " + putAverageTime / keyProbability);
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