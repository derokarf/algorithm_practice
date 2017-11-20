package chapter21;

public class InsertionSort extends SortBase{

  @Override
  public void sort(Comparable[] a) { // Sort a[] into increasing order.
    int N = a.length;
    for (int i = 1; i < N; i++) { // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
      for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
        exch(a, j, j - 1);
      }
    }
  }
  
  @Override
  public String getName(){
    return "Insertion";
  }
  
  @Override
  public double forecastTime(){
    return lastTime*2*2;
  }  
}
