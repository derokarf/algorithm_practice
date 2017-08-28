package chapter14;

import org.junit.Test;
import static org.junit.Assert.*;

public class BitonicSearchTest {

    public BitonicSearchTest() {
    }

    @Test
    public void testIsInclude_normal_true() {
        System.out.println("Обычная битоническая последовательность, число ПРИНАДЛЕЖИТ");
        int[] list = {-3, 0, 1, 2, 4, 5, 7, 9, 8, 6, 3, -2};
        int value = 7;
        BitonicSearch instance = new BitonicSearch();
        int expResult = 6;
        int result = instance.isInclude(list, value);
        assertEquals("fail", expResult, result);
    }

    @Test
    public void testIsInclude_normal_false() {
        System.out.println("Обычная битоническая последовательность, число НЕ ПРИНАДЛЕЖИТ");
        int[] list = {-3, 0, 1, 2, 4, 5, 7, 9, 8, 6, 3, -2};
        int value = -10;
        BitonicSearch instance = new BitonicSearch();
        int expResult = -1;
        int result = instance.isInclude(list, value);
        assertEquals("fail", expResult, result);
    }

    @Test
    public void testIsInclude_small_true() {
        System.out.println("Длина равна 1, ПРИНАДЛЕЖИТ");
        int[] list = {-3};
        int value = -3;
        BitonicSearch instance = new BitonicSearch();
        int expResult = 0;
        int result = instance.isInclude(list, value);
        assertEquals("fail", expResult, result);
    }

    @Test
    public void testIsInclude_small_false() {
        System.out.println("Длина равна 1, НЕ ПРИНАДЛЕЖИТ");
        int[] list = {-3};
        int value = -10;
        BitonicSearch instance = new BitonicSearch();
        int expResult = -1;
        int result = instance.isInclude(list, value);
        assertEquals("fail", expResult, result);
    }

    @Test
    public void testIsInclude_size_2_true() {
        System.out.println("Длина равна 2, ПРИНАДЛЕЖИТ");
        int[] list = {-3, 8};
        int value = 8;
        BitonicSearch instance = new BitonicSearch();
        int expResult = 1;
        int result = instance.isInclude(list, value);
        assertEquals("fail", expResult, result);
    }

    @Test
    public void testIsInclude_dec_size_2_true() {
        System.out.println("Длина равна 2, ПРИНАДЛЕЖИТ");
        int[] list = {-3, -5};
        int value = -5;
        BitonicSearch instance = new BitonicSearch();
        int expResult = 1;
        int result = instance.isInclude(list, value);
        assertEquals("fail", expResult, result);
    }

    @Test
    public void testIsInclude_size_2_false() {
        System.out.println("Длина равна 2, НЕ ПРИНАДЛЕЖИТ");
        int[] list = {-3, 8};
        int value = -10;
        BitonicSearch instance = new BitonicSearch();
        int expResult = -1;
        int result = instance.isInclude(list, value);
        assertEquals("fail", expResult, result);
    }

    @Test
    public void testIsInclude_list_constant_true() {
        System.out.println("Последовательность одинаковых элементов, ПРИНАДЛЕЖИТ");
        int[] list = {3, 3, 3, 3, 3};
        int value = 3;
        BitonicSearch instance = new BitonicSearch();
        int expResult = 0;
        int result = instance.isInclude(list, value);
        assertEquals("fail", expResult, result);
    }

    @Test
    public void testIsInclude_list_constant_false() {
        System.out.println("Последовательность одинаковых элементов, НЕ ПРИНАДЛЕЖИТ");
        int[] list = {3, 3, 3, 3, 3};
        int value = -10;
        BitonicSearch instance = new BitonicSearch();
        int expResult = -1;
        int result = instance.isInclude(list, value);
        assertEquals("fail", expResult, result);
    }

    @Test
    public void testIsInclude_no_bitonic_true() {
        System.out.println("НЕ битоническая последовательность, число ПРИНАДЛЕЖИТ");
        int[] list = {-3, 10, 1, 2, 4, -8, 7, 9, 16, 6, 3, -2};
        int value = -8;
        BitonicSearch instance = new BitonicSearch();
        int expResult = -1;
        int result = instance.isInclude(list, value);
        assertEquals("fail", expResult, result);
    }

    @Test
    public void testIsInclude_no_bitonic_false() {
        System.out.println("НЕ битоническая последовательность, число НЕ ПРИНАДЛЕЖИТ");
        int[] list = {-3, 10, 1, 2, 4, -8, 7, 9, 16, 6, 3, -2};
        int value = -16;
        BitonicSearch instance = new BitonicSearch();
        int expResult = -1;
        int result = instance.isInclude(list, value);
        assertEquals("fail", expResult, result);
    }
}
