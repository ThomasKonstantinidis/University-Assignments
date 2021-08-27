/*STAMATINA PAPAILIOU 4O EXAMHNO FOITHSHS P3150246
THWMAS KWNSTANTINIDHS 2O EXAMHNO FOITHSHS P3160074*/
//menu of services
import java.util.*;
public class mainApp{
	public static void main(String args[]){
	Scanner in=new Scanner(System.in);
	Collectionpar collectionpar=new Collectionpar();
	Collectionyp collectionyp=new Collectionyp();
	boolean flag=false;
	int codecontract=6;//arxikopoihsh sto dioti uparxoun hdh 6 energa sumbolaia
	
	
	while(!flag){
		System.out.println("1.OUR SERVICES:");//prwth epilogh h emfanish twn uphresiwn
		System.out.println("2.CONTRACT PROGRAM:");//dhmiourgia programmatos sumbolaiou
		System.out.println("3.CARDOTYPE:");//dhmiourgia kartosumbolaiou
		System.out.println("4.MOBILE INTERNET");//dhmiourgia sumbolaiou kinhtou internet
		System.out.println("5.CONTRACTS/TYPE");//emfanish listas energwn sumbolaiwn 
		System.out.println("6.UPDATING OF STATICAL DATA:");//enhmerwsh statistikwn stoixeiwn
		System.out.println("0.EXIT");//exodos
		
		int answer=in.nextLine();
		if (answer.equals("1"))//emfanish twn uphresiwn
		collectionyp.listServices();
	    else if (answer.equals("2")){//dexetai stoixeia gia thn dhmiourgia neou programmatos sumbolaiou
		    System.out.print("name of service?");
			String onomauphresias=in.nextLine();
			System.out.print("monthly fee?");
			double mhniaiopagio=in.nextDouble();
			System.out.print("sale:");
			int sale=in.nextInt();
			System.out.print("Name? ");
			String name=in.nextLine();
			System.out.print("Surname? ");
			String surname=in.nextLine();
			System.out.print("phone?");
			String phone=in.nextLine();
			System.out.print("date of active?");
			String hmeromhniaenergopoihshs=in.nextLine();
			System.out.print("cash or card ?");
			String troposexoflhshs=in.nextLine();
			System.out.print("extrasale ?");
			String extrasale=in.nextLine();
			int codecontract=codecontract+1;
			System.out.print("type of contract");
			String type=in.nextLine();
			double leptaomilias=0;
			int SMS=0;
			
			
			Collectionpar px6=new Collectionpar(onomauphresias,mhniaiopagio,sale,name,surname,phone,hmeromhniaenergopoihshs,troposexoflhshs,extrasale,codecontract,type,leptaomilias,SMS);
			collectionpar.addContracts(px6);//prosthhkh sthn lista twn energwn sumbolaiwn
		}
		else if (answer.equals("3")){//dexetai stoixeia gia thn dhmiourgia neou kartosumbolaiou
			   System.out.print("name of service?");
			String onomauphresias=in.nextLine();
			System.out.print("monthly fee?");
			double mhniaiopagio=in.nextDouble();
			System.out.print("sale:");
			int sale=in.nextInt();
			System.out.print("Name? ");
			String name=in.nextLine();
			System.out.print("Surname? ");
			String surname=in.nextLine();
			System.out.print("phone?");
			String phone=in.nextLine();
			System.out.print("date of active?");
			String hmeromhniaenergopoihshs=in.nextLine();
			System.out.print("card or cash ?");
			String troposexoflhshs=in.nextLine();
			System.out.print("extrasale ?");
			double extrasale=in.nextDouble();
			int codecontract=codecontract+1;
			System.out.print("type of contract");
			String type=in.nextLine();
			double leptaomilias=0;
			int SMS=0;
			
			
			Collectionpar px6=new Collectionpar(onomauphresias,mhniaiopagio,sale,name,surname,phone,hmeromhniaenergopoihshs,troposexoflhshs,double extrasale,int codecontract,String type,leptaomilias,SMS);
			collectionpar.addContracts(px6);//prosthhkh neou kartosumbolaiou sthn lista me ta energa sumbolaia
				
			
		}
		else if (answer.equals("4")){
		   System.out.print("name of service?");
			String onomauphresias=in.nextLine();
			System.out.print("monthly fee?");
			double mhniaiopagio=in.nextDouble();
			System.out.print("sale:");
			double sale=in.nextDouble();
			System.out.print("Name? ");
			String name=in.nextLine();
			System.out.print("Surname? ");
			String surname=in.nextLine();
			System.out.print("phone?");
			String phone=in.nextLine();
			System.out.print("date of active:");
			String hmeromhniaenergopoihshs=in.nextLine();
			System.out.print("card or cash?");
			String troposexoflhshs=in.nextLine();
			System.out.print("extrasale ?");
			double extrasale=in.nextDouble();
			int codecontract=codecontract+1;
			System.out.print("type of contract");
			String type=in.nextLine();
			int ogkos=0;
			
			
			Collectionpar px6=new Collectionpar(onomauphresias,mhniaiopagio,sale,name,surname,phone,hmeromhniaenergopoihshs,troposexoflhshs,extrasale,codecontract,type,ogkos);
			collectionpar.addContracts(px6);
			
		}
		else if (answer.equals("5")) {//dexetai enan tupo sumbolaiou kai emfanizei ta sumbolaia me ton tupo afto
			System.out.print("Type?");
			String type=in.nextLine();
			collectionpar.Type(type);
			
			
			
		}
		else if (answer.equals("6")){
			System.out.print("codecontract?");//emfanish ths sulloghs kai ananewsh twn statistikwn stoixeiwn 
			int kwdikossumbolaiou=in.nextInt();
			System.out.print("type?//1.mobilephone 3.mobileinternet");
			String type1="mobilephone";
			String type2="mobileinternet";
			System.out.print("newfreetime");
			int newlepta=in.nextInt();
			System.out.print("newSMS");
			int newsms=in.nextInt();
			System.out.print("newdata");
			int newogkos=in.nextInt();
			collectionpar.update(kwdikossumbolaiou,type1,type2,newlepta,newsms,newogkos);
			
				
			
			
		}
		else if( answer.equals("0")) flag=true;//exodos
		

		
			
		}
		
	}
}