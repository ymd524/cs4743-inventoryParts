package MVC.controllers;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JScrollPane;
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
	private ArrayList<String> partList = new ArrayList();
	private String[] parts;
	//private JList list = new JList(test);
	public showInventoryController(inventoryListView view, inventoryModel model){
		this.view = view;
		this.model = model;	
		//view.add(new JScrollPane(list),BorderLayout.CENTER);
	}

	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		String command = view.getSelectedValue();//gets and assigns the selected value from JList to command
		//String partId = model.getLocationIdPartByName(command);
		partList = model.getLocationIdPartByName(command);
		//partList = null;
	    parts = new String[partList.size()];//creates new String array the size of ArrayList namesArray
		parts = partList.toArray(parts);//assigns values from arrayList to array
		
		view.repaint();
		//view.add(new JScrollPane(list),BorderLayout.CENTER);/*adds list and add button to jframe*/
		//String command = view.getSelectedValue();//gets and assigns the selected value from JList to command
		System.out.println("command = " +command);
		//int id = model.getLocationIdPartByName(command);
		
		
	}
}
