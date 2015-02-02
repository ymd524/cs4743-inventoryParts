package MVC.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MVC.controllers.deletePartController;
import MVC.models.inventoryModel;

public class errorView extends JFrame{
	
	private inventoryModel model;
	private JPanel deletePanel = new JPanel();
	private JLabel confirm;
	private JButton Button = new JButton("OK");
	private String[] errorArray = new String[10];

	public errorView (inventoryModel model){
		super("Error!");
		this.model = model;
		errorArray = model.getErrorArray();
		for(int i=0;i<errorArray.length;i++){
			confirm = new JLabel(errorArray[i]);
			deletePanel.add(confirm);
		}
		deletePanel.add(Button);
		this.add(deletePanel);

		Button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				resetErrors();	
				closeWindow();
			}
		});
	}
	
	public void registerListeners(deletePartController controller) {
		Button.addActionListener(controller);
	}
	
	public void resetErrors(){
		model.resetErrors();
	}
	public void closeWindow (){
		this.dispose();
	}

}
