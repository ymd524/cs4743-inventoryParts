package MVC.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import MVC.models.inventoryModel;
import MVC.views.editInvView;
import MVC.views.editPartView;
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

	public editInvController(inventoryModel model, editInvView eView) {
		// TODO Auto-generated constructor stub]
		this.model = model;
		this.view = eView;
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

			model.checkNameLength(name);
			model.checkQuantity(q);
			model.checkName(name);
			if (model.getFlag() == 0) {
				//model.updatePart(num, name, ven, ext, unit);
				model.updateInv(name, loc, quantity);
				
				model.resetInv();
				view.closeWindow();// close editPartView
			} else if (model.getFlag() == 1) {
				errorView = new errorView(model);// creates new errorView
				errorView.setSize(400, 300);/* starts new errorView */
				errorView.setVisible(true);
			}
		}
	}
}
