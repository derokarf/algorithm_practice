/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app11;

/**
 *
 * @author igor
 */
public class LogArray {

    //алгоритм Евклида
    private static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        int r = p % q;
        return gcd(q, r);
    }

    //создание и заполнение логического массива
    public static boolean[][] createLA(int N) {

        boolean[][] la;

        if (N < 1) {
            System.out.println("Размер массива должен быть больше 0");
            la = new boolean[0][0];
            return la;
        }

        la = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                la[i][j] = gcd(i + 1, j + 1) == 1;
            }
        }

        return la;
    }

    //тест использования
    public static void main(String[] args) {

        int N = 8;
        //генерируем массив
        boolean[][] t_la = createLA(N);
        //вывод результата
        for (int i = 0; i < t_la.length; i++) {
            String row = "|";
            for (int j = 0; j < N; j++) {
                row = row.concat(t_la[i][j] ? "t" : "f").concat("(").concat(String.valueOf(i + 1))
                        .concat("-").concat(String.valueOf(j + 1)).concat(")|");
            }
            System.out.println(row);
        }
    }
}
