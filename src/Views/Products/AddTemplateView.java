package Views.Products;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MVC.MasterFrame;
import Models.ProductTemplateModel;

public class AddTemplateView extends JPanel {
	
	private MasterFrame master;
	private String myTitle = "Add Template";
	private ProductTemplateModel model;
	private String[] parts;
	private ArrayList<String> partsArray = new ArrayList();
	private ArrayList<String> partsList = new ArrayList();
	private int newFrameX = 20, newFrameY = 20; //used to cascade or stagger starting x,y of JInternalFrames
	JButton button;
	JPanel panel;
	JList list;
	JTextField name;
	JTextField desc;
	
	public AddTemplateView(final ProductTemplateModel model, MasterFrame m){
		master = m;
		this.model = model;
		this.setLayout(new BorderLayout());
		model.setPartsList();
		partsArray = model.getPartsList();
		parts = new String[partsArray.size()];
		parts = partsArray.toArray(parts);
		panel = new JPanel();
		list = new JList(parts);
		name = new JTextField(20);
		desc = new JTextField(20);
		panel.add(new JLabel("Template Name: "));
		panel.add(name);
		panel.add(new JLabel("Template Desc: "));
		panel.add(desc);
		
		
		button = new JButton("Save Template");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nullString = null;
				String emptyString = "";
				if(getNameText().equals(nullString) || getNameText().equals(emptyString)){
					master.displayChildMessage("New template name can not be blank");
				}
				
				Object[] command = getSelectedValues();
				for(int i=0; i  < command.length; i++){
					partsList.add(command[i].toString());
				}
				
				model.addProduct(getNameText(), getDescText(), partsList);
				master.displayChildMessage("New template added. Close and reopen product list to view changes.");
			}
		});
		
		this.add(panel, BorderLayout.NORTH);
		this.add(list, BorderLayout.CENTER);
		this.add(button, BorderLayout.SOUTH);
	}
	
	public String getTitle(){
		return myTitle;
	}
	
	public String getNameText(){
		return name.getText();
	}
	
	public String getDescText(){
		return desc.getText();
	}
	
	public  Object[] getSelectedValues(){
		return list.getSelectedValues();
	}

}
