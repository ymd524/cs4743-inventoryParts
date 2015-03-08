package MVC.models;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.sql.DataSource;

/**
 * @author ymd524
 * Helper Class to connect and read from my db
 */
public class gatewaySQL {
	
	private Connection conn;
	private ResultSet rs;
	PreparedStatement sRS;
	
	public gatewaySQL(String dbName, String uname, String pwd){
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
	
	public void deletePart(int id){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM parts WHERE id = ?");
            stmt.setInt(1, id);
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
	
	public void deleteInvItem(int id){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM inventoryItems WHERE partId = ?");
            stmt.setInt(1, id);
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
	
	public void updatePart(String partNum, String partName, String vendor, String unit, String extNum) {
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE parts SET partNumber = ?, partName = ?, vendor = ?, unit = ?, extPartNumber = ? WHERE id = ?");
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
	
	public void updatePartName(String partName, int id){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE parts SET partName = ? WHERE id = ?");
            stmt.setString(1, partName);
            stmt.setInt(2, id);
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
	
	public void updatePartNumber(String partNumber, int id){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE parts SET partNumber = ? WHERE id = ?");
            stmt.setString(1, partNumber);
            stmt.setInt(2, id);
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
	
	public void updateVendor(String vendor, int id){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE parts SET vendor = ? WHERE id = ?");
            stmt.setString(1, vendor);
            stmt.setInt(2, id);
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
	
	public void updateUnit(String unit, int id){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE parts SET unit = ? WHERE id = ?");
            stmt.setString(1, unit);
            stmt.setInt(2, id);
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
	
	public void updateExtPartNumber(String extPartNumber, int id){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE parts SET extPartNumber = ? WHERE id = ?");
            stmt.setString(1, extPartNumber);
            stmt.setInt(2, id);
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
	
	public void updateInvName(String name, int id) {//partId int as string sent
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE parts SET partName = ? WHERE id = ?");
            stmt.setString(1, name);
            stmt.setInt(2, id);
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

	public void updateInvL(String location, int id) {//locationId sent not name
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int val = Integer.parseInt(location);
        try {
            stmt = conn.prepareStatement("UPDATE parts SET locationId = ? WHERE id = ?");
            stmt.setInt(1, val);
            stmt.setInt(2, id);
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

	public void updateInvQ(String quantity, int id) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int val = Integer.parseInt(quantity);
        try {
            stmt = conn.prepareStatement("UPDATE inventoryItems SET quantity = ? WHERE id = ?");
            stmt.setInt(1, val);
            stmt.setInt(2, id);
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

	public void addInventoryItem(int partId, int locationId, int quantity){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO inventoryItems" + "(partId, locationId, quantity) VALUES" + "(?, ?, ?)");
            stmt.setInt(1, partId);
            stmt.setInt(2, locationId);
            stmt.setInt(3, quantity);
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
	
	public void deleteInventoryItem(int id){
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM inventoryItems WHERE id = ?");
            stmt.setInt(1, id);
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
	
	
	
	public void updateInventoryItem(int id, int quantity) {
		PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE inventoryItems SET quantity = ? WHERE id = ?");
            stmt.setInt(1, quantity);
            stmt.setInt(2, id);
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
	
	public ResultSet getAllParts(){
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement("SELECT * FROM parts");
			rs = stmt.executeQuery();
			rs.first();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public ResultSet getAllLocations(){
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement("SELECT * FROM locations");
			rs = stmt.executeQuery();
			rs.first();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public ResultSet getAllUnits(){
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement("SELECT * FROM units");
			rs = stmt.executeQuery();
			rs.first();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public String getLocationByName(String name){
		PreparedStatement stmt = null;
		String str = null;
		try{
			stmt = conn.prepareStatement("SELECT * FROM locations where name = ?");
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			rs.first();
			str = rs.getString("id");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return str;
	}
	public String getLocationById(int id){
		PreparedStatement stmt = null;
		String str = null;
		try{
			stmt = conn.prepareStatement("SELECT * FROM locations where id = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			rs.first();
			str = rs.getString("name");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return str;
	}
	
	
	public ResultSet getAllInventoryItems(){
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement("Select * FROM inventoryItems");
			rs = stmt.executeQuery();
			rs.first();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public String getPartNameById(int i){
		PreparedStatement stmt = null;
		String str = null;
		try{
			stmt = conn.prepareStatement("SELECT partName FROM parts WHERE id = ? ");
			stmt.setInt(1, i);
			rs = stmt.executeQuery();
			rs.first();
			str = rs.toString();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return str;
	}
	
	public ResultSet getPartByName(String name){
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement("SELECT * FROM parts WHERE partName = ? ");
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			rs.first();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public ResultSet getPartsByLocation(String loc){
		PreparedStatement stmt = null;
		String str = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM inventoryItems WHERE locationId = ? ");
			stmt.setString(1, loc);
			rs = stmt.executeQuery();
			rs.first();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getPartName(int i){
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement("SELECT partName FROM parts WHERE id = ? ");
			stmt.setInt(1, i);
			rs = stmt.executeQuery();
			rs.first();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return rs;
	}
	public ResultSet getInvByPartId(String part){
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM inventoryItems WHERE partId = ? ");
			stmt.setString(1, part);
			rs = stmt.executeQuery();
			rs.first();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}