//stoixeia uphresiwn
public class Service{
	
	private String onomauphresias;
	private double mhniaiopagio;
	private double sale;
	

	
	public Service(String onomauphresias,double mhniaiopagio,double sale){
		this.onomauphresias=onomauphresias;
		this.mhniaiopagio=mhniaiopagio;
		this.sale=sale;
		
		
	}
	public String getOnomauphresias(){
		return onomauphresias;
	}
	
	public double getMhniaiopagio(){
		return mhniaiopagio;
		
	}
	
	
	public double getSale(){
		return sale;
		
	}
	
	public void print(){
		System.out.println("onomauphresias:" + getOnomauphresias()+ "mhniaiopagio" + getMhniaiopagio()+ "sale:" + getSale() );
	
	}
}