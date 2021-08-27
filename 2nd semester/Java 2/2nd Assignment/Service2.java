import java.io.*;
import java.util.*;

public class Servise2{
	
	private static int numInstancesMobilePhone = 0;
	private static int numInstancesMobileInternet = 0;
	private Vector<Servise2> StoreProducts = new Vector<Servise2>();
	
	public void loadFile(String data){
		int counter = 0;
		File f = null;
		BufferedReader reader = null;
		Service2 product = null;
		String line;
		try{
			f = new File(data)
		}
		catch (NullPointerException e){
			System.err.println("File not found.");
		}
		try{
			reader = new BufferedReader(new FileReader(f));
		}
		catch (fileNotFoundException e){
			System.err.println("Error opening file!");
		}
		try{
			line = reader.readLine();
			while (line!=null){
				counter++;
				StringTokenizer st = new StringTokenizer(line,"#");
				String token = st.nextToken();
				if(token.equals("Mobilephone")){
					product = new Mobilephone();
					product.setonomauphresias(st.nextToken());
					product.setmhniaiopagio(Double.parseDouble(st.nextToken()));
					product.setsale(Double.parseDouble(st.nextToken()));
					((Mobilephone)product).setdwreanxronos(Double.parseDouble(st.nextToken()));
					((Mobilephone)product).setdwreansms(Integer.parseInt(st.nextToken()));
					((Mobilephone)product).setxrewshmetaxronos(Double.parseDouble(st.nextToken()));
					((Mobilephone)product).setxrewshmetasms(Double.parseDouble(st.nextToken()));
					if(token.equals("Cardotype")){
						((Cardotype)product).setmhniaiodiathesimosexrono(Double.parseDouble(st.nextToken()));
					}	
				}
				else if(token.equals("Mobileinternet")){
					product = new Mobileinternet();
					product.setonomauphresias(st.nextToken());
					product.setmhniaiopagio(Double.parseDouble(st.nextToken()));
					product.setsale(Double.parseDouble(st.nextToken()));
					((Mobileinternet)product).setXrewshmetaogko(Double.parseDouble(st.nextToken()));
					((Mobileinternet)product).setMb(Integer.parseInt(st.nextToken()));
			    }
				if(product!=null)StoreProducts.add(product);
				line=reader.readLine();
				
			}	
		}
		catch(IOException e){
			System.err.println("Error reading line " + counter + ".");
		}
		try{
			reader.close();
		}
		catch(IOException e){
			System.err.println("Error closing file.");
		}
	} 
	public Service2 get(int i){
		return StoreProducts.get(i);
	}
	public int size(){
		return StoreProducts.size();
	}