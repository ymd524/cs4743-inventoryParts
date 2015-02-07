package MVC.views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import MVC.controllers.addPartController;
import MVC.models.inventoryModel;

public class addPartsView extends JFrame{
	final String[] choices = { "Unknown", "pieces", 
	  "feet" , "inches" , "units" , "sets" };
	private inventoryModel model;
	private JPanel addPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JLabel numLabel = new JLabel("Part #: ");
	private JLabel nameLabel = new JLabel("     Part Name: ");
	private JLabel venLabel = new JLabel("  Vendor: ");
	private JLabel qLabel = new JLabel("   Quantity: ");
	private JLabel extLabel = new JLabel("     Ext. Part #: ");
	private JTextField numText = new JTextField(20);
	private JTextField nameText = new JTextField(20);
	private JTextField venText = new JTextField(20);
	private JTextField qText = new JTextField(20);
	private JTextField extText = new JTextField(20);
	private JButton addButton = new JButton("Add Part");
	private JButton cancelButton = new JButton("Cancel");
	private JComboBox combo = new JComboBox(choices);
	
	public addPartsView(inventoryModel model){
		this.model = model;
		

		addPanel.add(numLabel);/*adds labels and text fields to addPanel*/
		addPanel.add(numText);
		addPanel.add(extLabel);
		addPanel.add(extText);
		addPanel.add(nameLabel);
		addPanel.add(nameText);
		addPanel.add(venLabel);
		addPanel.add(venText);
		addPanel.add(qLabel);
		addPanel.add(qText);
		addPanel.add(combo);
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
	
	public int getQuantity(){
			return Integer.parseInt(qText.getText());
	}
	public String getUnitQ() {
		String str = (String)combo.getSelectedItem();
		return str;
	}
}
