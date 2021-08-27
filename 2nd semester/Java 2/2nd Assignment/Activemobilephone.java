public class Activemobilephone extends Contract {//energa sumbolaia kinhths thlefwnias
	private double leptaomilias;
	private int SMS;
	double sunolo;
	int dwreanupoloipo;
	
	public Activemobilephone(String onomauphresias,double mhniaiopagio,double sale,String name,String surname,String phone,String hmeromhniaenergopoihshs,String troposexoflhshs,double extrasale,int kwdikossumbolaiou,String type,double leptaomilias,int SMS){
		super(onomauphresias,mhniaiopagio,sale,name,surname,phone,hmeromhniaenergopoihshs,troposexoflhshs,extrasale,kwdikossumbolaiou,type);
		this.leptaomilias=leptaomilias;
		this.SMS=SMS;
		
	}
	
	
		
	public double getLeptaomilias(){
		return leptaomilias;
	}
	public void setLeptaomilias(){
		this.leptaomilias=leptaomilias;
	}

	public int getSMS(){
		return SMS;
	}
	public void setSMS(){
		this.SMS=SMS;
	}

	public double Sunololepta(double sunolo,int dwreanupoloipo){//upologismos sunolikou posou kai upoloipou
		
		if (leptaomilias>dwreanxronos){
			double k=leptaomilias-dwreanxronos;
			double l=k*xrewshmetaxronos;
			sunolo=l-(l*sale)-(l*extrasale);
			
		}
		else if(leptaomilias<=dwreanxronos){
			dwreanupoloipo=dwreanxronos-leptaomilias;
			
		    sunolo=leptaomilias-(leptaomilias*sale)-(leptaomilias*extrasale);
			
		}
	return sunolo;}
	
	public int Sunolosms(int sunolo1,int dwreanupoloipo1){
		if (SMS>dwreansms){
			double k=SMS-dwreansms;
			double l=k*xrewshmetasms;
			sunolo1=l-(l*sale)-(l*extrasale);
			
		}
		else if (SMS<=dwreansms){
			dwreanupoloipo1=dwreansms-SMS;
			sunolo1=dwreansms-(dwreansms*sale)-(dwreansms*extrasale);
			
		}
		
		
		return sunolo1;
	}

	
	public void print(){
		System.out.println("leptaomilias:" + getLeptaomilias() + "SMS" + getSMS());
		super.print();
}
}
