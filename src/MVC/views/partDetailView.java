package MVC.views;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MVC.controllers.partDetailController;
import MVC.models.inventoryModel;

public class partDetailView extends JFrame{	
	private inventoryModel model;
	private JPanel detailPanel;
	private JPanel buttonPanel;
	private JLabel num = new JLabel("Part #: ");
	private JLabel name = new JLabel("Part name: ");
	private JLabel ven = new JLabel("Vendor: ");
	private JLabel q = new JLabel("Quantity: ");
	private JButton editButton = new JButton("Edit Part");
	private JButton deleteButton = new JButton("Delete Part");
	private String partName;
	private String partNumber;
	private String  partVendor;
	private int partQuantity;
	

	public partDetailView(inventoryModel model){
		super("Part Detail");
		this.model = model;
		partName = model.getCurrentObject().getName();/*gets and assigns values of currentObject*/
		partNumber = model.getCurrentObject().getNum();
		partVendor = model.getCurrentObject().getVendor();
		partQuantity = model.getCurrentObject().getQuantity();
		num = new JLabel("Part #: " + partNumber);/*creates JLabels displaying values*/
		name = new JLabel("Part name: " + partName);
		ven = new JLabel("Vendor: " + partVendor);
		q = new JLabel("Quantity: " + partQuantity);
		
		buttonPanel = new JPanel();/*creates and adds button panel to JFrame*/
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		detailPanel = new JPanel();/*creates and adds detailPanel to JFrame*/
		detailPanel.add(num);
		detailPanel.add(name);
		detailPanel.add(ven);
		detailPanel.add(q);
		this.add(detailPanel);
	}

	//closes part detail view
	public void closeWindow(){
		this.closeWindow();		
	}
	
	public void registerListeners(partDetailController controller) {
		editButton.addActionListener(controller);
		deleteButton.addActionListener(controller);
	}
}
