package MVC.views.productsViews;

import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import MVC.controllers.productControllers.editProductController;
import MVC.controllers.productControllers.productDetailController;
import MVC.models.productModel;

public class productDetailView extends JFrame {

	public productModel model; 
	public JLabel numLabel;
	public JLabel descLabel;
	public JLabel addedLabel;
	public JLabel modifiedLabel;
	public String productNumber;
	public String productDesc;
	public Date dateAdded;
	public Date dateModified;
	public JButton editButton = new JButton("Edit Product");
	public JButton deleteButton = new JButton("Delete Product");
	
	public productDetailView(productModel model){
		this.model = model;
	
		GridLayout grid = new GridLayout(4,7);

		productNumber = model.getProductNumber();/*gets and assigns values of currentObject*/
		productDesc = model.getProductDesc();
		dateAdded = model.getDateAdded();
		dateModified = model.getDateModified();
		numLabel = new JLabel("Product Number: " + productNumber);/*creates JLabels displaying values*/
		descLabel = new JLabel("Product Description: " + productDesc);
		addedLabel = new JLabel("Date added: " + dateAdded);
		modifiedLabel = new JLabel("Date Modified: " + dateModified);

		this.setLayout(grid);
		this.add(numLabel);/*creates and adds buttons and labels to JFrame*/
		this.add(descLabel); 
		this.add(addedLabel);
		this.add(modifiedLabel);
		this.add(editButton);
		this.add(deleteButton);
	
	}
	
	public void registerListeners(productDetailController detailController) {
		editButton.addActionListener(detailController);
		deleteButton.addActionListener(detailController);
	}
}
