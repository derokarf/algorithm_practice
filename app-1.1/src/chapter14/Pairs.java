package chapter14;

import java.util.Arrays;

public class Pairs {

    public ResultRow[] getFarthestPair(double[] list) {

        if (list.length < 2) {
            return null;
        }
        ResultRow[] res = new ResultRow[2];
        res[0] = new ResultRow();
        res[1] = new ResultRow();
        int i;
        //т.к. самая далекая пара - это максимум и минимум, то ищем их
        ResultRow max = new ResultRow();
        ResultRow min = new ResultRow();
        max.value = Double.MIN_VALUE;
        min.value = Double.MAX_VALUE;
        for (i = 0; i < list.length; i++) {
            if (list[i] < min.value) {
                min.value = list[i];
                min.i = i;
            }
            if (list[i] > max.value) {
                max.value = list[i];
                max.i = i;
            }
        }
        res[0] = min;
        res[1] = max;

        return res;
    }

    public ResultRow[] getClosestPair(double[] list) {

        if (list.length < 2) {
            return null;
        }
        ResultRow[] res = new ResultRow[2];
        res[0] = new ResultRow();
        res[1] = new ResultRow();
        //Отсортируем массив, сложность O(n log(n))
        Arrays.sort(list);

        double min = Double.MAX_VALUE;
        double t_min;
        for (int i = 1; i < list.length; i++) {
            t_min = Math.abs(list[i - 1] - list[i]);
            if (t_min < min) {
                res[0].value = list[i - 1];
                res[0].i = i - 1;
                res[1].value = list[i];
                res[1].i = i;
            }
        }

        return res;
    }

    public static void main(String[] args) {

        double[] list = new double[10];
        StringBuilder sb = new StringBuilder("Массив: ");
        for (int i = 0; i < list.length; i++) {
            list[i] = Math.random() * 100;
            sb.append(i).append("[").append(list[i]).append("] ");
        }
        System.out.println(sb.toString());

        ResultRow[] result;
        Pairs utilPairs = new Pairs();

        result = utilPairs.getClosestPair(list);
        if (result != null) {
            sb = new StringBuilder("Самая близкая пара: ");
            sb.append(result[0].i).append("[").append(result[0].value).append("] ").append(result[1].i).append("[").append(result[1].value).append("]");
            System.out.println(sb.toString());
        }

        result = utilPairs.getFarthestPair(list);
        if (result != null) {
            sb = new StringBuilder("Самая далекая пара: ");
            sb.append(result[0].i).append("[").append(result[0].value).append("] ").append(result[1].i).append("[").append(result[1].value).append("]");
            System.out.println(sb.toString());
        }

    }
}
