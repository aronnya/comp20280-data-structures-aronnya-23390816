package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private final E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            // TODO
            element = e;
            next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            // TODO
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            // TODO
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            // TODO
            next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
        // TODO
        return size;
    }

    //@Override
    public boolean isEmpty() {
        // TODO
        return size == 0;
    }

    @Override
    public E get(int position) {
        // TODO
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }

        Node<E> curr = head;
        for (int i = 0; i < position; i++) {
            curr = curr.getNext();
        }

        return curr.getElement();
    }

    @Override
    public void add(int position, E e) {
        // TODO
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }

        if (position == 0) {
            addFirst(e);
        } else {
            Node<E> curr = head;
            for (int i = 0; i < position - 1; i++) {
                curr = curr.getNext();
            }

            Node<E> newest = new Node<>(e, curr.getNext());
            curr.setNext(newest);
            size++;
        }
    }


    @Override
    public void addFirst(E e) {
        // TODO
        head = new Node<>(e, head);
        size++;
    }

    @Override
    public void addLast(E e) {
        // TODO
        add(size, e);
    }

    @Override
    public E remove(int position) {
        // TODO
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }

        if (position == 0) {
            return removeFirst();
        }

        Node<E> curr = head;
        for (int i = 0; i < position - 1; i++) {
            curr = curr.getNext();
        }

        Node<E> removed = curr.getNext();
        curr.setNext(removed.getNext());
        size--;

        return removed.getElement();
    }

    @Override
    public E removeFirst() {
        // TODO
        if (isEmpty()) {
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        return answer;
    }

    @Override
    public E removeLast() {
        // TODO
        if (isEmpty()) {
            return null;
        }

        return remove(size - 1);
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    // Q6(b)
    public void reverse() {
        head = reverseRecursive(head);
    }

    private Node<E> reverseRecursive(Node<E> node) {
        if (node == null || node.getNext() == null) {
            return node;
        }

        Node<E> newHead = reverseRecursive(node.getNext());
        node.getNext().setNext(node);
        node.setNext(null);

        return newHead;
    }

    // Q7(b)
    public SinglyLinkedList<E> recursiveCopy() {
        SinglyLinkedList<E> copy = new SinglyLinkedList<>();
        copy.head = copyRecursive(this.head);
        copy.size = this.size;
        return copy;
    }

    private Node<E> copyRecursive(Node<E> node) {
        if (node == null) {
            return null;
        }

        return new Node<>(node.getElement(), copyRecursive(node.getNext()));
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

    }
}
