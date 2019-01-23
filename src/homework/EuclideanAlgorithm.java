package homework;

import java.util.Random;

public class EuclideanAlgorithm {

	public static void main(String[] args) {
		Random randy = new Random();
		
		int m, n;
		for (int i = 0; i < 5; i++){
			m = randy.nextInt(100);
			n = randy.nextInt(m);
			System.out.println(m + " & " + n);
			System.out.println("GCD: " + gcd(m,n));
		}
	}
	
	public static int gcd(int m, int n){
		if (n == 0)
			return m;
		return gcd(n, m % n);
	}

}
