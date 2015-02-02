package MVC.controllers;

import java.awt.event.*;

import javax.swing.JFrame;

import MVC.models.inventoryModel;
import MVC.views.addPartsView;
import MVC.views.showPartsView;

public class menuController implements ActionListener{
	
	private inventoryModel model;
	private showPartsView view;
	private addPartsView addView;
	
	public menuController(inventoryModel model, showPartsView view){
		this.model = model;
		this.view = view;
	}
	
	@Override
    public void actionPerformed(ActionEvent e)
    {	
		addView = new addPartsView(model);//creates addPartsView
		addPartController addController = new addPartController(model, addView);//creates addPartController
		addView.registerListeners(addController);//register addPartController as listener
		addView.setSize(400, 300);/* starts new addPartView*/
		addView.setVisible(true);
    }
	
}
