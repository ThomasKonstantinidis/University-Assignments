
//stoixeia kathe sumbolaiou
public class Contract extends Service {
	
	private String name;
	private String surname;
	private String phone;
	private String hmeromhniaenergopoihshs;
	private String troposexoflhshs;
	private double extrasale;
	private int kwdikossumbolaiou;
	private String type;
	
	
	
	public Contract(String onomauphresias,double mhniaiopagio,double sale,String name,String surname,String phone,String hmeromhniaenergopoihshs,String troposexoflhshs,double extrasale,int kwdikossumbolaiou,String type){
		super(onomauphresias,mhniaiopagio,sale);
		this.name=name;
		this.surname=surname;
		this.phone=phone;
		this.hmeromhniaenergopoihshs=hmeromhniaenergopoihshs;
		this.troposexoflhshs=troposexoflhshs;
		this.extrasale=extrasale;
		this.kwdikossumbolaiou=kwdikossumbolaiou;
		this.type=type;
		
		
		
	}
	
	public String getName(){
		return name;
	}
	public String getSurname(){
		return surname;
	}
	public String getPhone(){
		return phone;
	}
	
	public String getHmeromhniaenergopoihshs(){
		return hmeromhniaenergopoihshs;
	}
	public String getTroposexoflhshs(){
		return troposexoflhshs;
	}
	public double getExtrasale(){
		return extrasale;
	}
	public void setExtrasale(){
		this.extrasale=extrasale;
	}

	public int getKwdikossumbolaiou(){
		return kwdikossumbolaiou;
		
	}
	public String getType(){
		return type;
	}
	
	public void print(){
		System.out.println("name:" + getName() + "surname" + getSurname() + "phone" +getPhone() + "hmeromhniaenergopoihshs" +getHmeromhniaenergopoihshs() + "troposexoflhshs" +getTroposexoflhshs() + "extrasale" + getExtrasale()+ "kwdikossumbolaiou" +getKwdikossumbolaiou() );
	super.print();} 
}