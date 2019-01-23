package p1;

public interface FBListIterator {
	
	/**
	 * Resets the iterator's position to zero.
	 */
	public void reset();
	
	/**
	 * Returns the current node in the list and prepares the iterator to return the next.
	 * The runtime of this method is constant
	 * @return Returns true if the incremented successfully, false if the
	 *         counter exceeds the number of people.
	 */
	public Person next();
	
	/**
	 * Returns the number of people contained in the list. The runtime is
	 * constant.
	 * @return The number of people in the list
	 */
	public int size();
}
