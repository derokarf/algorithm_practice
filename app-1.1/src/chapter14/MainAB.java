package chapter14;

public class MainAB {

    public void mainA(int[] listN) {
        for (int N : listN) {
            int sum = 0;
            for (int n = N; n > 0; n /= 2) {
                for (int i = 0; i < n; i++) {
                    sum++;
                }
                System.out.println(sum);
            }

        }

    }

    public void mainB(int[] listN) {
        for (int N : listN) {
            int sum = 0;
            for (int i = 1; i < N; i *= 2) {
                for (int j = 0; j < i; j++) {
                    sum++;
                }
                System.out.println(sum);
            }
        }

    }

    public static void main(String[] args) {
        MainAB test = new MainAB();
        int[] listN = {1, 2, 4, 8, 16, 32, 64};

        long ttdd1, ttdd2;

        ttdd1 = System.nanoTime();
        test.mainA(listN);
        ttdd2 = System.nanoTime();
        System.out.println("A:" + String.valueOf(ttdd2 - ttdd1));

        ttdd1 = System.nanoTime();
        test.mainB(listN);
        ttdd2 = System.nanoTime();
        System.out.println("B:" + String.valueOf(ttdd2 - ttdd1));
    }
}
