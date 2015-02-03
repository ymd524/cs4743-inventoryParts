package MVC.models;

import java.util.ArrayList;

public class addPartModel {
	
	private String num;
	private String name;
	private String vendor;
	private int quantity;
	private int id;
	
	public addPartModel(String num, String name, String vendor, int quantity, int id){
		this.num = num;
		this.name = name;
		this.vendor = vendor;
		this.quantity = quantity;
		this.id = id;
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
	
	public int getId(){
		return this.id;
	}
}
