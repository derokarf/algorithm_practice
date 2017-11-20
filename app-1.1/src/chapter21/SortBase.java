
package chapter21;

abstract public class SortBase{
  abstract public void sort(Comparable[] a);
  abstract public String getName();
  abstract public double forecastTime();
  
  public double lastTime = 0;

  protected boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  protected void exch(Comparable[] a, int i, int j) {
    Comparable t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

  protected void show(Comparable[] a) { // Print the array, on a single line.
    for (Comparable a1 : a) {
      System.out.println(a1 + " ");
    }
  }

  public boolean isSorted(Comparable[] a) { // Test whether the array entries are in order.
    for (int i = 1; i < a.length; i++) {
      if (less(a[i], a[i - 1])) {
        return false;
      }
    }
    return true;
  }
  
}
