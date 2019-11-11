import treeviewer.BinaryTreeViewer;
import java.util.Random;

public class AD_Lab2Prep {
    public static void main(String args[]) {
        Random rnd = new Random(42);
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();

        for (int i = 0; i < 10; i++) {
            int k = rnd.nextInt(100);
            bst.put(k, i);
            System.out.println("Put (" + k + ", " + i + ")");
        }
        for (int i = 0; i < 100; i++) {
            Integer v = bst.get(i);
            if (v != null) {
                System.out.println("got k = " + i + " and value " + v);
            }
        }

        BinaryTreeViewer btv = new BinaryTreeViewer();
        btv.setTree(bst);
    }
}
