/**
 * 
 */
package MVC.controllers;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MVC.models.inventoryModel;
import MVC.views.deleteInvView;
import MVC.views.deletePartsView;
import MVC.views.editInvView;
import MVC.views.editPartView;
import MVC.views.inventoryListView;
import MVC.views.showPartsView;

public class showInvListController implements ActionListener {
	private inventoryListView view;
	private inventoryModel model;
	private editInvView eView;
	private deleteInvView dView;
	public showInvListController(inventoryModel model, inventoryListView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		this.model = model;	
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String command = arg0.getActionCommand();//gets command from button panel in the partDetailView
		if (command.equals("Edit Part")) {	
			eView = new editInvView(model);//creates editPartView
			editInvController editInventoryController = new editInvController(model, eView);//creates editPartController
			eView.registerListeners(editInventoryController);// registers editPartController as listener
			eView.setSize(350, 250);/*starts new editPartView*/
			eView.setVisible(true);
		
		}else if (command.equals("Delete Part")){
			dView = new deleteInvView(model);//creates deletePartView
			deleteInvController deleteController = new deleteInvController(model, dView);//creates deletePartController
			dView.registerListeners(deleteController);//registers deletePartController as listener
			dView.setSize(275, 85);/*starts new deletePartView*/
			dView.setVisible(true);
		}

	}

}
