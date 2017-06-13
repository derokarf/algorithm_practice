package chapter13;

import java.io.File;
import java.util.Iterator;
import java.util.Scanner;

public class StackFileList {

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

    private class RowFileList {

        public RowFileList(File _file) {
            file = _file;
        }
                
        public File file;
        public int countTabs = 0;
    }

    public String getList(String path) {
        StringBuilder _list = new StringBuilder();
        File firstFile = new File(path);
        
        if(!firstFile.isDirectory()){
            System.out.println("Это файл");
            return "";
        }

        Stack<RowFileList> queueDir = new Stack<>();

        RowFileList lastFileListRow;
        RowFileList nextFileListRow;
        
        lastFileListRow = new RowFileList(firstFile);
        queueDir.push(lastFileListRow);
        while (!queueDir.isEmpty()) {
            lastFileListRow = queueDir.pop();
            _list.append(getDirString(lastFileListRow));
            for (File g_file : lastFileListRow.file.listFiles()) {
                if (g_file.isDirectory()) {
                    nextFileListRow = new RowFileList(g_file);
                    nextFileListRow.countTabs = lastFileListRow.countTabs + 1;
                    queueDir.push(nextFileListRow);
                } else {
                    _list.append(getFileString(lastFileListRow.countTabs,g_file));
                }
            }
        }

        return _list.toString();
    }
    
    private String getFileString(int countTabs,File _file){
        StringBuilder strFile = new StringBuilder(getSpaceString(countTabs + 1));
        strFile.append("│ -");
        strFile.append(_file.getName());
        strFile.append("\n");
        return strFile.toString();
    }
    
    private String getDirString(RowFileList _row){
        StringBuilder strDir = new StringBuilder(getSpaceString(_row.countTabs));
        strDir.append("└─ ");
        strDir.append(_row.file.getAbsolutePath());
        strDir.append("\n");
        return strDir.toString();
    }
    
    private String getSpaceString(int i) {
        StringBuilder _str = new StringBuilder();
        for (int j = 0; j < i; j++) {
            _str = _str.append("│  ");
        }
        return _str.toString();
    }

    public static void main(String args[]) {
        System.out.println("StackFileList");
        System.out.println("Введите путь к каталогу");
        Scanner scan = new Scanner(System.in);
        StackFileList fList = new StackFileList();
        System.out.println(fList.getList(scan.nextLine()));
    }
}
