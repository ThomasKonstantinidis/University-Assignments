import java.util.*;
import java.io.*;
import java.lang.*;

public class ex2{
	public static void main(String[] args) {
		try{
			File f = new File(args[0]);
			List<Integer> l = Utilities.convertFileSequenceToList(f);
			quickSort(l, 0, l.size()-1);
			
			for(int k=0; k<l.size(); k++){
				System.out.print(l.get(k)+" ");
			}
		}
		catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	public static void quickSort(List<Integer> arr, int begin , int end){
		if(begin < end){
			String partitionIndex = partition(arr, begin, end);
			String[] parts= partitionIndex.split("-");
			int resultafter = Integer.parseInt(parts[1])+1;
			int resultbefore = Integer.parseInt(parts[0])-1;
			quickSort(arr, begin, resultbefore);
			quickSort(arr, resultafter, end);
		}
	}
	private static String partition(List<Integer> arr, int begin, int end){
		int pivot = getRandomInt(begin, end);
		Collections.swap(arr, pivot, end);
		pivot = end;
		int i = begin-1;
		int b = 1;
		boolean flag = true;
		for (int j=begin; j <= end-b; j++){
			if(arr.get(j) < arr.get(pivot)){
				i++;
				Collections.swap(arr, i, j);
			}else if(arr.get(j) == arr.get(pivot)){
				Collections.swap(arr, j, end-b);
				b++;
				j--;
			}
		}
		int start = i+1;
		int fin = end-b;
		while(fin<pivot){
			Collections.swap(arr, i+1, pivot);
			pivot--;
			i++;
		}
		pivot = i-1;
		String arxi = Integer.toString(start);
		String telos = Integer.toString(pivot);
		return arxi+"-"+telos;
	}
	
	public static int getRandomInt(int max,int min){
		int x = (int)(Math.random()*((max-min)+1))+min;
		return x;
	}
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		