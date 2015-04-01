package Models;

public class InventoryItem {
	
	private int itemId;
	private int partId;
	private int quantity; 
	private String name;
	private String location;
	private String type;
	private String listText;
	
	public InventoryItem(int itemId, int partId, int quantity, String name, String location, String type){
		this.itemId = itemId;
		this.partId = partId;
		this.quantity = quantity;
		this.name = name;
		this.location = location;
		this.type = type;
		this.listText = toString();
	}
	
	
	public int getItemId(){
		return this.itemId;
	}
	
	public int getPartId(){
		return this.partId;
	}
	
	public int getQuantity(){
		return this.quantity;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getLocation(){
		return this.location;
	}
	
	public String getType(){
		return this.type;
	}
	
	public String getListText(){
		return listText;
	}
	
	public String toString(){
		listText = ("Inventory Item #: " + itemId + " ID #: " + partId + " Name: " + name
				+ " Type: " + type + " Location: " + location);
		return listText;
	}
}
