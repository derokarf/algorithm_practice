/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app11;

import java.math.BigInteger;
import libBook.StdOut;

/**
 *
 * @author igor
 */
public class Fibonacci {

	static BigInteger[] listFib;

	public static BigInteger F(int N) {
		
		if(N>=0){
			listFib = new BigInteger[N + 1];
			return calcF(N);			
		}else{
			StdOut.println("N должно быть целым неотрицательным числом");
			return BigInteger.valueOf(0);
		}
	}

	private static BigInteger calcF(int N) {
		if (N == 0) {
			listFib[N] = BigInteger.valueOf(N);
			return BigInteger.valueOf(0);
		}
		if (N == 1) {
			listFib[N] = BigInteger.valueOf(N);
			return BigInteger.valueOf(1);
		}
		if (listFib[N] == null) {
			listFib[N] = calcF(N - 1).add(calcF(N - 2));
		}
		return listFib[N];
	}

	public static void main(String[] args) {
		for (int N = 0; N < 100; N++) {
			StdOut.println(N + " " + F(N));
		}
	}
}
