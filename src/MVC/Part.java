package MVC;

public class Part {
	
	private int id;
	private String num;
	private String name;
	private String vendor;
	private String unit;
	private String ext;

	public Part(int partId, String partNum, String partName, String vendor, String unit, String ext){
		id =  partId;
		num = partNum;
		name = partName;
		this.vendor = vendor;
		this.unit = unit;
		this.ext = ext;
	}
	
	public int getId(){
		return id;
	}
	
	public String getNum(){
		return num;
	}
	
	public String getName(){
		return name;
	}
	
	public String getVendor(){
		return vendor;
	}
	
	public String getUnit(){
		return unit;
	}
	
	public String getExt(){
		return ext;
	}
	
}
