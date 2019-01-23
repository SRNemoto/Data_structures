package p1;

public class Person {

	private String personID;
	private long phoneNum;

	//private Person next;

	/**
	 * The constructor for the Person class. The runtime has a constant cost
	 * Initializes the personID and phoneNum fields
	 * 
	 * @param personID
	 *            The String name of the person
	 * @param phoneNum
	 *            The phone number as a long
	 */
	public Person(String personID, long phoneNum) {
		this.personID = personID;
		this.phoneNum = phoneNum;
	}

	/**
	 * Returns the Person's String name / PersonID field. This method has a
	 * constant cost and runtime
	 * 
	 * @return The person's name.
	 */
	public String getPersonID() {
		return personID;
	}

	/**
	 * Returns the person's phone number as long. This method has a constant
	 * cost and runtime
	 * 
	 * @return The phone number.
	 */
	public long getPhoneNum() {
		return phoneNum;
	}

	/**
	 * Returns the person's name and phone number separated by a colon and a
	 * space.
	 */
	@Override
	public String toString() {
		return personID + ": " + phoneNum;
	}
}
