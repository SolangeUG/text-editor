package textgen;

import java.util.AbstractList;


/**
 * A class that implements a doubly linked list
 * @author UC San Diego Intermediate Programming MOOC team
 * @author Solange U. Gasengayire
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {

	LLNode<E> head;
	LLNode<E> tail;
	private int size;

	/**
	 * Create a new empty LinkedList
	 */
	public MyLinkedList() {
		// DONE: Implement this method
		this.size = 0;
		this.head = new LLNode<>(null);
		this.tail = new LLNode<>(null);
		head.next = tail;
		tail.next = head;
		head.prev = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) {
		// DONE: Implement this method
		if (element == null) {
			throw new NullPointerException("The element to add cannot be null");
		}

		if (this.size == 0) {
			new LLNode<>(element, head, tail);
		} else {
			new LLNode<>(element, tail.prev, tail);
		}
		this.size++;

		return true;
	}

	/**
	 * Get the element at position index
	 * @param index position index
	 * @return the element at that position
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E get(int index) {
		// DONE: Implement this method.
		if (index < 0 || index >= size)  {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
		}

		LLNode<E> node = getNode(index);
		return node.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param index index where the element should be added
	 * @param element The element to add
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public void add(int index, E element ) {
		// DONE: Implement this method
		if (size == 0 && index == 0) {
			// We're allowing adding to an emptyList when the specified index is 0
			this.add(element);
		} else {

			if (element == null) {
				throw new NullPointerException("The element to add cannot be null");
			}

			if (index < 0 || index >= size) {
				throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
			}

			LLNode<E> node = getNode(index);
			new LLNode<>(element, node.prev, node);
			this.size++;
		}
	}


	/**
	 * Return the size of the list
	 * @return this list size
	 */
	public int size() {
		// DONE: Implement this method
		return this.size;
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 */
	public E remove(int index) {
		// DONE: Implement this method
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
		}

		LLNode<E> node = getNode(index);
		E data = node.data;

		LLNode<E> prevNode = node.prev;
		LLNode<E> nextNode = node.next;
		prevNode.next = nextNode;
		nextNode.prev = prevNode;

		this.size--;

		return data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) {
		// DONE: Implement this method
		if (element == null) {
			throw new NullPointerException("The element to add cannot be null");
		}

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
		}

		LLNode<E> node = getNode(index);
		E data = node.data;
		node.data = element;

		return data;
	}

	/**
	 * Return a string representation of this list
	 * @return this list as a string
	 */
	@Override
	public String toString() {
		StringBuilder list = new StringBuilder("[");
		for (int i = 0; i < this.size; i++) {
			LLNode<E> node = getNode(i);
			list.append(node.toString());
			if (i < this.size - 1) {
				list.append("|");
			}
		}
		list.append("]");
		return list.toString();
	}

	/**
	 * Return the node at the specified index
	 * @param index The requested index
	 * @return the node at that position in the list
	 */
	private LLNode<E> getNode(int index) {
		int i = -1;
		LLNode<E> current = head;
		while (i < index) {
			i++;
			current = current.next;
		}
		// At this point, i = index!
		return current;
	}
}

/**
 * A class that represents a node in the list
 * @param <E> The type of the element stored in the node
 */
class LLNode<E> {

	LLNode<E> prev;
	LLNode<E> next;
	E data;

	/**
	 * Constructor
	 * @param e element stored in this node
	 */
	LLNode(E e) {
		this.data = e;
	}

	/**
	 * Constructor
	 * @param e element stored in this node
	 * @param previous the previous node to this one
	 */
	LLNode(E e, LLNode<E> previous) {
		this(e);
		this.prev = previous;
		this.next = previous.next;
		previous.next = this;
	}

	/**
	 * Constructor
	 * @param e element stored in this node
	 * @param prevNode the previous node to this one
	 * @param nextNode the next node to this one
	 */
	LLNode(E e, LLNode<E> prevNode, LLNode<E> nextNode) {
		this(e);
		this.prev = prevNode;
		prevNode.next = this;
		this.next = nextNode;
		nextNode.prev = this;
	}

	/**
	 * Return a string representation of this node
	 * @return this node as a string
	 */
	@Override
	public String toString() {
		return String.valueOf(data);
	}
}
