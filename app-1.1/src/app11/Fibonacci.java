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
public class Fibonacci {

    protected ArrayList<BigInteger> listFib = new ArrayList<>();

    public Fibonacci() {
        listFib.add(BigInteger.valueOf(0));
        listFib.add(BigInteger.valueOf(1));
    }

    public BigInteger F(int N) {

        if (N >= 0) {
            if (listFib.size() > N) {
                return listFib.get(N);
            } else {
                return calcF(N);
            }
        } else {
            System.out.println("N должно быть целым неотрицательным числом");
            return BigInteger.valueOf(0);
        }
    }

    protected BigInteger calcF(int N) {
        for (int i = listFib.size(); i <= N; i++) {
            BigInteger a = listFib.get(i - 1);
            BigInteger b = listFib.get(i - 2);
            listFib.add(a.add(b));
        }
        return listFib.get(listFib.size() - 1);
    }
}
