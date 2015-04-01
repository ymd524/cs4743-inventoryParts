package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import MVC.Part;

public class inventoryGatewaySQL {
	
	private Connection conn;
	private ResultSet rs;
	private ArrayList<String> locationsArray = new ArrayList();
	private ArrayList<Integer> partsIds = new ArrayList();
	private ArrayList<InventoryItem> inventoryItemObjectList = new ArrayList();
	private InventoryItem inventoryObject;
	private ResultSet results;
	private String locationName;
	private int itemId;
	private int productPartId;
	private int quantity;
	private int locationId;
	private String name;
	private String location;
	private String type;
	private String partName;
	private String productName;

	
	public inventoryGatewaySQL(String dbName, String uname, String pwd){
		String url = "jdbc:mysql://devcloud.fulgentcorp.com:3306/" + dbName;
		
		//set up the driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		//System.out.println("MySQL JDBC Driver Registered!");
		
		try {
			this.conn = DriverManager.getConnection(url,uname, pwd);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public ResultSet getLocationByName(String name){
		
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM locations WHERE name = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return rs;
	}
	
	public void setNewInventoryQuantity(int locationId, int partId, String type, int newQuantity){
			PreparedStatement stmt = null;
	        try {
	            stmt = conn.prepareStatement("UPDATE inventoryItems SET quantity = ? WHERE locationId = ? AND productPartId = ? AND type = ?");
	            stmt.setInt(1, newQuantity);
	            stmt.setInt(2,locationId);
	            stmt.setInt(3, partId);
	            stmt.setString(4, type);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
	
	public ResultSet getQuantity(int productId, int partId){
		
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM products_parts WHERE productId = ? AND partId = ?");
            stmt.setInt(1, productId);
            stmt.setInt(2, partId);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return rs;
	}
	
	public int getQuantityRequired(int productId, int partId){
		int q = 0;
		
		try{
			results = getQuantity(productId, partId);
			results.first();
			q = results.getInt("quantity");		
		}catch(SQLException e){
			e.printStackTrace();
		}
		return q;
	}
	
	public ResultSet getInventoryQuantity(int productPartId, int locationId, String type){
		
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM inventoryItems WHERE productPartId = ? AND locationId = ? AND type = ?");
            stmt.setInt(1, productPartId);
            stmt.setInt(2, locationId);
            stmt.setString(3, type);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return rs;
	}
	
	public int getQuantityInStock(int productPartId, int locationId, String type){
		int q = 0;
		
		try{
			results = getInventoryQuantity(productPartId, locationId, type);
			if(!results.next()){
				return q;
			}
			results.first();
			q = results.getInt("quantity");		
		}catch(SQLException e){
			e.printStackTrace();
		}
		return q;
	}
	
	
	public int getLocationIdByName(String name){
		locationId = 0;
		
		try{
			results = getLocationByName(name);
			results.first();
			locationId = results.getInt("id");		
		}catch(SQLException e){
			e.printStackTrace();
		}
		return locationId;
	}
	
	public void addInventoryItem(int productPartId, int locationId, int quantity, String type){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO inventoryItems" + "(productPartId, locationId, quantity, type) VALUES" + "(?, ?, ?, ?)");
            stmt.setInt(1, productPartId);
            stmt.setInt(2, locationId);
            stmt.setInt(3, quantity);
            stmt.setString(4, type);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	try {
        		if(stmt != null)
        			stmt.close();
        	} catch(SQLException e) {
        		e.printStackTrace();
        	}
        }		
	}
	
	public void deleteInventoryItemById(int id){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM inventoryItems WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	
	
	public ResultSet getAllLocations(){
		PreparedStatement stmt = null;
	       try {
	            stmt = conn.prepareStatement("SELECT * FROM locations");
	            rs = stmt.executeQuery();
	            rs.first();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			
			return rs;
	}
	
	
	
	
	public ArrayList getLocationsArrayList(){
		try{
			results = getLocationNameResultsById(locationId);
			locationName = results.getString("name");
			locationsArray.add(locationName);
			while(results.next()){
				locationName = results.getString("name");
				locationsArray.add(locationName);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return locationsArray;
	}
	
	public ResultSet getProductNameResultsById(int productId){
		PreparedStatement stmt = null;
	       try {
	            stmt = conn.prepareStatement("SELECT * FROM products WHERE id = ?");
	            stmt.setInt(1, productId);
	            rs = stmt.executeQuery();
	            rs.first();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			
			return rs;
	}
	public ResultSet getPartNameResultsById(int partId){
		PreparedStatement stmt = null;
	       try {
	            stmt = conn.prepareStatement("SELECT * FROM parts WHERE id = ?");
	            stmt.setInt(1, partId);
	            rs = stmt.executeQuery();
	            rs.first();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			
			return rs;
	}
	public ResultSet getLocationNameResultsById(int locationId){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM locations WHERE id = ?");
            stmt.setInt(1, locationId);
            rs = stmt.executeQuery();
            rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return rs;
	}
	
	
	public String getProductNameById(int productId){
		try{
			results = getProductNameResultsById(productId);
			productName = results.getString("productDesc");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return productName;
	}
	public String getPartNameById(int partId){
		try{
			results = getPartNameResultsById(partId);
			partName = results.getString("partName");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return partName;
	}
	public String getLocationNameById(int locationId){
		try{
			results = getLocationNameResultsById(locationId);
			locationName = results.getString("name");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return locationName;
	}
	
	public ResultSet getAllInventoryItems(){
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM inventoryItems");
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ArrayList getInventoryItemArrayList(){
		inventoryItemObjectList = new ArrayList();
		try{
			results = getAllInventoryItems();
			if(!results.next()){
				return inventoryItemObjectList;
			}else{
				results.first();
				itemId = results.getInt("id");
				productPartId = results.getInt("productPartId");
				locationId = results.getInt("locationId");
				quantity = results.getInt("quantity");
				type = results.getString("type");
				if(type.equals("product")){
					name = getProductNameById(productPartId);
				}else if(type.equals("part")){
					name = getPartNameById(productPartId);
				}
				location = getLocationNameById(locationId);
				inventoryObject = new InventoryItem(itemId, productPartId, quantity, name, location, type);			
				inventoryItemObjectList.add(inventoryObject);
				
				while(results.next()){
					itemId = results.getInt("id");
					productPartId = results.getInt("productPartId");
					locationId = results.getInt("locationId");
					quantity = results.getInt("quantity");
					type = results.getString("type");
					if(type.equals("product")){
						name = getProductNameById(productPartId);
					}else if(type.equals("part")){
						name = getPartNameById(productPartId);
					}
					location = getLocationNameById(locationId);
					inventoryObject = new InventoryItem(itemId, productPartId, quantity, name, location, type);			
					inventoryItemObjectList.add(inventoryObject);	
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		//System.out.println(inventoryItemObjectList.size());	
		return inventoryItemObjectList;
	}
	
}
