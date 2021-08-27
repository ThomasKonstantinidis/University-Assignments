import java.time.*;
import java.time.format.*;

public class MySale {
	private static int code = 1;
	private Product item;
	private Person customer;
	private double finalprice = item.getPrice();
	
	// for the sale's date
	LocalDate localDate = LocalDate.now(); 
	DateTimeFormatter today = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 	
	private String salesday = today.format(localDate);
	
	// Constructor
	public MySale(int code, Product item, Person customer, String salesday, double finalprice){
		this.code = code;
		this.item = item;
		this.customer = customer;
		this.salesday = salesday;
		this.finalprice = finalprice;
	}
	
	public void codeIncrease(){
		code++;
	}
	
	public String toString(){
		return "Sale's Code: "+code+"\nProduct"+item.toString()+"\nCustomer's Info: "+customer.toString()+"\nDate of Sale: "+salesday+"\nFinal Price: "+finalprice;
	}
}