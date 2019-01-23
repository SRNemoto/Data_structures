package p3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class WordCounter {

	/**
	 * This method takes in Strings that indicate the file paths of the file to
	 * be read and the file to output the word counts and hash table statistics
	 * in.
	 * 
	 * @param inputFileName
	 *            The file path of the file to be read.
	 * @param outputFileName
	 *            The file path of the file to store the outputted statistics in
	 * @return true if valid file paths, false if files could not be found.
	 */
	public static boolean wordCount(String inputFileName, String outputFileName) {
		// The boolean that indicates a found file
		boolean success = true;

		// The Java I/O classes
		BufferedReader reader = null;
		FileWriter outputStream = null;

		// Where the string of the file is stored in
		// An ArrayList was created to account for if there are multiple lines
		// in the text
		ArrayList<String> lines = new ArrayList<String>();

		// An instance of the HashTable class I created to count the frequencies
		HashTable words = new HashTable();

		try {
			// Java I/O operations
			reader = new BufferedReader(new FileReader(inputFileName));
			outputStream = new FileWriter(outputFileName);

			// Stores each line in the lines ArrayList
			String line = reader.readLine();
			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}

			// Runs through each line
			for (String a : lines) {

				// Splits the words by punctuation
				StringTokenizer splitter = new StringTokenizer(a.toLowerCase(), " .\',!?;:\"()“”");
				while (splitter.hasMoreTokens()) {
					words.insert(splitter.nextToken());
				}
			}

			// Use the HashTable's toString() method to write the statistics to
			// the output file
			outputStream.write(words.toString());

			// Closes the Java I/O classes
			if (reader != null)
				reader.close();
			if (outputStream != null)
				outputStream.close();

		} catch (IOException e) {
			// When stuff doesn't work
			// e.printStackTrace();
			success = false;
		}
		return success;
	}

	public static void test(String string) {
		HashTable words = new HashTable();
		StringTokenizer splitter = new StringTokenizer(string.toLowerCase(), " .\',!?;:\"()“”");
		while (splitter.hasMoreTokens()) {
			words.insert(splitter.nextToken());
			// System.out.println(splitter.nextToken());
		}
		System.out.println(words.toString());
	}

	/**
	 * The main method that runs this program. The console will ask for the
	 * input file path, then the output file path. If the paths are not valid,
	 * it will ask again.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//For getting user input
		Scanner scan = new Scanner(System.in);
		boolean error = true;

		// Example file paths I used on my computer.
		// "C:/Users/Shota/Desktop/srn24_P3/The_Call_of_the_Wild (truncated).txt"
		// "C:/Users/Shota/Desktop/srn24_P3/output.txt"
		while (error) {
			//Takes the file paths from user
			System.out.println("Please type the input file path:");
			String input = scan.nextLine();
			System.out.println("Please type the output file path:");
			String output = scan.nextLine();

			//Runs wordCount
			error = !(wordCount(input, output));

			//If wordCount returned false, means file could not be found
			if (error)
				System.out.println("Could not find file path. Try again.\n");
		}
		scan.close();
		System.out.println("The word counts have been printed in the output file. Have a nice day!");
	}
}
