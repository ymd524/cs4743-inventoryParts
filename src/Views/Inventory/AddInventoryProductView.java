package Views.Inventory;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controllers.Inventory.AddInventoryProductController;
import MVC.MasterFrame;
import Models.InventoryModel;
import Models.PartModel;
import Models.ProductTemplateModel;

public class AddInventoryProductView extends JPanel{
	private MasterFrame master;
	private InventoryModel model;
	private ProductTemplateModel productModel;
	private String[] locations;
	private String[] products;
	private String myTitle = "Add Inventory Product";
	private ArrayList<String> productList = new ArrayList();
	private ArrayList<String> locationsList = new ArrayList();
	JComboBox locationsBox;
	JComboBox productsBox;
	JLabel location = new JLabel("Choose Location");
	JLabel product = new JLabel("Choose Product");
	JButton save = new JButton("Save");
	JTextField qText = new JTextField(20);
	
	public AddInventoryProductView(MasterFrame m){
		master = m;
		model = master.getInventoryModel();
		AddInventoryProductController controller = new AddInventoryProductController(this, master);
		this.setLayout(new GridLayout(3,1));
		productModel = master.getProductTemplateModel();
		productModel.setProductArray();
		productList = productModel.getProductArraylist();
		products = new String[productList.size()];
		products = productList.toArray(products);
		productsBox = new JComboBox(products);
		
		model.setLocationsArrayList();
		locationsList = model.getLocationsArrayList();
		locations = new String[locationsList.size()];
		locations = locationsList.toArray(locations);
		locationsBox = new JComboBox(locations);
		
		save.addActionListener(controller);

		this.add(location);
		this.add(locationsBox);
		this.add(product);
		this.add(productsBox);
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
		String str = (String)productsBox.getSelectedItem();
		return str;
	}
	
	public int getQText(){
		return Integer.parseInt(qText.getText());
	}
}
