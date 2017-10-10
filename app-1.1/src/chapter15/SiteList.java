package chapter15;

import java.util.Iterator;

public class SiteList implements Iterable {

    private Site[] a = new Site[0];
    private int N = 0;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int max) {
        Site[] temp = new Site[max];
        for (int i = 0; i < a.length; i++) {
            temp[i] = a[i];
        }
        //добавляем оставшиеся узлы
        for (int j = a.length; j < temp.length; j++) {
            temp[j] = new Site(j, 0);
        }
        a = temp;
    }

    public Site getSite(int index) {
        return a[index];
    }

    public int newSite(int index) {
        if (index >= N) {
            N = index + 1;
            if (index >= a.length) {
                resize(2 * index);
            }
        }
        return a[index].id;
    }

    @Override
    public Iterator iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator {

        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < N;
        }

        @Override
        public Site next() {
            return a[i++];
        }

        @Override
        public void remove() {
        }
    }
}
