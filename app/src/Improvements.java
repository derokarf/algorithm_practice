import libs.Lists;

public class Improvements {

  private static void insertionSort(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) {
      for (int j = i; j > lo && less(a[j], a[j - 1]); j--)
        exch(a, j, j - 1);
    }
  }

  private static Comparable[] bGlobal;//хранитель ссылки на массив b
  private static Comparable[] aGlobal;//хранитель ссылки на массив a
  private static int insertSortVal;
  private static boolean reverse_flag = false;
  private static Comparable[] res;

  //проверка массива на упорядоченность
  public static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++)
      if (less(a[i], a[i - 1])) return false;
    return true;
  }

  //корневая функция сортировки
  public static void sort(Comparable[] a) {
    //создаем второй буфер-массив для слияния уже отсортированных массивов
    Comparable[] b = new Comparable[a.length];
    insertSortVal = (int) (a.length * 0.1);
    //Маловероятный, но теоретически возможный случай, когда буферы поменялись местами перед последним слиянием
    if (sort1(b, 0, a.length - 1, a).equals(b)) {
      System.arraycopy(b, 0, a, 0, a.length);
    }
  }

  private static void exch(Comparable[] a, int i, int j) {
    Comparable t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

  private static Comparable[] merge(Comparable[] a, int lo, int mid, int hi, Comparable[] b) {
    int i = lo, j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      //левая часть закончилась
      if (i > mid) a[k] = b[j++];
        //правая часть закончилась
      else if (j > hi) a[k] = b[i++];
        //ключ в правой меньше чем ключ в левой
      else if (less(b[j], b[i])) a[k] = b[j++];
        //ключ в левой меньше чем ключ в правой
      else a[k] = b[i++];
    }
    return a;
  }

  //сливает массивы, один из которых был уже отсортирован и потому для него не выполнялся merge
  private static Comparable[] merge_sort(Comparable[] a, int lo, int mid, int hi, Comparable[] b, Boolean isLeftNoMerge) {
    //a - сюда выполнялся merge половинок
    //b - отсюда выполнялся merge половинок
    //return - возвращаем буфер, в который производилось слияние
    //мы выполняем слияние в массив, противоположный тому, где лежит [lo,mid]

    //если (левая)[lo,mid] не перемещалась пишем в буфер a
    if (isLeftNoMerge) {
      int i = lo, j = mid + 1;
      for (int k = lo; k <= hi; k++) {
        //левая часть закончилась, берем данные из правой, но с буфера a
        if (i > mid) a[k] = a[j++];
          //правая часть закончилась, берем данные из левой, но с буфера b
        else if (j > hi) a[k] = b[i++];
          //ключ в правой меньше чем ключ в левой
        else if (less(a[j], b[i])) a[k] = a[j++];
          //ключ в левой меньше чем ключ в правой
        else a[k] = b[i++];
      }
      return a;
    } else {
      //если (правая)[mid,hi] не перемещалась, пишем в буфер b
      int i = lo, j = mid + 1;
      for (int k = lo; k <= hi; k++) {
        //левая часть закончилась, завершаем, т.к. правая отсортирована и уже на месте
        if (i > mid) return b;
          //правая часть закончилась, берем данные из левой, но с буфера a
        else if (j > hi) b[k] = a[i++];
          //ключ в правой меньше чем ключ в левой
        else if (less(b[j], a[i])) b[k] = b[j++];
          //ключ в левой меньше чем ключ в правой
        else b[k] = a[i++];
      }
      return b;
    }
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  private static Comparable[] sort1(Comparable[] a, int lo, int hi, Comparable[] b) {
    // а - буфер для записи отсортированных данных (массив назначения)
    // b - буфер чтения с несортированными данными (исходный массив)
    if (hi <= lo) {
      return b;
    }
    Boolean isLeftNoMerge = null; //флаг факта перемещения,
    // true - левая часть не перемещалась
    //false - правая часть не перемещалась
    //null - обе части либо перемещались, либо нет
    //Если размер массива для стортировки стал достаточно мал, используем сортировку вставками
    if ((hi - lo) <= insertSortVal) {
      insertionSort(b, lo, hi);
      return b;
    }
    Comparable[] c;// ссылка на массив, в который производилось слияние
    int mid = lo + (hi - lo) / 2;
    //ПЕРВАЯ СОРТИРОВКА
    c = sort1(a, lo, mid, b);
    //если слияние в буфер записи не производилось, помечаем левую часть массива как неперемещенную
    if (!c.equals(a)) {
      isLeftNoMerge = true;
    }
    //ВТОРАЯ СОСРТИРОВКА
    c = sort1(a, mid + 1, hi, b);
    //если левая часть перемещалась
    //и слияние в буфер записи не производилось на второй сортировке, помечаем правую часть массива как неперемещенную
    if (isLeftNoMerge == null) {
      if (!c.equals(a)) {
        isLeftNoMerge = false;
      }
    } else {
      if (!c.equals(a)) {
        //если не перемещалась ни левая, ни правая часть, запоминаем это
        isLeftNoMerge = null;
      }
    }
    //выбираем какой merge использовать:
    //обе отсортированные половины:
    if (isLeftNoMerge == null) {
      if (c.equals(a)) {
        // Проверка массива на упорядоченность, если массив упорядочен, т.е. элементы левой отсортированной части
        // не больше элементов правой отсортированной части - слияние не производится
        if (c[mid].compareTo(c[mid + 1]) <= 0) {
          return c;
        }
        // - перемещались, меняeм буфера местами
        c = merge(b, lo, mid, hi, a);
      } else {
        // Проверка массива на упорядоченность, если массив упорядочен, т.е. элементы левой отсортированной части
        // не больше элементов правой отсортированной части - слияние не производится
        if (c[mid].compareTo(c[mid + 1]) <= 0) {
          return c;
        }
        // - не перемещались
        c = merge(a, lo, mid, hi, b);
      }
    } else {
      // перемещалась только одна половина
      c = merge_sort(a, lo, mid, hi, b, isLeftNoMerge);
    }
    return c;
  }

  public static void main(String[] args) {
    int N = 1001;
    Integer[] data = new Integer[N];
    for (int i = 0; i < data.length; i++) {
      data[i] = (int) (Math.random() * N);
    }
    Lists.show("src", data);
    sort(data);
    System.out.println("Sorted: " + isSorted(data));
    Lists.show("sorted", data);
  }
}
