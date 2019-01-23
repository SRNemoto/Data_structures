package p2;

import java.util.ArrayList;

/**
 * This class is an arrayList in which more frequent items are pushed to the
 * front of the array, or "float" like a bubble to the front.
 * 
 * @author Shota
 *
 */
public class BubbleArray {
	private ArrayList<HuffmanNode> array;

	/**
	 * instantiates the ArrayList
	 */
	public BubbleArray() {
		array = new ArrayList<HuffmanNode>();
	}

	/**
	 * Adds a node to the ArrayList
	 * 
	 * @param node
	 */
	public void add(HuffmanNode node) {
		array.add(node);
	}

	/**
	 * Increases the frequency of the node at the given index by 1
	 * 
	 * @param index
	 */
	public void increment(int index) {
		array.get(index).addFreq();
		swap(index);
	}

	/**
	 * If the node at the index has a larger frequency than its predecessor,
	 * swaps their positions then continues the swaps until it is no longer more
	 * frequent
	 * 
	 * @param index
	 */
	public void swap(int index) {
		if (index > 0 && array.get(index).getFreq() > array.get(index - 1).getFreq()) {
			HuffmanNode current = array.get(index);
			array.set(index, array.get(index - 1));
			array.set(index - 1, current);
			swap(index - 1);
		}
	}

	public int size() {
		return array.size();
	}

	public HuffmanNode get(int index) {
		return array.get(index);
	}

	public ArrayList<HuffmanNode> getArray() {
		return array;
	}
}
