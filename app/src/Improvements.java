import libs.Lists;

public class Improvements {

  private static void insertionSort(Comparable[] a, int lo, int hi) {
    for (int i = lo; i < (hi + 1); i++) {
      for (int j = i; j > lo && less(a[j], a[j - 1]); j--)
        exch(a, j, j - 1);
    }
  }

  private static Comparable[] aux;
  private static int insertSortVal;
  private static int count = 0;
  private static boolean reverse_flag = false;
  private static Comparable[] src;

  public static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++)
      if (less(a[i], a[i - 1])) return false;
    return true;
  }

  public static void sort(Comparable[] a) {
    aux = new Comparable[a.length];
    insertSortVal = (int) (a.length * 0.1);
    sort1(a, 0, a.length - 1, 0);
    if(src.equals(aux)){
      for(int i=0; i < a.length; i++)
        a[i] = aux[i];
    }
  }

  private static void exch(Comparable[] a, int i, int j) {
    Comparable t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

  private static Comparable[] merge(Comparable[] a, int lo, int mid, int hi, Comparable[] b) {
    count++;
    //checking for apply insertion sort
    if (b[mid].compareTo(b[mid + 1]) > 0) {
      int i = lo, j = mid + 1;
      for (int k = lo; k <= hi; k++) {
        if (i > mid) a[k] = b[j++];
        else if (j > hi) a[k] = b[i++];
        else if (less(b[j], b[i])) a[k] = b[j++];
        else a[k] = b[i++];
      }
    } else {
      for (int i = 0; i <= hi; i++) {
        a[i] = b[i];
      }
    }
    return a;
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  private static void sort1(Comparable[] a, int lo, int hi, int level) {
    System.out.println("sort1 " + level);
    if (hi <= lo) return;
    //checking for apply insertion sort
    if ((hi - lo) <= insertSortVal) {
      insertionSort(a, lo, hi);
    } else {
      int mid = lo + (hi - lo) / 2;
      ++level;
      sort1(a, lo, mid, level);
      sort2(a, mid + 1, hi, level);
      //check of order
      System.out.println("lo=" + lo + " mid=" + mid + " hi=" + hi + " flag=" + reverse_flag);
      System.out.println("before merge");
      System.out.print("a: ");
      Lists.show(a);
      System.out.print("aux: ");
      Lists.show(aux);
      if (reverse_flag) {
        System.out.println("reverse");
        src = merge(a, lo, mid, hi, aux);
      } else {
        src = merge(aux, lo, mid, hi, a);
      }
      System.out.println("after merge");
      System.out.print("a: ");
      Lists.show(a);
      System.out.print("aux: ");
      Lists.show(aux);
      reverse_flag = false;
    }
  }

  private static void sort2(Comparable[] a, int lo, int hi, int level) {
    System.out.println("sort2 " + level);
    if (hi <= lo) return;
    //checking for apply insertion sort
    if ((hi - lo) <= insertSortVal) {
      insertionSort(a, lo, hi);
    } else {
      int mid = lo + (hi - lo) / 2;
      ++level;
      sort1(a, lo, mid, level);
      sort2(a, mid + 1, hi, level);
      //check of order
      System.out.println("lo=" + lo + " mid=" + mid + " hi=" + hi + " flag=" + reverse_flag);
      System.out.println("before merge");
      System.out.print("a: ");
      Lists.show(a);
      System.out.print("aux: ");
      Lists.show(aux);
      if (reverse_flag) {
        System.out.println("reverse");
        src = merge(a, lo, mid, hi, aux);
      } else {
        src = merge(aux, lo, mid, hi, a);
      }
      System.out.println("after merge");
      System.out.print("a: ");
      Lists.show(a);
      System.out.print("aux: ");
      Lists.show(aux);
      reverse_flag = !reverse_flag;
    }
  }

  public static void main(String[] args) {
    int N = 1000;
    Integer[] data = new Integer[N];
//    Integer[] data = {10, 2, 6, 17, 10, 16, 18, 5, 10, 1, 7, 18, 8, 16, 19, 18, 3, 18, 0, 7};
    //10  2  6  17  10  16  18  5  10  1  7  18  8  16  19  18  3  18  0  7
    for (int i = 0; i < data.length; i++) {
      data[i] = (int) (Math.random() * N);
//      data[i] = N - i;
    }

    System.out.print("src: ");
    Lists.show(data);
    sort(data);
    System.out.println("Sorted: " + isSorted(data));
    System.out.print("sorted: ");
    Lists.show(data);
    System.out.println("count: " + count);

  }
}
