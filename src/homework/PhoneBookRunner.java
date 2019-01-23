package homework;

import java.util.Random;

public class PhoneBookRunner {

	public static void main(String[] args) {
		PhoneBook<Integer> SSN_PB = new PhoneBook<Integer>(PhoneBook.SSN_type);
		PhoneBook<String> name_PB = new PhoneBook<String>(PhoneBook.name_type);
		Random randy = new Random();
		
		String[] random_names = new String[]{"BigDaddy", "LittleDaddy", "BigFish", "LittleFish", "BigMama", "BiggerMama"}; 
		
		for (String temp : random_names){
			int random_pNumber = new Integer(1000000 + randy.nextInt(10000000));
			SSN_PB.addPerson(random_pNumber, new Integer(randy.nextInt(1000000000) + 100000000));
			name_PB.addPerson(random_pNumber, temp);
		}
		
		int pNumber = 1000000 + randy.nextInt(10000000);
		Integer SSN = new Integer(randy.nextInt(1000000000) + 100000000);
		String name = "Waldo";
		
		System.out.println("Phone Number: " + pNumber + "\nSSN: " + SSN + "\nName: " + name);
		
		SSN_PB.addPerson(pNumber, SSN);
		name_PB.addPerson(pNumber, name);
		
		System.out.println("Phone number by SSN: " + SSN_PB.findPerson(SSN));
		System.out.println("Phone number by name: " + name_PB.findPerson(name));
		
		//These won't compile, so I can't seem to get an output.
//		SSN_PB.addPerson(pNumber, "Wrong!");
//		name_PB.addPerson(pNumber, 4222222);
	}

}
