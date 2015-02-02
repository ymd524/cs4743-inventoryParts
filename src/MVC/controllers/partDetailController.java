package MVC.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MVC.models.inventoryModel;
import MVC.views.deletePartsView;
import MVC.views.editPartView;
import MVC.views.partDetailView;

public class partDetailController implements ActionListener{
		private partDetailView view;
		private inventoryModel model;
		private editPartView editView;
		private deletePartsView deleteView;
	
	public partDetailController(inventoryModel model, partDetailView view){
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();//gets command from button panel in the partDetailView
		if (command.equals("Edit Part")) {	
			editView = new editPartView(model);//creates editPartView
			editPartController editController = new editPartController(model, editView);//creates editPartController
			editView.registerListeners(editController);// registers editPartController as listener
			editView.setSize(350, 250);/*starts new editPartView*/
			editView.setVisible(true);
		
		}else if (command.equals("Delete Part")){
			deleteView = new deletePartsView(model);//creates deletePartView
			deletePartController deleteController = new deletePartController(model, deleteView);//creates deletePartController
			deleteView.registerListeners(deleteController);//registers deletePartController as listener
			deleteView.setSize(275, 85);/*starts new deletePartView*/
			deleteView.setVisible(true);
		}
	}
}
