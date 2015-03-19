package MVC.controllers.productControllers;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MVC.models.inventoryModel;
import MVC.models.productModel;
import MVC.views.partDetailView;
import MVC.views.productsViews.productDetailView;
import MVC.views.productsViews.productPartDetailView;
import MVC.views.productsViews.productPartsListView;
import MVC.views.productsViews.showTemplatesView;

public class showTemplatesController implements ListSelectionListener{
	
	public showTemplatesView view;
	public productModel model;
	public productDetailView detailView;
	public productDetailController detailController;
	public productPartsListView partsListView;
	
	public showTemplatesController(showTemplatesView view, productModel model){
		this.view = view;
		this.model = model; 
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		String command = view.getSelectedValue();//gets and assigns the selected value from JList to command
		String[] tokens = command.split(" ");
		model.setProductByNum(tokens[0]);
		
		detailView = new productDetailView(model);//creates partDetailView
		productDetailController detailController = new productDetailController(model, detailView);//creates partDetailController
		detailView.registerListeners(detailController);//registers partDetailController as listener
		detailView.setSize(350, 250);/* start new partDetailView */
		detailView.setVisible(true);
		
		
		partsListView = new productPartsListView(model);
		productPartsListController productPartsController = new productPartsListController(partsListView, model);
		partsListView.registerListeners(productPartsController);
		partsListView.setSize(350, 250);/* start new partDetailView */
		partsListView.setVisible(true);
	}
}
