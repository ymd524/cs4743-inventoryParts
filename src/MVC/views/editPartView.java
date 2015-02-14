package MVC.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MVC.controllers.editPartController;
import MVC.models.inventoryModel;

public class editPartView extends JFrame{
	private inventoryModel model;
	private JButton saveButton = new JButton("Save");
	private JButton cancelButton = new JButton("Cancel");
	private String partName;
	private String extNum;
	private String partNumber;
	private String partVendor;
	private String partUnit;
	private JLabel extLabel = new JLabel("Part #: ");
	private JLabel numLabel = new JLabel("Ext. Part #: ");
	private JLabel nameLabel = new JLabel("Part Name: ");
	private JLabel venLabel = new JLabel("Vendor: ");
	private JLabel unitLabel = new JLabel("Unit: ");
	private JTextField numText = new JTextField(20);
	private JTextField extText = new JTextField(50);
	private JTextField nameText = new JTextField(255);
	private JTextField vText = new JTextField(255);
	private JTextField Unit = new JTextField(20);
	final String[] choices = { "Unknown", "pieces", 
			  "feet" , "inches" , "units" , "sets" };
	final String[] places = { "Facility 1 Warehouse 1", "Facility 1 Warehouse 2", 
			  "Facility 1" , "Facility 2" , "Area 51" , "Unknown" };

	
	public editPartView(inventoryModel model){
		this.model = model;
		GridLayout grid = new GridLayout(6,2);
		partName = model.getCurrentPartName();/*gets and sets values of currentObject*/
		partNumber = model.getCurrentPartNumber();
		partVendor = model.getCurrentPartVendor();
		partUnit = model.getCurrentPartUnit();
		extNum = model.getCurrentPartExt();

		numText.setText(partNumber);/*fills JTextFields with current Parts's values*/
		nameText.setText(partName);
		vText.setText(partVendor);
		extText.setText(extNum);
		Unit.setText(partUnit);
		
		this.setLayout(grid);
		this.add(numLabel);/*add labels and text fields to JFrame*/
		this.add(numText);
		this.add(extLabel);
		this.add(extText);
		this.add(nameLabel);
		this.add(nameText);
		this.add(venLabel);
		this.add(vText);
		this.add(unitLabel);
		this.add(Unit);
		this.add(saveButton);/*adds button to editPanel*/
		this.add(cancelButton);
	}
	
	public void registerListeners(editPartController controller) {
		cancelButton.addActionListener(controller);
		saveButton.addActionListener(controller);
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
	
	public String getNumText(){
		return numText.getText();
	}
	
	public String getExtText(){
		return extText.getText();
	}
	
	public String getVText(){
		return vText.getText();
	}
	
	public String getCurrName(){
		return partName;
	}
	public String getUnitText() {
		return Unit.getText();
	}
}
