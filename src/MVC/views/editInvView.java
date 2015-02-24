package MVC.views;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import MVC.controllers.editInvController;
import MVC.models.inventoryModel;

public class editInvView extends JFrame {
	private inventoryModel model;
	private JButton saveButton = new JButton("Save");
	private JButton cancelButton = new JButton("Cancel");
	private String partName;
	private String loc;
	private int quantity;
	private JLabel nameLabel = new JLabel("Part Name: ");
	private JLabel locLabel = new JLabel("Location: ");
	private JLabel qLabel = new JLabel("Quantity: ");
	private JTextField nameText = new JTextField(255);
	private JTextField locText = new JTextField(255);
	private JTextField qText = new JTextField(20);
	public editInvView(inventoryModel model) {
		// TODO Auto-generated constructor stub
		this.model = model;
		GridLayout grid = new GridLayout(6,2);
		
		partName = model.getCurrentPartName();/*gets and sets values of currentObject*/
		loc = model.getCurrentLocation();
		quantity = model.getCurrentInvQ();
		String str = Integer.toString(quantity);

		nameText.setText(partName);
		locText.setText(loc);
		qText.setText(str);
		
		this.setLayout(grid);
		this.add(nameLabel);
		this.add(nameText);
		this.add(locLabel);
		this.add(locText);
		this.add(qLabel);
		this.add(qText);
		this.add(saveButton);/*adds button to editPanel*/
		this.add(cancelButton);
	}

	public void registerListeners(editInvController editInventoryController) {
		// TODO Auto-generated method stub
		
		cancelButton.addActionListener(editInventoryController);
		saveButton.addActionListener(editInventoryController);
	}
	
	public void closeWindow (){
		this.dispose();
	}

	/*
	 * gets text from text fields
	 */
	public String getNameText(){
		return nameText.getText();
	}
	
	public String getQText(){
		return qText.getText();
	}
	
	public String getLocText() {
		return locText.getText();
	}
}
