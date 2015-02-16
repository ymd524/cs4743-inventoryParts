package MVC.models;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;

import MVC.controllers.menuController;
import MVC.controllers.showPartsController;
import MVC.views.showPartsView;

public class inventoryModel {
	private addPartModel newPart;
	public addPartModel currentObject;
	private showPartsView showView;
	private menuController menuController;
	private showPartsController controller;
	private int currentId = 0;
	private String[] errorArray = new String[10];
	private int flag = 0;
	private int arrayIndex = 0;
	private int nameIndex = 0;
	private int id = 1;
	private ResultSet results;
	public ArrayList<addPartModel> partsList = new ArrayList();
	public ArrayList<String> nameArray = new ArrayList();
	public ArrayList<String> inventoryArray = new ArrayList();
	public ArrayList<inventoryItem> inventoryList = new ArrayList();
	public ArrayList<String> locations = new ArrayList();
	public inventoryItem inventoryItem;
	public ArrayList<String> itemArray = new ArrayList();
	public ArrayList<String> unitList = new ArrayList();
	public addPartModel testPart;
	//public gatewaySQL gateway = new gatewaySQL("ymd524", "ymd524", "HRqEF9KWp7MFw04SR0zZ");
	public gatewaySQL gateway = new gatewaySQL("lop343", "lop343", "dragon91z");
	public String[] locationsArray;
	
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
	
	/*
	 * adds new instance of addPartModel to arraylist and the name value to arraylist of names
	 */

	public void addPart(String num, String name, String vendor, 
			String ext, String unit){
		
		gateway.addPart(num, name, vendor, unit, ext);	
	}		
	
	public void addInventoryItem(int partId, int locationId, int quantity){
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
	
	/*
	 * closes the current showPartView then instanciates a new one and starts it up
	 * to update the list on the jframe
	 */
	public void resetList(){

		showView.removeList();//close showPartsView
		showView = new showPartsView(this); /*create new showPartsView*/
		controller = showView.getController();
		menuController = showView.getMenuController();
		currentObject=null;
		setCurrentObject(currentObject);
		showView.registerListeners(controller, menuController);	//register other controllers as listeners 
		showView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/* start up new showPartsview */
		showView.setSize(400, 300);
		showView.setVisible(true);			
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
	
	public void setCurrentObject(addPartModel currObject){
		currentObject = currObject;
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
	
	/*
	 * getters
	 */
	public ResultSet getCurrentPart(){
		return results;
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
	
	public int getCurrentId(){
		return currentId;
	}
	

	
	/*public void getObjectByInt(int x){
			setCurrentObject(partsList.get(x));
	}*/
	
	public ArrayList getLocationsArray(){
		results = null;
		try{
			results = gateway.getAllLocations();
			while(results.next()){
				locations.add(results.getString("name"));
			}
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		
		return locations;
	}
	public ArrayList getNameArray(){
		return nameArray;
	}
	
	public ArrayList getInventoryArray(){
		return inventoryArray;
	}
	
	public ArrayList getUnitList(){
		UnitList();
		return unitList;
	}
	
	public ArrayList getObjectArray(){
		return partsList;
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
		try{
			results = gateway.getAllParts();
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
	
	public int getLocationIdPartByName(String name){
		int i = 0;
		i = gateway.getLocationByName(name);
		return i;
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
	
	
}
