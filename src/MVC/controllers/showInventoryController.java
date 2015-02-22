package MVC.controllers;

import java.awt.BorderLayout;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
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
	private ArrayList<String> partList = new ArrayList<String>();
	private String[] parts;
	private JList list;
	private MVC.controllers.invDetailView invDetailView;
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
		list.setVisibleRowCount(5);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFixedCellHeight(44);
		list.setFixedCellWidth(100);
		
		
		view.add(new JScrollPane(list),BorderLayout.CENTER);
		view.setVisible(true);
		//view.repaint();
		list.addListSelectionListener(new ListSelectionListener() {
	         //private inventoryModel model;
			//private inventoryListView view;

			@Override
	         public void valueChanged(ListSelectionEvent e2) {
	     		if (e2.getValueIsAdjusting())
	    			return;
	     		
	            System.out.println(e2);
	    		invDetailView = new MVC.controllers.invDetailView(model);
	    		/*showInvListController controller1 = new showInvListController(this.model, this.view);
	    		invDetailView.registerListeners(controller1);*/
	    		invDetailView.setSize(250, 150);
	    		invDetailView.setLocation(800, 0);
	    		invDetailView.setVisible(true);
	    		view.repaint();
	    		view.revalidate();
	         }
	      });
		/*invDetailView = new MVC.controllers.invDetailView(model);
		showInvListController controller1 = new showInvListController(this.model, this.view);
		invDetailView.registerListeners(controller1);
		invDetailView.setSize(350, 250);
		invDetailView.setLocation(500, 40);
		invDetailView.setVisible(true);	*/
		partList.clear();
		
	}
}
