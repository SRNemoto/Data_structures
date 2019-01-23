package p3;

import java.util.ArrayList;

/**
 * A class to handle collisions in the HashTable class.
 * @author Shota
 *
 */
public class Bucket {
	ArrayList<HashNode> list;

	/**
	 * The constructor. Just instantiates the ArrayList
	 */
	public Bucket() {
		list = new ArrayList<HashNode>();
	}

	/**
	 * Adds another node to the bucket
	 * @param node The node to be added
	 */
	public void add(HashNode node) {
		list.add(node);
	}
	
	/**
	 * Returns the node at the given index
	 * @param index The specified index
	 * @return The requested node
	 */
	public HashNode get(int index){
		return list.get(index);
	}

	/**
	 * Removes the node at the given index
	 * @param index The specified index
	 * @return The removed node
	 */
	public HashNode remove(int index) {
		return list.remove(index);
	}

	/**
	 * Removes the node with the specified string
	 * @param string The given string
	 * @return The removed node
	 */
	public HashNode remove(String string) {
		int index = searchIndex(string);
		return list.remove(index);
	}

	/**
	 * Returns the index of the node with the given string.
	 * @param string The word to search for.
	 * @return The index of the given word. -1 if not found.
	 */
	public int searchIndex(String string) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).data.equals(string))
				return i;
		}
		return -1;
	}

	/**
	 * Returns the HashNode with the given string. 
	 * @param string The word to search for
	 * @return The Hashnode with the given string. Null if not found
	 */
	public HashNode search(String string) {
		int index = searchIndex(string);
		if (index == -1)
			return null;
		else
			return list.get(index);
	}

	/**
	 * Increments the frequency of the given word.
	 * @param string The word to increment
	 * @return true if successful, false if not.
	 */
	public boolean increment(String string) {
		HashNode node = search(string);
		if (node != null) {
			node.increment();
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the size of this bucket.
	 * @return
	 */
	public int size(){
		return list.size();
	}
}
