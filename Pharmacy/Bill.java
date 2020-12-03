package Pharmacy;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Bill {

	public void view_bill(ArrayList<Cart> c) {
		System.out.println("Medicines in the cart are: ");
		for (Cart c12 : c) {
			System.out.println("Name: " + c12.tablet + " Quantity: " + c12.quantity + " Cost: " + c12.cost);
		}
	}
	public void CreateInvoice(ArrayList<Cart> c, String usrname, ArrayList<DataBase> db) throws Exception {
		String address = null;
		String name = null;
		String email = null;
		long phnno = 0;
		float totalcost = 0;
		for (DataBase d1 : db) {
			if (d1.usrname.equals(usrname)) {
				address = d1.address;
				email = d1.email;
				phnno = d1.phnno;
				name = d1.name;
			}
		}
		int count = 0;
		System.out.println("-------------------------------------------------------------");
		System.out.println(" Name : " + name);
		System.out.println(" Email : " + email);
		System.out.println(" Phno : " + phnno);
		System.out.println(" Address : " + address);
		for (Cart c12 : c) {
			count++;
			System.out.println(" " + count + " Name: " + c12.tablet + " Quantity: " + c12.quantity + " Cost: "
					+ c12.cost + " Cost*Quantity: " + c12.cost * c12.quantity);
			totalcost += (c12.cost * c12.quantity);
		}
		System.out.println(" Your Total: " + totalcost);
		System.out.println("-------------------------------------------------------------");
		String path;
		path = "D:\\" + usrname + ".txt";
		FileWriter myWriter = new FileWriter(path);
		int countf = 0;
		myWriter.write("-------------------------------------------------------------" + "\n");
		myWriter.write(" Name : " + name + "\n");
		myWriter.write(" Email : " + email + "\n");
		myWriter.write(" Phno : " + phnno + "\n");
		myWriter.write(" Address : " + address + "\n");
		for (Cart c12 : c) {
			countf++;
			myWriter.write(" " + countf + " Name: " + c12.tablet + " Quantity: " + c12.quantity + " Cost: " + c12.cost
					+ " Cost*Quantity: " + c12.cost * c12.quantity + "\n");
			totalcost += (c12.cost * c12.quantity);
		}
		myWriter.write(" Your Total: " + totalcost + "\n");
		myWriter.write("-------------------------------------------------------------" + "\n");
		myWriter.close();
		SendEmail mail = new SendEmail(usrname,path,email);
		mail.send_mail();
	}

}
