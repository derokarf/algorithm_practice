package chapter14;

public class Pairs {

    public ResultRow[] getFarthestPair(double[] list) {
        ResultRow[] res = new ResultRow[2];
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
        ResultRow[] res = new ResultRow[2];

        return res;
    }

    public static void main(String[] args) {

    }
}
