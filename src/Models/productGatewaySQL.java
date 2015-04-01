package Models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import MVC.Product;
import MVC.ProductPart;
import MVC.User;


public class productGatewaySQL {
	
	private Connection conn;
	private ResultSet rs;
	PreparedStatement sRS;
	ArrayList<String> AL = new ArrayList();
	ArrayList<Integer> list = new ArrayList();
	String productNum;
	String productDesc;
	String partName;
	String partNum;
	String vendor;
	String unit;
	String ext;
	String email; 
	String name;
	String role;
	String pw;
	int userId;
	int productId;
	int partId;
	int partQuantity;
	Timestamp timestamp;
	private ResultSet results;
	private Product product;
	private ProductPart productPart;
	private User user;
	
	public productGatewaySQL(String dbName, String uname, String pwd){
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
	
	public ResultSet getAllProducts(){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM products");
            rs = stmt.executeQuery();
            rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
	}
	
	public ResultSet getAllParts(){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM parts");
            rs = stmt.executeQuery();
            rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
	}
	public int addProductGetId(String num, String desc){
		addProduct(num, desc);
		int id = 0;
		try{
			results = getProductIdByNum(num);
			id = results.getInt("id");

		}catch(SQLException e){
			e.printStackTrace();
		}
		return id;
	}

	public void addProduct(String productNum, String productDesc){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO products" + "(productNum, productDesc) VALUES" + "(?, ?)" );
            stmt.setString(1, productNum);
            stmt.setString(2, productDesc);
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
	
	public ResultSet getProductIdByNum(String num){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM products WHERE productNum  = ?");
            stmt.setString(1, num);
            rs = stmt.executeQuery();
            rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return rs;		
	}
	
	public void addProductParts(int partId, int productId){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO products_parts" + "(productId, partId, quantity) VALUES" + "(?, ?, ?)" );
            stmt.setInt(1, productId);
            stmt.setInt(2, partId);
            stmt.setInt(3,0);
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
	
	public ArrayList getAllProductParts(int id){
		AL = new ArrayList();
		try{
			results = getProductPartsIdList(id);
			if(!results.next()){
				return AL;
			}
			results.first();
			partId = results.getInt("partId");
			AL.add(getPartName(partId));
			while(results.next()){
				partId = results.getInt("partId");
				AL.add(getPartName(partId));			
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return AL;
	}
	
	public ResultSet getProductPartsIdList(int id){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM products_parts WHERE productId = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            //rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
	}
	
	public String getPartName(int id){
		ResultSet results;
		try{
			results = getPartById(id);
			partName = results.getString("partName");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return partName;
	}
	
	public ResultSet getPartById(int id){
		
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM parts WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return rs;
	}
	
	public int getPartId(String name){
		int i  = 0;
		try{
			results = getPartIdByName(name);
			i = results.getInt("id");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return i;
	}
	
	public ResultSet getPartIdByName(String name){
		
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM parts WHERE partName = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return rs;
	}
	
	
	
	public ArrayList getProductsArrayList(){
		AL = new ArrayList();
		try{
			results = getAllProducts();
			productNum = results.getString("productNum");
			AL.add(productNum);
			while(results.next()){
				productNum = results.getString("productNum");
				AL.add(productNum);				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return AL;
	}
	
	public ArrayList getPartsArrayList(){
		AL = new ArrayList();
		try{
			results = getAllParts();
			partName = results.getString("partName");
			AL.add(partName);
			while(results.next()){
				partName = results.getString("partName");
				AL.add(partName);			
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return AL;
	}
	
	
	public ResultSet getProductByNum(String num){
		
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM products WHERE productNum = ?");
            stmt.setString(1, num);
            rs = stmt.executeQuery();
            rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return rs;
	}
	
	public void updateProductNum(int id, String num){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE products SET productNum = ? WHERE id = ?");
            stmt.setString(1, num);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void updateProductDesc(int id, String desc){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE products SET productDesc = ? WHERE id = ?");
            stmt.setString(1, desc);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void updateProductPartQuantity(int productId, int partId, int q){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE products_parts SET quantity = ?  WHERE productId = ? AND partId = ?");
            stmt.setInt(1, q);
            stmt.setInt(2, productId);
            stmt.setInt(3, partId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public Product getProductObjectByNum(String num){
		try{
			results = getProductByNum(num);
			productNum = results.getString("productNum");
			productDesc = results.getString("productDesc");
			productId = results.getInt("id");
			timestamp = results.getTimestamp("lastModified");
			product = new Product(productNum, productDesc, productId, timestamp);

		}catch(SQLException e){
			e.printStackTrace();
		}
		return product;
	}
	
	public ResultSet getProductPartByName(String name){
		
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM parts WHERE partName = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return rs;
	}
	
	public ProductPart getProductPartObjectByName(String name){
		try{
			results = getProductPartByName(name);
			partNum = results.getString("partNumber");
			partName = results.getString("partName");
			vendor = results.getString("vendor");
			unit = results.getString("unit");
			ext = results.getString("extPartNumber");
			partId = results.getInt("id");
			
			productPart = new ProductPart(partId, partNum, partName, vendor, unit, ext);
				
		}catch(SQLException e){
			e.printStackTrace();
		}
		return productPart;
	}
	
	public void deleteProductById(int id){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM products WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void deleteProductFromProductPartsById(int id){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM products_parts WHERE productId = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void deletePartFromProductPartsById(int id){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM products_parts WHERE partId = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public ResultSet getUserObjectByEmail(String email){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM users WHERE email  = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            //rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return rs;		
	}
	
	public ResultSet getPartQuantity(int productId, int partId){
		int q = 0;
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM products_parts WHERE productId = ? AND partId = ?");
            stmt.setInt(1, productId);
            stmt.setInt(2, partId);
            rs = stmt.executeQuery();
            rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }

		return rs;
	}
	
	public int getQuantity(int productId, int partId){
		int q = 0;
		try{
			results = getPartQuantity(productId, partId);
			q = results.getInt("quantity");
				
		}catch(SQLException e){
			e.printStackTrace();
		}
		return q;
	}
	
	public User getUserObject(String email){
		try{
			results = getUserObjectByEmail(email);
			if(!results.next()){
				user = null;
				return user;
			}else{
				results.first();
				this.email = email;
				name = results.getString("fullName");
				role = results.getString("role");
				userId = results.getInt("id");
				pw = results.getString("password");
				user = new User(name, email, role, userId, pw);
			}		
		}catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}

}