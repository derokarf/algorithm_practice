package chapter14;

public class MainABC {

    // сложность 2N-1 ~ 2N при N = 2^N
    public void mainA(int[] listN) {
        System.out.println("MainA -------------------");
        for (int N : listN) {
            long count = 0;
            int sum = 0;
            for (int n = N; n > 0; n /= 2) {
                for (int i = 0; i < n; i++) {
                    sum++;
                    count++;
                }

            }
            System.out.println(String.valueOf(N) + ":" + String.valueOf(count));
        }
        System.out.println("-------------------------");
    }

    //сложность N-1 при N = 2^N
    public void mainB(int[] listN) {
        System.out.println("MainB -------------------");
        for (int N : listN) {
            long count = 0;
            int sum = 0;
            for (int i = 1; i < N; i *= 2) {
                for (int j = 0; j < i; j++) {
                    sum++;
                    count++;
                }
            }
            System.out.println(String.valueOf(N) + ":" + String.valueOf(count));
        }
        System.out.println("-------------------------");
    }

    //сложность NlgN при N = 2^N
    public void mainC(int[] listN) {
        System.out.println("MainC -------------------");
        for (int N : listN) {
            long count = 0;
            int sum = 0;
            for (int i = 1; i < N; i *= 2) {
                for (int j = 0; j < N; j++) {
                    sum++;
                    count++;
                }
            }
            System.out.println(String.valueOf(N) + ":" + String.valueOf(count));
        }

        System.out.println("-------------------------");
    }

    public static void main(String[] args) {
        MainABC test = new MainABC();
        int[] listN = new int[16];

        for (int i = 0; i < listN.length; i++) {
            listN[i] = (int) Math.pow(2, i);
        }

        test.mainA(listN);
        test.mainB(listN);
        test.mainC(listN);

    }
}
