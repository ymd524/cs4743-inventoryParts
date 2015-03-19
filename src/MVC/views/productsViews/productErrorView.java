package MVC.views.productsViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MVC.controllers.deletePartController;
import MVC.models.inventoryModel;
import MVC.models.productModel;

public class productErrorView extends JFrame{
	
	private productModel model;
	private JPanel errorPanel = new JPanel();
	private JLabel confirm;
	private JButton button = new JButton("OK");
	private String[] errorArray = new String[10];

	public productErrorView (productModel model){
		super("Error!");
		this.model = model;
		errorArray = model.getErrorArray();
		for(int i=0;i<errorArray.length;i++){
			confirm = new JLabel(errorArray[i]);
			errorPanel.add(confirm);
		}
		errorPanel.add(button);
		this.add(errorPanel);

		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				resetErrors();	
				closeWindow();
			}
		});
	}
	
	/*public void registerListeners(deletePartController controller) {
		button.addActionListener(controller);
	}*/
	
	public void resetErrors(){
		model.resetErrors();
	}
	public void closeWindow (){
		this.dispose();
	}

}
