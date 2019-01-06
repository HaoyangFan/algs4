package week1; // DON'T FORGET TO REMOVE IT FOR SUBMISSION!

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONSTANT = 1.96;
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Invalid parameters to constructor");
        }
        double[] thresholds = new double[trials];
        // run simulations in given number of times
        for (int i = 0; i < trials; i++) {
            thresholds[i] = simulate(n);
        }
        mean = StdStats.mean(thresholds);
        stddev = StdStats.stddev(thresholds);
        confidenceLo = mean - CONSTANT * stddev / Math.sqrt(trials);
        confidenceHi = mean + CONSTANT * stddev / Math.sqrt(trials);
    }

    private double simulate(int size) {
        Percolation percolation = new Percolation(size);
        // generate random row indices and col indices
        int[] indices = StdRandom.permutation(size * size);
        int i = 0;
        while (!percolation.percolates()) {
            int row = indices[i] / size, col = indices[i++] - row * size;
            percolation.open(row + 1, col + 1);
        }
        return (double) percolation.numberOfOpenSites() / (size * size);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidenceLo;
    }

    public double confidenceHi() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats p = new PercolationStats(size, trials);
        System.out.printf("mean                    = %f%n", p.mean());
        System.out.printf("stddev                  = %f%n", p.stddev());
        System.out.printf("95%% confidence interval = [%f, %f]%n", p.confidenceLo(), p.confidenceHi());
    }
}
