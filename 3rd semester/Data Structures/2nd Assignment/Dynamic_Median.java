import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Dynamic_Median {

	public static void main(String[] args) {

     
        String input = args[0];/*filename*/


        String Fline = null;

        try {
        		PQ<Song> min = new PQ<Song>(10, new DefaultComparator2()); 
        		PQ<Song> max = new PQ<Song>(10, new DefaultComparator());
        		
	           
	            FileReader fileReader = 
	                new FileReader(input);
	
	           
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);
	            
	            long median = 0; 
	            int count = 0;
	           String [] tokens;
	            while((Fline = bufferedReader.readLine()) != null) {
	            	
	            
					 tokens = Fline.split(" ");
				int id = Integer.parseInt(tokens[0]);
				String title = tokens[1];
				int likes = Integer.parseInt(tokens[2]);
	            	 

	            	 
	            	 if(likes > median) {
	            		max.insert(new Song(id, title, likes)); 
	            	 } else{
	            		 min.insert(new Song(id, title, likes));
	            	 }
	            	 
	            	 if(min.size() - max.size() == 1) {
	            		 median = min.max().getLikes();
	            	 } else if (max.size() - min.size() == 1){
	            		 median = max.max().getLikes();
	            	 } else {
	            			  if(max.size() - min.size() <= 0 ) {
								  median=min.max().getLikes();
							  } 
						else			{	
							median=max.max().getLikes();
						}
	            	 }
	            	
	            	 if(min.size() - max.size() > 1){
	            		 max.insert(min.getMax());
	            	 } else if(max.size() - min.size() > 1){
	            		 min.insert(max.getMax());
	            	 }
	            	 
	            	 
					if(count % 5 == 0 && count !=0) System.out.println("Median= " + median);
	            	 count++;
	            }   
	            
	            if(count % 5 != 0) System.out.println("Median= " + median);
	  
	            bufferedReader.close();  

        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                input + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + input + "'");                  
        
        }
		
		

		
		
		
		
	}

}
