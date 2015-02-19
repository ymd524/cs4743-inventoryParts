package MVC.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import MVC.controllers.addPartController;
import MVC.models.inventoryModel;

public class addPartsView extends JFrame{
/*	final String[] places = { "Facility 1 Warehouse 1", "Facility 1 Warehouse 2", 
			  "Facility 1" , "Facility 2" , "Area 51" , "Unknown" };*/
	final String[] choices = { "Unknown", "pieces", 
			  "feet" , "inches" , "units" , "sets" };
	private inventoryModel model;
	private JPanel addPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JLabel numLabel = new JLabel("Part #: ");
	private JLabel nameLabel = new JLabel("Part Name: ");
	private JLabel venLabel = new JLabel("Vendor: ");
	private JLabel extLabel = new JLabel("Ext. Part #: ");
	private JLabel unitLabel = new JLabel("Unit of Quantity: ");
	private JTextField numText = new JTextField(20);
	private JTextField nameText = new JTextField(255);
	private JTextField venText = new JTextField(255);
	private JTextField extText = new JTextField(50);
	private JButton addButton = new JButton("Add Part");
	private JButton cancelButton = new JButton("Cancel");
	private JComboBox combo;
	private ArrayList<String> arraylist = new ArrayList();
	private String[] units;
	
	public addPartsView(inventoryModel model){
		this.model = model;
		GridLayout grid = new GridLayout(6,7);
		arraylist = model.getUnitList();//gets ArrayList of names
		units = new String[arraylist.size()];//creates new String array the size of ArrayList namesArray
		units = arraylist.toArray(units);//assigns values from arrayList to array
		combo = new JComboBox(choices);

		this.setLayout(grid);;
		this.add(numLabel);/*adds labels and text fields to JFrame*/
		this.add(numText);
		this.add(extLabel);
		this.add(extText);
		this.add(nameLabel);
		this.add(nameText);
		this.add(venLabel);
		this.add(venText);
		this.add(unitLabel);
		this.add(combo);
		this.add(addButton);/*adds buttons to JFrame*/
		this.add(cancelButton);
		//this.add(buttonPanel, BorderLayout.SOUTH);/*adds buttonPanel and addPanel to JFrame*/
		//this.add(addPanel);
	}

	public void registerListeners(addPartController controller) {
		cancelButton.addActionListener(controller);
		addButton.addActionListener(controller);
	}
	
	public void closeWindow (){
		this.dispose();
	}
	
	/*
	 * gets text field values
	 */
	
	public String getNum(){
		return numText.getText();
	}
	
	public String getExtNum(){
		return extText.getText();
	}
	
	public String getName(){
		return nameText.getText();
	}
	
	public String getVendor(){
		return venText.getText();
	}
	
	/*public int getQuantity(){
			return Integer.parseInt(qText.getText());
	}*/
	public String getUnitQ() {
		String str = (String)combo.getSelectedItem();
		return str;
	}
	/*public String getLoc() {
		String lstr = (String)lcombo.getSelectedItem();
		return lstr;
	}*/
}
