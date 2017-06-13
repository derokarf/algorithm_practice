package chapter13;

import java.io.File;
import java.util.Iterator;
import java.util.Scanner;

public class QueueFileList {

    //реализация очереди взята из книги
    private class Queue<Item> implements Iterable<Item> {

        private Node first;
        private Node last;
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

        public void enqueue(Item item) {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) {
                first = last;
            } else {
                oldlast.next = last;
            }
            N++;
        }

        public Item dequeue() {
            Item item = first.item;
            first = first.next;
            if (isEmpty()) {
                last = null;
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
    }

    public String getList(String path) {
        StringBuilder _list = new StringBuilder();
        File firstFile = new File(path);

        Queue<File> queueDir = new Queue<>();

        if (!firstFile.isDirectory()) {
            return "";
        }
        queueDir.enqueue(firstFile);
        while (!queueDir.isEmpty()) {
            File t_file = queueDir.dequeue();
            _list.append(t_file.getAbsolutePath()).append("\n");
            for (String fname : t_file.list()) {
                fname = t_file.getAbsolutePath().concat(File.separator).concat(fname);
                File g_file = new File(fname);
                if (g_file.isDirectory()) {
                    queueDir.enqueue(g_file);
                } else {
                    _list.append(" - ").append(g_file.getName()).append("\n");
                }
            }
        }
        return _list.toString();
    }

    public static void main(String args[]) {
        System.out.println("QueueFileList");
        System.out.println("Введите путь к каталогу");
        Scanner scan = new Scanner(System.in);
        QueueFileList fList = new QueueFileList();
        System.out.println("Результат:\n".concat(fList.getList(scan.nextLine())));
    }

}
