package MVC.controllers;

import java.awt.event.*;

import javax.swing.JFrame;

import MVC.models.inventoryModel;
import MVC.models.productModel;
import MVC.views.addInvsView;
import MVC.views.addPartsView;
import MVC.views.cProductView;
import MVC.views.showPartsView;

public class menuController implements ActionListener{
	
	private inventoryModel model;
	private showPartsView view;
	private addPartsView addView;
	private addInvsView addinvView;
	private cProductView cProductView;
	private productController pController;
	private productModel proModel;
	
	public menuController(inventoryModel model, showPartsView view, productModel proModel){
		this.model = model;
		this.view = view;
		this.proModel = proModel;
	}
	
	@Override
    public void actionPerformed(ActionEvent e){	
		String command = e.getActionCommand();
		if (command.equals("Add new part")) {
			addView = new addPartsView(model);//creates addPartsView
			addPartController addController = new addPartController(model, addView);//creates addPartController
			addView.registerListeners(addController);//register addPartController as listener
			addView.setSize(400, 300);/* starts new addPartView*/
			addView.setVisible(true);
			//System.out.println("menuController = " +command);
		} else if (command.equals("Add New Inventory Item")) {
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
