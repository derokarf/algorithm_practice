package mypackage;

import StdLib.StdOut;
import StdLib.StdRandom;

public class QuickSort {

  private static int insertSortSize;

  /** Метод быстрой сортировки.
   *
   * @param a Массив для сортировки
   * @param size Размер интервала для сортировки вставками
   */
  public static void sort(Comparable[] a, int size) {
    StdRandom.shuffle(a);
    insertSortSize = size;
    sort(a, 0, a.length - 1);
  }

  private static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) {
      return;
    }

    // Небольшие массивы сортируем вставками
    if ((hi - lo) <= insertSortSize) {
      insertionSort(a, lo, hi);
      return;
    }

    int j = partition(a, lo, hi);
    sort(a, lo, j - 1);
    sort(a, j + 1, hi);
  }

  // Сортировка вставками для небольших подмассивов
  private static void insertionSort(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) {
      for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
        exch(a, j, j - 1);
      }
    }
  }

  private static int partition(Comparable[] a, int lo, int hi) {
    int i = lo;
    int j = hi + 1;
    Comparable v = a[lo];
    while (true) {
      while (less(a[++i], v)) {
        if (i == hi) {
          break;
        }
      }
      while (less(v, a[--j])) {
        if (j == lo) {
          break;
        }
      }
      if (i >= j) {
        break;
      }
      exch(a, i, j);
    }
    exch(a, lo, j);
    return j;
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  private static void exch(Comparable[] a, int i, int j) {
    Comparable t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

  /**
   * Проверка массива на отсортированность.
   * @param a Массив
   * @return true/false
   */
  public static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++) {
      if (less(a[i], a[i - 1])) {
        return false;
      }
    }
    return true;
  }

  private static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.print(a[i] + " ");
    }
    StdOut.println();
  }

  /**
   * Функция тестирования класса.
   * @param args Аргументы командной строки
   */
  public static void main(String[] args) {
    // Тестируем сортировку
    long startTime;
    long stopTime;

    for (int E = 3; E <= 6; E++) {
      String res = "E=" + String.valueOf(E) + ": ";
      for (int L = 0; L < 30; L++) {
        // Вычисляем длинну массива
        Integer[] list = new Integer[Double.valueOf(
            Math.pow(new Double(10),new Double(E))
        ).intValue()];
        for (int i = 0; i < list.length; i++) {
          // Заполняем массив
          list[i] = StdRandom.uniform(list.length);
        }
        // Сортируем
        startTime = System.nanoTime();
        sort(list, L);
        stopTime = System.nanoTime();
        res = res + (stopTime - startTime) + "; ";
      }
    }
  }
}
