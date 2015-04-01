package Views.Parts;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MVC.MasterFrame;
import Models.PartModel;

public class AddPartView extends JPanel{
	
	private MasterFrame master;
	private PartModel model;
	private String myTitle = "Add Part";
	JLabel num = new JLabel("Part #: ");
	JLabel name = new JLabel("Part Name: ");
	JLabel vendor = new JLabel("Vendor: ");
	JLabel unit = new JLabel("Unit: ");
	JLabel ext = new JLabel("Ext Part #: ");
	JTextField numText = new JTextField(20);
	JTextField nameText = new JTextField(20);
	JTextField vendorText = new JTextField(20);
	JTextField unitText = new JTextField(20);
	JTextField extText = new JTextField(20);
	JButton save = new JButton("Save");

	
	
	public AddPartView(MasterFrame m){
		master = m;
		model = master.getPartModel();
		this.setLayout(new GridLayout(6,1));
		
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nullString = null;
				String emptyString = "";
				/*if(getNameText().equals(nullString) || getNameText().equals(emptyString)){
					master.displayChildMessage("New template name can not be blank");
				}*/
				
				
				model.addPart(getNumText(), getNameText(), getVendorText(), getUnitText(), getExtText());
				master.displayChildMessage("New part added. Close and reopen product list to view changes.");
			}
		});
		
		
		this.add(num);
		this.add(numText);
		this.add(name);
		this.add(nameText);
		this.add(vendor);
		this.add(vendorText);
		this.add(unit);
		this.add(unitText);
		this.add(ext);
		this.add(extText);
		this.add(save);
		
		this.setPreferredSize(new Dimension(300, 300));
		
	}
	
	public String getTitle(){
		return myTitle;
	}
	
	public String getNumText(){
		return numText.getText();
	}
	
	public String getNameText(){
		return nameText.getText();
	}
	
	public String getVendorText(){
		return vendorText.getText();
	}
	
	public String getUnitText(){
		return unitText.getText();
	}
	
	public String getExtText(){
		return extText.getText();
	}
}
