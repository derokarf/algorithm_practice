package chapter14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SortArrayN {

    protected ArrayList getCommonValues(int[] list1, int[] list2) {
        ArrayList<Integer> result = new ArrayList();

        if (((list1) == null) || ((list2) == null)) {
            return result;
        }

        int iB = 0;
        int iA = 0;
        int a, b;
        while ((iA < list1.length) && (iB < list2.length)) {
            a = list1[iA];
            b = list2[iB];
            if (a == b) {
                result.add(a);
                iB++;
                iA++;
            } else if (a > b) {
                iB++;//сдвигаем
            } else {
                iA++;
            }
        }

        return result;
    }

public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите размер первого массива");
        int[] list1 = new int[scan.nextInt()];
        int i;
        for (i = 0; i < list1.length; i++) {
            list1[i] = (int)(Math.random() * 20);
        }

        System.out.println("Введите размер второго массива");
        int[] list2 = new int[scan.nextInt()];
        for (i = 0; i < list2.length; i++) {
            list2[i] = (int)(Math.random() * 20);
        }        
        
        //сортировка
        Arrays.sort(list1);
        Arrays.sort(list2);
        
        StringBuilder strBF = new StringBuilder("Первый массив: ");
                
        for(i=0;i<list1.length;i++){
            strBF.append(list1[i]);
            strBF.append(" ");
        }
        
        System.out.println(strBF.toString());
        strBF = new StringBuilder("Второй массив: ");
                
        for(i=0;i<list2.length;i++){
            strBF.append(list2[i]);
            strBF.append(" ");
        }
        System.out.println(strBF.toString());

        SortArrayN testFindCommon = new SortArrayN();
        ArrayList<Integer> result = testFindCommon.getCommonValues(list1, list2);
        strBF = new StringBuilder("Общие элементы: ");
                
        for(i=0;i<result.size();i++){
            strBF.append(result.get(i));
            strBF.append(" ");
        }
        System.out.println(strBF.toString());        
    }
}
