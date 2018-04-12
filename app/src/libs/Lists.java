package libs;

/**
 * Содержит вспомогательные утилиты для работы с массивами.
 */
public class Lists {
  /**
   * Отображает массив в строку.
   * @param label Префикс строки
   * @param a Отображаемый массив
   */
  public static void show(String label, Comparable[] a) {
    StringBuilder str = new StringBuilder();
    str.append(label).append(": ");
    for (int i = 0; i < a.length; i++) {
      str.append(a[i]).append("  ");
    }
    System.out.println(str);
  }

  /**
   * Сравнивает два значения.
   * @param v Первое значение
   * @param w Второе значение
   * @return Возвращает true если v меньше w
   */
  public static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  /**
   * Сравнивает два значения.
   * @param v Первое значение
   * @param w Второе значение
   * @return Возвращает true если v меньше или равен w
   */
  public static boolean less2(Comparable v, Comparable w) {
    return ((v.compareTo(w) < 0) || (v.compareTo(w) == 0));
  }

  /**
   * Меняет местами пару элементов в массиве.
   * @param a Массив
   * @param i Индекс первого элемента
   * @param j Индекс Второго элемента
   */
  public static void exch(Comparable[] a, int i, int j)  {
    Comparable t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

  /**
   * Проверяет, отсортированы ли элементы массива повозрастанию.
   * @param a Проверяемый массив
   * @return Возвращает true в случае удачной проверки
   */
  public static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++) {
      if (less(a[i], a[i - 1])) {
        return false;
      }
    }
    return true;
  }

  /**
   * Сравнивает два массива поэлементно.
   * @param a Первый массив
   * @param b Второй массив
   * @return Возвращает true если массивы равны
   */
  public static  boolean isEqual(Comparable[] a, Comparable[] b) {
    if (a.length != b.length) {
      return false;
    }
    for (int i = 0; i < a.length; i++) {
      if (a[i] != b[i]) {
        return false;
      }
    }
    return true;
  }
}
