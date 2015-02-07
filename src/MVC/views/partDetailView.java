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
	private JLabel idLabel = new JLabel("Part ID: ");
	private JLabel numLabel = new JLabel("Part #: ");
	private JLabel nameLabel = new JLabel("Part name: ");
	private JLabel venLabel = new JLabel("Vendor: ");
	private JLabel qLabel = new JLabel("Quantity: ");
	private JLabel extLabel = new JLabel("Ext. Part #: ");
	private JButton editButton = new JButton("Edit Part");
	private JButton deleteButton = new JButton("Delete Part");
	private String partName;
	private String partNumber;
	private String extNumber;
	private String  partVendor;
	private int partQuantity;
	private String partUnit;
	private int partId;


	public partDetailView(inventoryModel model){
		super("Part Detail");
		this.model = model;
		partName = model.getCurrentObject().getName();/*gets and assigns values of currentObject*/
		partNumber = model.getCurrentObject().getNum();
		partVendor = model.getCurrentObject().getVendor();
		partQuantity = model.getCurrentObject().getQuantity();
		partUnit = model.getCurrentObject().getUnit();
		extNumber = model.getCurrentObject().getExt();
		partId = model.getCurrentObject().getId();
		idLabel = new JLabel("Part ID: " + partId);/*creates JLabels displaying values*/
		numLabel = new JLabel("Part #: " + partNumber);
		nameLabel = new JLabel("Part name: " + partName);
		venLabel = new JLabel("Vendor: " + partVendor);
		qLabel = new JLabel("Quantity: " + partQuantity + " " + partUnit);
		extLabel = new JLabel("Ext. part #: " + extNumber);
		
		buttonPanel = new JPanel();/*creates and adds button panel to JFrame*/
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		detailPanel = new JPanel();/*creates and adds detailPanel to JFrame*/
		detailPanel.add(idLabel);
		detailPanel.add(numLabel);
		detailPanel.add(extLabel);
		detailPanel.add(nameLabel);
		detailPanel.add(venLabel);
		detailPanel.add(qLabel);
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
