package Models;

import java.util.ArrayList;

import MVC.Session;

public class InventoryModel {
	private ArrayList<InventoryItem> inventoryArrayList = new ArrayList();
	private ArrayList<String> locationsArrayList = new ArrayList();
	private ArrayList<Integer> partIdsList = new ArrayList();
	public inventoryGatewaySQL gateway = new inventoryGatewaySQL("ymd524", "ymd524", "HRqEF9KWp7MFw04SR0zZ");
	private String location;
	private int locationId;
	private int quantity;
	private InventoryItem item;
	private Session session;
	
	public InventoryModel(){
		
	}
	
	public void addInventoryItem(int productPartId, int locationId, int quantity, String type){
		gateway.addInventoryItem(productPartId, locationId, quantity, type);
	}
	
	public int getLocationIdByName(String name){
		return gateway.getLocationIdByName(name);
	}
	
	public void deleteInventoryItemById(int id){
		gateway.deleteInventoryItemById(id);
	}
	
	public void endSession(){
		this.session = null;
	}
	
	public void setSession(Session session){
		this.session = session;
	}
	
	public void setInventoryArrayList(){
		inventoryArrayList = gateway.getInventoryItemArrayList();
	}
	
	public ArrayList getInventoryArrayList(){
		return inventoryArrayList;
	}
	
	public void setLocationsArrayList(){
		locationsArrayList = gateway.getLocationsArrayList();
	}
	
	public ArrayList getLocationsArrayList(){
		return locationsArrayList;
	}
	
	public void setInventoryItem(InventoryItem item){
		this.item = item;
	}
	
	public void setNewInventoryQuantity(int locationId, int partId, String type, int newQuantity){
		gateway.setNewInventoryQuantity(locationId, partId, type, newQuantity);
	}
	
	public InventoryItem getInventoryItem(){
		return this.item;
	}
	
	public int getRequiredQuantity(int productId, int partId){
		int quantity = gateway.getQuantityRequired(productId, partId);
		return quantity;
	}
	
	public int getQuantityInStock(int productPartId, int locationId, String type){
		quantity = gateway.getQuantityInStock(productPartId, locationId, type);
		return quantity;
	}
	
	
}
