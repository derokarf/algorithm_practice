package chapter15;

import java.util.Iterator;

public class SiteList implements Iterable {

    private Site[] a = new Site[1];

    public boolean isEmpty() {
        return a.length == 0;
    }

    public int size() {
        return a.length;
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
        if ((index >= 0) && (index < a.length)) {
            return a[index].id;
        }

        if (index >= a.length) {
            resize(2 * index);
        }
        Site item = new Site(index, 0);
        a[index] = item;
        return index;
    }

    @Override
    public Iterator iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator {

        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < a.length;
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
