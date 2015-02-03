package MVC.models;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.JFrame;

import MVC.controllers.menuController;
import MVC.controllers.showPartsController;
import MVC.views.showPartsView;

public class inventoryModel {
	private addPartModel newPart;
	public addPartModel currentObject;
	private showPartsView showView;
	private menuController menuController;
	private showPartsController controller;
	private String[] errorArray = new String[10];
	private int flag = 0;
	private int arrayIndex = 0;
	private int nameIndex = 0;
	private int id = 1;
	public ArrayList<addPartModel> partsList = new ArrayList();
	public ArrayList<String> nameArray = new ArrayList();
	
	public inventoryModel(){
		
	}

	/*
	 * removes the name of the currentObject from arrayList of names
	 * removes the currentObject from arrayList of objects
	 * sets currentObject to null
	 */
	public void deletePart(){
		nameArray.remove(currentObject.getName());
		partsList.remove(currentObject);
		currentObject = null;
		setCurrentObject(currentObject);
	}
	
	/*
	 * adds new instance of addPartModel to arraylist and the name value to arraylist of names
	 */
	public void addPart(String num, String name, String vendor, int quantity){
		newPart = new addPartModel(num, name, vendor, quantity, id);
		nameArray.add(name);
		partsList.add(newPart);	
		id++;
	}		
	
	/*
	 * if any of the fields dont match the current value, they are updated in the addPartModel
	 */
	public void updatePart(String num, String name, String vendor, int quantity){
		if(!currentObject.getName().equals(name)){
			currentObject.setNewName(name);
			
		}
		
		if(!currentObject.getNum().equals(num)){
			currentObject.setNewNum(num);
		}
		
		if(!currentObject.getVendor().equals(vendor)){
			currentObject.setNewV(vendor);
		}
		
		if(currentObject.getQuantity() != quantity){
			currentObject.setNewQ(quantity);
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
	
	/*
	 * getters
	 */
	
	public int getObjectIndex(){
		return nameIndex;
	}
	
	public void getObjectByInt(int x){
			setCurrentObject(partsList.get(x));
	}
	
	public ArrayList getNameArray(){
		return nameArray;
	}
	
	public ArrayList getObjectArray(){
		return partsList;
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
	
}



