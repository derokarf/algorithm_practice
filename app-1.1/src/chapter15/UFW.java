package chapter15;

import libBook.StdIn;
import libBook.StdOut;

public class UFW {

    private int[] id;
    private int[] hg; //высоты
    private int count;

    public UFW(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        hg = new int[N];
        for (int i = 0; i < N; i++) {
            hg[i] = 0;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return;
        }
        if (hg[i] < hg[j]) {
            id[i] = j;

        } else if (hg[i] > hg[j]) {
            id[j] = i;
        } else {
            id[j] = i;
            hg[i]++;
        }
        count--;
    }

    private void hg_print() {
        for (int i = 0; i < hg.length; i++) {
            System.out.println("hg[" + String.valueOf(i) + "]=" + String.valueOf(hg[i]));
        }
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        UFW uf = new UFW(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
        uf.hg_print();
    }
}
