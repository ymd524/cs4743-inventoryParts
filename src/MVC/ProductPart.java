package MVC;

public class ProductPart {

	private int id;
	private int quantity;
	private String partNumber;
	private String partName;
	private String vendor;
	private String unit;
	private String extPartNumber;
	
	public ProductPart(int id, String num, String name, String vendor, String unit, String ext){
		this.id = id;
		this.partNumber = num;
		this.partName = name;
		this.vendor = vendor;
		this.unit = unit;
		this.extPartNumber = ext;
		this.quantity = quantity;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getPartNumber(){
		return this.partNumber;
	}
	
	public String getPartName(){
		return this.partName;
	}
	
	public String getVendor(){
		return this.vendor;
	}
	
	public String getUnit(){
		return this.unit;
	}
	
	public String getExt(){
		return this.extPartNumber;
	}
	
	/*public int getQuantity(){
		return this.quantity;
	}*/
}
