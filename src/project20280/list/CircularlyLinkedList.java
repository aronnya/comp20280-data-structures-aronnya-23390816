package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        // TODO
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> curr = tail.next;
        for (int j = 0; j < i; j++) {
            curr = curr.next;
        }

        return curr.data;
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        // TODO
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException();
        }

        if (i == 0) {
            addFirst(e);
        } else if (i == size) {
            addLast(e);
        } else {
            Node<E> curr = tail.next;
            for (int j = 0; j < i - 1; j++) {
                curr = curr.next;
            }

            Node<E> newest = new Node<E>(e, curr.next);
            curr.next = newest;
            size++;
        }
    }

    @Override
    public E remove(int i) {
        // TODO
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (i == 0) {
            return removeFirst();
        }

        Node<E> curr = tail.next;
        for (int j = 0; j < i - 1; j++) {
            curr = curr.next;
        }

        Node<E> removed = curr.next;
        curr.next = removed.next;

        if (removed == tail) {
            tail = curr;
        }

        size--;
        return removed.data;
    }

    public void rotate() {
        // TODO
        if (tail != null) {
            tail = tail.next;
        }
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;
        int count = 0;

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public E next() {
            if (count == 0) {
                curr = curr.next;
            }
            E res = curr.data;
            curr = curr.next;
            count++;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        // TODO
        if (isEmpty()) {
            return null;
        }

        Node<E> head = tail.next;
        E answer = head.data;

        if (head == tail) {
            tail = null;
        } else {
            tail.next = head.next;
        }

        size--;
        return answer;
    }

    @Override
    public E removeLast() {
        // TODO
        if (isEmpty()) {
            return null;
        }

        if (size == 1) {
            return removeFirst();
        }

        Node<E> curr = tail.next;
        while (curr.next != tail) {
            curr = curr.next;
        }

        E answer = tail.data;
        curr.next = tail.next;
        tail = curr;
        size--;

        return answer;
    }

    @Override
    public void addFirst(E e) {
        // TODO
        if (isEmpty()) {
            tail = new Node<E>(e, null);
            tail.next = tail;
        } else {
            Node<E> newest = new Node<E>(e, tail.next);
            tail.next = newest;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        // TODO
        addFirst(e);
        tail = tail.next;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (isEmpty()) {
            sb.append("]");
            return sb.toString();
        }
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}