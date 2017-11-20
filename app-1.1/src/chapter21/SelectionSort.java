package chapter21;

public class SelectionSort extends SortBase {

  public void sort(Comparable[] a) { // Sort a[] into increasing order.
    int N = a.length; // array length
    for (int i = 0; i < N; i++) { // Exchange a[i] with smallest entry in a[i+1...N).
      int min = i; // index of minimal entr.
      for (int j = i + 1; j < N; j++) {
        if (less(a[j], a[min])) {
          min = j;
        }
      }
      exch(a, i, min);
    }
  }

  @Override
  public String getName() {
    return "Selection";
  }
  
  @Override
  public double forecastTime(){
    return lastTime*2*2;
  }
}
