//katalogos me parexomenes uphresies
import java.util.*;

public class Collectionyp{
	
	public Collectionyp(){
    ArrayList <Service> services= new ArrayList <Service>();
	Mobileinternet px1=new Mobileinternet("Kinhtointernet",5.00,10.00 ,1100,200.00);
	Mobileinternet px2=new Mobileinternet("Kinhtointernet",7.00,25.00,1000,100.00);
	Contractprogram px3=new Contractprogram("programma sumbolaiou",10.00 ,15 ,1000.00,1000,0.20,0.50);
	Contractprogram px4=new Contractprogram("stathero",12.00 ,19.00 ,1000.00,1000,0.27,0.59);
	Cardotype px5=new Cardotype("kinhto",17.00 ,16.00 ,1000.00,1500,0.25,0.55);
	Cardotype px6=new Cardotype("oikogeneiako",15.00 ,10.00 ,1000.00,1200,0.20,0.50);
	services.add(px1);
	services.add(px2);
	services.add(px3);
	services.add(px4);
	services.add(px5);
	services.add(px6);
	}
	
	
		public void listServices(){//emfanish twn uperesiwn
		for(int i=0; i<services.size(); i++){
			System.out.println (services.get(i).print());
		}
		
}
}

