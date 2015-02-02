package MVC.controllers;

import java.awt.event.*;

import javax.swing.text.View;

import MVC.models.inventoryModel;
import MVC.views.deletePartsView;

public class deletePartController implements ActionListener{
	private inventoryModel model;
	private deletePartsView view;
	
	public deletePartController(inventoryModel model, deletePartsView view){
		this.model = model;
		this.view = view;
	}
	
	@Override
    public void actionPerformed(ActionEvent e)
    {
		String command = e.getActionCommand();//gets command values from deletePartView
		if (command.equals("Cancel")) {
			view.closeWindow();//close deletePartView
		}else if (command.equals("Delete")){
			model.deletePart();//deletes currentObject from arrayList and corrsponding name in names arrayList
			model.resetList();//restarts the showPartsView for updated list values
			view.closeWindow();//closes deletePartView
		}	
    }
}
