/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app11;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author igor
 */
public class Binom {

    //кэш значений факториала
    protected ArrayList<factRow> factCache = new ArrayList();

    //нужен, т.к. !0=1
    Binom() {
        factRow t_row = new factRow();
        t_row.index = 0;
        t_row.fact = BigInteger.valueOf(1);
        factCache.add(0, t_row);
    }

    //класс объекта кэша значений факториала
    protected class factRow {

        public int index;
        public BigInteger fact;
    }

    //посчитать факториалы всех чисел до N, которых нет в кэше
    protected BigInteger calcFactRow(int N) {

        for (int i = factCache.size(); i <= N; i++) {
            factRow t_row = new factRow();
            t_row.index = i;
            t_row.fact = factCache.get(i - 1).fact.multiply(BigInteger.valueOf(i));
            factCache.add(t_row);
        }
        return factCache.get(factCache.size() - 1).fact;
    }

    //получить факториал числа
    private BigInteger getFact(int N) {
        if (N < (factCache.size())) {
            return factCache.get(N).fact;
        } else {
            return calcFactRow(N);
        }
    }

    //вероятность биноминального распределения
    public double getBinom(int N, int k, double p) {
        double _bin;

        if (N <= 0 || k <= 0 || k >= N || p < 0 || p > 1) {
            System.out.println("Проверьте правильность параметров функции");
            return 0;
        }
        BigInteger c = this.getFact(N).divide(this.getFact(k).multiply(this.getFact(N - k)));
        _bin = Math.pow(p, k) * Math.pow((1 - p), (N - k)) * c.doubleValue();
        return _bin;
    }

    public static void main(String[] args) {

        Binom B = new Binom();
        System.out.println(B.getBinom(100, 50, 0.25));

    }

}
