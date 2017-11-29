package chapter21;

public class DoublingTest {

  SortBase[] listSorter;

  public DoublingTest(SortBase[] list) {
    listSorter = list;
  }

  public void testing(int N, int max_N) {
    double timeRes = 0;
    int i;
    System.out.println("Сортировка   N          реальное   прогноз   отношение");
    while (N <= max_N) {

      Integer[] data = new Integer[N];
      Integer[] tmp_data = new Integer[N];
      for (i = 0; i < data.length; i++) {
        data[i] = (int) (Math.random() * N);
      }
      for (i = 0; i < listSorter.length; i++) {
        System.arraycopy(data, 0, tmp_data, 0, data.length);
        timeRes = onceSort(listSorter[i], tmp_data);
        System.out.println(listSorter[i].getName() + "    " + tmp_data.length + "           " + String.format("%.4f", timeRes) + "       " + String.format("%.4f",listSorter[i].forecastTime()) + "      " + String.format ("%.1f", timeRes / listSorter[i].lastTime));
        listSorter[i].lastTime = timeRes;
      }
      N = N * 2;
    }
  }

  private double onceSort(SortBase sorter, Integer[] tmp_data) {
    long timeStart;
    timeStart = System.nanoTime();
    sorter.sort(tmp_data);
    return (double) (System.nanoTime() - timeStart) / 1000000000.0;
  }

  public static void main(String[] args) {
    SortBase[] list = {new SelectionSort(),new InsertionSort(),new ShellSort()};
    DoublingTest testerSort = new DoublingTest(list);
    testerSort.testing(1000, 200000);
  }
}
