package p2;

import java.util.ArrayList;

/**
 * Creates a min-on-top heap that doubles as a sort of priority queue.
 * @author Shota
 *
 */
public class MyHeap {

	ArrayList<HuffmanNode> heap;

	/**
	 * Instantiates the arrayList that represents the heap, and adds all of the elements in the given array.
	 * This step is done so that the order of the given array is preserved, but the elements can still be altered.
	 * @param array
	 */
	public MyHeap(ArrayList<HuffmanNode> array) {
		heap = new ArrayList<HuffmanNode>();
		heap.addAll(array);
	}

	/**
	 * Moves the item at the index down if its children are less frequent than it
	 * @param index
	 */
	public void siftDown(int index){
		HuffmanNode parent = heap.get(index);
		int parent_index = index;
		int child_index = 2 * parent_index + 1;

		while (child_index < heap.size()) {
			if (child_index + 1 < heap.size() && heap.get(child_index).getFreq() > heap.get(child_index + 1).getFreq())
				child_index++;

			HuffmanNode child = heap.get(child_index);
			if (parent.getFreq() < child.getFreq())
				break;

			heap.set(parent_index, child);
			heap.set(child_index, parent);
			parent_index = child_index;
			child_index = 2 * parent_index + 1;
		}
	}
	
	/**
	 * Moves the node at the index up if it is less frequent than its parents
	 */
	public void siftUp(int index){
		if (index == 0)
			return;
		HuffmanNode child = heap.get(index);
		int child_index = index;
		int parent_index = (child_index-1) / 2;

		while (child_index > 0) {
			HuffmanNode parent = heap.get(parent_index);

			if (parent.getFreq() < child.getFreq())
				break;

			heap.set(parent_index, child);
			heap.set(child_index, parent);

			child_index = parent_index;
			parent_index = child_index / 2;
		}
	}

	/**
	 * Converts this array into a min-on-top heap
	 */
	public void heapify() {
		for (int i = (heap.size() - 2) / 2; i >= 0; i--)
			siftDown(i);
	}
	
	public void insert(HuffmanNode node){
		heap.add(node);
		siftUp(heap.size() - 1);
	}

	public HuffmanNode get(int index) {
		return heap.get(index);
	}

	public int size() {
		return heap.size();
	}
	
	/**
	 * Returns the least frequent item in the heap and removes it from the heap, reordering the heap as necessary in the process.
	 * @return
	 */
	public HuffmanNode poll(){
		HuffmanNode min = null;
		if (heap.size() > 1){
			min = heap.get(0);
			heap.set(0, heap.remove(heap.size() - 1));
			siftDown(0);
		}
		else if(heap.size() == 1){
			min = heap.remove(0);
		}
		return min;
	}
}
