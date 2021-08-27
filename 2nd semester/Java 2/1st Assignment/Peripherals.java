public class Peripherals extends Product{	
	private double discount; 
	
	// Constructor
	public Peripherals(String name, int year, String constructor, double firstprice, double Discount){
		super(name, year, constructor, firstprice);
		discount = Discount;
	}
	
	// Setters
	public void setDiscount(double Discount){
		discount = Discount;
	}
	
	//Getters
	public double getPrice(){
		return firstprice - firstprice*discount;
	}
	public double getDiscount(){
		return discount;
	}
	
	// toString
	public String toString(){
		return (super.toString()+"\nDiscount: "+getDiscount()+"\nPrice: "+getPrice());
	}
}