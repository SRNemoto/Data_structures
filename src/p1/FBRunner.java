package p1;

public class FBRunner {

	public static void main(String[] args) {
		String[] names = new String[] { "Jake", "Jack", "John", "James", "Joseph", "Jonathan", "Jerome", "Jaques",
				"Jose", "Jenkins" };
		FBArrayList arrayFB = new FBArrayList();
		FBLinkedList linkFB = new FBLinkedList();

		//This for loop populates the lists with people.
		for (int i = 0; i < 10; i++) {
			Person person = new Person(names[i], (long) (Math.random() * 10000000));
			arrayFB.insert(i, person);
			linkFB.insert(i, person);
		}

		// This for loop tests the insertion methods of the lists by adding
		// someone with the name "BigMomma" and the phone number 4222222.
		for (int j = 1; j < 4; j++) {
			Person mom = new Person("BigMomma " + j, 4222222);
			linkFB.insert(2, mom);
			arrayFB.insert(2, mom);
		}

		// This for loop tests the removal methods of the lists by removing all
		// but one BigMommas
		for (int i = 0; i < 2; i++) {
			linkFB.remove(2);
			arrayFB.remove(2);
		}

		//Check for the number of people with the prefix 422
		localsTotNum(arrayFB, 422);
		//Display the contents of the array
		displayContents(arrayFB);
		
		//Repeat for the linked list
		localsTotNum(linkFB, 422);
		displayContents(linkFB);
		
	}

	/**
	 * Checks the provided list for people with phone numbers containing the
	 * given prefix. The runtime of this method is linear with the size of the
	 * array because it must check each element of the list once.
	 * 
	 * @param list
	 *            The list to check
	 * @param prefix
	 *            The prefix to check for
	 * @return The number of people with phone numbers containing the prefix
	 */
	private static long localsTotNum(FBListIterator list, long prefix) {
		long counter = 0;
		list.reset();
		for (int i = 0; i < list.size(); i++) {
			Person person = list.next();
			if (comparePrefix(person.getPhoneNum(), prefix)) {
				counter++;
			}
		}
		System.out.println("\nNumber of people with prefix in arrayFB: " + counter);
		return counter;
	}

	/**
	 * A helper method that checks if the provided number contains the prefix.
	 * The runtime increases linearly with the number of digits in the given
	 * prefix.
	 * 
	 * @param number
	 *            The number to check.
	 * @param prefix
	 *            The prefix to check for
	 * @return True if the number contains the prefix, false if not.
	 */
	private static boolean comparePrefix(long number, long prefix) {
		String str_num = number + "";
		String str_pre = prefix + "";
		String num_pre = str_num.substring(0, str_pre.length());

		boolean equal;
		if (str_pre.equals(num_pre))
			equal = true;
		else
			equal = false;

		return equal;
	}

	/**
	 * Prints out the name and phone number of every person in the given list.
	 * The runtime increases linearly with the size of the list.
	 * 
	 * @param list
	 *            The list to display the contents of.
	 */
	private static void displayContents(FBListIterator list) {
		System.out.println("\nList contents:");

		list.reset();
		for (int j = 0; j < list.size(); j++) {
			Person current = list.next();
			System.out.println(current.toString());
		}
	}
}
