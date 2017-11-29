package chapter15;

import libBook.StdIn;

public class WeightedQuickUnionDynamicSize {

    private SiteList siteList;
    private int count = 0; // number of components

    public WeightedQuickUnionDynamicSize() {
        siteList = new SiteList();
    }

    public int count() {
        return siteList.size() - count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int p) {
        while (p != siteList.getSite(p).id) {
            p = siteList.getSite(p).id;
        }
        return p;
    }

    public int newSite(int site) {
        if (siteList.size() <= site) {
            return siteList.addSite(site);
        }
        return siteList.getSite(site).id;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return;
        }
// Make smaller root point to larger one.
        if (siteList.getSite(i).sz < siteList.getSite(j).sz) {
            siteList.getSite(i).id = j;
            siteList.getSite(j).sz += siteList.getSite(i).sz;
        } else {
            siteList.getSite(j).id = i;
            siteList.getSite(i).sz += siteList.getSite(j).sz;
        }
        count++;
    }

    public static void main(String[] args) { // Solve dynamic connectivity problem on StdIn.
        WeightedQuickUnionDynamicSize uf = new WeightedQuickUnionDynamicSize(); // Initialize N components.
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt(); // Read pair to connect.
            uf.newSite(q);
            uf.newSite(p);
            if (uf.connected(p, q)) {
                continue; // Ignore if connected.
            }
            uf.union(p, q); // Combine components
            System.out.println(p + " " + q); // and print connection.
        }
        System.out.println(uf.count() + " components");
    }

}
