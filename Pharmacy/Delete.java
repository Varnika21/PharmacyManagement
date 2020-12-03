package Pharmacy;

import java.util.Scanner;

import Pharmacy_Exceptions.MedicineNotFoundException;
import Pharmacy_Exceptions.MedicineQuantityExceeded;

public class Delete extends Add {
	public Delete() throws Exception {
		super();
	}

	Scanner sc = new Scanner(System.in);

	public void Delete_quantity(String tab) {
		boolean flag = false;
		for (Cart cart1 : c) {
			if (cart1.tablet.equalsIgnoreCase(tab)) {
				flag = true;
				System.out.println("Enter the quantity to be deleted");
				int q = sc.nextInt();
				if (cart1.quantity > q) {
					cart1.quantity = cart1.quantity - q;
					for (medicine m : set) {
						if (tab.equals(m.tablet)) {
							m.quantity += q;
						}
					}
				} else {
					try {
						throw new MedicineQuantityExceeded(
								"The quantity to be deleted is greater than the amount present");
					} catch (MedicineQuantityExceeded eq) {
						System.err.println(eq);
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

	public void delete_medicine(String tab) {
		// TODO Auto-generated method stub
		view_cart();
		boolean flag = false;
		for (Cart c1 : c) {
			if (c1.tablet.equals(tab)) {
				flag = true;
				for (medicine m : set) {
					if (tab.equals(m.tablet)) {
						m.quantity += c1.quantity;
					}
				}
				c.remove(c1);
			}
		}
		if (flag == false) {
			try {
				throw new MedicineNotFoundException("The medicine you entered is not in the cart");
			} catch (MedicineNotFoundException ed) {
				System.err.println(ed);
			}
		}
	}
}
