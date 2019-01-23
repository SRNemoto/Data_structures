package p4;

/**
 * This class allows a given array to act as a heap.
 * 
 * @author Shota
 *
 */
public class MyHeap {

	/**
	 * Checks that the given element is greater than it's children.
	 * 
	 * @param arr
	 *            The heap to sift.
	 * @param parent
	 *            The given element to check
	 * @param last
	 *            The last usable element in the array.
	 */
	public static void siftDown(int[] arr, int parent, int last) {
		// Finds the child element
		int child = 2 * parent + 1;

		// Runs until the end of the heap has been reached
		while (child <= last) {

			// If the right child is greater than the left child
			if (child + 1 < arr.length && arr[child] < arr[child + 1])
				child++;

			// If the parent is larger than the child
			if (arr[parent] > arr[child])
				break;

			// Swaps the parent and the child
			int temp = arr[parent];
			arr[parent] = arr[child];
			arr[child] = temp;

			// Sets the child as the new parent, finds the new child
			parent = child;
			child = 2 * parent + 1;
		}
	}

	/**
	 * Creates a heap inside of the given array.
	 * 
	 * @param arr
	 *            The given heap
	 */
	public static void heapify(int[] arr) {
		for (int i = (arr.length - 2) / 2; i >= 0; i--) {
			siftDown(arr, i, arr.length - 1);
		}
	}

	/**
	 * Removes the top element in the heap, replaces it with the last element,
	 * then sifts the new top down.
	 * 
	 * @param arr
	 *            The given heap
	 * @param last
	 *            The last usable element in the heap
	 * @return The max element.
	 */
	public static int poll(int[] arr, int last) {
		// Saves the max element
		int top = arr[0];
		// Replaces the max element and sifts it down
		arr[0] = arr[last];
		siftDown(arr, 0, last - 1);
		// Returns the max element
		return top;
	}
}
