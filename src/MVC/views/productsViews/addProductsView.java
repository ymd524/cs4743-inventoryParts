package MVC.views.productsViews;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import MVC.controllers.addPartController;
import MVC.controllers.productControllers.addProductController;
import MVC.models.inventoryModel;
import MVC.models.productModel;

public class addProductsView extends JFrame{
	final String[] choices = { "Unknown", "pieces", 
			  "feet" , "inches" , "units" , "sets" };
	private productModel model;
	private JPanel addPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JLabel numLabel = new JLabel("Part #: ");
	private JLabel descLabel = new JLabel("Part Desc: ");
	private JTextField numText = new JTextField(20);
	private JTextField descText = new JTextField(255);
	private JButton addButton = new JButton("Add Part");
	private JButton cancelButton = new JButton("Cancel");
	private ArrayList<String> arraylist = new ArrayList();
	
	public addProductsView(productModel model){
		this.model = model;
		GridLayout grid = new GridLayout(6,7);

		this.setLayout(grid);;
		this.add(numLabel);/*adds labels and text fields to JFrame*/
		this.add(numText);
		this.add(descLabel);
		this.add(descText);
		this.add(addButton);/*adds buttons to JFrame*/
		this.add(cancelButton);
	}

	public void registerListeners(addProductController controller) {
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
	
	public String getDesc(){
		return descText.getText();
	}
	
	/*public String getUnitQ() {
		String str = (String)combo.getSelectedItem();
		return str;
	}*/
}
