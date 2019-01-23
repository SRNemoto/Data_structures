package homework;

public class PhoneBook<AnyType> {
	public static final boolean SSN_type = true;
	public static final boolean name_type = false;
	public static final int initial_size = 20;

	private boolean type;
	private AnyType[] ids;
	private int[] phone_numbers = new int[initial_size];

	public PhoneBook(boolean b) {
		this.type = b;
		ids = (AnyType[]) new Object[initial_size];
	}

	public boolean addPerson(int phone_number, AnyType identifier) {
		if ((identifier instanceof String && type == name_type)
				|| (identifier instanceof Integer && type == SSN_type)) {
			int index = searchEmpty();
			if (index != -1) {
				phone_numbers[index] = phone_number;
				ids[index] = identifier;
				return true;
			}
			System.err.println("Phone Book is full.");
			return false;
		} else {
			System.err.println("Wrong Type of Identifier!");
			return false;
		}
	}

	public void deletePerson(AnyType identifier) {
		int index = search(identifier);
		if (index != -1) {
			ids[index] = null;
			phone_numbers[index] = 0;
		}

	}

	public int findPerson(AnyType identifier) {
		int index = search(identifier);
		if (index != -1)
			return phone_numbers[index];
		else{
			System.err.println("Person not found.");
			return 0;
		}
	}

	private int search(AnyType identifier) {
		for (int i = 0; i < ids.length; i++) {
			if (compareId(ids[i], identifier))
				return i;
		}
		return -1;
	}

	private int searchEmpty() {
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] == null && phone_numbers[i] == 0)
				return i;
		}
		return -1;
	}

	private boolean compareId(AnyType p1, AnyType p2) {
		if (type == SSN_type) {
			Integer ID1 = (Integer) p1;
			Integer ID2 = (Integer) p2;

			if (ID1.intValue() == ID2.intValue())
				return true;
			else
				return false;
		} else {
			String p1Name = (String) p1;
			String p2Name = (String) p2;

			if (p1Name.equalsIgnoreCase(p2Name))
				return true;
			else
				return false;
		}
	}

	private boolean checkIfWorldExploded() {
		System.err.println("The world has exploded");
		if (ids.length == phone_numbers.length)
			return false;
		else
			return true;
	}
}
