package MVC.views;


import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import MVC.controllers.menuController;
import MVC.controllers.showPartsController;
import MVC.models.inventoryModel;



public class showPartsView extends JFrame{
	
	private inventoryModel model;
	private JList list;
	private JButton addButton = new JButton("Add new part");
	private String[] names;
	private ArrayList<String> namesArray = new ArrayList();
	private showPartsController controller;
	private menuController menuController;
	
	
	public showPartsView (inventoryModel model){
		super("Parts List");
		this.model = model;
		controller = new showPartsController(this, this.model);
		menuController = new menuController(this.model, this);
		model.setShowView(this);//sets current instance of view in inventoryModel
		model.getPartsList();
		addList();//creates and adds JList to jframe with current arrayList values
	}
	
	public void addList(){
		namesArray=model.getNameArray();//gets ArrayList of names
		names = new String[namesArray.size()];//creates new String array the size of ArrayList namesArray
		names = namesArray.toArray(names);//assigns values from arrayList to array
		
		list = new JList(names);/*creates and formats list with names array values as elements*/
		list.setVisibleRowCount(5);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFixedCellHeight(44);
		list.setFixedCellWidth(100);
		
		this.add(new JScrollPane(list),BorderLayout.CENTER);/*adds list and add button to jframe*/
		this.add(addButton, BorderLayout.SOUTH);
	}
	
	public void registerListeners(showPartsController controller1, menuController controller2) {
		list.addListSelectionListener(controller1);
		addButton.addActionListener(controller2);

	}
	
	public void removeList(){
		this.dispose();//disposes current instance of showPartsView
	}
	
	
	/*
	 * getters
	 * 
	 */
	
	public int getArrayIndex(String name){
		int ndx = 0;
		for(int i=0; i<names.length;i++){
			if(names[i].equals(name)){
				ndx = i;
				return ndx;
			}
		}
		return -1;
	}
	
	public showPartsController getController(){
		return controller;
	}
	
	public menuController getMenuController(){
		return menuController;
	}

	
	public String getSelectedValue() {
		return list.getSelectedValue().toString();
	}

}
