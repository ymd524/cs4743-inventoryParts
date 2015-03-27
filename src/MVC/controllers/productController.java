package MVC.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MVC.models.inventoryModel;
import MVC.models.productModel;
import MVC.views.addInvsView;
import MVC.views.cProductView;
import MVC.views.errorView;

public class productController implements ActionListener {
	private cProductView view;
	private inventoryModel model;
	private productModel proModel;
	private errorView errorView;
	
	public productController(inventoryModel model, productModel proModel, cProductView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		this.model = model;
		this.proModel = proModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if (command.equals("Cancel")) {
			view.closeWindow();
		}else if (command.equals("Create Product")){
			String product = view.getProduct();
			String location = view.getLoc();
			System.out.println("product = " +product+ ", loccation = " +location);
			
			int productId = proModel.getProductId(product);
			System.out.println("productID = " +productId);
			model.checkLoc(location);
			//model.checkPL(name, loc);
			
			
			if(model.getFlag() == 0){
				//model.addInventoryItem(name, loc, quantity);
				model.resetInv();
				model.resetList();
				view.closeWindow();
			} else if (model.getFlag() == 1) {
				errorView = new errorView(model);
				errorView.setSize(400, 300);
				errorView.setVisible(true);
			}
		}
		
	}
}
