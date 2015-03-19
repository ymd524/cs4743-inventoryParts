package MVC.controllers.productControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import MVC.models.inventoryModel;
import MVC.models.productModel;
import MVC.views.addPartsView;
import MVC.views.errorView;
import MVC.views.showPartsView;
import MVC.views.productsViews.addProductPartsView;
import MVC.views.productsViews.addProductsView;
import MVC.views.productsViews.deleteProductPartsView;

public class deleteProductPartController implements ActionListener{
	private deleteProductPartsView view;
	private productModel model;
	//private showPartsView showView;
	private String[] names;
	private String error;
	private ArrayList<String> namesArray = new ArrayList();
	private errorView errorView;

	
	public deleteProductPartController(productModel model, deleteProductPartsView addView){
		this.view = addView;
		this.model = model;		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();//gets command value from buttonPanel in addPartView
		if (command.equals("Cancel")) {
			view.closeWindow();//closes addPartView
		}else if (command.equals("Delete Part")){
			String partName = view.getPartName();/*sets values from addPartView's text fields*/
			model.deletePartFromProduct(partName);
			view.closeWindow();

		}
	}	


}
