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
import MVC.views.productsViews.addProductsView;

public class addProductController implements ActionListener{
	private addProductsView view;
	private productModel model;
	//private showPartsView showView;
	private String[] names;
	private String error;
	private ArrayList<String> namesArray = new ArrayList();
	private errorView errorView;

	
	public addProductController(productModel model, addProductsView addView){
		this.view = addView;
		this.model = model;		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();//gets command value from buttonPanel in addPartView
		if (command.equals("Cancel")) {
			view.closeWindow();//closes addPartView
		}else if (command.equals("Add Part")){
			String desc = view.getDesc();/*sets values from addPartView's text fields*/
			String num = view.getNum();
			model.addProduct(num, desc);
		}
	}	


}
