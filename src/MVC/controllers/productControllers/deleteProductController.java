package MVC.controllers.productControllers;
//package MVC.controllers;

import java.awt.event.*;

import javax.swing.text.View;

import MVC.models.productModel;
import MVC.views.deletePartsView;
import MVC.views.productsViews.deleteProductsView;

public class deleteProductController implements ActionListener{
	private productModel model;
	private deleteProductsView view;
	
	public deleteProductController(productModel model, deleteProductsView view){
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
			//model.deletePart(model.getCurrentPartId());//deletes currentObject from arrayList and corrsponding name in names arrayList
			//model.resetList();//restarts the showPartsView for updated list values
			view.closeWindow();//closes deletePartView
		}	
    }
}