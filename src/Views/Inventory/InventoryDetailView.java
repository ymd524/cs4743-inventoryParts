package Views.Inventory;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MVC.MasterFrame;
import MVC.Part;
import MVC.Session;
import Models.InventoryItem;
import Models.InventoryModel;
import Models.PartModel;

public class InventoryDetailView extends JPanel {
	
	private MasterFrame master;
	private InventoryModel model;
	private String myTitle = "Inventory Detail";
	private JLabel itemId;
	private JLabel partId;
	private JLabel quantity; 
	private JLabel name;
	private JLabel location;
	private JLabel type;
	
	private JButton delete = new JButton("Delete Template");
	private InventoryItem item;
	
	public InventoryDetailView(MasterFrame m){
		master = m;
		model = master.getInventoryModel();
		this.item = model.getInventoryItem();
		this.setLayout(new GridLayout(7,1));
		
		itemId = new JLabel("Item ID #: " + item.getItemId());
		partId = new JLabel("Part ID #: " + item.getPartId());
		quantity = new JLabel("Quantity: " + item.getQuantity());
		name = new JLabel("Part Name: " + item.getName());
		location = new JLabel("Location: " + item.getLocation());
		type = new JLabel("Type: " + item.getType());	
		
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*if(session == null || !session.canDeleteInventory()){
					master.displayChildMessage("You do not have permission to Delete a part.");
				}else{*/
				model.deleteInventoryItemById(item.getItemId());
				master.displayChildMessage("Part Deleted Successfully");
				//master.closeAddPartFrame();
				}
		});
		
		this.add(itemId);
		this.add(partId);
		this.add(name);
		this.add(type);
		this.add(quantity);
		this.add(location);
		this.add(delete);
		
	}
	
	public String getTitle(){
		return myTitle;
	}
	
}
