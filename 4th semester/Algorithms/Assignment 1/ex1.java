import java.util.*;
import java.io.*;

public class ex1{
	public static void main(String[] args) {
		try{
			File file = new File(args[0]);
			List<Integer> lista = Utilities.convertFileSequenceToList(file);
			int x = Integer.parseInt(args[1]);
			int left = 0;
			int current = 0;
			int right = lista.size()-1;
			while( left < right ){
				int half = (left + right)/2;
				if(lista.get(half) < x){
					left = half + 1;
				}
				else{
					right = half - 1;
					if(lista.get(half)==x)
						current = half;
				}					
			}
			int first = 0;
			if(lista.get(left)==x){
				first = left;
			}else if(lista.get(right)==x){
				first = right;
			}else{
				first = current;
			}
				
			left = 0;
			right = lista.size()-1;
			while( left < right ){
				int half = (left + right)/2;
				if(lista.get(half) <= x)
					left = half + 1;
				else
					right = half - 1;
			}
			
			int last = 0;
			if(lista.get(right)==x)
				last = right;	
			else
				last = left-1;
			
			System.out.print("The first position of ");
			System.out.print(x);
			System.out.print(" is: ");
			System.out.print(first);
			System.out.print(" and the last is: ");
			System.out.print(last);
		}
		catch (IOException e) {
            e.printStackTrace();
			
        }
	}
}