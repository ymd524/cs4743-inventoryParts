package MVC.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MVC.controllers.deleteInvController;
import MVC.models.inventoryModel;

public class deleteInvView extends JFrame {
	private inventoryModel model;
	private JPanel deletePanel = new JPanel();
	private JLabel confirm = new JLabel("Are you sure you want to delete this part?");
	private JButton deleteButton = new JButton("Delete");
	private JButton cancelButton = new JButton("Cancel");

	
	
	public deleteInvView (inventoryModel model){
		this.model = model;
		deletePanel.add(confirm);/*adds confirmation label and delete/cancel buttons to deletePartView*/
		deletePanel.add(deleteButton);
		deletePanel.add(cancelButton);
		this.add(deletePanel);
	}
	
	public void registerListeners(deleteInvController deleteController) {
		cancelButton.addActionListener(deleteController);
		deleteButton.addActionListener(deleteController);
	}
	
	public void closeWindow (){
		this.dispose();
	}
}
