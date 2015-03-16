package MVC.views;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import MVC.controllers.menuController;
import MVC.controllers.showInvListController;
import MVC.controllers.showInventoryController;
import MVC.controllers.showPartsController;
import MVC.models.addPartModel;
import MVC.models.inventoryItem;
import MVC.models.inventoryModel;

public class inventoryListView extends JFrame {
	private inventoryModel model;
	private JList list;
	private JButton addButton = new JButton("Add New Inventory Item");
	private ArrayList<String> locationList = new ArrayList();
	private ArrayList<String> partsList = new ArrayList();
	private String[] parts;
	private String[] locations;
	private showInventoryController controller1;
	private menuController controller2;
	public inventoryListView(inventoryModel model){
		super("Inventory List");
		this.model = model;
		controller1 = new showInventoryController(this, this.model);
		//controller2 = new menuController(this.model, this);
		
		model.setInvView(this);
		//delete part entry and Inventory item entry
		addList();
	}
	
	public void addList(){
		locationList=model.getLocationsArray();//gets ArrayList of items
		locations = new String[locationList.size()];//creates new String array the size of ArrayList namesArray
		locations = locationList.toArray(locations);//assigns values from arrayList to array
		
		list = new JList(locations);/*creates and formats list with names array values as elements*/
		list.setVisibleRowCount(5);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFixedCellHeight(44);
		list.setFixedCellWidth(100);
		this.add(new JScrollPane(list),BorderLayout.WEST);/*adds list and add button to jframe*/
		this.add(addButton, BorderLayout.SOUTH);
	}
	
	public void registerListeners(showInventoryController controller1, menuController controller2) {
		list.addListSelectionListener(controller1);
		addButton.addActionListener(controller2);
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
	
	public menuController getMenuController(){
		return controller2;
	}
}
