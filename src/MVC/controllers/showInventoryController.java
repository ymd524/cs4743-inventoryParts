package MVC.controllers;

import java.sql.ResultSet;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MVC.models.addPartModel;
import MVC.models.inventoryModel;
import MVC.views.inventoryListView;
import MVC.views.partDetailView;


public class showInventoryController implements ListSelectionListener  {

	private inventoryListView view;
	private inventoryModel model;
	private addPartModel addModel;
	private partDetailView detailView;
	private ResultSet results;
	
	public showInventoryController(inventoryListView view, inventoryModel model){
		this.view = view;
		this.model = model;		
	}

	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		String command = view.getSelectedValue();//gets and assigns the selected value from JList to command
		System.out.println(command);
		int id = model.getLocationIdPartByName(command);
		
		
	}
}
