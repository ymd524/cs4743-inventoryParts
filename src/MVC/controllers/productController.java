package MVC.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	private ArrayList<String> partsList = new ArrayList();
	private ArrayList<String> parts = new ArrayList();
	private ArrayList<String> products = new ArrayList();
	private int flag = 0;

	public productController(inventoryModel model, productModel proModel,
			cProductView view) {
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
		} else if (command.equals("Create Product")) {
			String product = view.getProduct();
			String location = view.getLoc();
			parts.clear();
			// System.out.println("product = " +product+ ", loccation = "
			// +location);
			// int locId = model.getLocationId(location);
			parts = model.getLocationIdPartByName(location);
			// System.out.println("parts at location selected = "
			// +parts.toString());
			flag = model.getProductFlag();
			if (flag == 1) {
				products = model.getProductsArray(location);
				// System.out.println("product Arraylist = "
				// +products.toString());
				for (String tmp : products) {
					// System.out.println("products");
					int pid = Integer.parseInt(tmp);
					String productDesc = proModel.getProductDescById(pid);
					parts.add(productDesc);
					break;
				}
				model.resetSearch();
			}
			// System.out.println("parts at location selected = "+parts.toString());
			int productId = proModel.getProductIdByDesc(product);
			proModel.setId(productId);
			proModel.getAllProductParts();
			partsList = proModel.getProductPartsArray();
			// System.out.println("product parts list = "
			// +partsList.toString());

			model.checkCreation(parts, partsList);

			int exist = model.checkProductQ(product);
			if (exist == 1) {
				model.updateProductQ(product, productId);
				model.resetInv();
				model.resetList();
				view.closeWindow();
			} else {
				if (model.getFlag() == 0) {
					model.addProduct(productId, location);
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
}
