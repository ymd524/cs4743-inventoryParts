package MVC.views;

import java.awt.GridLayout;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private Date d;
	private java.util.Date date2;
	
	//private JComboBox combo1;
	private JComboBox combo2;
	private ArrayList<String> loclist = new ArrayList();
	private String[] locations;
	//private ArrayList<String> partlist = new ArrayList();
	//private String[] parts;
	
	public editInvView(inventoryModel model) {
		// TODO Auto-generated constructor stub
		super("Edit Inventory Item");
		this.model = model;
		GridLayout grid = new GridLayout(6,2);
		
		partName = model.getCurrentPartName();/*gets and sets values of currentObject*/
		loc = model.getCurrentLocation();
		quantity = model.getCurrentInvQ();
		String str = Integer.toString(quantity);
		
/*		model.getPartsList();
		partlist = model.getNameArray();
		//System.out.println("combo1 = "+partlist.toString());
		parts = new String[partlist.size()];
		parts = partlist.toArray(parts);
		combo1 = new JComboBox(parts);*/
		
		loclist = model.getLocationsArray();
		//System.out.println("combo2 = "+loclist.toString());
		locations = new String[loclist.size()];
		locations = loclist.toArray(locations);
		combo2 = new JComboBox(locations);

		nameText.setText(partName);
		locText.setText(loc);
		qText.setText(str);
		
		this.setLayout(grid);
		this.add(nameLabel);
		this.add(nameText);
		//this.add(combo1);
		
		this.add(locLabel);
		//this.add(locText);
		this.add(combo2);
		
		this.add(qLabel);
		this.add(qText);
		this.add(saveButton);
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
	
	public int getQText(){
		int zero;
		if (qText.getText().isEmpty()) {
			zero = 0;
		} else {
            zero = Integer.parseInt(qText.getText());
        }
		return zero;
		//return qText.getText();
	}
	
	public String getLocText() {
		return locText.getText();
	}
}
