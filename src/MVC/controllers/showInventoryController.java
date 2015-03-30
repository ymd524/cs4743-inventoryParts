package MVC.controllers;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MVC.models.inventoryModel;
import MVC.models.productModel;
import MVC.views.inventoryListView;

public class showInventoryController implements ListSelectionListener  {

	private inventoryListView view;
	private inventoryModel model;
	private productModel proModel;
	private ArrayList<String> partList = new ArrayList<String>();
	private ArrayList<String> products = new ArrayList<String>();
	private String[] parts;
	private JList list;
	private MVC.views.invDetailView invDetailView;
	private Date date;
	private int flag =0;
	private int nonproducts =0;
	public showInventoryController(inventoryListView view, inventoryModel model, productModel proModel){
		this.view = view;
		this.model = model;
		this.proModel = proModel;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			return;
	    } else {
	    	//view.dispose();
	    	//view.removeAll();
	    	//view.repaint();
	    	//view.remove(list);
	    	//model.resetInv();
	    }
		partList.clear();
		//view.repaint();
		view.revalidate();
		//view.invalidate();
		//System.out.println("outer valueChanged " +e.getValueIsAdjusting());
		String command = view.getSelectedValue();
		partList = model.getLocationIdPartByName(command);		
		partList = model.getPartsL();
		nonproducts = partList.size();
		flag = model.getProductFlag();
		if (flag == 1) {
			products = model.getProductsArray(command);
			
			//System.out.println("product Arraylist = " +products.toString());

			for (String tmp : products) {
				//System.out.println("products");
				int pid = Integer.parseInt(tmp);
				String productDesc = proModel.getProductDescById(pid);
				partList.add(productDesc);
			}
			model.resetSearch();
		}
		parts = new String[partList.size()];
		parts = partList.toArray(parts);
		list = new JList(parts);
		list.setVisibleRowCount(5);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFixedCellHeight(44);
		list.setFixedCellWidth(100);
		view.add(new JScrollPane(list),BorderLayout.CENTER);
		view.setVisible(true);
		//view.repaint();
		//view.invalidate();
	    //}
		
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
	         public void valueChanged(ListSelectionEvent e2) {
	     		if (e2.getValueIsAdjusting()) {
	    			return;
	     		} else {
	     			//System.out.println("changed 2");
	     		}
	     		//model.resetInv();//maybe works
	            //System.out.println("First index: " + e2.getFirstIndex());
	            //System.out.println(", Last index: " + e2.getLastIndex());
	     		view.repaint();
	     		//view.revalidate();
	     		view.invalidate();
	     	    //JList list = (JList) e2.getSource();
                //System.out.println("list getselectedvalue     " +list.getSelectedValue());
	    	    //System.out.println("inner valueChanged " +e2.getValueIsAdjusting());
	            int index = list.getSelectedIndex();
	            if (index >= nonproducts) {
	            	JOptionPane.showMessageDialog(null, "Not able to edit product.");
	            	model.resetInv();
	            	view.repaint();
	            	view.revalidate();
	            } else {
	     		String str = list.getSelectedValue().toString();
	            
	            //System.out.println("inner valueChanged  " +str);
	     		model.setCurrentInventory(str);
	     		
	    		date = model.getCurrentTime();
	    		//System.out.println("DATE ON OPENING INVENTORY DETAIL VIEW = "+ date);
	    		//System.out.println("part9z = "+ date + "x");
	     		
	    		invDetailView = new MVC.views.invDetailView(model);
	    		//System.out.println("DetailView created!!");
	    		//invDetailView.closeWindow();
	    		
	    		showInvListController controller1 = new showInvListController(model, view, date);
	    		invDetailView.registerListeners(controller1);
	    		invDetailView.setSize(300, 250);
	    		invDetailView.setLocation(800, 0);
	    		invDetailView.setVisible(true);
	    		//model.resetInv();
	    		view.repaint();
	    		//view.revalidate();
	            }
	         }
	      });
		partList.clear();
	//}
	}
}
