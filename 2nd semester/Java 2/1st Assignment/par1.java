import java.io.*;
import java.util.*;

public class par1 {
	private ArrayList <Availables> StoreProducts = new ArrayList<Availables>();
	
	 public void loadFile(String data) { //FILE'S NAME AS ARGUMENT.
        int counter = 0; //COUNT THE LINES.
		int k=0;
		String item="";
		ArrayList<String> obj = new ArrayList<String>();

        File f = null;  // THE OBJECT.
        BufferedReader reader = null; // ALLOW US TO READ THE LINES VIA READER.READLINE().
        Product product = null; 
        Availables pr = null; 
        String line;

        try {
            f = new File(data); // COULD'T FIND THE NAME.
        } catch (NullPointerException e) {
            System.err.println("File not found.");
        }

        try {
            reader = new BufferedReader(new FileReader(f)); //COULDN'T OPEN IT.
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file!");
        }

        try { //ABOUT LINES.
			line = reader.readLine() ; //read the 1st line.
			while(line != null){
				if(line.trim().equals("ITEM")){ //check the equality after the removal.
					
					line = reader.readLine(); // reads next line.
					if(line.trim().equals("{")){
						line = reader.readLine();
					    while(!(line.trim().equals("}"))){
							
							    if(line.trim().startsWith("ITEM_TYPE")){
									item = upperCaseFirst(line.trim().substring(10).toLowerCase());
									k++;
								}
								if(line.trim().startsWith("MODEL")){
									obj.add(line.trim());
								    k++;
								}
								if(line.trim().startsWith("MODEL_YEAR"))
								    obj.add(line.trim());
								if(line.trim().startsWith("CONSTRUCTOR"))
									obj.add(line.trim());
								if(line.trim().startsWith("PRICE")){
									obj.add(line.trim());
								    k++;
								}
								
								if(line.trim().startsWith("TYPE"))
									obj.add(line.trim());
								if(line.trim().startsWith("DIMENSION"))
									obj.add(line.trim());
							    if(line.trim().startsWith("TECHNOLOGY"))
									obj.add(line.trim());
								if(line.trim().startsWith("PRINTING_TYPE"))
									obj.add(line.trim());
								if(line.trim().startsWith("SIZE"))
									obj.add(line.trim());
								if(line.trim().startsWith("MEMORY"))
									obj.add(line.trim());
								if(line.trim().startsWith("CHIPSET"))
									obj.add(line.trim());
								if(line.trim().startsWith("FREQUENCY"))
									obj.add(line.trim());
								if(line.trim().startsWith("RAM_TYPE"))
									obj.add(line.trim());
								if(line.trim().startsWith("PIECES"))
									obj.add(line.trim());
								if(line.trim().startsWith("SPEED"))
									obj.add(line.trim());
								if(line.trim().startsWith("CORES"))
									obj.add(line.trim());
								if(line.trim().startsWith("GRACHICS"))
									obj.add(line.trim());
								if(line.trim().startsWith("MOTHERBOARD_TYPE"))
									obj.add(line.trim());
								if(line.trim().startsWith("NUMBEROFSATA"))
									obj.add(line.trim());
								if(line.trim().startsWith("RESOLUTION"))
									obj.add(line.trim());
								
						}
						if( k == 3 ){
							for (String temp : obj) {
								
								if(temp.startsWith("MODEL"))
									product.setName(temp.substring(6));
									
								if(temp.startsWith("MODEL_YEAR"))
									product.setYear(Integer.parseInt(temp.substring(11)));
								
								if(temp.startsWith("CONSTRUCTOR"))
									product.setConstructor(temp.substring(12));
									
								if(temp.startsWith("PRICE"))
									product.setFirstPrice(Double.parseDouble(temp.substring(6)));
								
								if(item.equals("Motherboard")){
									if(temp.startsWith("PIECES"))
									    ((Motherboard) product).setMemory(Integer.parseInt(temp.substring(7)));
									if(temp.startsWith("MOTHERBOARD_TYPE"))
									    ((Motherboard) product).setType(temp.substring(17));
									if(temp.startsWith("NUMBEROFSATA"))
									    ((Motherboard) product).setNumberOfSATA(Integer.parseInt(temp.substring(13)));
								}
								else if(item.equals("Cpu")){
									if(temp.startsWith("SPEED"))
									    ((CPU) product).setSpeed(Double.parseDouble(temp.substring(6)));
									if(temp.startsWith("CORES"))
									    ((CPU) product).setNumberOfCores(Integer.parseInt(temp.substring(6)));
									if(temp.startsWith("GRAPHICS"))
									    ((CPU) product).setGraphics(Boolean.parseBoolean(temp.substring(9)));
								}
								else if(item.equals("Ram")){
									if(temp.startsWith("RAM_TYPE"))
									    ((RAM) product).setRAMType(temp.substring(9));
									if(temp.startsWith("PIECES"))
									    ((RAM) product).setRAMMemory(Integer.parseInt(temp.substring(7)));
									if(temp.startsWith("FREQUENCY"))
									    ((RAM) product).setFrequency(Integer.parseInt(temp.substring(10)));	
								}	
								else if(item.equals("Graphicscard")){
									if(temp.startsWith("CHIPSET"))
									    ((GraphicsCard) product).setChipset(temp.substring(8));
									if(temp.startsWith("MEMORY"))
									    ((GraphicsCard) product).setGraphicsMemory(Integer.parseInt(temp.substring(7)));
								}	
								else if(item.equals("Harddrive")){
									if(temp.startsWith("TYPE"))
									    ((HardDrive) product).setDriveType(temp.substring(5));
									if(temp.startsWith("SIZE"))
									    ((HardDrive) product).setSize(Double.parseDouble(temp.substring(5)));
									if(temp.startsWith("MEMORY"))
									    ((HardDrive) product).setDriveMemory(Integer.parseInt(temp.substring(7)));	
								}
								else if(item.equals("Keyboard")){
									if(temp.startsWith("CONNECTION"))
									    ((Keyboard) product).setConnection(temp.substring(11));	
								}	
								else if(item.equals("Mouse")){
									if(temp.startsWith("CONNECTION"))
									    ((Mouse) product).setConnection(temp.substring(11));
									if(temp.startsWith("TECHNOLOGY"))
									    ((Mouse) product).setTechnology(temp.substring(11));
								}
								else if(item.equals("Printers")){
									if(temp.startsWith("TECHNOLOGY"))
									    ((Printer) product).setTechnology(temp.substring(11));
									if(temp.startsWith("PRINTING_TYPE"))
									    ((Printer) product).setPrintingType(temp.substring(14));	
								}	
								else if(item.equals("Screen")){
									if(temp.startsWith("TYPE"))
									    ((Screen) product).setType(temp.substring(5));
									if(temp.startsWith("DIMENSION"))
									    ((Screen) product).setDimension(Double.parseDouble(temp.substring(10)));
									if(temp.startsWith("RESOLUTION"))
									    ((Screen) product).setResolution(temp.substring(11));
									if(temp.startsWith("PORT"))
									    ((Screen) product).setPort(temp.substring(5));
							    }
								
							
						    }							
						}
						StoreProducts.add(pr); //name of the arraylist.//cd
					
						 //item
					} //{
				}//if
				line = reader.readLine();
			}								
							
								


        } catch (IOException e) {
            System.out.println("Error reading line " + counter + ".");
        }

        try {
            reader.close(); //COULDN'T CLOSE IT.
        } catch (IOException e) {
            System.err.println("Error closing file.");
        }
    }
	
	public static String upperCaseFirst(String value) {

        char[] array = value.toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        return new String(array);
    }

	public Availables get(int i){
		return StoreProducts.get(i); // RETURN WHICH ITEM  IS THIS.
	}


	public int size()	{
		return StoreProducts.size(); //RETURNS THE NUMBER OF ITEMS.
	}

}

