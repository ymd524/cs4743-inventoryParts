package MVC.models;

import java.util.ArrayList;

public class addPartModel {
	
	private String num;
	private String name;
	private String vendor;
	private int quantity;
<<<<<<< HEAD
	
	private int id;
	
	public addPartModel(String num, String name, String vendor, int quantity, int id){
=======
	

	private String unit;
	public addPartModel(String num, String name, String vendor, int quantity, String unit){
>>>>>>> origin/dev_u2
		this.num = num;
		this.name = name;
		this.vendor = vendor;
		this.quantity = quantity;
<<<<<<< HEAD
		
		this.id = id;
=======
		this.unit = unit;
>>>>>>> origin/dev_u2
	}
	
	/*
	 * updates currentObject's values
	 */
	public void setNewName(String s){
		this.name = s;
	}
	
	public void setNewNum(String s){
		this.num = s;
	}
	
	public void setNewV(String s){
		this.vendor = s;
	}
	
	public void setNewQ(int s){
		this.quantity = s;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/*
	 * getters
	 */
	public String getNum(){
		return this.num;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getVendor(){
		return this.vendor;
	}
	
	public int getQuantity(){
		return this.quantity;
	}
	
<<<<<<< HEAD
	public int getId(){
		return this.id;
=======
	

	
	public String getUnit() {
		return unit;
>>>>>>> origin/dev_u2
	}
}
