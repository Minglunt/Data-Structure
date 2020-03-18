package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;


public class PercolationStats {

    private int[] xList;
    private int size;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        xList = new int[T];
        size = T;
        for (int i = 0; i < T; i++){
            Percolation model = pf.make(N);
            while (!model.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                model.open(row, col);
            }
            xList[i] = model.numberOfOpenSites();
        }
    }

    public double mean() {
        int sum = 0;
        for (int i:xList) {
            sum += i;
        }
        return Math.round(sum/size);
    }

    public double stddev() {
        double sum = 0;
        double mean = this.mean();
        for (int i:xList) {
            sum += Math.pow((i - mean), 2);
        }
        return Math.sqrt(sum/size);
    }

    public double confidenceLow() {
        double mean = this.mean();
        double sigma = this.stddev();
        return mean - 1.96 * sigma / Math.sqrt(size);
    }

    public double confidenceHigh() {
        double mean = this.mean();
        double sigma = this.stddev();
        return mean + 1.96 * sigma / Math.sqrt(size);
    }

    // unit test
    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(10, 5, pf);
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
        System.out.println(ps.confidenceHigh());
        System.out.println(ps.confidenceLow());
    }
}
