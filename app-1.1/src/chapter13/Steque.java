package chapter13;

import java.util.Iterator;
import java.util.Scanner;

public class Steque<Item> implements Iterable<Item> {

    private Node first; //Первый элемент стека
    private Node last; //Последний элемент стека
    private int N;

    private class Node {

        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (oldFirst == null) {
            last = first;
        } else {
            first.next = oldFirst;
        }
        N++;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (first == null) {
            first = last;
        } else {
            oldLast.next = last;
        }
        N++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        if (first == null) {
            last = first;
        }
        N--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите элемент стека, n> вставка в начало очереди, n< - вставка в конец очереди");
        System.out.println("pop - вытолкнуть, exit - выход");

        Steque<Integer> testSteque = new Steque();
        String command = "";
        while (!command.equals("exit")) {
            command = scan.nextLine();
            if (command.equalsIgnoreCase("pop")) {
                System.out.println("pop ".concat(String.valueOf(testSteque.pop())));
            }
            int index;
            Integer val;
            index = command.lastIndexOf("<");
            if (index > 0) {
                val = Integer.valueOf(command.substring(0, index));
                testSteque.enqueue(val);
            } else {
                index = command.lastIndexOf(">");
                if (index > 0) {
                    val = Integer.valueOf(command.substring(0, index));
                    testSteque.push(val);
                }
            }

            for (Integer i : testSteque) {
                System.out.print(String.valueOf(i).concat("  "));
            }
            System.out.println("");
        }
    }
}
