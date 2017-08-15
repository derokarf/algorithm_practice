package chapter14;

import java.util.Scanner;

public class BitonicSearch {

    protected static int[] list = {-3, 0, 1, 2, 4, 5, 7, 9, 8, 6, 3, -2};

    public int isInclude(int value) {
        int a, b;
        a = 0;
        b = list.length - 1;
        int mid;
        if ((value < list[0]) && (value < list[list.length - 1])) {
            return -1;
        }
        //ищем перегиб, сложность ~lgN
        while (b - a > 1) {
            mid = a + (b - a) / 2;
            if (list[mid] > list[mid - 1]) {
                //возрастающая
                a = mid;
            } else {
                //убывающая
                b = mid;
            }
        }
        int mainMid;
        if (list[a] > list[b]) {
            mainMid = a;
        } else {
            mainMid = b;
        }
        //проверяем первую последовательность, сложность ~lgN
        a = 0;
        b = mainMid;
        while (b - a > 1) {
            mid = a + (b - a) / 2;
            if (value >= list[mid]) {
                a = mid;
            } else {
                b = mid;
            }
        }
        if (value == list[a]) {
            return a;
        } else if (value == list[b]) {
            return b;
        } else {
            //проверяем вторую последовательность, сложность ~lgN
            a = mainMid;
            b = list.length - 1;
            while (b - a > 1) {
                mid = a + (b - a) / 2;
                if (value <= list[mid]) {
                    a = mid;
                } else {
                    b = mid;
                }
            }
            if (value == list[a]) {
                return a;
            } else if (value == list[b]) {
                return b;
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) {

        BitonicSearch bs = new BitonicSearch();
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < list.length; i++) {
            System.out.print(" " + i + "  ");
        }
        System.out.println("");
        for (int i = 0; i < list.length; i++) {
            System.out.print("[" + list[i] + "] ");
        }
        System.out.println("");
        System.out.println("Введите число для поиска в последовательности");
        int val = scan.nextInt();
        int res = bs.isInclude(val);
        if (res < 0) {
            System.out.println("Число не принадлежит последовательности");
        } else {
            System.out.println("Число принадлежит последовательности под индексом ".concat(String.valueOf(res)));
        }

    }

}
