package p1;

public class FBLinkedList implements FBListIterator {

	/**
	 * A private helper class for FBLinkedList to use and act as the "links" in
	 * the list Contains a 'payload' field that stores the Person this node
	 * represents. Contains a 'next' field that stores the next Node in the
	 * list.
	 * 
	 * @author Shota
	 *
	 */
	private class Node {
		Person payload = null;
		Node next = null;

		/**
		 * Constructor setting the payload and next fields of this instance
		 * 
		 * @param payload
		 *            The Person this node represents
		 * @param next
		 *            The next Node in the list
		 */
		Node(Person payload, Node next) {
			this.payload = payload;
			this.next = next;
		}
	}

	/**
	 * The number of People in this phone book
	 */
	private int count;

	/**
	 * The first Person in this list.
	 */
	private Node top;

	/**
	 * This field stores the position of the ListIterator
	 */
	private Node iterator_next;

	/**
	 * The constructor for FBLinkedList. Sets the number of people to zero, and
	 * sets the first Person to null. This method has a constant runtime.
	 */
	public FBLinkedList() {
		count = 0;
		top = null;
		iterator_next = null;
	}

	/**
	 * Inserts the given person at the given index in the list. The worst case
	 * running time is that Person is inserted at the end of the list, and the
	 * entire linked list has to be traversed to find the end. This method's
	 * runtime increases linearly with the size of the array.
	 * 
	 * @param index
	 *            The index to insert the given person at.
	 * @param person
	 *            The person to insert in to the list.
	 */
	public void insert(int index, Person person) {
		// If the list is empty, automatically adds the Person to the top
		if (count == 0) {
			top = new Node(person, null);
			count++;
			reset(); //For the iterator
		} else if (index == 0) {
			// Special protocol if the desired location of insertion is the top.
			Node new_top = new Node(person, top);
			top = new_top;
			count++;
		} else if (index > 0) {
			// If the index exceeds the size of the list, make it insert at the
			// very end.
			if (index >= count)
				index = count;

			// Reserves space for the previous Node and the current Node.
			Node previous = null;
			Node current = top;

			// Searches for the Node specified by the index and the Node's
			// predecessor.
			for (int i = 0; i < index; i++) {
				previous = current;
				current = current.next;
			}

			// This if statement shouldn't happen but just as a safeguard
			// against if the special case doesn't trigger for some reason
			if (previous != null)
				// Creates a new node for the given Person referencing the node
				// formerly in its position as the next. Adjusts the previous
				// Node's 'next' reference to the new Node.
				previous.next = new Node(person, current);

			// Increments the number of people in the phone book
			count++;
		}
	}

	/**
	 * Removes the Person at the specified index from the list. The worst case
	 * runtime is if the removed Person is at the end of the list and the entire
	 * linked list has to be traversed to reach the end. The runtime increases
	 * linearly with the size of the array.
	 * 
	 * @param index
	 *            The index to remove the Person from.
	 * @return The removed Person.
	 */
	public Person remove(int index) {
		// Reserves space for the Node to remove
		Node current = null;

		// Special protocol for if the top is being removed
		if (index == 0) {
			current = top;
			top = top.next;
			count--;
		} else if (index > 0 && index < count) {

			// If the index is valid, search for the specified Person
			Node previous = null;
			current = top;
			for (int i = 0; i < index; i++) {
				previous = current;
				current = current.next;
			}
			// This if statement shouldn't happen but just as a safeguard
			// against if the special case doesn't trigger for some reason
			if (previous != null)
				// Adjusts the 'next' references to skip over the specified
				// Person
				previous.next = current.next;

			count--;
		}

		// If current isn't null, return current's payload. If it is, return
		// null.
		Person person;
		if (current != null)
			person = current.payload;
		else
			person = null;

		return person;
	}

	/**
	 * Returns the Person at the specified index. The worst case runtime is when
	 * the last Person in the array is called for, and the method must traverse
	 * the entirety of the list to retrieve it.
	 * 
	 * @param index
	 *            The index of the list to retrieve the person from.
	 * @return The person at the index. If the index exceeds the size of the
	 *         list, returns null.
	 */
	public Person lookup(int index) {
		Node current = null;
		if (index < count) {
			current = top;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
		}
		return current.payload;
	}

	@Override
	public void reset() {
		iterator_next = top;

	}

	@Override
	public Person next() {
		Person person = null;

		if (iterator_next.payload != null)
			person = iterator_next.payload;

		if (iterator_next.next != null)
			iterator_next = iterator_next.next;
		else
			iterator_next = null;

		return person;
	}

	@Override
	public int size() {
		return count;
	}

}
