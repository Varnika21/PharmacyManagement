package Pharmacy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

class DataBase {
	String usrname;
	String password;
	String name;
	long phnno;
	String address;
	String email;

	DataBase(String usrname, String password, String name, long phnno, String address, String email) {
		this.usrname = usrname;
		this.address = address;
		this.email = email;
		this.name = name;
		this.password = password;
		this.phnno = phnno;
	}
}

public class LoginSignup extends CartUser {

	public void get(String usrname, String password, String name, long phnno, String address, String email)
			throws Exception {
		DataBase d1 = new DataBase(usrname, password, name, phnno, address, email);
		db.add(d1);
		FileWriter myWriter = new FileWriter("D:\\Customer_Details.txt", true);
		myWriter.write(usrname + " " + password + " " + name + " " + phnno + " " + address + " " + email + "\n");
		myWriter.close();
		System.out.println("------Welcome to the store--------");
		asking_user();
	}

	public void get(String usrname, String password) throws Exception {
		File file = new File("D:\\Customer_Details.txt");
		FileReader fr = new FileReader(file); // reads the file
		BufferedReader buffer = new BufferedReader(fr); // creates a buffering character input stream
		String line;
		while ((line = buffer.readLine()) != null) {
			String str = line;
			String[] arrstr = str.split(" ", 6);
			DataBase d1 = new DataBase(arrstr[0], arrstr[1], arrstr[2], Long.parseLong(arrstr[3]), arrstr[4],
					arrstr[5]);
			db.add(d1);
		}
		for (DataBase d1 : db) {
			if (d1.usrname.equals(usrname) && d1.password.equals(password)) {

				System.out.println("------Welcome to the store--------");
				asking_user();
			}
		}
		fr.close();
		System.out.println("---------Please enter correct password--------");
	}
}
