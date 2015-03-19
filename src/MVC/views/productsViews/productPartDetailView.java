package MVC.views.productsViews;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MVC.controllers.partDetailController;
import MVC.models.inventoryModel;
import MVC.models.productModel;

public class productPartDetailView extends JFrame{	
	private productModel model;
	private JPanel detailPanel;
	private JPanel buttonPanel;
	private JLabel idLabel = new JLabel("Part ID: ");
	private JLabel numLabel = new JLabel("Part #: ");
	private JLabel nameLabel = new JLabel("Part name: ");
	private JLabel venLabel = new JLabel("Vendor: ");
	private JLabel qLabel = new JLabel("Quantity: ");
	private JLabel extLabel = new JLabel("Ext. Part #: ");
	private JLabel unitLabel = new JLabel();
	private JLabel locLabel = new JLabel("Location: ");
	private JButton editButton = new JButton("Edit Part");
	private JButton deleteButton = new JButton("Delete Part");
	private String partName;
	private String partNumber;
	private String extNumber;
	private String  partVendor;
	private int partQuantity;
	private String partUnit;
	private int partId;
	private String partloc;



	public productPartDetailView(productModel model) {
		super("Part Detail");
		this.model = model;
		
		GridLayout grid = new GridLayout(4,7);
	
		partNumber = model.getPartNum();/*gets and assigns values of currentObject*/
		partName = model.getPartName();
		partVendor = model.getPartVendor();
		partUnit = model.getPartUnit();
		extNumber = model.getPartExt();
	//	idLabel = new JLabel("Part ID: " + partId);/*creates JLabels displaying values*/
		numLabel = new JLabel("Part #: " + partNumber);
		nameLabel = new JLabel("Part name: " + partName);
		venLabel = new JLabel("Vendor: " + partVendor);
		extLabel = new JLabel("Ext. part #: " + extNumber);
		unitLabel = new JLabel("Unit: " + partUnit);

		this.setLayout(grid);
		/*creates and adds buttons and labels to JFrame*/
		this.add(numLabel);
		this.add(extLabel);
		this.add(unitLabel);
		this.add(nameLabel);
		this.add(venLabel);
		this.add(editButton);
		this.add(deleteButton);
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
