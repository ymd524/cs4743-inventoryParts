package MVC.views;

import java.awt.BorderLayout;
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
	private JPanel editPanel = new JPanel();
	private JButton saveButton = new JButton("Save");
	private JButton cancelButton = new JButton("Cancel");
	private String partName;
	private String partNumber;
	private String partVendor;
	private int partQuantity;
	private String partUnit;
	private int index;
	final String[] choices = { "Unknown", "pieces", 
			  "feet" , "inches" , "units" , "sets" };
	private JPanel panel = new JPanel();
	private JLabel num = new JLabel("Part #: ");
	private JLabel name = new JLabel("Part Name: ");
	private JLabel ven = new JLabel("Vendor: ");
	private JLabel q = new JLabel("Quantity: ");
	private JTextField PartNum = new JTextField(20);
	private JTextField PartName = new JTextField(20);
	private JTextField Vendor = new JTextField(20);
	private JTextField Quantity = new JTextField(10);
	private JTextField Unit = new JTextField(10);
	//private JComboBox combo = new JComboBox(choices);
	
	public editPartView(inventoryModel model){
		this.model = model;
		partName = model.getCurrentObject().getName();/*gets and sets values of currentObject*/
		partNumber = model.getCurrentObject().getNum();
		partVendor = model.getCurrentObject().getVendor();
		partQuantity = model.getCurrentObject().getQuantity();
		partUnit = model.getCurrentObject().getUnit();
		PartNum.setText(partNumber);/*fills JTextFields with currentObject's values*/
		PartName.setText(partName);
		Vendor.setText(partVendor);
		Quantity.setText(Integer.toString(partQuantity));
		Unit.setText(partUnit);
		
		panel.add(num);/*add labels and text fields to panel*/
		panel.add(PartNum);
		panel.add(name);
		panel.add(PartName);
		panel.add(ven);
		panel.add(Vendor);
		panel.add(q);
		panel.add(Quantity);
		panel.add(Unit);
		editPanel.add(saveButton);/*adds button to editPanel*/
		editPanel.add(cancelButton);
		this.add(panel,BorderLayout.CENTER);/*adds panels to JFrame*/
		this.add(editPanel, BorderLayout.SOUTH);
		
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
		return PartName.getText();
	}
	
	public String getNumText(){
		return PartNum.getText();
	}
	
	public String getVText(){
		return Vendor.getText();
	}
	
	public String getQText(){
		return Quantity.getText();
	}
	
	public String getCurrName(){
		return partName;
	}
	public String getUnitText() {
		return Unit.getText();
	}
}
