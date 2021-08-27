import java.io.*;
import java.util.*;

public class new1 {
	
	
	 public void loadFile(String data) { //FILE'S NAME AS ARGUMENT.
        int counter = 0; //COUNT THE LINES.
		boolean k=false;

        File f = null;  // THE OBJECT.
        BufferedReader reader = null; // ALLOW US TO READ THE LINES VIA READER.READLINE().
        int product ; 
        String line;
        String line2;

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
						String s[] = new String[15];
						line = reader.readLine();
						int i=0;
						int n=0;
					    while (!(line.trim().equals("}")) & i<3){
							if(line.trim().startsWith("ITEM_TYPE") || line.trim().startsWith("PRICE") || line.trim().startsWith("MODEL")){
								i++;
							}
							s[n] = line.trim();
							line = reader.readLine();
							n++;
						}
						if(i==3){
							int l=0;
							while (l<=n){
							
							    if(s[l].toLowerCase().startsWith("item_type"))
									if(s[l].toLowerCase().substring(10).equals("motherboard"))
								        product = 1; //CREATES THE OBJECT.
									else if(s[l].toLowerCase().substring(10).equals("cpu"))
										product = 2;
									else if(s[l].toLowerCase().substring(10).equals("ram"))
										product = 3;
									else if(s[l].toLowerCase().substring(10).equals("graphicscard"))
										product = 4;
									else if(s[l].toLowerCase().substring(10).equals("harddrive"))
										product = 5;
									else if(s[l].toLowerCase().substring(10).equals("printers"))
										product = 6;
									else if(s[l].toLowerCase().substring(10).equals("keyboard"))
										product = 7;
									else if(s[l].toLowerCase().substring(10).equals("mouse"))
										product = 8;
									else if(s[l].toLowerCase().substring(10).equals("screen"))
										product = 9;
								
								l++;
							}
						     //name of the arraylist.//cd
					
						} //item
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
	

}

