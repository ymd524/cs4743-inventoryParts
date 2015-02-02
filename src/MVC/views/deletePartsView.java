package MVC.views;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MVC.controllers.deletePartController;
import MVC.models.inventoryModel;

public class deletePartsView extends JFrame{
	
	private inventoryModel model;
	private JPanel deletePanel = new JPanel();
	private JLabel confirm = new JLabel("Are you sure you want to delete this part?");
	private JButton deleteButton = new JButton("Delete");
	private JButton cancelButton = new JButton("Cancel");

	
	
	public deletePartsView (inventoryModel model){
		this.model = model;
		deletePanel.add(confirm);/*adds confirmation label and delete/cancel buttons to deletePartView*/
		deletePanel.add(deleteButton);
		deletePanel.add(cancelButton);
		this.add(deletePanel);
	}
	
	public void registerListeners(deletePartController controller) {
		cancelButton.addActionListener(controller);
		deleteButton.addActionListener(controller);
	}
	
	public void closeWindow (){
		this.dispose();
	}
}
