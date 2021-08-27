import java.time.*;
import java.time.format.*;

public class MyOrder {
	private static int code = 1;
	private final static int plusdays = 3; //how many days till the arrival day
	private Product item; // the choosen product for order
	private Person customer; // the customer's information
	private double finalprice = item.getPrice(); 
	private boolean done; // true=completed, false=waiting
	private String arrday = arrivalDay();

	// Calculate current day and day of arrival
	LocalDate localDate = LocalDate.now(); 
	DateTimeFormatter curday = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 	
	private String today = curday.format(localDate); // the current day hopefully in dd/MM/yyyy form
	
	// Constructor
	public MyOrder(int code, Product item, Person customer, String today, String arrday, double finalprice, boolean done){
		this.item = item;
		this.code = code;
		this.customer = customer;
		this.today = today;
		this.arrday = arrday;
		this.finalprice = finalprice;
		this.done = done;
	}
	
	/* Increases the code of order from 1 to n so that each code 
		is a unique serial number */ 
	public void codeIncrease(){
		code++;
	}
	
	// Returns the situation of the order placed
	public boolean isCompleted(){
		return done;
	}
	
	public String situation(){
		if (isCompleted()==true)
			return "Completed";
		else 
			return "Waiting";
	}
	
	// Returns the day of the order
	public String currentDay(){ 
		return today;
	}
	
	// Returns the arrival day
	public String arrivalDay(){
		LocalDate a = localDate.plusDays(plusdays);
		DateTimeFormatter b = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String arday = b.format(a);
		return arday;
	}
	
	// toString (better to make getters??)
	public String toString(){
		return "Order's Code: "+code+"\nProduct"+item.toString()+"\nCustomer's Info: "+customer.toString()+"\nDate of Order: "+today+"\nEstimated Date of Arrival: "+arrday+"\nFinal Price: "+finalprice+"\nOrder's Situation: "+situation();
	}
}

