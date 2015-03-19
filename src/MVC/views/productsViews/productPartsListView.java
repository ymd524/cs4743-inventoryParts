package MVC.views.productsViews;


import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import MVC.controllers.menuController;
import MVC.controllers.showPartsController;
import MVC.controllers.productControllers.addProductPartController;
import MVC.controllers.productControllers.deleteProductPartController;
import MVC.controllers.productControllers.productPartsListController;
import MVC.models.inventoryModel;
import MVC.models.productModel;



public class productPartsListView extends JFrame{
	
	private productModel model;
	private JList list;
	private JPanel buttonPanel = new JPanel();
	private JButton addButton = new JButton("Add new part");
	private JButton deleteButton = new JButton("Delete part");
	private String[] parts;
	private ArrayList<String> partsArrayList = new ArrayList();
	private addProductPartsView addView;
	private addProductPartController addController;
	private deleteProductPartsView deleteView;
	private deleteProductPartController deleteController;
	
	
	public productPartsListView (productModel model){
		super("Product Parts List");
		this.model = model;
		addList();//creates and adds JList to jframe with current arrayList values
	}
	
	public void addList(){
		partsArrayList=model.getAllProductPartsList();//gets ArrayList of names
		parts = new String[partsArrayList.size()];//creates new String array the size of ArrayList namesArray
		parts = partsArrayList.toArray(parts);//assigns values from arrayList to array
		
		list = new JList(parts);/*creates and formats list with names array values as elements*/
		list.setVisibleRowCount(5);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFixedCellHeight(44);
		list.setFixedCellWidth(100);
		
		this.add(new JScrollPane(list),BorderLayout.CENTER);/*adds list and add button to jframe*/
		buttonPanel.add(addButton);
		buttonPanel.add(deleteButton);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
	}
	
	public void registerListeners(productPartsListController controller1) {
		list.addListSelectionListener(controller1);
	    addButton.addActionListener(new ActionListener() {
	        	 
	    	public void actionPerformed(ActionEvent e)
	        	{
	        		addView = new addProductPartsView(model);//creates partDetailView
	        		addController = new addProductPartController(model, addView);//creates partDetailController
	        		addView.registerListeners(addController);//registers partDetailController as listener
	        		addView.setSize(350, 250);/* start new partDetailView */
	        		addView.setVisible(true);
	            }
	        });  
	    
        deleteButton.addActionListener(new ActionListener() {
        	 
            public void actionPerformed(ActionEvent e)
            {
        		deleteView = new deleteProductPartsView(model);//creates partDetailView
        		deleteController = new deleteProductPartController(model, deleteView);//creates partDetailController
        		deleteView.registerListeners(deleteController);//registers partDetailController as listener
        		deleteView.setSize(350, 250);/* start new partDetailView */
        		deleteView.setVisible(true);
        		//model.closeView(this);
            }
        });   

	}
	
	public void closeWindow(){
		this.dispose();//disposes current instance of showPartsView
	}

	
	/*
	 * getters
	 * 
	 */
	
	
	public String getSelectedValue() {
		return list.getSelectedValue().toString();
	}

}
