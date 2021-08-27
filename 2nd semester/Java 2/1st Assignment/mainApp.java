// This is the main program
import java.util.*;
public class mainApp {
	public static void main(String args[]) {
     	Scanner in = new Scanner(System.in);
		MyCatalogs catalogs = new MyCatalogs();
      	boolean done=false;
      	String answer, category, type, product, typeProductName;
   	 	/*What the client wants to do in general*/
		while ( ! done)	{				
   	 		System.out.println("\n1. Product Overview");
   	 		System.out.println("2. Order Overview");
   	 		System.out.println("3. Sale Overview");
   	 		System.out.println("0. exit");
   			System.out.print("> ");
   	 		answer = in.nextLine();

			/*Product Overview*/
   	 		if (answer.equals ("1")){
				System.out.println("\nCategory:\na. Accessories \nb. Peripherals \nPlease choose a category.");
				category = in.nextLine();
					// Accessories 
					if(category.equalsIgnoreCase("a") || category.equalsIgnoreCase("Accessories")){  
						System.out.println("\nType:\na. Motherboard \nb. CPU \nc. RAM \nd. Graphics Card \ne. Hard Drive \nf. exit ");
						type = in.nextLine();
						if(type.equalsIgnoreCase("a")){ //Motherboards
							selectAndFind(1, catalogs, in);
						}
						if(type.equalsIgnoreCase("b")){ //CPUs
							selectAndFind(2, catalogs, in);
						}
						if(type.equalsIgnoreCase("c")){ //RAMs
							selectAndFind(3, catalogs, in);
						}
						if(type.equalsIgnoreCase("d")){ //Graphics Cards
							selectAndFind(4, catalogs, in);
						}
						if(type.equalsIgnoreCase("e")){ // Hard Drives
							selectAndFind(5, catalogs, in);
						}
						if(type.equalsIgnoreCase("f")) { // exit
							done = true; //exit
							break;
						}
					}
					// Peripherals
					else if(category.equalsIgnoreCase("b") || category.equalsIgnoreCase("Peripherals")){
						System.out.println("\nType:\na. Screens \nb. Keyboard \nc. Mouse \nd. Printer \ne. exit ");
						type = in.nextLine();
						if(type.equalsIgnoreCase("a")){ // Screens
							selectAndFind(6, catalogs, in);
						}
						if(type.equalsIgnoreCase("b")){ // Keyboards
							selectAndFind(7, catalogs, in);
						}
						if(type.equalsIgnoreCase("c")){ // Mouses
							selectAndFind(8, catalogs, in);
						}
						if(type.equalsIgnoreCase("d")){ // Printer
							selectAndFind(9, catalogs, in);
						}
						if(type.equalsIgnoreCase("e")) { // exit
							done = true; //exit
							break;
						}
					}
			}
			/*Order Overview */
			else if (answer.equals("2")){ 
				catalogs.showOrdered();
			}
			/*Sale Overview*/
			else if (answer.equals("3")){ 
				catalogs.showSold();
			}
			else if (answer.equals ("0")) done = true; // exit from program
		}
	}
	// Prints the products of the category that the client chose and the client types which product they wish to buy.
	public static void selectAndFind(int a,	MyCatalogs catalogs, Scanner in){
		switch(a){
			case 1:catalogs.showMotherboards(); break;
			case 2:catalogs.showCPUs(); break;
			case 3:catalogs.showRAMs(); break;
			case 4:catalogs.showGraphicsCards(); break;
			case 5:catalogs.showHardDrives(); break;
			case 6:catalogs.showScreens(); break;
			case 7:catalogs.showKeyboards(); break;
			case 8:catalogs.showMouses(); break;
			case 9:catalogs.showPrinters(); break;
			case 10:System.out.println("\nPlease type again the name of the product you would like to buy. "); String typeProductName = in.nextLine(); findProduct(typeProductName, catalogs, in); break;
		}
		if(a != 10){
			System.out.println("\nPlease type the name of the product you would like to buy. ");
			String typeProductName = in.nextLine();
			findProduct(typeProductName, catalogs, in);
		}	
	}
	// Finds the product the client wishes to buy.
	public static void findProduct(String typeProductName,/*String name, String phone*/ MyCatalogs catalogs, Scanner in){
		boolean repeat = true;
		for(Availables item: MyCatalogs.availables){ 
			boolean right = true;
			if(item.getProductName().equalsIgnoreCase(typeProductName)){
				repeat = false;
				Scanner inn = new Scanner(System.in); 
				if (item.getAvailability()>0){
					System.out.println(item.getProduct().toString()+"\n\nDo you wish to buy it?\nPlease answer 'yes' or 'no'.");
					while (right==true){
						String anss = inn.nextLine();
						if (anss.equalsIgnoreCase("yes")){
							System.out.println("Name? ");
							String name = inn.nextLine();
							System.out.println("Phone? ");
							String phone = inn.nextLine();
							MySale sale = new MySale(MySale.code, item.getProduct(), name, phone, item.currentDay(), item.getProductPrice());  
							catalogs.addSold(sale); 
							MySale.codeIncrease();
							item.decreaseAvailability();
							System.out.println(sale.toString());
							right = false;
							break;
						}
						else if (anss.equalsIgnoreCase("no")){
							right = false;
							break;
						}
						else 
							System.out.println("Please type 'yes' or 'no' ");
							right = true;
					}
				}
				else if (item.getAvailability()==0){
					System.out.println("\nProduct out of stock.\nDo you want to order it?\nPlease enter 'yes' or 'no'.");
					while (right==true){
						String ans = inn.nextLine();
						if (ans.equalsIgnoreCase("yes")){
							System.out.println("Name? ");
							String name = inn.nextLine();
							System.out.println("Phone? ");
							String phone = inn.nextLine();
							MyOrder order = new MyOrder(MyOrder.code, item.getProduct(), name, phone, item.currentDay(), item.arrivalDay(),  item.getProductPrice(), false);
							order.setDone();
							catalogs.addOrdered(order); 
							System.out.println(order.toString());
							MyOrder.codeIncrease();
							item.decreaseAvailability();
							right = false;
							break;
						}
						else if (ans.equalsIgnoreCase("no")){
							right = false;
							break;
						} 
						else {
							System.out.println("Please type 'yes' or 'no' ");
							right = true;
						}
					}
				}
			}
			else {
				right = false;
			}	
		}
		if( repeat == true ){
			System.out.println("We don't provide this product");
			selectAndFind(10, catalogs, in);
		}
	}
}