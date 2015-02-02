package MVC.views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import MVC.controllers.addPartController;
import MVC.models.inventoryModel;

public class addPartsView extends JFrame{
	
	private inventoryModel model;
	private JPanel addPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JLabel num = new JLabel("Part #: ");
	private JLabel name = new JLabel("     Part Name: ");
	private JLabel ven = new JLabel("  Vendor: ");
	private JLabel q = new JLabel("   Quantity: ");
	private JTextField PartNum = new JTextField(20);
	private JTextField PartName = new JTextField(20);
	private JTextField Vendor = new JTextField(20);
	private JTextField Quantity = new JTextField(20);
	private JButton addButton = new JButton("Add Part");
	private JButton cancelButton = new JButton("Cancel");

	public addPartsView(inventoryModel model){
		this.model = model;
		
		addPanel.add(num);/*adds labels and text fields to addPanel*/
		addPanel.add(PartNum);
		addPanel.add(name);
		addPanel.add(PartName);
		addPanel.add(ven);
		addPanel.add(Vendor);
		addPanel.add(q);
		addPanel.add(Quantity);
		buttonPanel.add(addButton);/*adds buttons to buttonPanel*/
		buttonPanel.add(cancelButton);
		this.add(buttonPanel, BorderLayout.SOUTH);/*adds buttonPanel and addPanel to JFrame*/
		this.add(addPanel);
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
		return PartNum.getText();
	}
	
	public String getName(){
		return PartName.getText();
	}
	
	public String getVendor(){
		return Vendor.getText();
	}
	
	public int getQuantity(){
			return Integer.parseInt(Quantity.getText());
	}
}
