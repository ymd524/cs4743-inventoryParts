package MVC.controllers;

import java.awt.event.*;
import java.util.ArrayList;
import MVC.models.inventoryModel;
import MVC.views.editPartView;
import MVC.views.errorView;

public class editPartController implements ActionListener {
		public inventoryModel model;
		public editPartView view;
		public String currName;
		public errorView errorView;
		private String error;
		private String[] names;
		private ArrayList<String> namesArray = new ArrayList();
		
	public editPartController(inventoryModel model, editPartView view){
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e)     {
		String command = e.getActionCommand();//gets values from editPanel in editPartView
		if (command.equals("Cancel")) {
			view.closeWindow();
		} else if (command.equals("Save")){
			String name = view.getNameText();/*gets values submitted for currentObject update*/
			String num = view.getNumText();
			String ven = view.getVText();
			String unit = view.getUnitText();
			String ext = view.getExtText();

			model.checkRequiredFields(name, num);
			model.checkNameLength(name);
			model.checkNumLength(num);
			model.checkVendorLength(ven);
			model.checkExtLength(ext);
			model.checkName(name);
			model.checkUnit(unit);
			
			if(model.getFlag() == 0){
				model.updatePart(num, name, ven, ext, unit);//values updates if necessary
				model.resetList();//restarts the showPartsView for updated list values
				view.closeWindow();//close editPartView
			} else if(model.getFlag() == 1){
				errorView = new errorView(model);//creates new errorView
				errorView.setSize(400, 300);/* starts new errorView*/
				errorView.setVisible(true);
			}
		}
    }
	
}
