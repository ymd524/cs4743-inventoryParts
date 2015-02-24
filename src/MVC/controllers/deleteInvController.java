package MVC.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MVC.models.inventoryModel;
import MVC.views.deleteInvView;
import MVC.views.deletePartsView;
import MVC.views.inventoryListView;

public class deleteInvController implements ActionListener {
	private inventoryModel model;
	private deleteInvView view;
	
	public deleteInvController(inventoryModel model, deleteInvView dView){
		this.model = model;
		this.view = dView;
	}
	
	@Override
    public void actionPerformed(ActionEvent e)
    {
		String command = e.getActionCommand();//gets command values from deletePartView
		if (command.equals("Cancel")) {
			view.closeWindow();//close deletePartView
		}else if (command.equals("Delete")){
			model.deletePart(model.getCurrentInvId());
			model.resetList();//restarts the showPartsView for updated list values
			view.closeWindow();//closes deletePartView
		}	
    }
}
