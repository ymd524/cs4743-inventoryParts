package MVC.views;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import MVC.controllers.menuController;
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
	private String[] locations;
	private JList list2;//Zedit
	public inventoryListView(inventoryModel model){
		super("Inventory List");
		this.model = model;
		addList();
	}
	
	
	public void addList(){
		locationList=model.getLocationsArray();//gets ArrayList of items
		locations = new String[locationList.size()];//creates new String array the size of ArrayList namesArray
		locations = locationList.toArray(locations);//assigns values from arrayList to array
		
		list = new JList(locations);/*creates and formats list with names array values as elements*/
		list2 = new JList(locations);//Zedit
		list.setVisibleRowCount(5);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFixedCellHeight(44);
		list.setFixedCellWidth(100);
		
		this.add(new JScrollPane(list),BorderLayout.WEST);/*adds list and add button to jframe*/
		//this.add(new JScrollPane(list2), BorderLayout.CENTER);//Zedit
		this.add(addButton, BorderLayout.SOUTH);
	}
	
	public void registerListeners(showInventoryController controller1, menuController controller2) {
		System.out.println("made it here");
		list.addListSelectionListener(controller1);
		//lcombo.addListSelectionListener(controller1);
		addButton.addActionListener(controller2);

	}
	
	public String getSelectedValue() {
		return list.getSelectedValue().toString();
	}
}
