import java.util.*;
public class MyCatalogs{
	private Product item;
	private MyOrder ordered;
	private MySale sold;
	public ArrayList<ArrayList<Product>> availables;
	public ArrayList<MyOrder> fororder;
	public ArrayList<MySale> forsale;
	public ArrayList<Accessories> accessories;
	//public ArrayList<Peripherals> peripherals;
	//public ArrayList peripherals;
	
	// Constructor
	public MyCatalogs(){
		// the three catalogs he wants us to do 
		ArrayList<ArrayList<Product>> availables = new ArrayList<ArrayList<Product>>();
		ArrayList<MyOrder> fororder = new ArrayList<MyOrder>();
		ArrayList<MySale> forsale = new ArrayList<MySale>();
		
		ArrayList<Accessories> accessories = new ArrayList<Accessories>();
		//ArrayList<Peripherals> peripherals = new ArrayList<Peripherals>;
		initializeAccessories();
	}
	
	public void removeFromAvailables(){
		availables.remove(item);
	}
	
	public void addSoled(){
		forsale.add(sold);
	}
	
	public void addOrdered(){
		fororder.add(ordered);
	}
	
	public void initializeAccessories(){
		// Motherboards
		accessories.add(new Motherboard("MB1", 2017, "Brand1", 95, discount, 64, "AMD", 4));
		accessories.add(new Motherboard("MB2", 2017, "Brand1", 90, discount, 32, "AMD", 4));
		accessories.add(new Motherboard("MB3", 2017, "Brand1", 100, discount, 128, "AMD", 4));
		accessories.add(new Motherboard("MB4", 2017, "Brand1", 100, discount, 64, "AMD", 6));
		accessories.add(new Motherboard("MB5", 2017, "Brand1", 95, discount, 32, "AMD", 6));
		accessories.add(new Motherboard("MB6", 2017, "Brand1", 105, discount, 128, "AMD", 6));
		accessories.add(new Motherboard("MB7", 2017, "Brand1", 105, discount, 64, "AMD", 8));
		accessories.add(new Motherboard("MB8", 2017, "Brand1", 100, discount, 32, "AMD", 8));
		accessories.add(new Motherboard("MB9", 2017, "Brand1", 110, discount, 128, "AMD", 8));
		accessories.add(new Motherboard("MB10", 2017, "Brand1", 95, discount, 64, "Intel", 4));
		accessories.add(new Motherboard("MB11", 2017, "Brand1", 90, discount, 32, "Intel", 4));
		accessories.add(new Motherboard("MB12", 2017, "Brand1", 100, discount, 128, "Intel", 4));
		accessories.add(new Motherboard("MB13", 2017, "Brand1", 100, discount, 64, "Intel", 6));
		accessories.add(new Motherboard("MB14", 2017, "Brand1", 95, discount, 32, "Intel", 6));
		accessories.add(new Motherboard("MB15", 2017, "Brand1", 105, discount, 128, "Intel", 6));
		accessories.add(new Motherboard("MB16", 2017, "Brand1", 105, discount, 64, "Intel", 8));
		accessories.add(new Motherboard("MB17", 2017, "Brand1", 100, discount, 32, "Intel", 8));
		accessories.add(new Motherboard("MB18", 2017, "Brand1", 110, discount, 128, "Intel", 8));
		// CPUs
		accessories.add(new CPU("CPU1", 2017, "Brand2", 190, discount, 3, 6, true));
		accessories.add(new CPU("CPU2", 2017, "Brand2", 195, discount, 3, 8, true));
		accessories.add(new CPU("CPU3", 2017, "Brand2", 200, discount, 3, 16, true));
		accessories.add(new CPU("CPU4", 2017, "Brand2", 195, discount, 3.6, 6, true));
		accessories.add(new CPU("CPU5", 2017, "Brand2", 200, discount, 3.6, 8, true));
		accessories.add(new CPU("CPU6", 2017, "Brand2", 205, discount, 3.6, 16, true));
		accessories.add(new CPU("CPU7", 2017, "Brand2", 200, discount, 4, 6, true));
		accessories.add(new CPU("CPU8", 2017, "Brand2", 205, discount, 4, 8, true));
		accessories.add(new CPU("CPU9", 2017, "Brand2", 210, discount, 4, 16, true));
		accessories.add(new CPU("CPU10", 2017, "Brand2", 190, discount, 3, 6, false));
		accessories.add(new CPU("CPU11", 2017, "Brand2", 195, discount, 3, 8, false));
		accessories.add(new CPU("CPU12", 2017, "Brand2", 200, discount, 3, 16, false));
		accessories.add(new CPU("CPU13", 2017, "Brand2", 195, discount, 3.6, 6, false));
		accessories.add(new CPU("CPU14", 2017, "Brand2", 200, discount, 3.6, 8, false));
		accessories.add(new CPU("CPU15", 2017, "Brand2", 205, discount, 3.6, 16, false));
		accessories.add(new CPU("CPU16", 2017, "Brand2", 200, discount, 4, 6, false));
		accessories.add(new CPU("CPU17", 2017, "Brand2", 205, discount, 4, 8, false));
		accessories.add(new CPU("CPU18", 2017, "Brand2", 210, discount, 4, 16, false));
		//RAMs   problem with the type
		//accessories.add(new RAM("RAM", 2017, "Brand1", 50, discount, setRAMType(DDR2), 4, 1600));
		
		//Graphics Cards
		accessories.add(new GraphicsCard("GRC", 2017, "Brand2", 200, discount, "nVidia", 2));
		
	}
}

/*chipset (nVidia ή AMD), μνήμη (2, 4, 6 GB)
String name, int year, String constructor, double price, double Discount, String TheChipset, int TheMemory




   RAM
   
String name,
 int year,
 String constructor,
 double price,
 double Discount,  
 Type TheType,		DDR2, DDR3, DDR4
 int capacity,		4, 8, 16
 int TheFrequency	1600,2666, 4600
 */
 
