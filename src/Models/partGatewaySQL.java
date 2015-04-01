package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import MVC.Part;
import MVC.ProductPart;

public class partGatewaySQL {

	private Connection conn;
	private ResultSet rs;
	private ArrayList<String> AL = new ArrayList();
	private ResultSet results;
	private String partName;
	private String partNum;
	private String vendor;
	private String unit;
	private String ext;
	private Part part;
	private int partId;
	
	public partGatewaySQL(String dbName, String uname, String pwd){
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
	
	public void addPart(String partNum, String partName, String vendor, String unit, String extNum){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO parts" + "(partNumber, partName, vendor, unit, extPartNumber) VALUES" + "(?, ?, ?, ?, ?)" );
            stmt.setString(1, partNum);
            stmt.setString(2, partName);
            stmt.setString(3, vendor);
            stmt.setString(4, unit);
            stmt.setString(5, extNum);
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
	
	public void deletePartById(int id){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM parts WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
	
	public ResultSet getPartByName(String name){
		
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM parts WHERE partName = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return rs;
	}
	
	public Part getPartObjectByName(String name){
		part = null;
		try{
			results = getPartByName(name);
			if(!results.next()){
				return part;
			}else{
				results.first();
				partNum = results.getString("partNumber");
				partName = results.getString("partName");
				vendor = results.getString("vendor");
				unit = results.getString("unit");
				ext = results.getString("extPartNumber");
				partId = results.getInt("id");
				part = new Part(partId, partNum, partName, vendor, unit, ext);
			}		
		}catch(SQLException e){
			e.printStackTrace();
		}
		return part;
	}
	
	
	
}
