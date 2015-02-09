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
	private String extNum;
	private String partNumber;
	private String partVendor;
	private int partQuantity;
	private String partUnit;
	private int index;
	private String partloc;
	final String[] choices = { "Unknown", "pieces", 
			  "feet" , "inches" , "units" , "sets" };
	final String[] places = { "Facility 1 Warehouse 1", "Facility 1 Warehouse 2", 
			  "Facility 1" , "Facility 2" , "Area 51" , "Unknown" };
	private JPanel panel = new JPanel();
	private JLabel extLabel = new JLabel("Part #: ");
	private JLabel numLabel = new JLabel("Ext. Part #: ");
	private JLabel nameLabel = new JLabel("Part Name: ");
	private JLabel venLabel = new JLabel("Vendor: ");
	private JLabel qLabel = new JLabel("Quantity: ");
	private JLabel locLabel = new JLabel("Location: ");
	private JTextField numText = new JTextField(20);
	private JTextField extText = new JTextField(20);
	private JTextField nameText = new JTextField(20);
	private JTextField vText = new JTextField(20);
	private JTextField qText = new JTextField(20);
	private JTextField Unit = new JTextField(10);
	private JTextField loc = new JTextField(10);
	
	public editPartView(inventoryModel model){
		this.model = model;
		partName = model.getCurrentObject().getName();/*gets and sets values of currentObject*/
		partNumber = model.getCurrentObject().getNum();
		partVendor = model.getCurrentObject().getVendor();
		partQuantity = model.getCurrentObject().getQuantity();
		partUnit = model.getCurrentObject().getUnit();
		extNum = model.getCurrentObject().getExt();
		partloc = model.getCurrentObject().getLocation();

		numText.setText(partNumber);/*fills JTextFields with currentObject's values*/
		nameText.setText(partName);
		vText.setText(partVendor);
		qText.setText(Integer.toString(partQuantity));
		extText.setText(extNum);
		Unit.setText(partUnit);
		loc.setText(partloc);
		
		panel.add(numLabel);/*add labels and text fields to panel*/
		panel.add(numText);
		panel.add(extLabel);
		panel.add(extText);
		panel.add(nameLabel);
		panel.add(nameText);
		panel.add(venLabel);
		panel.add(vText);
		panel.add(qLabel);
		panel.add(qText);
		panel.add(Unit);
		panel.add(locLabel);
		panel.add(loc);
		
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
	
	public String getQText(){
		return qText.getText();
	}
	
	public String getCurrName(){
		return partName;
	}
	public String getUnitText() {
		return Unit.getText();
	}
	public String getLocText() {
		return loc.getText();
	}
}
