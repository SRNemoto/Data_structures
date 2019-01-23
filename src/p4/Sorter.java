package p4;

import java.util.Random;

/**
 * This class holds the sorting algorithms (Heap, Quick, and Merge) used in this
 * project
 * 
 * @author Shota
 *
 */
public class Sorter {

	public static void main(String[] args) {
		Random randy = new Random();
		int[] test = new int[15];
		// int[] test2 = new int[10];
		for (int i = 0; i < test.length; i++) {
			test[i] = randy.nextInt(15);
			// test2[i] = 10 - i;
		}
		System.out.print("original: ");
		System.out.print("test: ");
		printArr(test);

		// heapSort(test);
		quickSort(test);
		// //mergeSort(test);
		System.out.print("\nSorted: ");
		printArr(test);

		System.out.println("Correct: " + check(test));

	}

	/**
	 * This method takes in an array of ints and sorts it in increasing order.
	 * Uses the Heap Sort algorithm.
	 * 
	 * @param arr
	 *            The array to be sorted
	 */
	public static void heapSort(int[] arr) {
		// Heapifies the array
		MyHeap.heapify(arr);

		// Runs through the array and polls the the array.
		for (int i = arr.length - 1; i > 0; i--) {
			arr[i] = MyHeap.poll(arr, i);
		}
	}

	/**
	 * This method takes in an array of ints and sorts it in increasing order.
	 * Uses the Quick Sort algorithm.
	 * 
	 * @param arr
	 *            The array to be sorted
	 */
	public static void quickSort(int[] arr) {
		// Runs the heavy-lifting method.
		quickSortHelper(arr, 0, arr.length - 1);
	}

	/**
	 * This is the recursive helper method used by quickSort(). It sorts the
	 * section of the given array specified by the given first index and last
	 * index.
	 * 
	 * @param arr
	 *            The array to sort
	 * @param first
	 *            The index of the first element
	 * @param last
	 *            The index of the last element
	 */
	private static void quickSortHelper(int[] arr, int first, int last) {
		// counter++;
		// System.out.println("\nStep: " + counter);
		// System.out.println("first: " + first + "(" + arr[first] + "), last: "
		// + last + "(" + arr[last] + ")");
		// System.out.print("pre-part arr: {");
		// for (int i = 0; i < arr.length; i++){
		// System.out.print(arr[i] + ", ");
		// }
		// System.out.print("}\n");

		// The base case. When the array is just one element.
		if (first >= last)
			return;

		// Finds the index to split the array into two parts at
		int split = partition(arr, first, last);

		// System.out.println("split: " + split + "(" + arr[split] + ")");
		// System.out.print("post-part arr: {");
		// for (int i = 0; i < arr.length; i++){
		// System.out.print(arr[i] + ", ");
		// }
		// System.out.print("}\n");

		// Recursively runs the method on the two smaller arrays.
		quickSortHelper(arr, first, split);
		quickSortHelper(arr, split + 1, last);

	}

	/**
	 * Splits the section of the given array specified by the first and last
	 * parameters into two smaller arrays split by a pivot value calculated by
	 * calcPivot(). Returns the index of the last element in the first split
	 * array.
	 * 
	 * @param arr
	 *            The array to sort
	 * @param first
	 *            The index of the first element
	 * @param last
	 *            The index of the last element
	 * @return The index of the last element in the first section.
	 */
	private static int partition(int[] arr, int first, int last) {
		int i = first - 1;
		int j = last + 1;
		// Calculates the value to use as the pivot
		int pivot = calcPivot(arr, first, last);

		// Runs until the j counter is less than the i counter.
		while (true) {
			do {
				i++; // increments i counter until it encounters an element
						// greater than or equal to the pivot
			} while (arr[i] < pivot);
			do {
				j--; // increments i counter until it encounters an element less
						// than or equal to the pivot
			} while (arr[j] > pivot);

			// If the counters have not passed each other.
			if (i < j)
				swap(arr, i, j);
			// If the counters have passed each other.
			else
				return j;

		}
	}

	/**
	 * Returns the median of three randomly chosen elements in the given portion
	 * of the array.
	 * 
	 * @param arr
	 *            The array to chose from
	 * @param first
	 *            The index of the first element
	 * @param last
	 *            The index of the last element
	 * @return The median of three random elements (if the array is two
	 *         elements, takes the average).
	 */
	private static int calcPivot(int[] arr, int first, int last) {
		Random randy = new Random();

		// If the array is only 2 elements or less, takes the average
		if (last - first < 2)
			return (arr[first] + arr[last]) / 2;

		// Gets the median of three elements
		int[] sample = new int[3];
		// Takes three random integers from the section of the array
		for (int i = 0; i < sample.length; i++) {
			sample[i] = arr[first + randy.nextInt(last - first + 1)];

			// Makes sure the three elements are ordered
			for (int j = i; j > 0; j--) {
				if (sample[j] > sample[j - 1]) {
					swap(sample, j, j - 1);
				}
			}
		}
		// Returns the middle element
		return sample[1];
	}

	/**
	 * Swaps two elements in the array.
	 * 
	 * @param arr
	 *            The array
	 * @param a
	 *            The index of the first element
	 * @param b
	 *            The index of the second element
	 */
	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	/**
	 * This method takes in an array of ints and sorts it in increasing order.
	 * Uses the Merge Sort algorithm.
	 * 
	 * @param arr
	 *            The array to be sorted
	 */
	public static void mergeSort(int[] arr) {
		// The temporary array to hold elements
		int[] temp = new int[arr.length];

		// Finds what power of two the array's size is
		double calc_power = Math.log(arr.length) / Math.log(2);
		// Rounds the calculated power up
		int max_power = (int) Math.floor(calc_power);
		if (calc_power > max_power)
			max_power++;

		// Runs log2(n) times
		for (int power = 0; power <= max_power; power++) {
			// The variables that indicate where the two temporary arrays start
			// and stop
			int head1 = 0, tail1 = 0, head2 = 0, tail2 = -1;

			// How far many times to iterate to create an array of the
			// appropriate size
			int increment = (int) Math.pow(2, power) - 1;

			// Runs until the tail of the second temporary array exceeds the
			// original size
			while (tail2 < arr.length - 1) {
				// Moves to the next temporary array
				head1 = tail2 + 1;
				tail1 = head1 + increment;
				head2 = tail1 + 1;
				tail2 = head2 + increment;

				// If the first temporary array exceeds the original's bounds
				if (tail1 > arr.length - 1) {
					tail1 = arr.length - 1;
					head2 = tail2 + 1; // This is so the merge() method skips
										// the second array
				}
				// if the second temporary array exceeds the original's bounds
				else if (tail2 > arr.length - 1)
					tail2 = arr.length - 1;

				// Merges the two temporary arrays.
				merge(temp, arr, head1, tail1, head2, tail2);
			}
			// Swaps which array represents the original and the temporary.
			int[] swap = arr;
			arr = temp;
			temp = swap;
		}
	}

	/**
	 * This method merges elements from two portions of the source array and
	 * adds them into the corresponding locations in the destination array in
	 * increasing order.
	 * 
	 * @param destination
	 *            The array to merge elements into
	 * @param source
	 *            The array to pull elements from
	 * @param head1
	 *            The first element of the first section
	 * @param tail1
	 *            The last element of the first section
	 * @param head2
	 *            The first element of the second section
	 * @param tail2
	 *            The last element of the second section
	 */
	private static void merge(int[] destination, int[] source, int head1, int tail1, int head2, int tail2) {
		// Just in case the first array somehow is past the original's bounds
		if (head1 > source.length - 1)
			return;

		// The counters that indicate where the loop is in going through each
		// array
		int i = head1, j = head2, counter = head1;

		// Runs until one array has had all elements copied
		while (i <= tail1 && j <= tail2) {
			if (source[j] < source[i]) {
				destination[counter] = source[j];
				j++;
			} else {
				destination[counter] = source[i];
				i++;
			}
			counter++;
		}

		// Copies the remainder of the first array into the destination
		for (int remainder = i; remainder <= tail1; remainder++) {
			destination[counter] = source[remainder];
			counter++;
		}

		// copies the remainder of the second array into the destination
		for (int remainder = j; remainder <= tail2; remainder++) {
			destination[counter] = source[remainder];
			counter++;
		}

	}

	/**
	 * Prints out the contents of the given array. A helper method for testing.
	 * 
	 * @param arr
	 *            The array to print out
	 */
	private static void printArr(int[] arr) {
		System.out.print("\n{ ");
		for (int a : arr) {
			System.out.print(a + ", ");
		}
		System.out.println("}");
	}

	/**
	 * Checks if all of the array's elements are less than the next element. A
	 * helper method used for testing.
	 * 
	 * @param arr
	 *            The array to check
	 * @return True if all elements are less than the next, false if not.
	 */
	private static boolean check(int[] arr) {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < max) {
				System.out.println("Error at i = " + i + " (" + arr[i] + ")");
				return false;
			}

			max = Math.max(arr[i], max);
		}
		return true;
	}
}
