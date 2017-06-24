import java.util.Iterator;

/**
 * 
 * @author Shashwat Koranne
 * 
 * Code in this file is written as a part of an assignment for the course,
 * 'Algorithms I' offered by Princeton University on coursera.org
 * 
 * Deque was implemented using Linked List
 *
 */
public class Deque<Item> implements Iterable<Item> {
	/**
	 * Initializing the head to start the Linked List initiation.
	 */
	static Node head;
	
	/**
	 * Private node class to help keep the track of elements of the Linked
	 * List.
	 * @param <Item> Can be any object, primitive or reference data type
	 */
	private static class Node<Item> {
		@SuppressWarnings("rawtypes")
		Node next;
		Item data;
		public Node(Item data, Node next) {
			this.next = next;
			this.data = data;
		}
	}
	
	/**
	 * Constructor to initialize the Linked List.
	 */
	public Deque() {
		this.head = null;
	}
	
	/**
	 * Checks if Deque is empty.
	 * @return true if deque is empty
	 */
	public boolean isEmpty() {
		if (this.head == null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks the size of the deque.
	 * @return the number of elements in the deque
	 */
	public int size() {
		int sizeCount = 0;
		Node pointer = head;
		while(pointer.next != null) {
			pointer = pointer.next;
			sizeCount++;
		}
		return ++sizeCount;
	}
	
	/**
	 * Adds an element to start of the deque.
	 * @param item is the element to be added
	 */
	public void addFirst(Item item) {
		if (this.isEmpty()) {
			head = new Node(item, head);
		} else {
			Node pointer = head;
			head = new Node(item, head);
			head.next = pointer;
		}
	}
	
	/**
	 * Adds an element to end of the deque.
	 * @param item is the element to be added
	 */
	public void addLast(Item item) {
		if (this.isEmpty()) {
			head = new Node(item, head);
		} else {
			Node pointer = head;
			while(pointer.next != null) {
				pointer = pointer.next;
			}
			pointer.next = new Node(item, pointer.next);
		}
	}
	
	/**
	 * Removes an element from the start of the deque.
	 * @return the item removed
	 */
	public Item removeFirst() {
		Item returnItem;
		if (this.isEmpty()) {
			return null;
		} else {
			Node pointer = head;
			returnItem = (Item) pointer.data;
			head = pointer.next;
		}
		return returnItem;
	}
	
	/**
	 * Removes an element from the end of the deque.
	 * @return the item removed
	 */
	public Item removeLast() {
		Item returnItem;
		if (this.isEmpty()) {
			return null;
		} else {
			Node pointer = head;
			while(pointer.next.next != null) {
				pointer = pointer.next;
			}
			returnItem = (Item) pointer.next.data;
		}
		return returnItem;
	}
	
	/**
	 * Prints the items in the deque
	 */
	public void showItems() {
		Node pointer = head;
		while(pointer.next != null) {
			System.out.println(pointer.data);
			pointer = pointer.next;
		}
		System.out.println(pointer.data);
	}
	
	/*public static void main(String[] args) {
		Deque d = new Deque();
		d.addFirst("abc");
		d.addLast(2);
		d.addLast(3.00);
		d.addFirst(4);
		d.addLast(5);
		//d.showItems();
		d.removeLast();
		d.removeFirst();
		d.showItems();
	}*/

	@Override
	public Iterator<Item> iterator() {
		return new RangeIterator();
	}
	
	private class RangeIterator implements Iterator<Item> {
		Node index;
		
		public RangeIterator() {
			index = Deque.head;
		}

		@Override
		public boolean hasNext() {
			Node pointer = index;
			if (pointer.next != null) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public Item next() {
			Node pointer = index;
			if (this.hasNext()) {
				pointer = pointer.next;
				return (Item) pointer.data;
			} else {
				return null;
			}
		}
	}
	
}
