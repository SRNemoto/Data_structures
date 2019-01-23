package p3;

public class HashTable {
	static final int initial_exp = 4;
	int current_exp;
	Bucket[] table;
	int count;

	/**
	 * Default constructor that uses the normal initial size.
	 */
	public HashTable() {
		table = new Bucket[(int) Math.pow(2, initial_exp)];
		current_exp = initial_exp;
		count = 0;
	}

	/**
	 * Method that increments the frequency of the given word in the HashTable
	 * or adds a new word if it doesn't exist in the table.
	 * 
	 * @param string
	 *            The word to increment.
	 */
	public void insert(String string) {
		HashNode node = search(string);
		if (node != null)
			node.increment();
		else
			add(string);
	}

	/**
	 * Creates a new HashNode for the given string and adds it to the table. It
	 * is private to prevent the user (me) from accidentally adding duplicates
	 * and instead forces me to use insert()
	 * 
	 * @param string
	 *            The word to add
	 */
	private void add(String string) {
		add(new HashNode(string));
	}

	/**
	 * Adds the new HashNode to the corresponding bucket. It
	 * is private to prevent the user (me) from accidentally adding duplicates
	 * and instead forces me to use insert()
	 * 
	 * @param node
	 *            The node to be added
	 */
	private void add(HashNode node) {
		int key = getCode(node.data);

		if (table[key] == null)
			table[key] = new Bucket();

		table[key].add(node);
		count++;
		double load_factor = count * Math.pow(table.length, -1);
		double max = 0.9f;

		if (load_factor > max) {
			expand();
		}
	}

	/**
	 * Expands the HashTable to have double the slots and rehashes the current
	 * elements.
	 */
	public void expand() {
		// Increments the current exponent
		current_exp++;
		// Creates a new Bucket[] Array
		Bucket[] temp = new Bucket[(int) Math.pow(2, current_exp)];

		// Runs through all of the words and adds them to the new array
		for (Bucket a : table) {
			if (a != null) {
				for (int i = 0; i < a.size(); i++) {
					HashNode word = a.get(i);
					// count++;
					if (temp[hashFunction(word.data, temp.length)] == null)
						temp[hashFunction(word.data, temp.length)] = new Bucket();

					temp[hashFunction(word.data, temp.length)].add(word);
				}
			}
		}

		// Sets this table's count and table array to the new table's
		this.table = temp;
	}

	/**
	 * Removes the given word from the HashTable. This method should not be used
	 * for the purposes of this project.
	 * 
	 * @param string
	 *            The word to remove
	 * @return The removed HashNode
	 */
	public HashNode remove(String string) {
		int key = getCode(string);
		count--;
		return table[key].remove(string);
	}

	/**
	 * Searches for the given word and returns the corresponding HashNode.
	 * 
	 * @param string
	 *            The word.
	 * @return The corresponding HashNode.
	 */
	public HashNode search(String string) {
		int key = getCode(string);
		if (table[key] != null)
			return table[key].search(string);
		else
			return null;
	}

	/**
	 * Searches for the given word and increments its frequency.
	 * 
	 * @param string
	 *            The word to increment
	 * @return True if successful, false if not.
	 */
	public boolean increment(String string) {
		int key = getCode(string);
		return table[key].increment(string);
	}

	/**
	 * Takes in a given string and applies Java's hash function to it. This
	 * method was created to be used in the expand() method.
	 * 
	 * @param string
	 *            The word to be hashed
	 * @param tableSize
	 *            The size of the table.
	 * @return The string's key.
	 */
	public int hashFunction(String string, int tableSize) {
		int key = string.hashCode() % tableSize;
		if (key < 0)
			key *= -1;
		return key;
	}

	/**
	 * The more commonly used form of hashFunction(). Takes in a string and
	 * returns the corresponding hash key.
	 * 
	 * @param string
	 *            The word to be hashed.
	 * @return The hash code of the word
	 */
	public int getCode(String string) {
		return hashFunction(string, table.length);
	}

	/**
	 * This method returns a string containing the unique word count, table
	 * size, average chain length, and the frequencies of all of the words in
	 * this table.
	 */
	public String toString() {
		String stats = "";
		String output = "";
		int list_sum = 0;
		stats = stats.concat("Number of unique words: " + count);

		for (int i = 0; i < table.length; i++) {
			if (table[i] != null && table[i].size() != 0) {
				list_sum += table[i].size();
				for (int j = 0; j < table[i].size(); j++) {
					HashNode node = table[i].get(j);
					output = output.concat("\n (" + node.data + " : " + node.frequency + ")");
				}
			}
		}
		double average = list_sum * Math.pow(table.length, -1);
		stats = stats.concat("\n HashTable size: " + table.length + "\n Average chain length: " + average);
		output = stats + output;

		return output;
	}
}
