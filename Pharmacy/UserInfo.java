package Pharmacy;

import java.util.Scanner;

public class UserInfo {
	public static String choice;
	public static String usrname = null;
	public static String password = null;
	public static String name = null;
	public static long phnno = 0;
	public static String address = null;
	public static String email = null;

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to login or signup: ");
		choice = sc.nextLine();
		if (choice.equalsIgnoreCase("signup")) {
			System.out.println("Please choose username: ");
			usrname = sc.nextLine();
			System.out.println("Enter Password: ");
			password = sc.nextLine();
			System.out.println("Enter your name: ");
			name = sc.nextLine();
			System.out.println("Enter phone number: ");
			phnno = sc.nextLong();
			sc.nextLine();
			System.out.println("Enter your Address: ");
			address = sc.nextLine();
			System.out.println("Enter the email id: ");
			email = sc.nextLine();
		}
		if (choice.equalsIgnoreCase("Login")) {
			System.out.println("Enter your username: ");
			usrname = sc.nextLine();
			System.out.println("Enter your password: ");
			password = sc.nextLine();
		}
		CollectInfo person1 = new CollectInfo(choice, usrname, password, name, phnno, address, email);
		Thread thread1 = new Thread(person1);
		thread1.start();
		thread1.join();
		/*CollectInfo person2 = new CollectInfo(choice, usrname, password, name, phnno, address, email);
		Thread thread2 = new Thread(person1);
		thread2.start();
		thread2.join();*/
	}

}
