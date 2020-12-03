package Pharmacy;

public class CollectInfo extends LoginSignup implements Runnable {
	public String choice;
	public String usrname;
	public String password;
	public String name;
	public long phnno;
	public String address;
	public String email;

	CollectInfo() {

	}

	CollectInfo(String choice, String usrname, String password, String name, long phnno, String address, String email) {
		this.choice = choice;
		this.usrname = usrname;
		this.password = password;
		this.name = name;
		this.phnno = phnno;
		this.address = address;
		this.email = email;
	}

	public void run() {
		try {
			if (choice.equalsIgnoreCase("Login")) {
				get(usrname, password);
			} else if (choice.equalsIgnoreCase("signup")) {
				get(usrname, password, name, phnno, address, email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}