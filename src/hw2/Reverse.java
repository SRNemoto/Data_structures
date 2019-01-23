package hw2;

public class Reverse {
	class Node {
		String payload;
		Node next;
		
		Node(String str){
			payload = str;
		}
	}

	Node head;
	
	
	
	public static void main(String[] args){
		Reverse list = new Reverse();
		for (int i = 0; i < 10; i++){
			list.add(list.new Node(i + ""));
		}
		list.reverse();
		for (int j = 0; j < 10; j++){
			System.out.println(list.remove());
		}
	}
	
	public void add(Node node){
		node.next = head;
		head = node;
	}
	
	public String remove(){
		String str = head.payload;
		head = head.next;
		return str;
	}

	public void reverse() {
		Node previous = null;
		Node current;
		Node next;

		current = head;
		while (current.next != null) {
			next = current.next;

			current.next = previous;
			previous = current;
			current = next;
		}
		current.next = previous;
		head = current;
	}
}
