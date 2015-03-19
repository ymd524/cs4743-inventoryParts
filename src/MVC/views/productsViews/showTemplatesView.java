package MVC.views.productsViews;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import MVC.controllers.addPartController;
import MVC.controllers.menuController;
import MVC.controllers.showPartsController;
import MVC.controllers.productControllers.addProductController;
import MVC.controllers.productControllers.showTemplatesController;
import MVC.models.inventoryModel;
import MVC.models.productModel;

public class showTemplatesView extends JFrame {
	private productModel model;
	private showTemplatesController controller;
	private JList list;
	private JButton addButton = new JButton("Add Product Template");
	private String[] products;
	private ArrayList<String> productArray = new ArrayList();
	private menuController menuController;
	private addProductsView addView;
	
	public showTemplatesView (productModel model){
		super("Products List");
		this.model = model;
		addList();//creates and adds JList to jframe with current arrayList values
		}
		
		public void addList(){
			productArray=model.getAllProducts();//gets ArrayList of names
			products = new String[productArray.size()];//creates new String array the size of ArrayList namesArray
			products = productArray.toArray(products);//assigns values from arrayList to array
			
			list = new JList(products);/*creates and formats list with names array values as elements*/
			list.setVisibleRowCount(5);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.setFixedCellHeight(44);
			list.setFixedCellWidth(100);
			
			this.add(new JScrollPane(list),BorderLayout.CENTER);/*adds list and add button to jframe*/
			this.add(addButton, BorderLayout.SOUTH);
		}
		
	public void registerListeners(showTemplatesController controller1) {
			list.addListSelectionListener(controller1);
			addButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{

					addView = new addProductsView(model);//creates addPartsView
					addProductController addController = new addProductController(model, addView);//creates addPartController
					addView.registerListeners(addController);//register addPartController as listener
					addView.setSize(400, 300);/* starts new addPartView*/
					addView.setVisible(true);
				}
			}); 
	}
	
	public void closeWindow(){
		this.closeWindow();
	}
	
	public String getSelectedValue() {
		return list.getSelectedValue().toString();
	}
}
