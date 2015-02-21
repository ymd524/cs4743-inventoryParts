package MVC.controllers;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MVC.models.addPartModel;
import MVC.models.inventoryModel;
import MVC.views.addPartsView;
import MVC.views.inventoryListView;
import MVC.views.partDetailView;


public class showInventoryController implements ListSelectionListener  {

	private inventoryListView view;
	private inventoryModel model;
	private addPartModel addModel;
	private partDetailView detailView;
	private ResultSet results;
	private ArrayList<String> partList = new ArrayList();
	private String[] parts;
	//private String[] links = {"old", "example"};
	private JList list;
	public showInventoryController(inventoryListView view, inventoryModel model){
		this.view = view;
		this.model = model;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;
		  
		partList.clear();
		String command = view.getSelectedValue();//gets and assigns the selected value from JList to command
		partList = model.getLocationIdPartByName(command);		
		partList = model.getPartsL();
		parts = new String[partList.size()];
		parts = partList.toArray(parts);
		list = new JList(parts);
		view.add(new JScrollPane(list),BorderLayout.CENTER);
		view.setVisible(true);
		partList.clear();
		
	}
}
