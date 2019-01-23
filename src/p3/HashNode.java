package p3;

public class HashNode {
	String data;
	int frequency;
	
	public HashNode(String data){
		this.data = data;
		this.frequency = 1;
	}
	
	public void increment(){
		frequency++;
	}
}
