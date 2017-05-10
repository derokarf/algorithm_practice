package chapter12;

import java.util.Scanner;

public final class Rational {

    private final long num;
    private final long denom;

    protected final long getNumerator() {
        return num;
    }

    protected final long getDenominator() {
        return denom;
    }

    public Rational(final int numerator, final int denominator) {
        assert denominator != 0 : "Знаменатель не может быть 0";
        if (numerator == 0) {
            num = 0;
            denom = 1;
        } else {
            final int p = Math.toIntExact(gcd(numerator, denominator));//вообще, здесь я бы лучше gcd тоже перегрузил,но будет много функций
            final double sign = Double.valueOf(numerator) / Double.valueOf(denominator);
            denom = Math.abs(denominator) / p;
            if (numerator > 0 && denominator < 0 || numerator < 0 && denominator > 0) {
                num = -Math.abs(numerator) / p;
            } else {
                num = Math.abs(numerator) / p;
            }
        }
    }

    //перегрузка конструктора для математических операций
    public Rational(final long numerator, final long denominator) {
        assert denominator != 0 : "Знаменатель не может быть 0";
        if (numerator == 0) {
            num = 0;
            denom = 1;
        } else {
            final long p = gcd(numerator, denominator);
            final double sign = Double.valueOf(numerator) / Double.valueOf(denominator);
            denom = Math.abs(denominator) / p;
            if (numerator > 0 && denominator < 0 || numerator < 0 && denominator > 0) {
                num = -Math.abs(numerator) / p;
            } else {
                num = Math.abs(numerator) / p;
            }
            assert (num <= Integer.MAX_VALUE) && (num >= Integer.MIN_VALUE) : "Превышено максимально значение числителя";
            assert (denom <= Integer.MAX_VALUE) && (denom >= Integer.MIN_VALUE) : "Превышено максимально значение знаменателя";
        }
    }

    private static long gcd(long _num, long _denom) {
        long r = _denom;
        while (r != 0) {
            r = _num % _denom;
            _num = _denom;
            _denom = r;
        }
        return Math.abs(_num);
    }

    public final Rational plus(Rational b) {
        return new Rational(num * b.getDenominator() + b.getNumerator() * denom, denom * b.getDenominator());
    }

    public final Rational minus(Rational b) {
        return new Rational(num * b.getDenominator() - b.getNumerator() * denom, denom * b.getDenominator());
    }

    public final Rational times(Rational b) {
        return new Rational(b.getNumerator() * num, b.getDenominator() * denom);

    }

    public final Rational divides(Rational b) {
        return new Rational(b.getDenominator() * num, b.getNumerator() * denom);
    }

    public final boolean equals(Rational that) {
        return num == that.getNumerator() && denom == that.getDenominator();
    }

    @Override
    public final String toString() {
        return denom == 1 ? String.valueOf(num) : String.valueOf(num).concat("/").concat(String.valueOf(denom));
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
                        System.out.println(val1.equals(val2) ? val1.toString().concat(" равно ").concat(val2.toString()) : val1.toString().concat(" не равно ").concat(val2.toString()));
                        break;
                    case 10:
                        isNewOperation = false;
                        break;
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
