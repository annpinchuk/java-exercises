package ua.procamp;

import static java.util.Objects.isNull;

/**
 * {@link LinkedQueue} implements FIFO {@link Queue}, using singly linked nodes. Nodes are stores in instances of nested
 * class Node. In order to perform operations {@link LinkedQueue#add(Object)} and {@link LinkedQueue#poll()}
 * in a constant time, it keeps to references to the head and tail of the queue.
 *
 * @param <T> a generic parameter
 */
public class LinkedQueue<T> implements Queue<T> {
    private Node<T> head;
    private Node<T> lastNode;
    private int size;

    public LinkedQueue() {
        this.head = new Node<>();
        lastNode = this.head;
        size = 0;
    }

    public void add(T element) {
        if (isNull(head.value)) {
            head.value = element;
        } else {
            var newNode = new Node<>(element);
            if (isNull(head.next)) {
                head.next = newNode;
            } else {
                lastNode.next = newNode;
            }
            lastNode = lastNode.next;
        }
        ++size;
    }

    public T poll() {
        T firstElement = null;
        if (!isEmpty()) {
            firstElement = head.value;
            head = head.next;
        }
        size--;
        return firstElement;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node() {
            this(null);
        }

        public Node(T value) {
            this(value, null);
        }

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}