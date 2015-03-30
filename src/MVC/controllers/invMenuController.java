package MVC.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MVC.models.inventoryModel;
import MVC.models.productModel;
import MVC.views.addInvsView;
import MVC.views.addPartsView;
import MVC.views.inventoryListView;
import MVC.views.cProductView;

public class invMenuController implements ActionListener {
	private inventoryModel model;
	private inventoryListView view;
	private addInvsView addinvView;
	private cProductView cProductView;
	private productController pController;
	private productModel proModel;

	public invMenuController(inventoryModel model, inventoryListView view, productModel proModel) {
		this.model = model;
		this.view = view;
		this.proModel = proModel;
	}
	@Override
    public void actionPerformed(ActionEvent e){	
		String command = e.getActionCommand();
		if (command.equals("Add New Inventory Item")) {
			addinvView = new addInvsView(model);
			addInvController addInvController = new addInvController(model, addinvView);
			addinvView.registerListeners(addInvController);
			addinvView.setSize(400, 300);
			addinvView.setVisible(true);
			//System.out.println("menuController = " +command);
		} else if (command.equals("Create Product")) {
			//System.out.println("Creating Product button pushed");
			cProductView = new cProductView(model, proModel);
			productController pController = new productController(model, proModel, cProductView);
			cProductView.registerListeners(pController);
			cProductView.setSize(500, 200);
			cProductView.setLocation(400, 300);
			cProductView.setVisible(true);
		}
    }
}