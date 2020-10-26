package ua.procamp;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}. In order to keep track on nodes, {@link LinkedList} keeps a reference to a head node.
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {

    private Node<T> head;
    private int size;

    public LinkedList() {
        size = 0;
        head = null;
    }

    public static <T> List<T> of(T... elements) {
        final LinkedList<T> linkedList = new LinkedList<>();
        Arrays.stream(elements).forEach(linkedList::add);
        return linkedList;
    }

    @Override
    public void add(T element) {
        add(size(), element);
    }

    @Override
    public void add(int index, T element) {
        Objects.checkIndex(index, size() + 1);
        size++;
        var newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }
        var current = getNodeAt(index - 1);
        if (nonNull(current.next)) {
            newNode.next = current.next;
        }
        current.next = newNode;
    }

    @Override
    public void set(int index, T element) {
        Objects.checkIndex(index, size());
        getNodeAt(index).value = element;
    }

    private Node<T> getNodeAt(int index) {
        return Stream.iterate(head, Objects::nonNull, node -> node.next)
                .skip(index).findFirst().orElseThrow(IndexOutOfBoundsException::new);
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size());
        return getNodeAt(index).value;
    }

    @Override
    public void remove(int index) {
        Objects.checkIndex(index, size());
        size--;
        if (index == 0) {
            head = head.next;
            return;
        }
        var prev = getNodeAt(index - 1);
        prev.next = prev.next.next;
    }

    @Override
    public boolean contains(T element) {
        return Stream.iterate(head, Objects::nonNull, node -> node.next)
                .anyMatch(node -> node.value.equals(element));
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        Node(final T value) {
            this(value, null);
        }

        Node(final T value, final Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}