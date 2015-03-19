package MVC.views;

import java.awt.GridLayout;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import MVC.controllers.showInvListController;
import MVC.models.inventoryModel;

public class invDetailView extends JFrame {
	private inventoryModel model;
	private JPanel detailPanel;
	private JPanel buttonPanel;
	private JLabel idLabel = new JLabel("ID: ");
	private JLabel nameLabel = new JLabel("Part Name: ");
	private JLabel locLabel = new JLabel("Location: ");
	private JLabel qLabel = new JLabel("Quantity: ");
	private JButton editButton = new JButton("Edit Part");
	private JButton deleteButton = new JButton("Delete Part");
	private String partName;
	private int id;
	private String loc;
	private String invloc;
	private int partq;
	private Date d;
	private Date date;
	public invDetailView(inventoryModel model) {
		// TODO Auto-generated constructor stub
		super("Inventory Part Detail");
		this.model = model;
		GridLayout grid = new GridLayout(3,7);

		id = model.getCurrentInvId();
		partName = model.getCurrentPartName();
		partq = model.getCurrentInvQ();
		loc = model.getCurrentLocation();
		
		idLabel = new JLabel("ID: " + id);/*creates JLabels displaying values*/
		nameLabel = new JLabel("Part Name: " + partName);
		qLabel = new JLabel("Location: " + loc);
		locLabel = new JLabel("Quantity: " + partq);

		this.setLayout(grid);
		this.add(idLabel);/*creates and adds buttons and labels to JFrame*/
		this.add(nameLabel);
		this.add(locLabel);
		this.add(qLabel);
		this.add(editButton);
		this.add(deleteButton);
	}

	public void closeWindow(){
		  //System.out.println("closeWindowMethod");
		  //JOptionPane.showMessageDialog(null, "Closed Window");
		  //setVisible(false);
		  
		  //setVisable(false);
		//dispose();
		this.closeWindow();
	}
	
	public void registerListeners(showInvListController controller) {
		editButton.addActionListener(controller);
		deleteButton.addActionListener(controller);
	}
	
}
