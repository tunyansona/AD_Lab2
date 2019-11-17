public class Stopwatch {
    private static long start;

    public static void tic() {
        start = System.currentTimeMillis();
    }

    public static double toc() {
        long stop = System.currentTimeMillis();
        return (stop - start) / 1000.0;
    }
}
