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

        //System.out.println("Open: " + i + " - " + j);

        // Skip if opened
        if (isOpen(i, j)) {
            return;
        }

        int currentIndex = (i * fieldSize) - (fieldSize - j);

        //System.out.println("Index: " + currentIndex);

        // Connect to virtual sites
        if (i == 1) {
            qu.union(currentIndex, 0);
        } else if (i == fieldSize) {
            qu.union(currentIndex, fieldSize*fieldSize + 1);
        }

        // Check neighbours
        // Top
        if (i > 1 && isOpen(i - 1, j)) {
            int topIndex = ((i - 1) * fieldSize) - (fieldSize - j);
            //System.out.println("\tTop: " + topIndex);
            qu.union(currentIndex, topIndex);
        }

        // Left
        if (j > 1 && isOpen(i, j - 1)) {
            int leftIndex = (i * fieldSize) - (fieldSize - j - 1);
            //System.out.println("\tTop: " + leftIndex);
            qu.union(currentIndex, leftIndex);
        }

        // Bottom
        if (i < fieldSize && isOpen(i + 1, j)) {
            int bottomIndex = ((i + 1) * fieldSize) - (fieldSize - j);
            //System.out.println("\tTop: " + bottomIndex);
            qu.union(currentIndex, bottomIndex);
        }

        // Right
        if (j < fieldSize && isOpen(i, j + 1)) {
            int rightIndex = (i * fieldSize) - (fieldSize - j + 1);
            //System.out.println("\tTop: " + rightIndex);
            qu.union(currentIndex, rightIndex);
        }

        // Mark site as opened
        opened[i-1][j-1] = true;

        System.out.println("Opened: " + currentIndex + "(" + i + ":" + j + ")");
    }

    public boolean isOpen(int i, int j) {
        if (i > fieldSize || i < 1 || j > fieldSize || j < 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }

        //System.out.println(i + ":" + j + " -> " + opened[i-1][j-1]);

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

}
