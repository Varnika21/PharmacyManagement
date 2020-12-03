package Pharmacy;

import java.io.*;
import java.util.*;

import Pharmacy_Exceptions.WrongChoiceException;

public class CartUser extends UserInfo {
	Scanner sc = new Scanner(System.in);
	String usrname = super.usrname;
	ArrayList<DataBase> db = new ArrayList<DataBase>();

	public void asking_user() throws Exception {
		Delete ad = new Delete();
		Bill b = new Bill();
		while (true) {
			System.out.printf("1.Add\n2.Delete\n3.Display Bill\n4.Pay and Exit\n5.Exit without Purchase\n");
			switch (sc.nextInt()) {
			case 1:
				boolean flag = true;
				ad.get_medicine_info();
				while (flag) {
					System.out.println(
							"Click 1 To enter a medicine or disease\nClick 2 To increase the quantity of medicine\nClick 3 To go back to Main menu");
					int choice;
					choice = sc.nextInt();
					sc.nextLine();
					switch (choice) {
					case 1: {
						String name;
						System.out.println("Enter the name of the medicine or disease");
						name = sc.nextLine();
						ad.set_medicine(name);
						ad.view_cart();
						break;
					}
					case 2: {
						ad.view_cart();
						System.out.println("Enter the name of the medicine whose quantity you want to increase");
						String tab = sc.nextLine();
						ad.Add_quantity(tab);
						break;
					}
					case 3: {
						flag = false;
						break;
					}
					default: {
						try {
							throw new WrongChoiceException("Entered wrong choice in Add menu");
						} catch (WrongChoiceException ae) {
							System.err.println(ae);
						}
					}
					}
				}
				break;
			case 2:
				ad.get_medicine_info();
				boolean flagD = true;
				while (flagD) {
					System.out.println(
							"Click 1 to delete medicine from your cart\nClick 2 to change the quantity of medicine\nClick 3 to go back");
					int choice;
					choice = sc.nextInt();
					sc.nextLine();
					switch (choice) {
					case 1: {
						String name;
						System.out.println("Enter the name of the medicine to be deleted");
						name = sc.nextLine();
						ad.delete_medicine(name);
						break;
					}
					case 2: {
						ad.view_cart();
						System.out.println("Enter the tablet whose quantity you want to edit");
						String tab = sc.nextLine();
						ad.Delete_quantity(tab);
						break;
					}
					case 3: {
						flagD = false;
						break;
					}
					default: {
						try {
							throw new WrongChoiceException("Entered wrong choice in delete menu");
						} catch (WrongChoiceException de) {
							System.err.println(de);
						}
					}
					}
				}
				break;
			case 3:
				b.view_bill(ad.c);
				break;
			case 4:
				File file = new File("D:\\Medicine_Details.txt");
				FileWriter fw = new FileWriter(file);
				System.out.println(ad.set.size());
				Iterator iter = ad.set.iterator();
				while(iter.hasNext())
				{
					medicine m = (medicine) iter.next();
					fw.write(m.disease+" "+m.tablet+" "+m.date+" "+m.month+" "+m.year+" "+m.cost+" "+m.quantity+"\n");
					//System.out.println(m.id+" "+m.disease+" "+m.tablet+" "+m.date+" "+m.month+" "+m.year+" "+m.cost+" "+m.quantity);
				}
				fw.flush();
				fw.close();
				b.CreateInvoice(ad.c, usrname, db);
				System.out.println("Thank You for Visiting!");
				System.exit(0);
				break;
			case 5:
				System.out.println("Thank You for Visiting!");
				System.exit(0);
				//break;
			default:
				try {
					throw new WrongChoiceException("The choice you entered does not exist in mainmenu");
				} catch (WrongChoiceException E) {
					System.err.println(E);
				}
			}
		}
	}
}
