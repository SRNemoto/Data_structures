package p4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runs the three algorithms in Sorter.java on the given input file. The file
 * must be formatted so that every line contains one integer and represents the
 * next element in the array. The results of the sorts will be outputted to the
 * files whose names are specified by the class constants, and the runtimes will
 * be outputted to the console.
 * 
 * @author Shota
 *
 */
public class Reporting2 {

	// The file names
	public static final String HEAP_FILE = "srn24HS.txt";
	public static final String QUICK_FILE = "srn24QS.txt";
	public static final String MERGE_FILE = "srn24MS.txt";

	/**
	 * Uses the Java Scanner class to take in the file path of the input file.
	 * Runs the three algorithms on the file
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);// The scanner to take in the
												// input file path

		// To hold the lines of the input file, representing another element
		ArrayList<String> lines = new ArrayList<String>();

		// To hold the original array and the workable copies
		int[] arr = null, clone = null;

		// This... has no purpose really.
		boolean success = true;

		// To hold the runtimes of the algorithms
		long start = 0, end = 0, elapsed = 0;
		String output = "";

		// The Java I/O operations
		BufferedReader reader = null;
		FileWriter writer = null;
		try {
			// More Java I/O operations
			reader = new BufferedReader(new FileReader(scan.nextLine()));
			// reader = new BufferedReader(new FileReader(TEST_INPUT));

			// Adds each line in the input file to the lines array
			String line = null; // Holds the current line being read
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}

			// Closes the reader
			if (reader != null)
				reader.close();

			// Converts the String lines into integers, moves them to arr
			arr = new int[lines.size()];
			for (int i = 0; i < lines.size(); i++) {
				arr[i] = Integer.parseInt(lines.get(i));
			}

			// Runs the heapSort algorithm on a clone of the array and saves the
			// runtime.
			clone = arr.clone();
			writer = new FileWriter(HEAP_FILE);
			start = System.currentTimeMillis();
			Sorter.heapSort(clone);
			end = System.currentTimeMillis();
			// Writes the sorted array to the output file
			for (int a : clone) {
				writer.write(a + " \n");
			}
			// Adds the elapsed time to the output string.
			elapsed = end - start;
			output = output.concat("HSsrn24 = " + elapsed + "; ");
			if (writer != null)
				writer.close();

			// Runs the QuickSort algorithm on a clone of the array and saves
			// the runtime
			clone = arr.clone();
			writer = new FileWriter(QUICK_FILE);
			start = System.currentTimeMillis();
			Sorter.quickSort(clone);
			end = System.currentTimeMillis();
			// Writes the sorted array to the output file
			for (int b : clone) {
				writer.write(b + " \n");
			}
			elapsed = end - start;
			// Adds the elapsed time to the output string.
			output = output.concat("QSsrn24 = " + elapsed + "; ");
			if (writer != null)
				writer.close();

			//Runs the MergeSort algorithm on a clone of the array and saves the runtime.
			clone = arr.clone();
			writer = new FileWriter(MERGE_FILE);
			Sorter.mergeSort(clone);
			// Writes the sorted array to the output file
			for (int c : clone) {
				writer.write(c + " \n");
			}
			// Adds the elapsed time to the output string.
			elapsed = end - start;
			output = output.concat("MSsrn24 = " + elapsed);
			
			//closes the FileWriter and Scanner
			if (writer != null)
				writer.close();
			scan.close();

		} catch (IOException e) {
			success = false;
			System.err.println("File not found");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			success = false;
			System.err.println("Format error in input file");
			e.printStackTrace();
		} finally {
			if (success) {
				System.out.println(output);
			}
		}
	}

}
