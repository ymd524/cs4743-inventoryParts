package MVC.controllers.productControllers;

import java.awt.event.*;
import java.util.ArrayList;

import MVC.models.inventoryModel;
import MVC.models.productModel;
import MVC.views.editPartView;
import MVC.views.errorView;
import MVC.views.productsViews.editProductsView;

public class editProductController implements ActionListener {
		public productModel model;
		public editProductsView view;
		public String currName;
		public errorView errorView;
		private String error;
		private String[] names;
		private ArrayList<String> namesArray = new ArrayList();
		
	public editProductController(productModel model, editProductsView view){
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e)     {
		String command = e.getActionCommand();//gets values from editPanel in editPartView
		if (command.equals("Cancel")) {
			view.closeWindow();
		} else if (command.equals("Save")){
			String desc = view.getDescText();/*gets values submitted for currentObject update*/
			String num = view.getNumText();


			/*model.checkRequiredFields(name, num);
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
				errorView.setSize(400, 300);/* starts new errorView
				errorView.setVisible(true);
			}*/
		}
    }
	
}
