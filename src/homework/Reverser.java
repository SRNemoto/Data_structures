package homework;

import java.util.Random;

public class Reverser {
	
	public static void main(String[] args){
		Random randy = new Random();
		int[] random = new int[randy.nextInt(15)];
		for (int j = 0; j < random.length; j++){
			random[j] = randy.nextInt(100);
		}
		System.out.println("O.G. array");
		for (int a : random){
			System.out.print(a + ", ");
		}
		random = reverse(random);
		
		System.out.println("\nReverse:");
		for (int a : random){
			System.out.print(a + ", ");
		}
		
	}
	public static int[] reverse(int[] arr){
		int temp = 0, last = arr.length - 1;
		for (int i = 0; i <= last/2; i++){
			temp = arr[i];
			arr[i] = arr[last - i];
			arr[last - i] = temp;
		}
		return arr;
	}
}
