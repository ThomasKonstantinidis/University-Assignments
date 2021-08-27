public class Example{
	public static void main(String[] args){
		par2 par = new par2();
		par.loadFile("PARARTIMA1.txt");
		System.out.println(par.size());
		System.out.println("****************************************");
		
		for (int i=0; i < par.size();i++){
			System.out.println(par.get(i).toString());
		}
	}
} 

/*
Now I have added two orders in the list
The Keyboard which was made without the file 
*/