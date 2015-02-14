package MVC.controllers;

import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MVC.models.addPartModel;
import MVC.models.inventoryModel;
import MVC.views.partDetailView;
import MVC.views.showPartsView;

public class showPartsController implements ListSelectionListener {
	
	private showPartsView view;
	private inventoryModel model;
	private addPartModel addModel;
	private partDetailView detailView;
	private ResultSet results;
	
	public showPartsController(showPartsView view, inventoryModel model){
		this.view = view;
		this.model = model;		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		String command = view.getSelectedValue();//gets and assigns the selected value from JList to command
		model.setCurrentPartByName(command);

		detailView = new partDetailView(model);//creates partDetailView
		partDetailController detailController = new partDetailController(model, detailView);//creates partDetailController
		detailView.registerListeners(detailController);//registers partDetailController as listener
		detailView.setSize(350, 250);/* start new partDetailView */
		detailView.setVisible(true);		
	}
}
