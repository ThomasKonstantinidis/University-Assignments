import java.util.*;
import java.io.*;


public class Top_k_withPQ {
	public static void main(String[] args){
		String [] tokens;
		File txt = new File(args[1]);
		
		/*bufferedReader*/
	    int	k = Integer.parseInt(args[0]);
		BufferedReader br= null;
	
		try{
			
			String FLine;
			br =  new BufferedReader(new FileReader(txt));
			PQ pq = new PQ(k);
			
			while((FLine = br.readLine()) != null){
				int lines=2;
				tokens = FLine.split(" ");
				int id = Integer.parseInt(tokens[0]);
				int likes = Integer.parseInt(tokens[tokens.length-1]);
				String title = tokens[1] + " ";

				while(lines<(tokens.length)-1){
					title = title + tokens[lines] + " ";
					lines++;
				}		
				Song c = new Song(id,title,likes);	
				if(pq.size()<k){
					pq.insert(c);
				}
				else if(pq.cmp.compare(c, pq.heap[k])!=0){
					pq.remove(pq.size());
					pq.insert(c);
				}
			}
			pq.print();
			
			
		}
		
		catch (IOException e) {
            System.out.println("Error reading file ");                
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