package p4;

import java.util.Random;

/**
 * This class tests the three sorting algorithms in Sorter.java on sorted,
 * reversed, and random arrays of 1000, 10000, 100000, and 1000000 elements.
 * Prints out the results in the console.
 * 
 * @author Shota
 *
 */
public class Reporting1 {

	private static final String HEAP = "Heap";
	private static final String QUICK = "Quick";
	private static final String MERGE = "Merge";

	private static final int HEAP_SORT = 1;
	private static final int QUICK_SORT = 2;
	private static final int MERGE_SORT = 3;

	private static final String SORTED = "Sorted";
	private static final String REVERSE = "Reverse";
	private static final String RANDOM = "Random";

	private static final int SORTED_ARR = 4;
	private static final int REVERSE_ARR = 5;
	private static final int RANDOM_ARR = 6;

	public static void main(String[] args) {
		// The strings to hold the outputs of each algorithm's tests
		String Heap = "", Quick = "", Merge = "";

		// Holds the mean or the median, depending on what was used
		double average;

		// The array to hold the runtimes of each sort.
		long[] runtimes = new long[10];

		// The size of the array to sort
		int size = 1000;

		// Running the sorted arrays for various sizes:
		while (size <= 1000000) {

			// Runs each algorithm on sorted arrays.
			test(runtimes, SORTED_ARR, HEAP_SORT, size);
			average = medianVal(runtimes);
			Heap = Heap.concat(generateStatString(HEAP, size, SORTED, average, varianceVal(runtimes, average)));

			test(runtimes, SORTED_ARR, QUICK_SORT, size);
			average = medianVal(runtimes);
			Quick = Quick.concat(generateStatString(QUICK, size, SORTED, average, varianceVal(runtimes, average)));

			test(runtimes, SORTED_ARR, MERGE_SORT, size);
			average = medianVal(runtimes);
			Merge = Merge.concat(generateStatString(MERGE, size, SORTED, average, varianceVal(runtimes, average)));

			// Runs each algorithm on reversed arrays.
			test(runtimes, REVERSE_ARR, HEAP_SORT, size);
			average = medianVal(runtimes);
			Heap = Heap.concat(generateStatString(HEAP, size, REVERSE, average, varianceVal(runtimes, average)));

			test(runtimes, REVERSE_ARR, QUICK_SORT, size);
			average = medianVal(runtimes);
			Quick = Quick.concat(generateStatString(QUICK, size, REVERSE, average, varianceVal(runtimes, average)));

			test(runtimes, REVERSE_ARR, MERGE_SORT, size);
			average = medianVal(runtimes);
			Merge = Merge.concat(generateStatString(MERGE, size, REVERSE, average, varianceVal(runtimes, average)));

			// Runs each algorithm on random arrays
			test(runtimes, RANDOM_ARR, HEAP_SORT, size);
			average = meanVal(runtimes);
			Heap = Heap.concat(generateStatString(HEAP, size, RANDOM, average, varianceVal(runtimes, average)));

			test(runtimes, RANDOM_ARR, QUICK_SORT, size);
			average = meanVal(runtimes);
			Quick = Quick.concat(generateStatString(QUICK, size, RANDOM, average, varianceVal(runtimes, average)));

			test(runtimes, RANDOM_ARR, MERGE_SORT, size);
			average = meanVal(runtimes);
			Merge = Merge.concat(generateStatString(MERGE, size, RANDOM, average, varianceVal(runtimes, average)));

			// Increases the size of the arrays for the next run
			size *= 10;
		}

		// Prints out the results of the tests
		System.out.println(Heap + "\n");
		System.out.println(Quick + "\n");
		System.out.println(Merge + "\n");

	}

	/**
	 * A helper method for testing the algorithms. To be used in the main
	 * method. 
	 * 
	 * Note: There is an unneccessary rebuilding of the sorted and reverse
	 * arrays whenever I run test() multiple times, but I didn't have time to
	 * fix that.
	 * 
	 * @param runtimes
	 *            The array to store the runtimes in. The length of the array is
	 *            the number of runs.
	 * @param arrayType
	 *            The integer code that specifies what type of array to generate
	 *            (Sorted, Reverse, or Random).
	 * @param sortType
	 *            The integer code that specifies which algorithm to run on the
	 *            array.
	 * @param size
	 *            The desired size of the array.
	 */
	private static void test(long[] runtimes, int arrayType, int sortType, int size) {
		// To hold the times at which the sorts were started
		long start = 0, end = 0;

		// The array to be sorted
		int[] arr = new int[size];

		// Runs for each slot in the runtimes array
		for (int i = 0; i < runtimes.length; i++) {

			// Generates different arrays based on given code
			switch (arrayType) {
			case SORTED_ARR:
				genSorted(arr);
				break;
			case REVERSE_ARR:
				genReverse(arr);
				break;
			case RANDOM_ARR:
				genRandom(arr);
				break;
			default:
				System.err.println("Error: Unavailable array generation requested");
				break;
			}

			// Runs a different algorithm based on the given code
			start = System.nanoTime(); // The time the algorithm was started
			switch (sortType) {
			case HEAP_SORT:
				Sorter.heapSort(arr);
				break;
			case QUICK_SORT:
				Sorter.quickSort(arr);
				break;
			case MERGE_SORT:
				Sorter.mergeSort(arr);
				break;
			default:
				System.err.println("Error: Unavailable Sorting algorithm requested");
				break;
			}

			end = System.nanoTime(); // The time the algorithm finished
			runtimes[i] = end - start; // The runtime.
		}
	}

	/**
	 * A helper method that adds elements in increasing order to the given
	 * array. To be used in test().
	 * 
	 * @param arr
	 *            The given array.
	 */
	private static void genSorted(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}
	}

	/**
	 * A helper method that adds elements in decreasing order to the given
	 * array. To be used in test().
	 * 
	 * @param arr
	 *            The given array.
	 */
	private static void genReverse(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr.length - i;
		}
	}

	/**
	 * A helper method that adds elements in random order to the given array.
	 * Duplicates may be added. To be used in test().
	 * 
	 * @param arr
	 *            The given array.
	 */
	private static void genRandom(int[] arr) {
		Random randy = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = randy.nextInt(arr.length) + 1;
		}
	}

	/**
	 * A helper method for calculating the median of an array of longs. It
	 * doesn't use the most efficient sorting algorithm, but the runtimes[]
	 * array is small so it'll be a small, constant cost relative to the sorting
	 * algorithms.
	 * 
	 * @param sample
	 *            The array to calculate the median of.
	 * @return The median.
	 */
	private static double medianVal(long[] sample) {

		// Runs through every element of the array
		for (int i = 1; i < sample.length; i++) {

			// Pushes each element to the appropriate location
			for (int j = i; j > 0; j--) {
				if (sample[j] > sample[j - 1]) {
					long temp = sample[j];
					sample[j] = sample[j - 1];
					sample[j - 1] = temp;
				}
			}
		}

		// If the array has an even number of elements, returns the average of
		// the two middle elements
		if (sample.length % 2 == 0)
			return (sample[sample.length / 2] + sample[sample.length / 2 - 1]) / 2;
		// If the array has an odd number of elements, returns the middle
		// element.
		else
			return sample[sample.length + 1];
	}

	/**
	 * Returns the mean of the given array of longs.
	 * 
	 * @param sample
	 *            The array to calculate the mean of.
	 * @return The mean.
	 */
	private static double meanVal(long[] sample) {
		double sum = 0;
		for (long element : sample) {
			sum += element;
		}
		return sum / sample.length;
	}

	/**
	 * Returns the variance of the given array of longs using the given mean.
	 * 
	 * @param sample
	 *            The array to calculate the variance of.
	 * @param mean
	 *            The mean to calculate the variance with.
	 * @return The variance.
	 */
	private static double varianceVal(long[] sample, double mean) {
		double sum = 0;
		for (long element : sample) {
			sum += Math.pow(element - mean, 2);
		}
		return sum / (sample.length - 1);
	}

	/**
	 * A helper method that pulls together all of the necessary statistics to
	 * form a string to print out to the user.
	 * 
	 * @param name
	 *            Which sorting algorithm was used
	 * @param size
	 *            The size of the sorted array
	 * @param arrayType
	 *            If the array was sorted, reversed, or random
	 * @param mean
	 *            The mean runtime (or median)
	 * @param variance
	 *            The variance in the runtime
	 * @return
	 */
	private static String generateStatString(String name, int size, String arrayType, double mean, double variance) {
		String stats = "\n" + name + " Sort on " + size + " element " + arrayType + " array:\nMean / Median Runtime: "
				+ mean + " nanoseconds \nVariance: " + variance + " nanoseconds\n";
		return stats;
	}

}
