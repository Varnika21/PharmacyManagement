package Pharmacy;
import java.io.*;
import java.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
class medicine {
int date,month,year;
String disease,tablet;
float cost;
int quantity;
public medicine(String disease, String tablet, int date,int month, int year ,float cost, int quantity) {
    this.disease = disease;
    this.tablet = tablet;
    this.date = date;
    this.month = month;
    this.year = year;
    this.cost=cost;
    this.quantity = quantity;
}
}
public class MedicineDetails{
	HashSet<medicine> set=new HashSet<medicine>();
	ArrayList<Cart> c = new ArrayList<Cart>();
	public void get_medicine_info() throws Exception
	{
         File file = new File("D:\\Medicine_Details.txt");
         FileReader fr=new FileReader(file);   //reads the file
         BufferedReader buffer = new BufferedReader(fr);  //creates a buffering character input stream
         String line;
         while ((line = buffer.readLine())!=null) {
             String str = line;
             String[] arrstr = str.split(" ",7);
             medicine m1 = new medicine(arrstr[0],arrstr[1],Integer.parseInt(arrstr[2]),Integer.parseInt(arrstr[3]),Integer.parseInt(arrstr[4]),Float.parseFloat(arrstr[5]),Integer.parseInt(arrstr[6]));
             String date = arrstr[5]+" "+arrstr[4]+" "+arrstr[3];
             if(expiry(date)>0)
             {
            	 set.add(m1);
             }
         }
         fr.close();   
	}
	public void view_cart() {
		System.out.println("Medicines in the cart are: ");
		for(Cart c12: c) {
			System.out.println("Name: "+c12.tablet+" Quantity: "+c12.quantity+ " Cost: "+c12.cost);
		}
	}
	private int expiry(String date1)
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd");
		LocalDateTime now = LocalDateTime.now();
		String date2 = dtf.format(now);
		
		return date1.compareTo(date2);
		
	}
}