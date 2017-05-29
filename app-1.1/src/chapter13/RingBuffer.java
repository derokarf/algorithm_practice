package chapter13;

import java.util.Iterator;

public class RingBuffer<Item> implements Iterable<Item> {

    private final Item[] ring;
    private int N = 0;
    private int last = -1;
    private int first = 0;
    private final int size;

    public RingBuffer(int _size) {
        size = _size;
        ring = (Item[]) new Object[size];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public boolean enqueue(Item item) {
        if (N == ring.length) {
            return false;
        }
        last = ++last % size;
        ring[last] = item;
        N++;
        return true;
    }

    public Item dequeue() {
        Item item = null;
        if (N == 0) {
            return item;
        }
        item = ring[first];
        N--;
        first = ++first % size;

        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RingIterator();
    }

    private class RingIterator implements Iterator<Item> {

        private int i = 0;
        private int index = first - 1;

        @Override
        public boolean hasNext() {
            return i < N;
        }

        @Override
        public Item next() {
            i++;
            index = ++index % size;
            return ring[index];
        }

        @Override
        public void remove() {
        }
    }

    public static void main(String args[]) {
        RingBuffer<Integer> testRing = new RingBuffer<>(8);
        //заполнение
        for (int i = 0; i < 8; i++) {
            testRing.enqueue(i);
        }
        printRing(testRing);
        //удаление
        System.out.println(testRing.dequeue());
        System.out.println(testRing.dequeue());

        printRing(testRing);
        //закольцовывание
        testRing.enqueue(17);
        printRing(testRing);
        //очистка очереди
        while (testRing.size() > 0) {
            testRing.dequeue();
            printRing(testRing);
        }
    }

    public static void printRing(RingBuffer<Integer> _ring) {
        for (Integer n : _ring) {
            System.out.print(String.valueOf(n).concat(" "));
        }
        System.out.println("");
    }
}
