import libs.Lists; /** Содержит базовые методы для работы с массивами, списками. */
import libs.StdRandom; /** Библиотека для работы со случаными числами. */

public class NutsBoltsQuick {

  /**
   * Начинает процесс сортировки.
   * @param a Массив "гаек"
   * @param b Массив "болтов"
   */
  public static void sort(Comparable[] a, Comparable[] b) {
    // Убираем зависимость входных данных
    StdRandom.shuffle(a);
    StdRandom.shuffle(b);
    sort(a, b,0, a.length - 1);
  }

  /**
   * Рекурсивная функция для сортировки.
   * @param a Массив "гаек"
   * @param b Массив "болтов"
   * @param lo Нижняя граница перестановки
   * @param hi Верхняя граница перестановки
   */
  private static void sort(Comparable[] a, Comparable[] b, int lo, int hi) {
    if (hi <= lo) {
      return;
    }
    int j = partition(a, b, lo, hi);
    sort(a, b, lo, j - 1);
    sort(a, b, j + 1, hi);
  }

  /** Выполняет разбиение и перестановки внутри массива.
   * @return Возвращает индекс центрального элемента
   * */
  private static int partition(Comparable[] a, Comparable[] b, int lo, int hi) {
    int i = lo; // левый и
    int j = hi + 1; // правый индекс просмотра
    // Берем центральный элемент из противоположного массива, b
    Comparable v = b[lo];

    // Ищем в текущем массиве (а) элемент равный v(взятому из b)
    // и меняем его местами с первым элементом
    for (int k = lo; k <= hi; k++) {
      if (v == a[k]) {
        Lists.exch(a, lo, k);
        break;
      }
    }

    //Выполняем перестановку в массиве a
    while (i <= j) {
      // ищем слева элемент, больше чем v
      while (Lists.less(a[++i], v)) {
        if (i == hi) {
          break;
        }
      }
      // ВНИМАНИЕ! Ищем справа элемент, меньше или РАВНЫЙ v. Это необходимое условие для того,
      // чтобы опорные элементы в обоих массивах встали на одно и тоже место после разбиения
      while (Lists.less2(v, a[--j])) {
        if (j == lo) {
          break;
        }
      }
      if (i >= j) {
        break;
      }
      // меняем элементы местами
      Lists.exch(a, i, j);
    }
    // Перемещаем опорный элемент на свое место
    Lists.exch(a, lo, j);

    // Опорным элементом для сортировки массива b будет a[j]==b[lo]
    Comparable v2 = a[j];
    //Выполняем перестановку в массиве b
    int l = lo; // левый и
    int m = hi + 1; // правый индекс просмотра
    while (l <= m) {
      // ищем слева элемент, больше чем v
      while (Lists.less(b[++l], v2)) {
        if (l == hi) {
          break;
        }
      }
      // ВНИМАНИЕ! Ищем справа элемент, меньше или РАВНЫЙ v. Это необходимое условие для того,
      // чтобы опорные элементы в обоих массивах встали на одно и тоже место после разбиения
      while (Lists.less2(v2, b[--m])) {
        if (m == lo) {
          break;
        }
      }
      if (l >= m) {
        break;
      }
      // меняем элементы местами
      Lists.exch(b, l, m);
    }
    // Перемещаем опорный элемент на свое место
    Lists.exch(b, lo, m);

    return j;
  }

  /** Тестирование класса. */
  public static void main(String[] args) {
    int n = 10000;

    Integer[] nuts = new Integer[n]; // Первый массив
    Integer[] bolts = new Integer[n]; // Второй массив
    // Заполняем его случайными значениями
    for (int i = 0; i < nuts.length; i++) {
      nuts[i] = (int)(Math.random() * n);
    }
    // Создаем два одинаковых массива
    System.arraycopy(nuts,0, bolts, 0, n);
    // Для "правильности" перемешиваем второй массив;
    StdRandom.shuffle(bolts);

    Lists.show("src nuts: ", nuts);
    Lists.show("src bolts: ", bolts);

    sort(nuts, bolts);

    Lists.show("sorted nuts: ", nuts);
    Lists.show("sorted bolts: ", bolts);

    System.out.println("nuts is sorted: " + Lists.isSorted(nuts));
    System.out.println("bolts is sorted: " + Lists.isSorted(bolts));
    System.out.println("nuts and bolts are equal: " + Lists.isEqual(nuts,bolts));
  }
}
