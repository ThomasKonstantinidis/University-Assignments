//katalogos me energa sumbolaia
import java.util.*;

public class Collectionpar{
	private ArrayList <Contract> contracts= new ArrayList <Contract>();
	 Activemobileinternet px1=new  Activemobileinternet("Mobile internet",10.00",20.00 ,"Koula","Papa","6981938532", "5/6" ,15.00 ,"card","1","oikogeneiako",5.00 ,150 );
	 Activemobileinternet px2=new  Activemobileinternet("Mobile internet",12.00,32.00 ,"Soula","Papathanasiou","69847362898","2/2",10.00 ,"cash","2","foithtiko paketo",15.00,5 );
	Activecontractprogram px3=new Activecontractprogram("Programm contract ",3.00,13,"Toula","Kosmidi","69711278378","3/3",20.00 ,"card","3","paketo gia grafeio",30,500);
	Activecontractprogram px4=new Activecontractprogram("Programm contract ",4.00,32,"Boula","Papadopoulou","69790278378","4/4",20.00 ,"card","4","oikogeneiako",90,1000);
	Activecardotype px5=new Activecardotype("Cardotype",2.00,50,"Basilis","Papadopoulos","69790212345","5/4",20.00 ,"cash","5","foitiko",40,1200);
	Activecardotype px6=new Activecardotype("Cardotype",4.00,32,"Mpampis","Makrhs","69321278378","9/4",10.00 ,"cash","6","oikogeneiako",90,1000);
	contracts.add(px1);
	contracts.add(px2);
	contracts.add(px3);
	contracts.add(px4);
    contracts.add(px5);
	contracts.add(px6);
	
 public void addContracts (Contract s){//prosthhkh sumbolaiou
	contracts.add(s);
 
 
	}
	//methodos gia emfanish twn sumbolaiwn me sugkekrimeno tupo
	public String Type(int theType){
		for (int i=0; i<contracts.size();i++)
			if (contracts.get(i).getType().equals(theType)){
				System.out.println(contracts.get(i).print());
			}
				
				
		}
		//methodos gia emfanish ths listas ,epilogh sumbolaiou kai ananewsh twn statistikwn tou stoixeiwn
		public void update(int kwdikos,String theType1,String theType2,String newleptaomilias,String newSMS,String newogkos ){
			for(int i=0; i<contracts.size(); i++)
				System.out.println(contracts.get(i).print());
				if(contracts.get(i).getKwdikossumbolaiou().equals(kwdikos)){
					if (contracts.get(i).getType().equals(theType1)){
						contracts.get(i).setLeptaomilias(newleptaomilias);
						contracts.get(i).setSMS(newSMS);
					}
					
					else if(contracts.get(i).getType().equals(theType3)){
						contracts.get(i).setOgkos(newogkos);
					}
					
				}
				
		}
	
}
