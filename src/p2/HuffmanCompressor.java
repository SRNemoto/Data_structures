package p2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HuffmanCompressor {

	/**
	 * Takes in a text file and compresses it with Huffman Encoding
	 * 
	 * @param inputFileName
	 *            The path of the file to be converted
	 * @param outputFileName
	 *            The path of the file to store the compressed file in.
	 * @return The result of the encoding ("OK", "Input file error", etc.)
	 * @throws IOException
	 */
	public static String huffmanCoder(String inputFileName, String outputFileName) {
		try {
			// Counts the frequency of characters, creates a list of
			// HuffmanNodes
			// from most frequent to least frequent
			ArrayList<HuffmanNode> table = count(inputFileName);
			if (table == null)
				return "Incorrect input file name";

			// Builds the tree and table, prints out the table
			HuffmanNode root = buildTree(new MyHeap(table));
			assignEncoding(root);
			printEncoding(table);

			// Converts the input file into HuffmanEncoding
			int[] counts = convertFile(inputFileName, outputFileName, table);
			if (counts == null)
				return "Incorrect output file name";

			// Calculates the cost of the original and compressed files, outputs
			// the
			// costs and the difference
			calculateSavings(counts);

		} catch (IOException e) {
			return "Unclosed input/output stream";
		}

		return "OK";
	}

	/**
	 * This main method asks for the two file paths as strings, then runs huffmanCoder()
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the input file path: ");
		String inputFileName = scan.nextLine();

		System.out.println("\nEnter the output file path: ");
		String outputFileName = scan.nextLine();
		scan.close();

		String result = huffmanCoder(inputFileName, outputFileName);
		System.out.println(result);

	}

	/**
	 * Scans each character of a given text file and outputs an ArrayList of
	 * HuffmanNodes, filling in inChar and frequency fields. I used an ArrayList
	 * here because I will be building a heap later, and it is easier for me to
	 * implement the heap with an ArrayList.
	 * 
	 * @param inputFileName
	 *            The path of the file to be converted
	 * @return The ArrayList of HuffmanNodes
	 * @throws IOException
	 */
	private static ArrayList<HuffmanNode> count(String inputFileName) throws IOException {
		BubbleArray bubble = new BubbleArray();

		BufferedReader reader = null;

		// For adding new characters to the BubbleArray
		boolean found = false;
		try {
			reader = new BufferedReader(new FileReader(inputFileName));

			// Reads each character from the input file
			int readChar;
			while ((readChar = reader.read()) != -1) {
				char current = (char) readChar;

				// Checks the character against every node in the BubbleArray
				for (int j = 0; j < bubble.size(); j++) {
					// if a match, increments the frequency in the node
					if (current == bubble.get(j).getChar()) {
						bubble.increment(j);
						found = true;
						break;
					}
				}
				// If no match found, adds the character to the BubbleArray
				if (!found)
					bubble.add(new HuffmanNode(new Character(current)));
				found = false;

			}
		} catch (IOException e) {
			// When stuff doesn't work
			e.printStackTrace();
			return null;
		} finally {
			// Closes the input stream
			if (reader != null)
				reader.close();
		}

		// Returns the arraylist in the BubbleArray
		return bubble.getArray();
	}

	/**
	 * Converts the heap into a min-on-top priority queue, then uses the heap to
	 * build the Huffman Encoding Tree. The tree is represented by the returned
	 * HuffmanNode, which serves as the root of the tree. I chose to use a more
	 * LinkedList-ish implementation because it was more intuitive to use. Also
	 * in the ArrayList implementation, I would have had to keep the combined
	 * nodes in the array along with the added interior nodes, which would have
	 * gotten confusing and difficult to implement.
	 * 
	 * @param heap
	 *            The heap with all of the HuffmanNodes
	 * @return The root of the Huffman Encoding Tree
	 */
	private static HuffmanNode buildTree(MyHeap heap) {
		// Ensures the heap is in min-on-top form
		heap.heapify();
		while (heap.size() > 1) {
			// Takes the two least frequent nodes
			HuffmanNode first = heap.poll();
			HuffmanNode second = heap.poll();

			// Creates a new interior node, sets the two least frequen nodes as
			// its children
			HuffmanNode interior = new HuffmanNode(null);
			interior.addLeft(first);
			interior.addRight(second);
			interior.setFreq(first.getFreq() + second.getFreq());

			// adds the interior node into the heap
			heap.insert(interior);
		}

		// Returns the root of the Huffman Encoding Tree (only node left)
		return heap.poll();
	}

	/**
	 * Traverses the Huffman Encoding Tree from the given root and concatenates
	 * the proper encoding to the encoding field in each HuffmanNode
	 * 
	 * @param root
	 *            The root of the Huffman Encoding Tree
	 */
	private static void assignEncoding(HuffmanNode root) {
		Queue<HuffmanNode> queue = new LinkedList<HuffmanNode>();
		queue.add(root);
		HuffmanNode current = null, left = null, right = null;

		// Uses a queue to do a level-order traversal of the Huffman Encoding
		// Tree
		while (queue.size() > 0) {
			// Takes out the current node
			current = queue.poll();
			if (current == null)
				continue;

			// Adds the children to the queue (they can be null, so if statement
			// above needed)
			left = current.getLeft();
			right = current.getRight();

			// Interior nodes add their own encoding to the child, so that the
			// leaf nodes will eventually have the entire encoding

			// adds 0 onto the encoding if it was left child
			if (left != null) {
				left.concatEncoding(current.getEncoding() + "0");
				queue.add(left);
			}

			// adds 1 onto the encoding if it was the right child
			if (right != null) {
				right.concatEncoding(current.getEncoding() + "1");
				queue.add(right);
			}
		}
	}

	/**
	 * Compresses the text file indicated by the given file path using the given
	 * encoding table and outputs the compressed file into the given file path.
	 * 
	 * @param inputFileName
	 *            The file path of the text file to be compressed
	 * @param outputFileName
	 *            The file path of the compressed text file
	 * @param table
	 *            The encoding table for character conversion
	 * @return The number of characters in the original text file (so it doesn't
	 *         have to be rescanned)
	 * @throws IOException
	 */
	private static int[] convertFile(String inputFileName, String outputFileName, ArrayList<HuffmanNode> table)
			throws IOException {
		boolean found = false;
		int original_counter = 0, new_counter = 0;

		// Goes through each character in the input file, converts it to
		// appropriate encoding
		// Counts the total number of characters in the input file and
		// compressed file
		BufferedReader reader = null;
		FileWriter outputStream = null;
		try {
			reader = new BufferedReader(new FileReader(inputFileName));
			outputStream = new FileWriter(outputFileName);

			int readChar;
			while ((readChar = reader.read()) != -1) {
				char current = (char) readChar;
				original_counter++;

				for (int j = 0; j < table.size(); j++) {
					if (current == table.get(j).getChar()) {
						outputStream.write(table.get(j).getEncoding());
						new_counter += table.get(j).getEncoding().length();
						found = true;
						break;
					}
				}

				// Says that something went wrong if character was not in the
				// table
				if (!found)
					System.err.println("Character not found: " + current);
				found = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (reader != null)
				reader.close();
			if (outputStream != null)
				outputStream.close();
		}
		return new int[] { original_counter, new_counter };
	}

	/**
	 * Prints out the given Huffman Encoding Table
	 * 
	 * @param table
	 *            The table.
	 */
	private static void printEncoding(ArrayList<HuffmanNode> table) {
		System.out.println("\nEncoding");
		for (HuffmanNode node : table) {
			System.out.println("\"" + node.getChar() + "\" : " + node.getFreq() + " : " + node.getEncoding());
		}
	}

	/**
	 * Calculates how much will be saved by compressing the file. Assumes that
	 * each character in the original text file costs 8 bits. Then prints out
	 * the calculated results
	 * 
	 * @param counters
	 *            An int array with the first element as the number of
	 *            characters
	 * @throws IOException
	 */
	private static void calculateSavings(int[] counters) throws IOException {
		int original_cost = counters[0] * 8;
		System.out.println("Original Cost: " + original_cost);
		System.out.println("Compressed Cost: " + counters[1]);
		System.out.println("Savings: " + (original_cost - counters[1]));
	}
}
