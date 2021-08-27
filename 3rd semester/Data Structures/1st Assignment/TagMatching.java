import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TagMatching {
	public static void main(String[] args) {
		File htmlf = null;
		if (0 < args.length) {
			htmlf = new File(args[0]);
		} 

        BufferedReader br = null;
		int total=0;

        try {

            String CLine;
            boolean ticket=false;
			String st=null;
			boolean flag=false;
            br = new BufferedReader(new FileReader(htmlf));
			StringStackImpl Stack = new StringStackImpl();
			//reading of html file
			while ((CLine = br.readLine()) != null) {
				boolean close=false;
				

                if(Stack.size()>0){
					st=Stack.peek();
					ticket=true;
					
					if (Stack.size()==1 ){
					
						String er=Stack.peek();
						if (close==true){
							flag=true;
							break;
						}
					}
				}
				
				String CLineNew="<";

				for(int k=1; CLine.charAt(k)!='>'; k++){
					//finding end tags
					if(CLine.charAt(k)!='/'){
						char ch=CLine.charAt(k);
					    CLineNew=CLineNew+ch;
					}
					else{
						close = true;
						if(Stack.size()==0){
							flag=true;
						}
					}
				}
				    
				CLineNew=CLineNew+">";
				Stack.push(CLineNew);
  
			    if(ticket==true && close==true){
				    if(CLineNew.equals(st)){
					    Stack.pop();
					    Stack.pop();
			        }
				    else{
						flag=true;
				        break;
				    }
					
					
			    }
				
            }
			if (Stack.size()==2 || flag==true){
				System.out.println("The HTML file's tags is not in line");
			}
			else{
				System.out.println("The HTML file's tags is in line");							
			}
				
        }
		

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
