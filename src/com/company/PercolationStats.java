package com.company;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int fieldSize;
    private int iterationsCount;
    private double openedSites[];

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        fieldSize = N;
        iterationsCount = T;
        openedSites = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);

            while (!p.percolates()) {
                int row, column;
                do {
                    row = StdRandom.uniform(1, N + 1);
                    column = StdRandom.uniform(1, N + 1);
                } while (p.isOpen(row, column));

                p.open(row, column);
            }

            openedSites[i] = openedRate(p);
        }
    }

    public double mean() {
        return StdStats.mean(openedSites);
    }

    public double stddev() {
        return StdStats.stddev(openedSites);
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev()) / java.lang.Math.sqrt(iterationsCount);
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / java.lang.Math.sqrt(iterationsCount);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new java.lang.IllegalArgumentException();
        }

        Integer N = Integer.parseInt(args[0]);
        Integer T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N, T);

        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }

    private double openedRate(Percolation p) {
        int opened = 0;
        for (int row = 1; row <= fieldSize; row++) {
            for (int column = 1; column <= fieldSize; column++) {
                if (p.isOpen(row, column)) {
                    opened++;
                }
            }
        }

        return (double)opened / (double)(fieldSize * fieldSize);
    }
}
