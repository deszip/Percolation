package com.company;

public class QuickUnion {

    private int[] id;
    private int[] sz;

    public QuickUnion(int N) {
        id = new int[N];
        sz = new int[N];

        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }

        logItems();
    }

    public int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }

        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        System.out.print(p + " - " + q + " ->  ");

        int i = root(p);
        int j = root(q);

        if (i == j) {
            return;
        }

        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }

        logItems();
    }

    private void logItems() {
        for (int i = 0; i < id.length; i++) {
            System.out.print(id[i] + " ");
        }

        System.out.print("   ");

        for (int i = 0; i < sz.length; i++) {
            System.out.print(sz[i] + " ");
        }

        System.out.println();
    }
}
