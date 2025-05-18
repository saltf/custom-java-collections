package set;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomHashSet<T> implements Iterable<T> {
    // Node class to handle collisions via linked list
    private static class Node<E> {
        E key;
        Node<E> next;

        public Node(E key) {
            this.key = key;
        }
    }

    private Node<T>[] table; // Hash table array (buckets)
    private int capacity = 16; // Default capacity
    private int size = 0;  // Number of elements in set

    public CustomHashSet() {
        @SuppressWarnings("unchecked")
        Node<T>[] newTable = new Node[capacity];
        this.table = newTable;
    }

    public void add(T key) {
        int index = hash(key);
        Node<T> current = table[index];

        if (current == null) {
            table[index] = new Node<>(key); // No collision
            size++;
            return;
        }

        while (true) {
            if (current.key.equals(key)) return; // Duplicate
            if (current.next == null) break;
            current = current.next;
        }

    }

    public void remove(T key) {
        int index = hash(key);
        Node<T> current = table[index];
        Node<T> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public boolean contains(T key) {
        int index = hash(key);
        Node<T> current = table[index];
        while (current != null) {
            if (current.key.equals(key)) return true;
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        for (int i = 0; i < table.length; i++){
            table[i] = null;
        }
    }

    private int hash(T key) {
        return Math.abs(key.hashCode()) % capacity;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean first = true;
        for(Node<T> node : table){
            Node<T> current = node;
            while (current != null) {
                if(!first) sb.append(", ");
                sb.append(current.key);
                first = false;
                current = current.next;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            Node<T> current = null;
            @Override
            public boolean hasNext() {
                while (current == null && index < table.length){
                    current = table[index++];
                }
                return  current!=null;
            }

            @Override
            public T next() {
                if(!hasNext()) throw new NoSuchElementException();
                T key = current.key;
                current = current.next;
                return key;
            }
        };
    }

}
