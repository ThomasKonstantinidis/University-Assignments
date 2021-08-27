import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
 import java.util.Scanner;

public class Top_k{
	
	protected Song[] array ;
	protected int count = 0;

	
	public Top_k (){
		this(10);
	}
	
	public Top_k(int length){
		array = new Song[length];
	}
	
	public static int partition(Song arr[], int l, int r){
		int i = l-1;
		int j = r;
		Song s=arr[r];
		for(;;)
		{
			
		
		
		while (arr[++i].compareTo(s)<0)
			while (s.compareTo(arr[--j])<0)
				if(j==l)break;
			if (i>=j)break;
					
					Song temp=arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
		}
		
		Song temp=arr[i];
					arr[i]=arr[r];
					arr[r]=temp;
		return i;
	}

	
	protected void add(Song[] array,Song item){
		
	    Song[] temp = new Song[array.length+1];
		for (int i=0; i < array.length; i++) {
		temp[i]=array[i]; }
			this.array=temp;
		this.array[count]=item;
		
		count++;
		
		
	}
	
	
	public void quickSort(Song arr[], int l, int r){
		if(r<=l){
			int i=partition(arr,l,r);
		quickSort(arr,l,i-1);
		quickSort(arr,i+1,r);
		}
		
	}
	
	
	protected void print(Song[] array, int k) {
		
		if(k > count) k = count;
		
		for(int i=count-1; i > (count-k-1); i--)
			System.out.print(array[i]);
		
		System.out.println();
	}
	public static void main(String[] args)throws FileNotFoundException{
		Scanner scan= new Scanner(System.in);

		if (args.length != 0)
{
	
		String input = args[0];//fileName


		
		String FLine=null;//line
		FileReader txt = new FileReader(input);/*fileReader*/
	
	            
	   BufferedReader br =  new BufferedReader(txt);/*bufferedReader*/
	
		try{
		Top_k items = new Top_k();
		int k= 0;
		
		//String answer = JOptionPane.showInputDialog("Give me k songs:");
		 System.out.println("Give me k songs:");
		  String answer= scan.nextLine();


        		
       k = Integer.parseInt(answer);
	
		int lines=0;

			while((FLine = br.readLine()) != null){
				lines++;
				System.out.println(FLine);
				
				if(lines>k){
				System.out.println("EXIT");
				break;
			}
			}

			String [] tokens;
			int p=0;
			
			
			
			while((FLine = br.readLine()) != null){
				tokens = FLine.split(" ");
				int id = Integer.parseInt(tokens[1]);
				String title = tokens[2];
				int likes = Integer.parseInt(tokens[3]);
				Song c = new Song(id,title,likes);
				 items.add(items.array,c);
				p++;
			}
	 br.close(); 
			items.quickSort(items.array, 0, items.count -1);
           	 
	            System.out.println("Your Top " + k + " is:");
	            items.print(items.array, k);
			
			
		}
		catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                input + "'");                
        }
		
		catch (IOException e) {
            System.out.println(
                "Error reading file '" + 
                input + "'");                
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
}
