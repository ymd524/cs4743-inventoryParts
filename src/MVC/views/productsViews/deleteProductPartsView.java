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
import MVC.controllers.productControllers.deleteProductPartController;
import MVC.controllers.productControllers.productPartsListController;
import MVC.models.inventoryModel;
import MVC.models.productModel;

public class deleteProductPartsView extends JFrame{
	private productModel model;
	private JPanel addPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JButton deleteButton = new JButton("Delete Part");
	private JButton cancelButton = new JButton("Cancel");
	private ArrayList<String> arraylist = new ArrayList();
	private JComboBox combo;
	private String[] parts;
	
	public deleteProductPartsView(productModel model){
		super("Delete Product Part");
		this.model = model;
		combo = new JComboBox();

		arraylist = model.getAllProductParts();//gets ArrayList of names
		parts = new String[arraylist.size()];//creates new String array the size of ArrayList namesArray
		parts = arraylist.toArray(parts);//assigns values from arrayList to array
		combo = new JComboBox(parts);
		
		addPanel.add(combo);
		
		buttonPanel.add(deleteButton);/*adds buttons to JFrame*/
		buttonPanel.add(cancelButton);
		this.add(buttonPanel, BorderLayout.SOUTH);/*adds buttonPanel and addPanel to JFrame*/
		this.add(addPanel);
	}
	

	

	public void registerListeners(deleteProductPartController controller) {
		cancelButton.addActionListener(controller);
		deleteButton.addActionListener(controller);  
	}
	
	public void closeWindow (){
		this.dispose();
	}
	
	/*
	 * gets text field values
	 */
	
	public String getPartName() {
		String str = (String)combo.getSelectedItem();
		return str;
	}
}
