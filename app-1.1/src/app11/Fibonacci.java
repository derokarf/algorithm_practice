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
public class Fibonacci 
{
	static BigInteger[] listFib = new BigInteger[100];
	
    public static BigInteger F(int N) 
    {
		
        if (N == 0){
			listFib[N] = BigInteger.valueOf(N);
			return BigInteger.valueOf(0);
		}
        if (N == 1){
			listFib[N] = BigInteger.valueOf(N);
			return BigInteger.valueOf(0);
		}
        listFib[N] = listFib[N-1].add(listFib[N-2]);
		return listFib[N];
    }
    
    public static void main(String[] args)
    {
        for (int N = 0; N < 100; N++)
            StdOut.println(N + " " + F(N));
    } 
}
