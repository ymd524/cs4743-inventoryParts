package MVC.models;

public class addPartModel {
	
	private String num;
	private String name;
	private String vendor;
	private int quantity;
	private int id;
	private String ext;
	private String unit;
	private String location;
	

	public addPartModel(String num, String name, String vendor, String ext, String unit){

		this.num = num;
		this.name = name;
		this.vendor = vendor;
		this.quantity = quantity;
		this.id = id;
		this.unit = unit;
		this.ext = ext;
		this.location = location;
	}
	
	/*
	 * updates currentObject's values
	 */
	public void setNewName(String s){
		this.name = s;
	}
	
	public void setNewNum(String s){
		this.num = s;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setNewV(String s){
		this.vendor = s;
	}
	
	public void setNewQ(int s){
		this.quantity = s;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setNewExt(String s){
		this.ext = s;
	}
	

	/*
	 * getters
	 */
	public String getNum(){
		return this.num;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getVendor(){
		return this.vendor;
	}
	
	public int getQuantity(){
		return this.quantity;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public String getExt(){
		return this.ext;
	}
	public String getLocation() {
		return location;
	}

}
