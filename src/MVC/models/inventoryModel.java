package MVC.models;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JList;

import MVC.controllers.invMenuController;
import MVC.controllers.menuController;
import MVC.controllers.showInventoryController;
import MVC.controllers.showPartsController;
import MVC.views.inventoryListView;
import MVC.views.showPartsView;

public class inventoryModel {
	//private addPartModel newPart;
	public addPartModel currentObject;
	public inventoryItem currentObject2;
	private showPartsView showView;
	private inventoryListView invView;
	private showInventoryController invController;
	private menuController menuController;
	private invMenuController menuController2;
	private showPartsController controller;
	private productModel proModel;
	private int currentId = 0;
	private String[] errorArray = new String[10];
	private int flag = 0;
	private int arrayIndex = 0;
	private int nameIndex = 0;
	private int id = 1;
	private ResultSet results;
	private ResultSet results2;
	private ResultSet results3;
	public ArrayList<addPartModel> partsList = new ArrayList();
	public ArrayList<String> nameArray = new ArrayList();
	public ArrayList<String> inventoryArray = new ArrayList();
	public ArrayList<inventoryItem> inventoryList = new ArrayList();
	public ArrayList<String> locations = new ArrayList();
	public inventoryItem inventoryItem;
	public ArrayList<String> itemArray = new ArrayList();
	public ArrayList<String> unitList = new ArrayList();
	public ArrayList<String> parts = new ArrayList();
	public addPartModel testPart;
	//public gatewaySQL gateway = new gatewaySQL("ymd524", "ymd524", "HRqEF9KWp7MFw04SR0zZ");
	public gatewaySQL gateway = new gatewaySQL("lop343", "lop343", "dragon91z");
	public String[] locationsArray;
	public String[] partsArray;
	public inventoryModel(){
		
	}

	/*
	 * removes the name of the currentObject from arrayList of names
	 * removes the currentObject from arrayList of objects
	 * sets currentObject to null
	 */
	public void deletePart(int id){
		nameArray.remove(getCurrentPartName());
		gateway.deletePart(id);
	}
	
	public void deleteInv(int id){
		gateway.deleteInventoryItem(id);
	}
	
	
	/*
	 * adds new instance of addPartModel to arraylist and the name value to arraylist of names
	 */

	public void addPart(String num, String name, String vendor, 
			String ext, String unit){
		
		gateway.addPart(num, name, vendor, unit, ext);	
	}		
	
	public void addInventoryItem(String name, String loc, int quantity){
		
		int partId = gateway.getPartIdByName(name);
		String locationstr = gateway.getLocationByName(loc);
		int locationId = Integer.parseInt(locationstr);
		gateway.addInventoryItem(partId, locationId, quantity);
		//inventoryItem = new inventoryItem(part, location, quantity);
		//inventoryList.add(inventoryItem);
	}
	
	/*
	 * if any of the fields dont match the current value, they are updated in the addPartModel
	 */

	public void updatePart(String num, String name, String vendor, 
			String ext, String unit){
		int id = getCurrentPartId();
		
		if(!getCurrentPartName().equals(name)){
			gateway.updatePartName(name, id);			
		}
		
		if(!getCurrentPartNumber().equals(num)){
			gateway.updatePartNumber(num,id);
		}
		
		if(!getCurrentPartVendor().equals(vendor)){
			gateway.updateVendor(vendor,id);
		}
		
		
		if(!getCurrentPartUnit().equals(unit)){
			gateway.updateUnit(unit,id);
		}

		if(!getCurrentPartExt().equals(ext)){
			gateway.updateExtPartNumber(ext,id);
		}
		
	}
	
	public void updateInv(String name, String location, String quantity){
		int id = getCurrentInvId();
		int a = getCurrentInvPart();
		//System.out.println("currentInvId = "+ id + " currentInvPart id = " +a);
		
		
		String aStr = Integer.toString(a);
		if(!aStr.equals(name)){
			gateway.updateInvName(name, a);	
		}
		
		
		if(!getCurrentLocation().equals(location)){
			gateway.updateInvL(location,id);
		}
		
		int n = getCurrentInvQ();
		aStr = Integer.toString(n);
		if(!aStr.equals(quantity)){
			gateway.updateInvQ(quantity,id);
		}
		
	}
	/*
	 * closes the current showPartView then instanciates a new one and starts it up
	 * to update the list on the jframe
	 */
	public void resetList(){

		showView.removeList();//close showPartsView
		proModel = showView.getproModel();
		showView = new showPartsView(this, proModel); /*create new showPartsView*/
		controller = showView.getController();
		menuController = showView.getMenuController();
		currentObject=null;
		setCurrentObject(currentObject);
		showView.registerListeners(controller, menuController);	//register other controllers as listeners 
		showView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/* start up new showPartsview */
		showView.setSize(400, 300);
		showView.setVisible(true);
	}
	
	public void resetInv(){

		invView.removeListInv();
		invView = new inventoryListView(this);
		invController = invView.getController();
		menuController2 = invView.getMenuController();
		
		currentObject2 = null;
		setCurrentObject2(currentObject2);
		
		invView.registerListeners(invController, menuController2);
		invView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		invView.setSize(400, 300);
		invView.setLocation(400, 0);
		invView.setVisible(true);			
	}
	/*
	 * setters
	 */
	
	
	public void setCurrentId(int i){
		currentId = i;
	}
	
	public void setShowView(showPartsView showView){
		this.showView = showView;
	}
	
	public void setInvView(inventoryListView invView) {
		this.invView = invView;
	}
	
	public void setCurrentObject(addPartModel currObject){
		currentObject = currObject;
	}
	public void setCurrentObject2(inventoryItem currObject2){
		currentObject2 = currObject2;
	}
	public void setFlag(int i){
		flag = i;
	}
	
	public void setError(String errorMessage){
		errorArray[arrayIndex] = errorMessage;
		arrayIndex++;
	}
	
	public void resetErrors(){
		errorArray = new String[10];
		arrayIndex = 0;
		setFlag(0);
	}
	
	public void setIndex(int i ){
		nameIndex=i;
	}
	
	public void changeNameInArray(int index, String newName){
		nameArray.set(index, newName);
	}
	
	public void setCurrentPartByName(String name){
		results = null;
		results = gateway.getPartByName(name);
	}
	
	public void setCurrentInventory(String name){
		String str = null;
		results = null;
		results2 = null;
		//System.out.println("setCurrentInventory = "+name);
		results = gateway.getPartByName(name);
		try{
			str = results.getString("id");
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		results2 = gateway.getInvByPartId(str);
	}
	
	/*
	 * getters
	 */
	public ResultSet getCurrentPart(){
		return results;
	}
	
	public ResultSet getCurrentInv(){
		int i = getCurrentInvId();
		results2 = gateway.getInvById(i);
		return results2;
	}
	
	public String getCurrentPartName(){
		String str=null;
		try{
			str = results.getString("partName");
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return str;
	}
	
	public String getCurrentPartNumber(){
		String str=null;
		try{
			str = results.getString("partNumber");
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return str;
	}
	
	public String getCurrentPartVendor(){
		String str=null;
		try{
			str = results.getString("vendor");
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return str;
	}
	
	public String getCurrentPartUnit(){
		String str=null;
		try{
			str = results.getString("unit");
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return str;
	}
	
	public String getCurrentPartExt(){
		String str=null;
		try{
			str = results.getString("extPartNumber");
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return str;
	}
	
	public int getCurrentPartId(){
		int id=0;
		try{
			id = results.getInt("id");
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return id;
	}
	public int getCurrentInvQ(){
		int val =0;
		try{
			val = results2.getInt("quantity");
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return val;
	}
	
	public String getCurrentLocation() {
		int id=0;
		String str;
		try{
			id = results2.getInt("locationId");
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		str = gateway.getLocationById(id);
		return str;
	}
	
	public int getCurrentInvId() {
		int id=0;
		try{
			id = results2.getInt("id");
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return id;
	}
	
	public int getCurrentInvPart() {
		int id=0;
		try{
			id = results2.getInt("partId");
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return id;
	}
	
	public Date getCurrentTime() {
		//String date;
		Date date2;
		try{
			//date = results2.getString("lastmodified");
			date2 = results2.getTimestamp("lastmodified");
			//System.out.println("date2 = " +date2);
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return date2;
	}
	
	public int getCurrentId(){
		return currentId;
	}	
	
	public ArrayList<String> getLocationsArray(){
		results = null;
		locations.clear();
		try{
			results = gateway.getAllLocations();
			locations.add(results.getString("name"));
			while(results.next()){
				locations.add(results.getString("name"));
			}
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return locations;
	}
	
	public ArrayList<String> getNameArray(){
		return nameArray;
	}
	
	public ArrayList<String> getInventoryArray(){
		return inventoryArray;
	}
	
	public ArrayList<String> getUnitList(){
		UnitList();
		return unitList;
	}
	
	public ArrayList<addPartModel> getObjectArray(){
		return partsList;
	}
	public ArrayList<String> getPartsL(){
		return parts;
	}

	public void getInventoryList() {
		results = null;
		try{
			results = gateway.getAllInventoryItems();
			while(results.next()){
				int i = results.getInt("partId");
				inventoryArray.add(gateway.getPartNameById(i));
			}
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void UnitList() {
		results = null;
		String str = null;
		try{
			results = gateway.getAllUnits();
			while(results.next()){
				str = results.getString("unit");
				unitList.add(str);
			}
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void getPartsList() {
		results = null;
		nameArray.clear();
		try{
			results = gateway.getAllParts();
			nameArray.add(results.getString("partName"));
			while(results.next()){
				nameArray.add(results.getString("partName"));
			}
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public String[] getLocations(){
		return locationsArray;
	}
	
	public addPartModel getCurrentObject(){
		return currentObject;
	}
	
	public int getFlag(){
		return flag;
	}
	
	public String[] getErrorArray(){
		return errorArray;
	}
	
	public ArrayList<String> getLocationIdPartByName(String name){
		String str, restr = null;
		ArrayList<Integer> arl = new ArrayList<Integer>();
		arl.clear();
		int count=0;
		
		str = gateway.getLocationByName(name);
		results = gateway.getPartsByLocation(str);
		try {
			arl.add(results.getInt("partId"));
			while(results.next()){
				arl.add(results.getInt("partId"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (count < arl.size()) {
			results = gateway.getPartName(arl.get(count++));
			try {
				restr = results.getString("partName");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			parts.add(restr);
		}
		arl.clear();
		return parts;
	}
	
	/*
	 * Check fields
	 *  gets name array and sets error flag to 1 if there is a match,
	 *  adds error desc to errorArray in model
	*/
	public void checkName(String name) {
		String error=null;
		
		for(int i=0; i<nameArray.size();i++){
			if(nameArray.get(i).equals(name) && !nameArray.get(i).equals(getCurrentPartName())){
				setFlag(1);
				error = "Part name is not unique";
				setError(error);
				return;
			}
		}
	}
	
	public void checkNameLength(String name) {
		String error = null;
		if(name.length() > 255){
			setFlag(1);
			error = "Name field cannot exceed 255 character";
			setError(error);
		}
	}
	
	public void checkNumLength(String num) {
		String error = null;
		if(num.length() > 20){
			setFlag(1);
			error = "Part number field cannot exceed 20 character";
			setError(error);
		}
	}

	public void checkVendorLength(String vendor) {
		String error = null;
		if(vendor.length() > 255){
			setFlag(1);
			error = "Vendor field cannot exceed 255 character";
			setError(error);
		}
	}
	
	public void checkExtLength(String ext) {
		String error = null;
		if(ext.length() > 50){
			setFlag(1);
			error = "External Part Number field cannot exceed 50 character";
			setError(error);
		}
	}

	/*
	 * sets flag to 1 if q is not > 0, adds error desc to errorArray
	 */
	public void checkQuantity(int q) {
		String error = null;
		if(q <= 0){
			setFlag(1);
			error = "Part quantity must be greater than 0";
			setError(error);
		}
	}
	

	/*
	 * sets flag to 1 if name or num are empty or null, 
	 * adds error desc to errorArray
	 */
	public void checkRequiredFields(String name, String num) {
		String nullString = null;
		String emptyString = new String();
		String error = null;
		
		if(name.equals(nullString) || name.equals(emptyString)){
			setFlag(1);
			error = "Part name is required";
			setError(error);
		}
		if(num.equals(nullString) || num.equals(emptyString)){
			setFlag(1);
			error = "Part number is required";
			setError(error);
		}
	}

	public void checkUnit(String unit) {
		String nstr = "Unknown";
		String nullString = null;
		String emptyString = new String();
		String error = null;
		
		if (unit.equalsIgnoreCase(nstr)) {
			setFlag(1);
			error = "Unit of Quantity cannot be Unknown";
			setError(error);
		}
		if (unit.equals(nullString) || unit.equals(emptyString)) {
			setFlag(1);
			error = "Unit of Quantity is required";
			setError(error);
		}
		
	}
	
	public void checkLoc(String location) {
		String nstr = "Unknown";
		String nullString = null;
		String emptyString = new String();
		String error = null;
		
		if (location.equalsIgnoreCase(nstr)) {
			setFlag(1);
			error = "Part Location cannot be Unknown";
			setError(error);
		}
		if (location.equals(nullString) || location.equals(emptyString)) {
			setFlag(1);
			error = "Part Location is required";
			setError(error);
		}
	}
	
	public void checkPL(String name, String location) {
		//check if location and part match already exist
		int flag = 0;
		String error = null;
		String parts = null;
		int matching = 0;
		
		String str = gateway.getLocationByName(location);
		//System.out.println("str = " +str);
		results3 = gateway.getPartsByLocation(str);
		try {
			matching = results3.getInt("partId");
			parts = gateway.getPartNameById(matching);
			//System.out.println("frist matching = " +matching + " part = " +parts);
			while(results3.next()){
				if (name.equals(parts)) {
					flag = 1;
					//System.out.println("if statment matching = " +matching + " part = " +parts);
					break;
				}
				matching = results3.getInt("partId");
				parts = gateway.getPartNameById(matching);
				//System.out.println("while matching = " +matching + " part = " +parts);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (flag == 1) {
			//System.out.println("Error");
			setFlag(1);
			error = "Part/Location already exist";
			setError(error);
		}
	}
	
}
