package MVC.views.productsViews;

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
import MVC.controllers.productControllers.editProductController;
import MVC.models.inventoryModel;
import MVC.models.productModel;

public class editProductsView extends JFrame{
	private productModel model;
	private JButton saveButton = new JButton("Save");
	private JButton cancelButton = new JButton("Cancel");
	private String partNumber;
	private String partDesc;
	private JLabel numLabel = new JLabel("Part #: ");
	private JLabel descLabel = new JLabel("Part Description: ");
	private JTextField numText = new JTextField(255);
	private JTextField descText = new JTextField(255);

	final String[] choices = { "Unknown", "pieces", 
			  "feet" , "inches" , "units" , "sets" };
	final String[] places = { "Facility 1 Warehouse 1", "Facility 1 Warehouse 2", 
			  "Facility 1" , "Facility 2" , "Area 51" , "Unknown" };

	
	public editProductsView(productModel model){
		this.model = model;
		GridLayout grid = new GridLayout(6,2);
		partNumber = model.getProductNumber();/*gets and sets values of currentObject*/
		partDesc = model.getProductDesc();	

		numText.setText(partNumber);/*fills JTextFields with current Parts's values*/
		descText.setText(partDesc);


		
		this.setLayout(grid);
		this.add(numLabel);/*add labels and text fields to JFrame*/
		this.add(numText);
		this.add(descLabel);
		this.add(descText);
		this.add(saveButton);/*adds button to editPanel*/
		this.add(cancelButton);
	}
	
	public void registerListeners(editProductController controller) {
		cancelButton.addActionListener(controller);
		saveButton.addActionListener(controller);
	}
	
	public void closeWindow (){
		this.dispose();
	}

	/*
	 * gets text from text fields
	 */
	public String getNumText(){
		return numText.getText();
	}
	
	public String getDescText(){
		return descText.getText();
	}
	
}
