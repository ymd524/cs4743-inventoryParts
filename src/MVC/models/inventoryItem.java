package MVC.models;

public class inventoryItem {
	
	addPartModel part;
	String location;
	int quantity;
	
	public inventoryItem(addPartModel part, String location, int quantity){
		
		this.part = part;
		this.location = location;
		this.quantity = quantity;
		
	}
	
	public addPartModel getPart(){
		return this.part;
	}
	
	public String getLocation(){
		return this.location;
	}
	
	public int getQuantity(){
		return this.quantity;
	}
	

}
