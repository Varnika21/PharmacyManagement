package Pharmacy;
import java.util.Scanner;

import Pharmacy_Exceptions.MedicineNotFoundException;
import Pharmacy_Exceptions.MedicineQuantityExceeded;

class Cart {
	String tablet;
	float cost;
	int quantity;

	Cart(String tablet, float cost, int quantity) {
		this.tablet = tablet;
		this.cost = cost;
		this.quantity = quantity;
	}
}

public class Add extends MedicineDetails {
	public String name;

	public Add() throws Exception {

	}

	Scanner sc = new Scanner(System.in);

	public void set_medicine(String name) throws Exception {
		this.name = name;
		check_availability();
	}

	public void get_medicine(medicine m) {
		int no_of_tablets;
		System.out.println("How many tablets do you want");
		no_of_tablets = sc.nextInt();
		try {
			if (no_of_tablets >= m.quantity) {
				throw new MedicineQuantityExceeded("We dont have those many tablets");
			} else {
				m.quantity = m.quantity - no_of_tablets;
				Cart c1 = new Cart(name, m.cost, no_of_tablets);
				c.add(c1);
			}
		} catch (MedicineQuantityExceeded mq) {
			System.err.println(mq);
		}
	}

	public int medicine_count() {
		int countmed = 0;
		for (medicine m : set) {
			if (m.disease.equalsIgnoreCase(name)) {
				countmed++;
			}
		}
		return countmed;
	}

	public void check_availability() throws Exception {
		int flag = 0;
		boolean flag2 = false;
		int count = 0;
		for (medicine m : set) {
			if (m.tablet.equalsIgnoreCase(name)) {
				flag2 = true;
				flag = 1;
				get_medicine(m);
				break;
			} else if (m.disease.equalsIgnoreCase(name)) {
				String choice;
				flag = 1;
				System.out.println("Do you want " + m.tablet + " medicine?(yes/no)");
				count++;
				if (count == 1) {
					sc.nextLine();
				}
				choice = sc.nextLine();
				if (choice.equals("yes")) {
					name = m.tablet;
					flag2 = true;
					get_medicine(m);
					break;
				}
			}

		}
		try {
			if (flag2 == false) {
				throw new MedicineNotFoundException("Desired medicine is not found");
			}
			if (flag == 0) {
				throw new MedicineNotFoundException("This medicine is not available");
			}
		} catch (MedicineNotFoundException me) {
			System.err.println(me);
		}
	}

	public void Add_quantity(String tab) {
		boolean flag = false;
		for (Cart cart1 : c) {
			if (cart1.tablet.equalsIgnoreCase(tab)) {
				flag = true;
				System.out.println("Enter the quantity to be added");
				int q = sc.nextInt();
				cart1.quantity = cart1.quantity + q;
				for (medicine m : set) {
					if (tab.equals(m.tablet)) {
						m.quantity -= q;
					}
				}
				view_cart();
			}
		}
		try {
			if (flag == false) {
				throw new MedicineNotFoundException("The medicine you entered is not in your cart");
			}
		} catch (MedicineNotFoundException me) {
			System.err.println(me);
		}
	}
}
