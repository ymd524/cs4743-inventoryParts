package Views.ProductParts;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import Controllers.ProductsParts.AddProductPartController;
import MVC.MasterFrame;
import MVC.Product;
import Models.ProductTemplateModel;

public class AddProductPartView extends JPanel{
	
	private ProductTemplateModel model;
	private MasterFrame master;
	private String[] parts;
	private ArrayList<String> partsArray = new ArrayList();
	JList list;
	JButton button;
	JPanel panel = new JPanel();
	private String myTitle = "Add Product Part";
	private int flag = 0;
	private Product product;
	
	public AddProductPartView(final ProductTemplateModel model, MasterFrame m){
		master = m;
		this.model = model;
		product = model.getCurrentProductObject();
		this.setLayout(new BorderLayout());
		model.setPartsList();
		AddProductPartController controller = new AddProductPartController(model, this, master);
		
		
		partsArray = model.getPartsList();
		parts = new String[partsArray.size()];
		parts = partsArray.toArray(parts);
		list = new JList(parts);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(controller);

		panel.add(new JLabel("Choose a part to add to template: "));
		
		
		button = new JButton("Save Part");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String command = getSelectedValue();
		    	if(flag == 0){
		    		int newPartId = model.getPartId(command);
		    		model.addProductPart(newPartId, product.getId());
		    	}
		    	master.displayChildMessage("Part successfully added to template. Close and reopen product parts list to see changes.");
			}
		});
		
		this.add(panel, BorderLayout.NORTH);
		this.add(list, BorderLayout.CENTER);
		this.add(button, BorderLayout.SOUTH);
	}
	
	public String getTitle(){
		return myTitle;
	}
	
	
	public  String getSelectedValue(){
		return list.getSelectedValue().toString();
	}
	
	public void setFlag(int i){
		flag = i;
	}

}
