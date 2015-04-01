package Views.ProductParts;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MVC.MasterFrame;
import MVC.Product;
import MVC.ProductPart;
import Models.ProductTemplateModel;

public class EditProductPartView extends JPanel{
	private ProductTemplateModel model;
	private MasterFrame master;
	private Product product;
	private ProductPart part;
	private String myTitle = "Edit Part";
	JLabel productLabel;
	JLabel partLabel;
	JLabel quantity;
	JTextField qText = new JTextField(3);
	JButton save = new JButton("Save");
	
	public EditProductPartView(final ProductTemplateModel model, MasterFrame m){
		this.model = model;
		master = m;
		product = model.getCurrentProductObject();
		part = model.getCurrentProductPartObject();
		this.setLayout(new GridLayout(5,1));
		
		productLabel = new JLabel("Product #: " + product.getNum());
		partLabel = new JLabel("Part Name: " + part.getPartName());
		quantity = new JLabel("Quantity: ");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					if(!qText.getText().matches("\\d+")){
						master.displayChildMessage("Value entered must be an integer");
					}else{
						int i = Integer.parseInt(qText.getText());
						model.updateProductPartQuantity(product.getId(), part.getId(), i);
						master.displayChildMessage("Update Successful! 	");				
					}
			}
		});
		
		
		
		this.add(productLabel);
		this.add(partLabel);
		this.add(quantity);
		this.add(qText);
		this.add(save);
		
	}
	
	public String getTitle(){
		return myTitle;
	}
	
}
