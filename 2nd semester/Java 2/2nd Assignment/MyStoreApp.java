import java.util.*;
public class StoreApp {
	public static void main(String args[]) {
		private String Order, Avaliables, Sale, exit;
     public Option(String flag, String opt) { this.flag = flag; this.opt = opt; }

		Scanner in = new Scanner(System.in);
     	Product myProduct = new Product();
		MyCatalogs catalogs = new MyCatalogs();
		public void initializeavailables(){
			catalogs.availables.add(new Motherboard("MB1", 2017, "Brand1", 95, discount, 64, "AMD", 4));
		    catalogs.availables.add(new Motherboard("MB2", 2017, "Brand1", 90, discount, 32, "AMD", 4));
			catalogs.availables.add(new CPU("CPU1", 2017, "Brand2", 190, discount, 3, 6, true));
		    catalogs.availables.add(new CPU("CPU2", 2017, "Brand2", 195, discount, 3, 8, true));
		
		}
		public void initializefororder(){
			catalogs.fororder.add();
		}
		public void initializeforsale(){
			catalogs.forsale.add();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		System.out.println("Phone? ");
		phone = in.nextLine();
		System.out.println("Address? ");
		address = in.nextLine();
		Person ob1 = new Person (name, phone, address);