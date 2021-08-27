import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class NetBenefit {
	public static void main(String[] args) {
		File txt = null;
		if (0 < args.length) {
			txt = new File(args[0]);
		} 

        BufferedReader br = null;

        try {
			String CLine;
			int cost=0;
			int sub=0;
			int subp=0;
			int upoloipo=0;
			String [] tokens ;
//open the text file
            br = new BufferedReader(new FileReader(txt));
			IntQueueImpl Queue = new IntQueueImpl();
			IntQueueImpl Queuep = new IntQueueImpl();
//reading of text file	
			while ((CLine = br.readLine()) != null) {
				boolean close=false;
				tokens = CLine.split(" ");
				int met = Integer.parseInt(tokens[1]);
				int price = Integer.parseInt(tokens[3]);
//array with the words of every line 			
				if (tokens[0].toLowerCase().equals("buy")){
				    Queue.put(met);
					Queuep.put(price);
				}
					
			    else if (tokens[0].toLowerCase().equals("sell")){
					//sub=current 
					
					while(!Queue.isEmpty() && close==false){
						
						if(upoloipo==0){
							
							if((Queue.peek()<met)){
								met=met-Queue.peek();
//calculate the benefit of the sale
								cost+=Queue.get() * (price - Queuep.get());
							}
							else if(met<Queue.peek()){
								cost += met * (price - Queuep.peek());
								upoloipo = Queue.peek()-met;
								close=true;
						    }
							else if(met==Queue.peek()){
								cost += met * (price - Queuep.get());
								upoloipo = Queue.get()-met;
								close=true;
							}
						}
						else{
							if((upoloipo<met)){
								met=met-upoloipo;
								cost+= upoloipo * (price - Queuep.get());
								upoloipo=0;
							}
							else if(met<upoloipo){
								cost += met * (price - Queuep.peek());
								upoloipo = upoloipo-met;
								close=true;
						    }
							else if(met==upoloipo){
								cost += met * (price - Queuep.get());
								upoloipo = 0;
								Queue.get();
								close=true;
							}
						}
					}
				}
			}
			System.out.println("The profit is: " +cost);
		}
		//exceptions
		catch (IOException e) {
            e.printStackTrace();
			
        } 



        finally {
			try {
                if (br != null)br.close();
            } 
			catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}
}
	
	

