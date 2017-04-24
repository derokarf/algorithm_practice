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
import java.util.Scanner;
import libBook.StdDraw;
import libBook.StdIn;

public class Histogram {

	//распределение значений по отрезкам
	public static int[] calcList(double l, double r, int N) {
		int[] listHist = new int[N];
		double[] src = StdIn.readAllDoubles();
		double delta = Math.abs((r - l) / N);

		for (int i = 0; i < src.length; i++) {
			//принадлежит ли вообще точка отрезку
			if ((src[i] < r) && (src[i] >= l)) {
				Double _d = Math.floor((src[i] - l) / delta);
				int _n = _d.intValue();
				listHist[_n]++;
			} else if (src[i] == r) {
				//Последний интервал включает в себя свою правую граничную точку, в отличие от остальных
				listHist[N - 1]++;
			}
		}

		return listHist;
	}

	//метод предназначен только для тестирования с помощью последовательно ввода значений в командной строке
	private static int[] test_calcList(double l, double r, int N) {
		int[] listHist = new int[N];

		Scanner in = new Scanner(System.in);
		System.out.println("Введите количество чисел для теста");
		int len = in.nextInt();

		double[] src = new double[len];
		for (int j = 0; j < src.length; j++) {
			System.out.println("Введите число, осталось ".concat(String.valueOf(src.length - j)));
			src[j] = in.nextDouble();
		}

		double delta = Math.abs((r - l) / N);

		for (int i = 0; i < src.length; i++) {
			//принадлежит ли вообще точка отрезку
			if ((src[i] < r) && (src[i] >= l)) {
				Double _d = Math.floor((src[i] - l) / delta);
				int _n = _d.intValue();
				listHist[_n]++;
			} else if (src[i] == r) {
				//Последний интервал включает в себя свою правую граничную точку, в отличие от остальных
				listHist[N - 1]++;
			}
		}

		for (int k = 0; k < listHist.length; k++) {
			System.out.println("listHist[".concat(String.valueOf(k)).concat("]=").concat(String.valueOf(listHist[k])));
		}

		return listHist;
	}

	//отрисовка гистограммы
	public static void drawRes(int[] _src) {

		System.out.println(_src.length);
		double _n = _src.length;
		double _d = 0.5/_n;
		System.out.println(_d);
		for (int i = 0; i < _src.length; i++) {
			double x = i*(_d) + _d/2 + 0.2;
			double y = _src[i]*0.05;
			double rw = _d/2;
			double rh = _src[i] * 0.05;
			StdDraw.rectangle(x, y, rw, rh);
		}
	}

	public static void main(String[] args) {
		double l, r;
		int N = 0;
		int[] list;

		Scanner in = new Scanner(System.in);
		System.out.println("Введите число L, начало отрезка");
		l = in.nextDouble();
		r = l;
		while (l >= r) {
			System.out.println("Введите число R, конец отрезка, оно должно быть больше l = ".concat(String.valueOf(l)));
			r = in.nextDouble();
		}
		while (N <= 0) {
			System.out.println("Введите целое положительное число интервалов,N");
			N = in.nextInt();
		}
		list = calcList(l, r, N);
		drawRes(list);
	}
}
	