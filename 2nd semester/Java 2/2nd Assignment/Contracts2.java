import java.io.*;
import java.util.*;

public class Contracts2{
	
	private static int numInstancesMobilePhone = 0;
	private static int numInstancesMobileInternet = 0;
	private Vector<Contracts2> StoreProducts = new Vector<Contracts2>();
	
	public void loadFile(String data){
		int counter = 0;
		File f = null;
		BufferedReader reader = null;
		Contracts2 product = null;
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
				if(token.equals("ActiveMobilePhone")){
					product = new ActiveMobilePhone();
					product.setonomauphresias(st.nextToken());
					product.setname(st.nextToken());
					product.setsurname(st.nextToken());
					product.setphone(st.nextToken());
					product.sethmeromhniaenergopoihshs(st.nextToken());
					product.settroposexoflhshs(st.nextToken());
					product.settype(st.nextToken());
					product.setmhniaiopagio(Double.parseDouble(st.nextToken()));
					product.setextrasale(Double.parseDouble(st.nextToken()));
					product.setsale(Double.parseDouble(st.nextToken()));
					product.setkwdikossumbolaiou(Integer.parseInt(st.nextToken()));
					((ActiveMobilePhone)product).setleptaomilias(Double.parseDouble(st.nextToken()));
					((ActiveMobilePhone)product).setSMS(Integer.parseInt(st.nextToken()));
					((ActiveMobilePhone)product).setsunolo(Double.parseDouble(st.nextToken()));
					((ActiveMobilePhone)product).setdwreanupoloipo(Integer.parseInt(st.nextToken()));
				}
				else if(token.equals("ActiveMobileinternet")){
					product = new ActiveMobileinternet();
					product.setonomauphresias(st.nextToken());
					product.setmhniaiopagio(Double.parseDouble(st.nextToken()));
					product.setsale(Double.parseDouble(st.nextToken()));
					product.setname(st.nextToken());
					product.setsurname(st.nextToken());
					product.setphone(st.nextToken());
					product.sethmeromhniaenergopoihshs(st.nextToken());
					product.settroposexoflhshs(st.nextToken());
					product.settype(st.nextToken());
					product.setextrasale(Double.parseDouble(st.nextToken()));
					product.setkwdikossumbolaiou(Integer.parseInt(st.nextToken()));
					((ActiveMobileinternet)product).setOgko(Double.parseDouble(st.nextToken()));
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
	public Contracts2 get(int i){
		return StoreProducts.get(i);
	}
	public int size(){
		return StoreProducts.size();
	}