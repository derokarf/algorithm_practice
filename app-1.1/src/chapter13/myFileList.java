package chapter13;

import java.io.File;
import java.util.Iterator;
import java.util.Scanner;

public class myFileList {

    //реализация класса взята из книги
    private class Stack<Item> implements Iterable<Item> {

        private Node first;
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
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            N++;
        }

        public Item pop() {
            Item item = first.item;
            first = first.next;
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

    private class rowFileList {

        public String path;
        public int countTabs = 0;
    }

    public String getList(String path) {
        String _list = "";
        File file = new File(path);

        String pathList[] = file.list();

        Stack<rowFileList> queueDir = new Stack<>();

        System.out.println("Dir:");

        rowFileList lastFileListRow;
        rowFileList nextFileListRow;

        if (pathList != null) {
            lastFileListRow = new rowFileList();
            lastFileListRow.path = path;
            queueDir.push(lastFileListRow);
            while (!queueDir.isEmpty()) {
                lastFileListRow = queueDir.pop();
                _list = _list.concat(getSpaceString(lastFileListRow.countTabs).concat(lastFileListRow.path.substring(lastFileListRow.path.lastIndexOf(File.separator) + 1))).concat("\n");
                File t_file = new File(lastFileListRow.path);
                for (String fname : t_file.list()) {
                    fname = lastFileListRow.path.concat(File.separator).concat(fname);
                    File g_file = new File(fname);
                    if (g_file.isDirectory()) {
                        nextFileListRow = new rowFileList();
                        nextFileListRow.path = fname;
                        nextFileListRow.countTabs = lastFileListRow.countTabs + 1;
                        queueDir.push(nextFileListRow);
                    } else {
                        _list = _list.concat(getSpaceString(lastFileListRow.countTabs + 1).concat(g_file.getName())).concat("\n");
                    }
                }
            }

        }
        return _list;
    }

    private String getSpaceString(int i) {
        String _str = "";
        for (int j = 0; j < i; j++) {
            _str = _str.concat("   ");
        }
        return _str;
    }

    public static void main(String args[]) {
        System.out.println("Введите путь к каталогу");
        Scanner scan = new Scanner(System.in);
        myFileList fList = new myFileList();
        System.out.println(fList.getList(scan.nextLine()));
    }
}
