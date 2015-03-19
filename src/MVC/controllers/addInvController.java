package MVC.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MVC.models.inventoryModel;
import MVC.views.addInvsView;
import MVC.views.addPartsView;
import MVC.views.errorView;

public class addInvController implements ActionListener{
	private addInvsView view;
	private inventoryModel model;
	private errorView errorView;
	
	
	public addInvController(inventoryModel model, addInvsView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if (command.equals("Cancel")) {
			view.closeWindow();
		}else if (command.equals("Add Inventory Item")){
			String name = view.getName();
			String loc = view.getLocat();
			int quantity = view.getQuantity();
			
			//System.out.println("name = " +name+ ", loc = " +loc+ ", quantity = " +quantity);
			
			model.checkLoc(loc);
			//model.ch
			model.checkQuantity(quantity);
			model.checkPL(name, loc);
			
			
			if(model.getFlag() == 0){
				model.addInventoryItem(name, loc, quantity);
				model.resetInv();
				model.resetList();
				view.closeWindow();
			} else if (model.getFlag() == 1) {
				errorView = new errorView(model);
				errorView.setSize(400, 300);
				errorView.setVisible(true);
			}
		}
		
	}
}
