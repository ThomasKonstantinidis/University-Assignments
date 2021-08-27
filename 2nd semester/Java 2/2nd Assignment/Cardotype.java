//kartosumbolaio
//exei ta stoixeia ths kihths thlefwnias kai epipleon mhniaiodiathesimosexrono

public class Cardotype extends Mobilephone{
	private double mhniaiodiathesimosexrono;
	
	
	public Cardotype(String onomauphresias,double mhniaiopagio,double sale,double dwreanxronos,int dwreansms,double xrewshmetaxronos,double xrewshmetasms){
		super(onomauphresias,mhniaiopagio,sale,dwreanxronos,dwreansms,xrewshmetaxronos,xrewshmetasms);
		this.mhniaiodiathesimosexrono=mhniaiodiathesimosexrono;
		
	}
	
	public double getMhniaiodiathesimosexrono(){
		return mhniaiodiathesimosexrono;
	}
	public void setMhnaiodiathesimosexrono(){
		this.mhniaiodiathesimosexrono=mhniaiodiathesimosexrono;
		
	}
	

	public void print(){
		System.out.println("mhniaiodiathesimosexrono:" +getMhniaiodiathesimosexrono() );
		super.print();
}
}