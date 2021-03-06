package MVC.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import MVC.controllers.invMenuController;
import MVC.controllers.menuController;
import MVC.controllers.showInvListController;
import MVC.controllers.showInventoryController;
import MVC.controllers.showPartsController;
import MVC.models.addPartModel;
import MVC.models.inventoryItem;
import MVC.models.inventoryModel;
import MVC.models.productModel;

public class inventoryListView extends JFrame {
	private inventoryModel model;
	private productModel proModel;
	private JList list;
	private JButton addButton = new JButton("Add New Inventory Item");
	private JButton createProduct = new JButton("Create Product");
	private ArrayList<String> locationList = new ArrayList();
	private ArrayList<String> partsList = new ArrayList();
	private String[] parts;
	private String[] locations;
	private showInventoryController controller1;
	private invMenuController controller2;
	//private menuController controller3;
	public inventoryListView(inventoryModel model, productModel proModel){
		super("Inventory List");
		this.model = model;
		this.proModel = proModel;
		
		controller1 = new showInventoryController(this, this.model, this.proModel);
		controller2 = new invMenuController(this.model, this, this.proModel);
		
		model.setInvView(this);
		model.setProductModel(proModel);//works with resetting inventoryListView
		//delete part entry and Inventory item entry
		addList();
	}
	
	public void addList(){
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(1, 2));
		
		locationList=model.getLocationsArray();//gets ArrayList of items
		locations = new String[locationList.size()];//creates new String array the size of ArrayList namesArray
		locations = locationList.toArray(locations);//assigns values from arrayList to array
		
		list = new JList(locations);/*creates and formats list with names array values as elements*/
		list.setVisibleRowCount(5);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFixedCellHeight(44);
		list.setFixedCellWidth(100);
		this.add(new JScrollPane(list),BorderLayout.WEST);/*adds list and add button to jframe*/
		subPanel.add(addButton, BorderLayout.WEST);
		subPanel.add(createProduct, BorderLayout.EAST);
		this.add(subPanel, BorderLayout.SOUTH);
	}
	
	public void registerListeners(showInventoryController controller1,
			invMenuController menuController2) {
		list.addListSelectionListener(controller1);
		//addButton.addActionListener(controller2);
		addButton.addActionListener(menuController2);
		createProduct.addActionListener(menuController2);
	}
	
	public String getSelectedValue() {
		return list.getSelectedValue().toString();
	}
	
	public void removeListInv(){
		this.dispose();
	}
	
	public showInventoryController getController(){
		return controller1;
	}
	
	public invMenuController getMenuController(){
		return controller2;
	}
}
