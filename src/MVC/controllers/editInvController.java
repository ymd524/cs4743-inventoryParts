package MVC.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
//import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import MVC.models.inventoryModel;
import MVC.views.editInvView;
import MVC.views.errorView;
import MVC.views.inventoryListView;

public class editInvController implements ActionListener {
	public inventoryModel model;
	public editInvView view;
	public String currName;
	public errorView errorView;
	private String error;
	private String[] names;
	private ArrayList<String> namesArray = new ArrayList();
	private java.util.Date date;
	private Date d;

	public editInvController(inventoryModel model, editInvView eView, Date date3) {
		// TODO Auto-generated constructor stub]
		this.model = model;
		this.view = eView;
		model.getCurrentInv();
		d = date3;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Cancel")) {
			view.closeWindow();
		} else if (command.equals("Save")) {
			String name = view.getNameText();
			String loc = view.getLocText();
			String quantity = view.getQText();
			int q = Integer.parseInt(quantity);

			//System.out.println("name= " +name+ " location= " +loc+ " quantity=" +quantity);
			model.checkNameLength(name);
			model.checkQuantity(q);
			model.checkName(name);
			if (model.getFlag() == 0) {
				//model.setCurrentInventory(name);
				date = model.getCurrentTime();
	    		//System.out.println("DATE RIGHT BEFORE SAVING IN EDIT INV CONTROLLER = "+ date);
				if (d.equals(date)) {
					model.updateInv(name, loc, quantity);
					//System.out.println("Here in actionPerformed");
				} else {
					  JOptionPane.showMessageDialog(null, "Needs to refresh view, information has changed.");
				}
				model.resetInv();
				view.closeWindow();
			} else if (model.getFlag() == 1) {
				errorView = new errorView(model);// creates new errorView
				errorView.setSize(400, 300);/* starts new errorView */
				errorView.setVisible(true);
			}
		}
	}
}
