package com.company;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF qu;
    private boolean opened[][];
    private int fieldSize;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        fieldSize = N;
        opened = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                opened[i][j] = false;
            }
        }

        qu = new WeightedQuickUnionUF(N * N + 2);
    }

    public void open(int i, int j) {
        if (i > fieldSize || i < 1 || j > fieldSize || j < 1) {
            throw new IndexOutOfBoundsException();
        }

        if (isOpen(i, j)) {
            return;
        }

        int currentIndex = xyTo1D(i, j);
        if (i == 1) {
            qu.union(currentIndex, 0);
        } else if (i == fieldSize) {
            qu.union(currentIndex, fieldSize*fieldSize + 1);
        }

        if (i > 1 && isOpen(i - 1, j)) {
            qu.union(currentIndex, xyTo1D(i - 1, j));
        }

        if (j > 1 && isOpen(i, j - 1)) {
            qu.union(currentIndex, xyTo1D(i, j - 1));
        }

        if (i < fieldSize && isOpen(i + 1, j)) {
            qu.union(currentIndex, xyTo1D(i + 1, j));
        }

        if (j < fieldSize && isOpen(i, j + 1)) {
            qu.union(currentIndex, xyTo1D(i, j + 1));
        }

        opened[i-1][j-1] = true;
    }

    public boolean isOpen(int i, int j) {
        if (i > fieldSize || i < 1 || j > fieldSize || j < 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }

        return opened[i-1][j-1];
    }

    public boolean isFull(int i, int j) {
        if (i > fieldSize || i < 1 || j > fieldSize || j < 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }

        int index = (i * fieldSize) - (fieldSize - j);
        return qu.connected(0, index);
    }

    public boolean percolates() {
        return qu.connected(0, fieldSize * fieldSize + 1);
    }

    private int xyTo1D(int x, int y) {
        return (x * fieldSize) - (fieldSize - y);
    }

}
