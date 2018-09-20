import StdLib.StdListOut;
import StdLib.StdRandom;

public class MaxPQ<Key extends Comparable<Key>> {
  private Key[] pq;// Heap-ordered complete binary tree
  private int N;

  public MaxPQ(Key[] a) {
    pq = a;
    N = pq.length;
    for (int k = 2; k <= N; k++) {
      swim(k);
    }
  }

  // Returns array with binary tree
  private Key[] getPQ() {
    return pq;
  }

  public boolean isEmpty() {
    return N == 0;
  }

  public int size() {
    return N;
  }

  public Key delMax() {
    Key max;
    // Here and below, if we work with pq array, indices less than 1,
    // because we work with a[0...N-1] elements
    max = pq[0]; // Retrieve max key from top.
    exch(1, N--); // Exchange with last item.
    pq[N] = null; // Avoid loitering.
    sink(1); // Restore heap property.
    return max;
  }

  private boolean less(int i, int j) {
    return pq[i - 1].compareTo(pq[j - 1]) < 0;
  }

  private void exch(int i, int j) {
    Key t = pq[i - 1];
    pq[i - 1] = pq[j - 1];
    pq[j - 1] = t;
  }

  private void swim(int k) {
    while (k > 1 && less(k / 2, k)) {
      exch(k / 2, k);
      k = k / 2;
    }
  }

  private void sink(int k) {
    while (2 * k <= N) {
      int j = 2 * k;
      if (j < N && less(j, j + 1)) {
        j++;
      }
      if (!less(k, j)) {
        break;
      }
      exch(k, j);
      k = j;
    }
  }

  public static void main(String[] args) {
    Integer[] list = new Integer[25];
    for (int i = 0; i < list.length; i++) {
      list[i] = StdRandom.uniform(list.length);
    }
    StdListOut.lineList(list);
    MaxPQ<Integer> testPQ = new MaxPQ<>(list);
    StdListOut.lineList(testPQ.getPQ());

    // Testing class
    StringBuilder str = new StringBuilder("Sorted: ");
    while (!testPQ.isEmpty()) {
      str.append(testPQ.delMax());
      str.append(" ");
    }
    System.out.println(str);
  }
}