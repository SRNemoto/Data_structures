package p2;

public class HuffmanNode {
	/**
	 * The character this node holds. Null if an interior node
	 */
	private Character inChar;

	/**
	 * The left child node (more frequently used)
	 */
	private HuffmanNode left;

	/**
	 * The right child node (less frequently used)
	 */
	private HuffmanNode right;

	/**
	 * The Huffman encoding for this character
	 */
	private String encoding;

	/**
	 * The number of times in the file this character appears
	 */
	private int frequency;

	public HuffmanNode(Character ch) {
		inChar = ch;
		left = null;
		right = null;
		encoding = "";
		frequency = ch == null ? 0 : 1;
	}

	public void addLeft(HuffmanNode child) {
		left = child;
	}
	
	public HuffmanNode getLeft(){
		return left;
	}

	public void addRight(HuffmanNode child) {
		right = child;
	}
	
	public HuffmanNode getRight(){
		return right;
	}

	public String getEncoding() {
		return encoding;
	}

	/**
	 * Adds the given String to the encoding field
	 * @param bits
	 */
	public void concatEncoding(String bits) {
		encoding = encoding + bits;
	}

	public char getChar() {
		return inChar.charValue();
	}

	public void addFreq() {
		frequency++;
	}

	public void setFreq(int freq) {
		frequency = freq;
	}

	public int getFreq() {
		return frequency;
	}

}
