package MVC.views;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MVC.controllers.addInvController;
import MVC.models.inventoryModel;

public class addInvsView extends JFrame {
	private inventoryModel model;
	private JPanel addPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JLabel nameLabel = new JLabel("Part Name: ");
	private JLabel locLabel = new JLabel("Location: ");
	private JLabel qLabel = new JLabel("Quantity: ");
	
	//private JTextField numText = new JTextField(20);
	//private JTextField nameText = new JTextField(255);
	private JTextField qText = new JTextField(20);
	
	private JButton addButton = new JButton("Add Inventory Item");
	private JButton cancelButton = new JButton("Cancel");
	private JComboBox combo1;
	private JComboBox combo2;
	
	private ArrayList<String> loclist = new ArrayList();
	private String[] locations;
	private ArrayList<String> partlist = new ArrayList();
	private String[] parts;
	
	
	public addInvsView(inventoryModel model) {
		// TODO Auto-generated constructor stub
		this.model =  model;
		GridLayout grid = new GridLayout(6,7);
		
		model.getPartsList();
		partlist = model.getNameArray();
		//System.out.println("combo1 = "+partlist.toString());
		parts = new String[partlist.size()];
		parts = partlist.toArray(parts);
		combo1 = new JComboBox(parts);
		
		loclist = model.getLocationsArray();
		//System.out.println("combo2 = "+loclist.toString());
		locations = new String[loclist.size()];
		locations = loclist.toArray(locations);
		combo2 = new JComboBox(locations);

		this.setLayout(grid);;		
		this.add(nameLabel);
		this.add(combo1);
		
		this.add(locLabel);
		this.add(combo2);
		
		this.add(qLabel);
		this.add(qText);
		
		this.add(addButton);
		this.add(cancelButton);
	}

	public void registerListeners(addInvController addInvController) {
		// TODO Auto-generated method stub
		cancelButton.addActionListener(addInvController);
		addButton.addActionListener(addInvController);
	}
	
	public void closeWindow (){
		this.dispose();
	}
	
	public String getName(){
		String str = (String)combo1.getSelectedItem();
		return str;
	}

	public String getLocat() {
		String str = (String)combo2.getSelectedItem();
		return str;
	}
	
	public int getQuantity(){
		int zero;
		if (qText.getText().isEmpty()) {
			zero = 0;
		} else {
            zero = Integer.parseInt(qText.getText());
        }
		return zero;
    }
}
