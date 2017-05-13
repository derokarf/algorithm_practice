package chapter12;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Enclosed.class)
public class RationalTest {

    @RunWith(Parameterized.class)
    public static class RationalTestParameterized {

        @Rule
        public TestName name = new TestName();

        static private long testNum;
        static private long testDenom;
        static private String testResult;
        static private String testMinus;
        static private String testPlus;
        static private String testTimes;
        static private String testDevides;

        Rational testingValue;
        Rational testingValue2;

        public RationalTestParameterized(long val1, long val2, String val3, String val4, String val5, String val6, String val7) {
            testNum = val1;
            testDenom = val2;
            testResult = val3;
            testMinus = val4;
            testPlus = val5;
            testTimes = val6;
            testDevides = val7;

        }

        @org.junit.runners.Parameterized.Parameters
        //{числитель, знаменатель, строка, вычитание, сложение, умножение, деление}
        public static Collection getParameters() {
            return Arrays.asList(new Object[][]{
                {6, 4, "3/2", "0", "3", "9/4", "1"},
                {-3, 2, "-3/2", "0", "-3", "9/4", "1"},
                {-3, -7, "3/7", "0", "6/7", "9/49", "1"},
                {18, 12, "3/2", "0", "3", "9/4", "1"},
                {-21, 19, "-21/19", "0", "-42/19", "441/361", "1"},
                {0, 4, "0", "0", "0", "0", "inf"},
                {Integer.MAX_VALUE, 10, String.valueOf(Integer.MAX_VALUE).concat("/10"), "0", String.valueOf(Integer.MAX_VALUE).concat("/5"), "inf", "1"},
                {Long.valueOf(Integer.MAX_VALUE) * Long.valueOf(4), Long.valueOf(Integer.MAX_VALUE) * Long.valueOf(8), "1/2", "0", "1", "1/4", "1"}
            });
        }

        //Тестирование создания объекта класса с рациональными значениями
        @Test
        public void testCreateRational() {
            testingValue = new Rational(testNum, testDenom);
            assertEquals("fail", testingValue.toString(), testResult);
        }

        //Вычитание
        @Test()
        public void testMinus() {
            testingValue = new Rational(testNum, testDenom);
            testingValue2 = new Rational(testNum, testDenom);
            assertEquals("fail", testingValue.minus(testingValue2).toString(), testMinus);
        }

        //Сложение
        @Test()
        public void testPlus() {
            testingValue = new Rational(testNum, testDenom);
            testingValue2 = new Rational(testNum, testDenom);
            assertEquals("fail", testingValue.plus(testingValue2).toString(), testPlus);
        }

        //Умножение
        @Test()
        public void testTimes() {
            try {
                testingValue = new Rational(testNum, testDenom);
                testingValue2 = new Rational(testNum, testDenom);
                assertEquals("fail", testingValue.times(testingValue2).toString(), testTimes);
            } catch (java.lang.AssertionError thrown) {
                System.out.println(name.getMethodName().concat(" : ").concat(thrown.getMessage()));
                assertNotEquals("", thrown.getMessage());
            }
        }

        //Деление
        @Test()
        public void testDivides() {
            try {
                testingValue = new Rational(testNum, testDenom);
                testingValue2 = new Rational(testNum, testDenom);
                assertEquals("fail", testingValue.divides(testingValue2).toString(), testDevides);
            } catch (java.lang.AssertionError thrown) {
                System.out.println(name.getMethodName().concat(" : ").concat(thrown.getMessage()));
                assertNotEquals("", thrown.getMessage());
            }
        }

    }

    public static class RationalTestSingle {

        private Rational testingValue;
        private Rational testingValue2;

        //Тестирование с нулевым значением знаменателя;
        @Test(expected = java.lang.AssertionError.class)
        public void testCreateRationalWithZero() {
            testingValue = new Rational(13, 0);
        }

        //Превышение значения числителя
        @Test(expected = java.lang.AssertionError.class)
        public void testCreateRationalnNumeratorBoundOf() {
            testingValue = new Rational(Long.valueOf(Integer.MAX_VALUE) + Long.valueOf(2), Long.valueOf(Integer.MAX_VALUE));
        }

        //Превышение значения знаменателя
        @Test(expected = java.lang.AssertionError.class)
        public void testCreateRationalDenominatorBoundOf() {
            testingValue = new Rational(Long.valueOf(Integer.MAX_VALUE), Long.valueOf(Integer.MAX_VALUE) + Long.valueOf(2));
        }

        //Сравнение
        @Test
        public void testEquals() {
            testingValue = new Rational(10, 5);
            testingValue2 = new Rational(-30, -15);
            assertEquals("fail", testingValue.equals(testingValue2), true);
        }

    }

}
