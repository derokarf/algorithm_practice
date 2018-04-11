import libs.Lists; /** Содержит базовые методы для работы с массивами, списками */
import libs.Res;
import libs.StdRandom; /** Библиотека для работы со случаными числами */

public class NutsBoltsQuick {

  public static void sort(Comparable[] a, Comparable[] b)
  {
    // Убираем зависимость входных данных
    StdRandom.shuffle(a);
    StdRandom.shuffle(b);
    Lists.show("src a: ", a);
    Lists.show("src b: ", b);
//    Integer[] a1 = {5,  1,  5,  3,  2,  3,  5,  0,  4,  7};
//    Integer[] b1 = {3,  0,  3,  4,  5,  5,  1,  5,  2,  7};
    sort(a, b,0, a.length - 1, 0, b.length - 1);
  }
  private static void sort(Comparable[] a, Comparable[] b, int a_lo, int a_hi, int b_lo, int b_hi)
  {
    if (a_hi <= a_lo) return;
    int j = partition(a, b, a_lo, a_hi, b_lo, b_hi);
    sort(a, b, a_lo, j-1, b_lo, j - 1);
    sort(a, b, j+1, a_hi, j+1, b_hi);
  }

  /** Выполняет разбиение и перестановки внутри массива
   * @return Возвращает индекс центрального элемента
   * */
  private static int partition(Comparable[] a, Comparable[] b, int a_lo, int a_hi, int b_lo, int b_hi)
  {
    int i = a_lo; // левый и
    int j = a_hi + 1; // правый индекс просмотра
    // Берем центральный элемент из противоположного массива, b
    Comparable v = b[b_lo];
    Comparable tmp;
    // Ищем в текущем массиве (а) элемент равный v(взятому из b) меняем его местами с первым элементом
//    System.out.println("v = b[b_lo] " + v);
    for(int k=a_lo; k <= a_hi; k++){
      if(v == a[k]){
         Lists.exch(a,a_lo,k);
         break;
      }
    }
//    Lists.show("a before part: ", a);
//    System.out.println("a_lo=" + a_lo);
//    System.out.println("a_hi=" + a_hi);
    //Выполняем перестановку в массиве a
    while (i <= j){
      // ищем слева элемент, больше чем v
      while (Lists.less(a[++i], v)){
        if(i == a_hi){
          break;
        }
      }
      // ищем справа элемент, меньше чем v
      while (Lists.less2(v, a[--j])){
        if(j == a_lo){
          break;
        }
      }
      if (i >= j){
        break;
      }
      // меняем элементы местами
      Lists.exch(a, i, j);
    }
    // Перемещаем опорный элемент на свое место
    Lists.exch(a, a_lo, j);
//    System.out.println("j=" + j);
//    Lists.show("a after  part: ", a);

    Comparable v2 = a[j];
//    Lists.exch(b,b_lo,j);
//    for(int k=b_lo; k <= b_hi; k++){
//      if(b[b_lo] == a[k]){
//        v2 = a[k];
//        break;
//      }
//    }
//    Lists.show("b before part: ", b);
//    System.out.println("b_lo=" + b_lo);
//    System.out.println("b_hi=" + b_hi);
    //Выполняем перестановку в массиве b
    int l = b_lo; // левый и
    int m = b_hi + 1; // правый индекс просмотра
    while (l <= m){
      // ищем слева элемент, больше чем v
      while (Lists.less(b[++l], v2)){
        if(l == b_hi){
          break;
        }
      }
      // ищем справа элемент, меньше чем v
      while (Lists.less2(v2, b[--m])){
        if(m == b_lo){
          break;
        }
      }
      if (l >= m){
        break;
      }
      // меняем элементы местами
      Lists.exch(b, l, m);
    }
    // Перемещаем опорный элемент на свое место
    Lists.exch(b, b_lo, m);
//    Lists.show("b after  part: ", b);
//    System.out.println("j=" + j);
//    System.out.println("m=" + m);
//    System.out.println("");

    return j;
  }

  /** Тестирование класса */
  public static void main (String[] args){
    int n = 10000;

    Integer[] nuts = new Integer[n]; // Первый массив
//    Integer[] nuts = {5,  1,  5,  3,  2,  3,  5,  0,  4,  7};
    Integer[] bolts = new Integer[n]; // Второй массив
//    Integer[] bolts = {3,  0,  3,  4,  5,  5,  1,  5,  2,  7};
    // Заполняем его случайными значениями
    for(int i=0; i<nuts.length; i++){
      nuts[i] = (int)(Math.random()*n);
    }
//    // Создаем два одинаковых массива
    System.arraycopy(nuts,0, bolts, 0, n);
//    // Для "правильности" перемешиваем второй массив;
    StdRandom.shuffle(bolts);

//    Lists.show("nuts: ", nuts);
//    Lists.show("bolts: ", bolts);

    sort(nuts, bolts);

    Lists.show("nuts: ", nuts);
    Lists.show("bolts: ", bolts);

    System.out.println("nuts is sorted: " + Lists.isSorted(nuts));
    System.out.println("bolts is sorted: " + Lists.isSorted(bolts));
    System.out.println("nuts and bolts are equal: " + Lists.isEqual(nuts,bolts));
  }
}
