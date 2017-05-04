package chapter12;

import java.util.Scanner;

public class Rational {

    private final int numerator;
    private final int denominator;

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public Rational(final int numerator, final int denominator) {
        if (numerator == 0) {
            this.numerator = 0;
            this.denominator = 1;
        } else {
            final int p = gcd(numerator, denominator);
            final float sign = Float.valueOf(numerator) / Float.valueOf(denominator);
            this.numerator = sign > 0 ? numerator / p : -Math.abs(numerator) / p;
            this.denominator = Math.abs(denominator) / p;
        }
    }

    public static int gcd(int _num, int _denom) {
        int r = _denom;
        while (r != 0) {
            r = _num % _denom;
            _num = _denom;
            _denom = r;
        }
        return Math.abs(_num);
    }

    public Rational plus(Rational b) {
        return new Rational(numerator * b.getDenominator() + b.getNumerator() * denominator, denominator * b.getDenominator());
    }

    public Rational minus(Rational b) {
        return new Rational(numerator * b.getDenominator() - b.getNumerator() * denominator, denominator * b.getDenominator());
    }

    public Rational times(Rational b) {
        return new Rational(b.getNumerator() * numerator, b.getDenominator() * denominator);

    }

    public Rational divides(Rational b) {
        return new Rational(b.getDenominator() * numerator, b.getNumerator() * denominator);
    }

    public boolean equals(Rational that) {
        return numerator == that.getNumerator() && denominator == that.getDenominator();
    }

    @Override
    public String toString() {
        return denominator == 1 ? String.valueOf(numerator) : String.valueOf(numerator).concat("/").concat(String.valueOf(denominator));
    }

    public static void main(String[] args) {
        boolean isNewVal = true;
        boolean isNewOperation;
        int _num, _denom;
        Scanner stdScan = new Scanner(System.in);
        while (isNewVal) {
            isNewOperation = true;
            System.out.println("Первое число");
            System.out.println("Введите числитель");
            _num = stdScan.nextInt();
            System.out.println("Введите знаменатель");
            _denom = stdScan.nextInt();
            Rational val1 = new Rational(_num, _denom);
            System.out.println("Первое число = ".concat(val1.toString()));
            System.out.println("Второе число");
            System.out.println("Введите числитель");
            _num = stdScan.nextInt();
            System.out.println("Введите знаменатель");
            _denom = stdScan.nextInt();
            Rational val2 = new Rational(_num, _denom);
            System.out.println("Второе число = ".concat(val2.toString()));
            while (isNewOperation) {
                System.out.println("Выберите операцию: 0(+) 1(-) 2(*) 3(:) 4(=) 10(выход)");
                switch (stdScan.nextInt()) {
                    case 0:
                        System.out.println(val1.toString().concat("+").concat(val2.toString()).concat("=").concat(val1.plus(val2).toString()));
                        break;
                    case 1:
                        System.out.println(val1.toString().concat("-").concat(val2.toString()).concat("=").concat(val1.minus(val2).toString()));
                        break;
                    case 2:
                        System.out.println(val1.toString().concat("*").concat(val2.toString()).concat("=").concat(val1.times(val2).toString()));
                        break;
                    case 3:
                        System.out.println(val1.toString().concat(":").concat(val2.toString()).concat("=").concat(val1.divides(val2).toString()));
                        break;
                    case 4:
                        System.out.println(val1.equals(val2) ? val1.toString().concat(" равно ").concat(val2.toString()) : val1.toString().concat("не равно ").concat(val2.toString()));
                        break;
                    case 10:
                        isNewOperation = false;
                    default:
                        System.out.println("Неверная операция");
                        break;

                }
            }
            System.out.println("Ввести новые значения? 0(нет) 1(да)");
            if (stdScan.nextInt() != 1) {
                isNewVal = false;
            }
        }

    }
}
