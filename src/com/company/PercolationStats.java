package com.company;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        Percolation p = new Percolation(N);

        while (!p.percolates()) {
            // generate random pair from 0 to N until it's closed
            int i, j;

            do {
                i = StdRandom.uniform(1, N+1);
                j = StdRandom.uniform(1, N+1);
            } while (p.isOpen(i, j));

            // open site with generated indexes
            p.open(i, j);
        }

        System.out.println("mean = " + mean());
        System.out.println("stddev = " + stddev());
        System.out.println("95% confidence interval = " + confidenceLo() + ", " + confidenceHi());
    }

    public double mean() {
        return 0.0;
    }

    public double stddev() {
        return 0.0;
    }

    public double confidenceLo() {
        return 0.0;
    }

    public double confidenceHi() {
        return 0.0;
    }

    public static void main(String[] args) {
        // Arguments checks
        if (args.length != 2) {
            throw new java.lang.IllegalArgumentException();
        }

        // Create PercolationStats
        Integer N = Integer.parseInt(args[0]);
        Integer T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N, T);


    }
}
