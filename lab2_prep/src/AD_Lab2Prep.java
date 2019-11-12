import treeviewer.BinaryTreeViewer;
import java.util.Random;

public class AD_Lab2Prep {
    public static void main(String args[]) {
        Random rnd = new Random(42);
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();

        System.out.println("Binary Search Tree");
        for (int i = 0; i < 10; i++) {
            int k = rnd.nextInt(100);
            bst.put(k, i);
//            System.out.println("Put (" + k + ", " + i + ")");
        }
        for (int i = 0; i < 100; i++) {
            Integer v = bst.get(i);
            if (v != null) {
//                System.out.println("got k = " + i + " and value " + v);
            }
        }

        System.out.println(bst.getAverageDistance());

        BinaryTreeViewer btv1 = new BinaryTreeViewer();
        btv1.setTree(bst);


        // Test Randomized Binary Search Tree
        RandomizedBinarySearchTree<Integer, Integer> rbst = new RandomizedBinarySearchTree<Integer, Integer>();

        System.out.println("Randomized Binary Search Tree");
        for (int i = 0; i < 10; i++) {
            int k = rnd.nextInt(100);
            rbst.put(k, i);
//            System.out.println("Put (" + k + ", " + i + ")");
        }
        for (int i = 0; i < 100; i++) {
            Integer v = rbst.get(i);
            if (v != null) {
//                System.out.println("got k = " + i + " and value " + v);
            }
        }

        System.out.println(rbst.getAverageDistance());

        BinaryTreeViewer btv2 = new BinaryTreeViewer();
        btv2.setTree(rbst);
    }
}
