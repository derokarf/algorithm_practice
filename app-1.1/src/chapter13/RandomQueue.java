package chapter13;

import java.util.Iterator;

public class RandomQueue<Item> implements Iterable<Item> {

    private Item[] box = (Item[]) new Object[1];
    private int N = 0;

    public boolean isEmpty() {
        return N == 0;
    }

    void enqueue(Item item) {
        if (N == box.length) {
            resize(2 * box.length);
        }
        box[N++] = item;
    }

    Item dequeue() {
        int index = (int) (Math.random() * (N - 1));
        Item item = box[index];
        box[index] = box[--N];
        box[N] = null;
        if (N > 0 && N == box.length / 4) {
            resize(box.length / 2);
        }
        return item;
    }

    Item sample() {

        return box[(int) (Math.random() * (N - 1))];
    }

    public int size() {
        return N;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = box[i];
        }
        box = temp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> { // Support LIFO iteration.

        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return box[--i];
        }

        @Override
        public void remove() {
        }
    }

    private static class Card {

        String _mark;

        public Card(String mark) {
            _mark = mark;
        }

        public String getMark() {
            return _mark;
        }
    }

    public static void main(String args[]) {
        String value[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String suit[] = {"♠", "♣", "♥", "♦"};

        RandomQueue<Card> cardBox = new RandomQueue();

        for (String tmp_suit : suit) {
            for (String tmp_value : value) {
                Card tmp = new Card(tmp_value.concat(tmp_suit));
                cardBox.enqueue(tmp);
            }
        }

        //Посмотрим колоду
        for (Card tmpCard : cardBox) {
            System.out.print(tmpCard._mark.concat(" "));
        }
        System.out.println("");

        //игроки
        String hand1 = "";
        String hand2 = "";
        String hand3 = "";
        String hand4 = "";
        //раздадим карты
        while (!cardBox.isEmpty()) {
            hand1 = hand1.concat(cardBox.dequeue()._mark).concat(" ");
            hand2 = hand2.concat(cardBox.dequeue()._mark).concat(" ");
            hand3 = hand3.concat(cardBox.dequeue()._mark).concat(" ");
            hand4 = hand4.concat(cardBox.dequeue()._mark).concat(" ");
        }

        System.out.println(hand1);
        System.out.println(hand2);
        System.out.println(hand3);
        System.out.println(hand4);

        System.out.println("В колоде осталось ".concat(String.valueOf(cardBox.size())).concat(" карт"));
    }
}
