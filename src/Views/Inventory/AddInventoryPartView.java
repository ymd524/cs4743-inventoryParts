package Views.Inventory;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controllers.Inventory.AddInventoryController;
import MVC.MasterFrame;
import Models.InventoryModel;
import Models.PartModel;

public class AddInventoryPartView extends JPanel{
	private MasterFrame master;
	private InventoryModel model;
	private PartModel partModel;
	private String[] locations;
	private String[] parts;
	private String myTitle = "Add Inventory Part";
	private ArrayList<String> partList = new ArrayList();
	private ArrayList<String> locationsList = new ArrayList();
	JComboBox locationsBox;
	JComboBox partsBox;
	JComboBox productsBox;
	JLabel location = new JLabel("Choose Location");
	JLabel part = new JLabel("Choose Part");
	JLabel quantity = new JLabel("Quantity: ");
	JTextField qText = new JTextField(20);
	JButton save = new JButton("Save");
	
	public AddInventoryPartView(MasterFrame m){
		master = m;
		model = master.getInventoryModel();
		AddInventoryController controller = new AddInventoryController(this, master);
		this.setLayout(new GridLayout(7,1));
		partModel = master.getPartModel();
		partModel.setPartArrayList();
		partList = partModel.getPartArrayList();
		parts = new String[partList.size()];
		parts = partList.toArray(parts);
		partsBox = new JComboBox(parts);
		
		model.setLocationsArrayList();
		locationsList = model.getLocationsArrayList();
		locations = new String[locationsList.size()];
		locations = locationsList.toArray(locations);
		locationsBox = new JComboBox(locations);//locationBox = new JComboBox
		save.addActionListener(controller);
		
		this.add(location);
		this.add(locationsBox);
		this.add(part);
		this.add(partsBox);
		this.add(quantity);
		this.add(qText);
		this.add(save);
		
	}
	
	public String getTitle(){
		return myTitle;
	}
	
	public String getLocationValue(){
		String str = (String)locationsBox.getSelectedItem();
		return str;
	}
	
	public String getPartValue(){
		String str = (String)partsBox.getSelectedItem();
		return str;
	}
	
	public int getQText(){
		return Integer.parseInt(qText.getText());
	}

}
