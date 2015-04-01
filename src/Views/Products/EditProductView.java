package Views.Products;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MVC.MasterFrame;
import MVC.Product;
import MVC.Session;
import Models.ProductTemplateModel;

public class EditProductView extends JPanel {

	private ProductTemplateModel model;
	private MasterFrame master;
	private Product product;
	private Session session;
	private String myTitle = "Edit Product";
	JDesktopPane desktop;
	JLabel num = new JLabel("Product #: ");
	JLabel desc = new JLabel("Desctiption: ");
	JTextField numText = new JTextField(20);
	JTextField descText = new JTextField(20);
	JButton save = new JButton("Save");
	
	public EditProductView(final ProductTemplateModel model, MasterFrame m){
		this.model = model;
		master = m;
		this.setLayout(new GridLayout(7,1));
		product = model.getCurrentProductObject();
		desktop = master.getDesktop();
		numText.setText(product.getNum());
		descText.setText(product.getDesc());

		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					int i = 0;
					
					if(!descText.getText().equals(product.getNum())){
						model.updateProductDesc(product.getId(), descText.getText());
					}
					
					if(numText.getText().equals(product.getNum())){
						master.displayChildMessage("Update successful. Select Products > Show Templates in the menu bar to show updated list.");
					}else if(!numText.getText().equals(product.getNum())){
						i = model.checkNum(numText.getText());
						if(i == 1){
							master.displayChildMessage("Product name not unique");
						}else{
							model.updateProductNum(product.getId(), numText.getText());
							master.displayChildMessage("Update successful. Select Products > Show Templates in the menu bar to show updated list.");
						}
					}
			}
		});
		
		this.add(num);
		this.add(numText);
		this.add(desc);
		this.add(descText);
		this.add(save);
	}
	
	public String getTitle(){
		return myTitle;
	}
	
	
}
