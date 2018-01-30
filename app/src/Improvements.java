import libs.Lists;

public class Improvements {

  private static void insertionSort(Comparable[] a, int lo, int hi) {
    for (int i = lo; i < (hi + 1); i++) {
      for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
        exch(a, j, j - 1);
    }
  }

  private static Comparable[] aux;
  private static Comparable[] tmp;
  private static Comparable[] src;
  private static int last_hi;
  private static boolean reverse_merge = false;
  private static int insertSortVal;

  public static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++)
      if (less(a[i], a[i - 1])) return false;
    return true;
  }

  public static void sort(Comparable[] a) {
    aux = new Comparable[a.length];
    src = a;
//    for(int i=0; i < aux.length; i++){
//      aux[i] = a[i];
//    }
    insertSortVal = (int) (a.length * 0.15);
    sort1(0, a.length - 1);
  }

  private static void exch(Comparable[] a, int i, int j) {
    Comparable t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

//  private static void merge(Comparable[] a, int lo, int mid, int hi) {
//    int i = lo, j = mid + 1;
//    for (int k = lo; k <= hi; k++)
//      aux[k] = a[k];
//    for (int k = lo; k <= hi; k++)
//      if (i > mid) a[k] = aux[j++];
//      else if (j > hi) a[k] = aux[i++];
//      else if (less(aux[j], aux[i])) a[k] = aux[j++];
//      else a[k] = aux[i++];
//  }

  private static void merge2(Comparable[] a, int lo, int mid, int hi, Comparable[] b) {
    int i = lo, j = mid + 1;
    for (int k = lo; k <= hi; k++)
      if (i > mid) a[k] = b[j++];
      else if (j > hi) a[k] = b[i++];
      else if (less(b[j], b[i])) a[k] = b[j++];
      else a[k] = b[i++];

    for(int e=lo; e <= hi;e++)
      b[e] = a[e];
  }

  private static void merge3(Comparable[] a, int lo, int mid, int hi, Comparable[] b) {
    //a - src
    //b - aux
    int i = lo, j = mid + 1;
    for (int k = lo; k <= hi; k++)
      if (i > mid) a[k] = a[j++];
      else if (j > hi) a[k] = b[i++];
      else if (less(a[j], b[i])) a[k] = a[j++];
      else a[k] = b[i++];

    //      if (i > mid) a[k] = b[j++];
//      else if (j > hi) a[k] = b[i++];
//      else if (less(b[j], b[i])) a[k] = b[j++];
//      else a[k] = b[i++];
    for(int e=lo; e <= hi;e++)
      b[e] = a[e];
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

//  private static void sort(Comparable[] a, int lo, int hi) {
//    if (hi <= lo) return;
//    //checking for apply insertion sort
//    if ((hi - lo) > insertSortVal) {
//      int mid = lo + (hi - lo) / 2;
//      sort(a, lo, mid);
//      sort(a, mid + 1, hi);
//      //check of order
//      if (a[mid].compareTo(a[mid + 1]) > 0) merge(a, lo, mid, hi);
//    } else {
//      insertionSort(a, lo, hi);
//    }
//  }

  private static void sort1(int lo, int hi) {
    if (hi <= lo) return;
    //checking for apply insertion sort
//    if ((hi - lo) > insertSortVal) {
//      int mid = lo + (hi - lo) / 2;
//      sort1(a, lo, mid, b);
//      sort1(a, mid + 1, hi, b);
//      //check of order
//      if (a[mid].compareTo(a[mid + 1]) > 0) merge2(a, lo, mid, hi, b);
//    } else {
//      insertionSort(a, lo, hi);
//    }
    int mid = lo + (hi - lo) / 2;
    sort1(lo, mid);
    sort1(mid + 1, hi);
    //check of order
//      if (a[mid].compareTo(a[mid + 1]) > 0){
//        tmp = a;
//        a = b;
//        b = tmp;
//        merge2(a, lo, mid, hi, b);
//      }
    System.out.println("before merge");
    System.out.print("aux: ");
    Lists.show(aux);
    System.out.print("src: ");
    Lists.show(src);
    System.out.println("lo=" + lo + " mid=" + mid + " hi=" + hi);
    if (lo != mid) {
      System.out.println("change");
      merge3(src, lo, mid, hi, aux);
      reverse_merge = false;
    } else {
      merge2(aux, lo, mid, hi, src);
      reverse_merge = true;
    }
    last_hi = hi;
//    merge2(aux, lo, mid, hi, src);
    System.out.println("after merge");
    System.out.print("aux: ");
    Lists.show(aux);
    System.out.print("src: ");
    Lists.show(src);
  }

  public static void main(String[] args) {
    int N = 20;
    Integer[] data = new Integer[N];
    for (int i = 0; i < data.length; i++) {
//      data[i] = (int) (Math.random() * N);
      data[i] = N - i;
    }

    System.out.print("src: ");
    Lists.show(data);
    sort(data);
    assert isSorted(data);
    System.out.print("sorted: ");
    Lists.show(data);
    Lists.show(aux);

  }
}
