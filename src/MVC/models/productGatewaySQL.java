package MVC.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class productGatewaySQL {
	
	private Connection conn;
	private ResultSet rs;
	PreparedStatement sRS;
	
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
	
	public void addProduct(String productNum, String productDesc){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO products" + "(productNum, productDesc) VALUES" + "(?, ?)" );
            stmt.setString(1, productNum);
            stmt.setString(2, productDesc);
           // stmt.setDate(3, dateAdded);
          // stmt.setDate(3, lastModified);
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
	
	public void updateProduct(String productNum, String productDesc){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE products" + "(productNum, productDesc) VALUES" + "(?, ?) WHERE productNum = ?" );
            stmt.setString(1, productNum);
            stmt.setString(2, productDesc);
           // stmt.setDate(3, dateAdded);
          // stmt.setDate(3, lastModified);
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
	
	public ResultSet getAllProducts(){
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement("Select * FROM products");
			rs = stmt.executeQuery();
			rs.first();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getAllParts(){
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement("Select * FROM parts");
			rs = stmt.executeQuery();
			rs.first();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public String getPartNameById(int id){
		PreparedStatement stmt = null;
		String num = null;
		try{
			stmt = conn.prepareStatement("Select * FROM parts WHERE id = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			rs.first();
			num = rs.getString("partName");
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return num;
	}

	

	public ResultSet getAllProductParts(int id){
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement("Select * FROM products_parts WHERE productId = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			rs.first();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getPartsById(){
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement("Select * FROM products");
			rs = stmt.executeQuery();
			rs.first();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getProductById(){
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement("Select * FROM products");
			rs = stmt.executeQuery();
			rs.first();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getPartByName(String name){
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement("Select * FROM parts WHERE partName = ?");
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			rs.first();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public void addProductPart(int id, int productId, int q){
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement("INSERT INTO products_parts" + "(partId, productId, quantity) VALUES" + "(?, ?, ?)" );
		    stmt.setInt(1, id);
		    stmt.setInt(2, productId);
		    stmt.setInt(3, q);
			stmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void deleteProductPart(int partId,int productId){
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement("DELETE FROM products_parts WHERE partId = ? AND productId = ?" );
		    stmt.setInt(1, partId);
		    stmt.setInt(2, productId);
			stmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public ResultSet getProductByNum(String num){
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement("SELECT * FROM products WHERE productNum = ?");
	        stmt.setString(1, num);
			rs = stmt.executeQuery();
			rs.first();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}

}
