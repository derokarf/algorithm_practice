package chapter12;

public class GATTACA {

    protected String strGen = "";

    //конструктор
    public GATTACA() {
    }

    //перегрузка конструктора
    public GATTACA(String _gen) {
        this.strGen = _gen;
    }

    public String getStrGen() {
        return strGen;
    }

    public void setStrGen(String strGen) {
        this.strGen = strGen;
    }

    //реализация стравнения методом полного циклического сдвига одной строки относительно другой
    private boolean eqOne(String _strGen2) {
        for (int i = 0; i < _strGen2.length(); i++) {
            if (_strGen2.equals(this.strGen)) {
                return true;
            } else {
                _strGen2 = _strGen2.concat(_strGen2.substring(0, 1)).substring(1);
            }
        }
        return false;
    }

    //реализация сравнения подстрок по первому общему символу
    private boolean eqTwo(String _strGen2) {
        if (_strGen2.length() == this.strGen.length()) {
            String firstChar = _strGen2.substring(0, 1);
            int index = this.strGen.indexOf(firstChar);
            int lenSub = this.strGen.length() - index;
            while (index >= 0) {
                //сравнить подстроку от найденного символа до конца
                if (this.strGen.substring(index).equals(_strGen2.substring(0, lenSub))) {
                    //сравнить оставшиеся подстроки строк
                    if (this.strGen.substring(0, index).equals(_strGen2.substring(lenSub))) {
                        return true;
                    }
                }
                index = this.strGen.indexOf(firstChar, index + 1);
                lenSub = this.strGen.length() - index;

            }
        }
        return false;
    }

    //третий метод в одну строку
    public boolean eq(String _strGen2) {
        return _strGen2.length() == this.strGen.length() && (this.strGen.concat(this.strGen).indexOf(_strGen2) > -1);
    }

    //перегрузка метода для сравнения двух объектов типа GATTACA
    public boolean eq(GATTACA _strGen2) {
        return _strGen2.getStrGen().length() == this.strGen.length() && (this.strGen.concat(this.strGen).indexOf(_strGen2.getStrGen()) > -1);

    }

    public static void main(String[] args) {
        GATTACA first = new GATTACA("ACTGACG");//ACTGACG
        GATTACA second = new GATTACA("TGACGAC");//TGACGAC
        boolean result = first.eq(second);
        if (result) {
            System.out.println("Строки циклически равны");
        } else {
            System.out.println("Строки циклически не равны");
        }

        //Анализ производительности методов сравнения
        String _src = "TGACGACACTGACTTTCCTA";
        String str1 = "";
        for (int i = 0; i < 1000; i++) {
            str1 = str1.concat(_src);
        }
        System.out.println("Длина строки ".concat(String.valueOf(str1.length())));
        String str2 = "GACTTTCCTA".concat(str1.substring(0, str1.length() - 10));
        long start;
        long stop;
        GATTACA genOne = new GATTACA(str1);

        start = System.nanoTime();
        System.out.println(genOne.eq(str2));
        stop = System.nanoTime();
        System.out.println("Третий метод: ".concat(String.valueOf(stop - start)));

        start = System.nanoTime();
        System.out.println(genOne.eqOne(str2));
        stop = System.nanoTime();
        System.out.println("Первый метод: ".concat(String.valueOf(stop - start)));

        start = System.nanoTime();
        System.out.println(genOne.eqTwo(str2));
        stop = System.nanoTime();
        System.out.println("Второй метод: ".concat(String.valueOf(stop - start)));

    }

}
