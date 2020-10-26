package ua.procamp;


import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import ua.procamp.exception.EmptyStackException;

public class LinkedStack<T> implements Stack<T> {

	private Node<T> head;
	private int size;

	public LinkedStack() {
		this.head = new Node<>();
		size = 0;
	}

	@Override
	public void push(T element) {
		if (isNull(head.value)) {
			head.value = element;
		} else {
			head = new Node<>(element, head);
		}
		size++;
	}

	@Override
	public T pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		T result = head.value;
		if (nonNull(head.next)) {
			head = head.next;
			size--;
		}
		return result;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	private class Node<T> {
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