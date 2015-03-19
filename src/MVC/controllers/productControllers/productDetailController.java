package MVC.controllers.productControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MVC.controllers.productControllers.deleteProductController;
import MVC.controllers.productControllers.editProductController;
import MVC.models.productModel;
import MVC.views.productsViews.deleteProductsView;
import MVC.views.productsViews.editProductsView;
import MVC.views.productsViews.productDetailView;

public class productDetailController implements ActionListener{

	private productDetailView view;
	private productModel model;
	private deleteProductsView deleteView;
  	private editProductsView editView;
	
	public productDetailController(productModel model, productDetailView view){

		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();//gets command from button panel in the partDetailView
		if (command.equals("Edit Product")) {	
			editView = new editProductsView(model);//creates editPartView
			editProductController editController = new editProductController(model, editView);//creates editPartController
			editView.registerListeners(editController);// registers editProductController as listener
			editView.setSize(350, 250);/*starts new editPartView*/
			editView.setVisible(true);
		
		}else if (command.equals("Delete Product")){
			deleteView = new deleteProductsView(model);//creates deletePartView
			deleteProductController deleteController = new deleteProductController(model, deleteView);//creates deletePartController
			deleteView.registerListeners(deleteController);//registers deletePartController as listener
			deleteView.setSize(275, 85);/*starts new deletePartView*/
			deleteView.setVisible(true);
		}
	}
	
}
