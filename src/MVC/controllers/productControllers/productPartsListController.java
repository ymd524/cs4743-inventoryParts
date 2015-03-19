package MVC.controllers.productControllers;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MVC.models.productModel;
import MVC.views.productsViews.productDetailView;
import MVC.views.productsViews.productPartDetailView;
import MVC.views.productsViews.productPartsListView;
import MVC.views.productsViews.showTemplatesView;


public class productPartsListController implements ListSelectionListener{
	
	public productModel model;
	public productPartsListView view;
	public productPartDetailView detailView;
	
	public productPartsListController(productPartsListView view, productModel model){
		this.view = view;
		this.model = model; 
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		String command = view.getSelectedValue();//gets and assigns the selected value from JList to command
		String[] tokens = command.split(" ");
		model.setPartByName(tokens[0]);
		
		
		detailView = new productPartDetailView(model);//creates partDetailView
		//productPartsListController Controller = new productPartsListController(view, model);//creates partDetailController
		//view.registerListeners(Controller);//registers partDetailController as listener
		detailView.setSize(350, 250);/* start new partDetailView */
		detailView.setVisible(true);
	}
}