package MVC.views;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import MVC.controllers.productController;
import MVC.models.inventoryModel;
import MVC.models.productModel;

public class cProductView extends JFrame {
	private inventoryModel model;
	private productModel proModel;
	private JPanel addPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JLabel locLabel = new JLabel("Location: ");
	private JLabel pLabel = new JLabel("Product: ");
	private JButton addButton = new JButton("Create Product");
	private JButton cancelButton = new JButton("Cancel");
	private JComboBox combo1;
	private JComboBox combo2;
	private ArrayList<String> loclist = new ArrayList();
	private String[] locations;
	private ArrayList<String> productlist = new ArrayList();
	private String[] products;
	
	public cProductView(inventoryModel model, productModel proModel) {
		super("Create Product");
		// TODO Auto-generated constructor stub
		this.model =  model;
		this.proModel = proModel;
		GridLayout grid = new GridLayout(3,2);
		
		loclist = model.getLocationsArray();
		locations = new String[loclist.size()];
		locations = loclist.toArray(locations);
		combo1 = new JComboBox(locations);
		
		//System.out.println("set 1");
		proModel.getProductsDescription();
		productlist = proModel.getProductDescArray();
		//System.out.println("set 2");
		products = new String[productlist.size()];
		products = productlist.toArray(products);
		combo2 = new JComboBox(products);
		
		this.setLayout(grid);;		
		
		this.add(locLabel);
		this.add(combo1);
		
		this.add(pLabel);
		this.add(combo2);
		
		this.add(addButton);
		this.add(cancelButton);
	}

	public void registerListeners(productController pController) {
		// TODO Auto-generated method stub
		cancelButton.addActionListener(pController);
		addButton.addActionListener(pController);
	}
	
	public void closeWindow (){
		this.dispose();
	}
	
	public String getLoc(){
		String str = (String)combo1.getSelectedItem();
		return str;
	}

	public String getProduct() {
		String str = (String)combo2.getSelectedItem();
		return str;
	}
}
