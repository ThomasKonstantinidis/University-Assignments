//kinhto Internet
public class Mobileinternet extends Service{
	private int mb;
	private double xrewshmetaogko;

	public Mobileinternet(String onomauphresias,double mhniaiopagio,double sale,int mb,double xrewshmetaogko){
		super(onomauphresias,mhniaiopagio,sale);
		this.mb=mb;
		this.xrewshmetaogko=xrewshmetaogko;
	}
	
	public int getMb(){
		return mb;
	}
	public void setMb(){
		this.mb=mb;
		
	}
	public double getXrewshmetaogko(){
		return xrewshmetaogko;
	}
	public void setXrewshmetaogko(){
		this.xrewshmetaogko=xrewshmetaogko;
	}
public void print(){
		System.out.println("mb:" +getMb() + "xrewshmetaogko" + getXrewshmetaogko() );
		super.print();
}
}