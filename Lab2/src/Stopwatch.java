class Stopwatch {
    private static long start;

    static void tic() {
        start = System.currentTimeMillis();
    }

    static double toc() {
        long stop = System.currentTimeMillis();
        return (stop - start) / 1000.0;
    }
}
