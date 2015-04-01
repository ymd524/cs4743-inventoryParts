package Models;

import java.util.ArrayList;

import MVC.Part;
import MVC.Session;

public class PartModel {
	
	private Session session;
	private ArrayList partArrayList = new ArrayList();
	public partGatewaySQL gateway = new partGatewaySQL("ymd524", "ymd524", "HRqEF9KWp7MFw04SR0zZ");
	private Part part;
	
	public PartModel(){
		
	}
	
	public void addPart(String num, String name, String vendor, 
			String ext, String unit){
		
		gateway.addPart(num, name, vendor, unit, ext);	
	}		
	
	public void endSession(){
		this.session = null;
	}
	
	public Session getSession(){
		return this.session;
	}
	
	public void setSession(Session session){
		this.session = session;
	}
	
	public void deletePartById(int id){
		gateway.deletePartById(id);
	}
	
	public void setPartArrayList(){
		partArrayList = gateway.getPartsArrayList();
	}
	
	public ArrayList getPartArrayList(){
		return partArrayList;
	}
	
	public void setPartByName(String name){
		part = gateway.getPartObjectByName(name);
	}
	public Part getCurrentPartObject(){
		return part;
	}
}
