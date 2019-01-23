package p1;

public class FBArrayList implements FBListIterator {

	/**
	 * This constant is for the initial size of the array, and for expanding the
	 * array when necessary.
	 */
	private static final int initial_max = 5;

	/**
	 * This field is multiplied by the initial_max field to increase the size of
	 * the array
	 */
	private static final int increase = 1;

	/**
	 * This field is multiplied by the initial_max field to decrease the size of
	 * the array
	 */
	private static final int decrease = -1;

	/**
	 * This field stores the position of the ListIterator
	 */
	private int iterator_index;

	/**
	 * The number of People in the array
	 */
	private int count;

	/**
	 * The array that holds the people in the phone book
	 */
	Person[] people;

	/**
	 * The constructor for FBArrayList. Sets the array size to initial_max, the
	 * initial number of people to zero, and the initial position of the
	 * ListIterator to zero. The runtime is constant
	 */
	public FBArrayList() {
		people = new Person[initial_max];
		count = 0;
		iterator_index = -1;
	}
	
	@Override
	public int size() {
		//Returns the number of people in the array
		return count;
	}

	/**
	 * Inserts the given person at the given index in the array. The worst case
	 * running time is that Person is inserted at the beginning of the filled
	 * array and every single element has to be moved back one space. This
	 * method's runtime increases linearly with the size of the array.
	 * 
	 * @param index
	 *            The index to insert the given person at.
	 * @param person
	 *            The person to insert in to the array.
	 */
	public void insert(int index, Person person) {
		// If maximum size of capacity reached, increase size
		if (count >= people.length)
			dilate(increase);

		// If the given index is past the last element
		if (index > count) {
			people[count] = person;

		} else {
			// Temporarily saves the person at the specified index
			Person temp = people[index];
			Person temp2; // Instantiates a second temporary spot
			// Puts given person in the given spot
			people[index] = person;

			// Saves the person in the spot, places the previous person in the
			// spot
			for (int i = index + 1; i <= count; i++) {
				temp2 = people[i];
				people[i] = temp;
				temp = temp2;
				temp2 = null;
			}
		}
		// Increases the count by one
		count++;
	}

	/**
	 * A helper method for the insert() and remove() array that creates a
	 * resized array and moves all of the elements of the previous array into
	 * it. The runtime increases linearly with the size of the array, as it has
	 * to run through every element once.
	 * 
	 * @param factor
	 *            Use either the increase or decrease constants as factors to
	 *            resize the array.
	 */
	private void dilate(int factor) {
		// instantiates a new array, either smaller or larger depending on the
		// givne factor
		Person[] new_arr = new Person[people.length + initial_max * factor];

		// Places every element in the people[] array into the new array
		for (int i = 0; i < count; i++) {
			new_arr[i] = people[i];
		}

		// Sets the new array as the people array.
		people = new_arr;
	}

	/**
	 * Removes the Person at the specified index from the array The worst case
	 * runtime is if the removed Person is at the beginning of the array and
	 * every single element must be shifted forward. The runtime increases
	 * linearly with the size of the array.
	 * 
	 * @param index
	 *            The index to remove the Person from.
	 * @return The removed person
	 */
	public Person remove(int index) {
		// Reserves space for the person being removed / returned
		Person person;

		// Makes sure the index is valid
		if (index > -1 && index < count) {

			// sets the person being removed as the person at the specified
			// index in the people[] array
			person = people[index];

			// Shifts everybody remaining in the people[] array one space
			// forward.
			for (int i = index + 1; i < count; i++) {
				people[i - 1] = people[i];
				people[i] = null;
			}
			
			//Reduce the count field by one b/c a person was removed
			count--;
		} else {
			
			//If the index was not valid, return null and remove nobody.
			person = null;
		}

		//If there are too many empty slots in the people[] array, reduce the size.
		int size_dif = Math.abs(people.length - count);
		if (size_dif > initial_max)
			dilate(decrease);

		//Return the person removed or null if no removal
		return person;
	}

	/**
	 * Returns the Person at the specified index. Returns null if the index is
	 * out of the valid bounds. The runtime of this array is constant.
	 * 
	 * @param index
	 *            The index to retrieve the person at.
	 * @return The Person at the specified index. Returns null if invalid index.
	 */
	public Person lookup(int index) {
		// Reserves space for the person being looked up
		Person person;

		//Checks to make sure the index is valid
		if (index >= 0 && index < count)
			person = people[index];
		else {
			person = null;
		}

		//Returns the person at the specified index, null if invalid
		return person;
	}

	@Override
	public Person next() {
		//Increments the iterator_index and looks up the person at the location in the array.
		iterator_index++;
		return lookup(iterator_index);
	}

	@Override
	public void reset() {
		//Resets the iterator to the beginning of the array.
		iterator_index = -1;
	}
}
