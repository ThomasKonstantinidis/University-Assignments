public class Activemobileinternet extends Contract{//energa sumbolaia kinhtou internet

	private int ogkos;
	
	public Activemobileinternet(String onomauphresias,double mhniaiopagio,double sale,String name,String surname,String phone,String hmeromhniaenergopoihshs,String troposexoflhshs,double extrasale,int kwdikossumbolaiou,String type,int ogkos){
		super(onomauphresias,mhniaiopagio,sale,name,surname,phone,hmeromhniaenergopoihshs,troposexoflhshs,extrasale,kwdikossumbolaiou,type);
		
		
	}

	public int getOgkos(){
		return ogkos;
	}
	public void setOgkos(){
		this.ogkos=ogkos;
	}
	

	public void print(){
	System.out.println("ogkos" + getOgkos());
	super.print();}
}