public class Mobilephone extends Service{//programma sumbolaiou kai kartosumbolaia
	private double dwreanxronos;//koina stoixeia twn duo
	private int dwreansms;
	private double xrewshmetaxronos;
	private double xrewshmetasms;
	
	
	public Mobilephone(String onomauphresias,double mhniaiopagio,double sale,double dwreanxronos,int dwreansms,double xrewshmetaxronos,double xrewshmetasms){
		super(onomauphresias,mhniaiopagio,sale);
		this.dwreanxronos=dwreanxronos;
		this.dwreansms=dwreansms;
		this.xrewshmetaxronos=xrewshmetaxronos;
		this.xrewshmetasms=xrewshmetasms;
	}
	public double getDwreanxronos(){
		return dwreanxronos;
	}
	public void setDwreanxronos(){
		this.dwreanxronos=dwreanxronos;
		
	}
	public int getDwreansms(){
		
		return dwreansms;
	}
	public void setDwreansms(){
		this.dwreansms=dwreansms;
		
	}
	public double getXrewshmetaxronos(){
		return xrewshmetaxronos;
	}
	public void setXrewshmetaxronos(){
		this.xrewshmetaxronos=xrewshmetaxronos;
		
	}
	public double getXrewshmetasms(){
		return xrewshmetasms;
	}
	public void setXrewshmetasms(){
		
	this.xrewshmetasms=xrewshmetasms;}
	
	public void print(){
		System.out.println("dwrean xronos:" +getDwreanxronos() + "dwreansms" + getDwreansms() + "xrewshmetaxronos" + getXrewshmetaxronos() +"xrewshmetasms" + getXrewshmetasms();
		
}
}
