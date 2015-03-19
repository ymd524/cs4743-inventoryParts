package MVC.views.productsViews;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import MVC.controllers.addPartController;
import MVC.controllers.productControllers.addProductController;
import MVC.controllers.productControllers.addProductPartController;
import MVC.controllers.productControllers.productPartsListController;
import MVC.models.inventoryModel;
import MVC.models.productModel;

public class addProductPartsView extends JFrame{
	private productModel model;
	private JPanel addPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JLabel qLabel = new JLabel("	Quantity: ");
	private JTextField qText = new JTextField(20);
	private JButton addButton = new JButton("Add Part");
	private JButton cancelButton = new JButton("Cancel");
	private ArrayList<String> arraylist = new ArrayList();
	private JComboBox combo;
	private String[] parts;
	
	public addProductPartsView(productModel model){
		super("Add Product Part");
		this.model = model;

		arraylist = model.getAllParts();//gets ArrayList of names
		parts = new String[arraylist.size()];//creates new String array the size of ArrayList namesArray
		parts = arraylist.toArray(parts);//assigns values from arrayList to array
		combo = new JComboBox(parts);
		
		addPanel.add(combo);
		addPanel.add(qLabel);/*adds labels and text fields to JFrame*/
		addPanel.add(qText);
		
		buttonPanel.add(addButton);/*adds buttons to JFrame*/
		buttonPanel.add(cancelButton);
		this.add(buttonPanel, BorderLayout.SOUTH);/*adds buttonPanel and addPanel to JFrame*/
		this.add(addPanel);
	}
	

	

	public void registerListeners(addProductPartController controller) {
		cancelButton.addActionListener(controller);
		addButton.addActionListener(controller);  
	}
	
	public void closeWindow (){
		this.dispose();
	}
	
	/*
	 * gets text field values
	 */
	
	public String getQ(){
		return qText.getText();
	}
	
	
	public String getPartName() {
		String str = (String)combo.getSelectedItem();
		return str;
	}
}
