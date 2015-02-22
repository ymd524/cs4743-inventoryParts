/**
 * 
 */
package MVC.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MVC.models.inventoryModel;
import MVC.views.inventoryListView;
import MVC.views.showPartsView;

public class showInvListController implements ListSelectionListener, ActionListener {
	private inventoryListView view;
	private inventoryModel model;

	public showInvListController(inventoryModel model2, inventoryListView view2) {
		// TODO Auto-generated constructor stub
		this.view = view2;
		this.model = model2;	
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if (e.getValueIsAdjusting())
			return;
		
		System.out.println("Hello, change!!");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Hello, change!!");

	}

}
