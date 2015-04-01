package Views.Inventory;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import Controllers.Inventory.ShowInventoryController;
import Controllers.Parts.ShowPartsController;
import MVC.MasterFrame;
import MVC.Session;
import Models.InventoryItem;
import Models.InventoryModel;
import Models.PartModel;
import Views.Parts.AddPartView;

public class ShowInventoryView extends JPanel {
		
		private MasterFrame master;
		private InventoryModel model;
		private String myTitle = "Inventory List";
		private Session session;
		private JDesktopPane desktop;
		private ArrayList<InventoryItem> inventoryArray = new ArrayList();
		private ArrayList<String> inventoryArrayList = new ArrayList();
		private int newFrameX = 30, newFrameY = 30; //used to cascade or stagger starting x,y of JInternalFrames
		private String[] locations;
		private String[] inventory;
		private JList list;
		JButton addProduct;
		JButton addPart;
		JPanel panel = new JPanel();
		JButton add = new JButton();
		JList partsList;
		
		public ShowInventoryView(MasterFrame m){
			master = m;
			model = master.getInventoryModel();
			
			this.setLayout(new BorderLayout());
			ShowInventoryController Controller = new ShowInventoryController(this, master);
			desktop = master.getDesktop();
			model.setInventoryArrayList();
				
			addPart = new JButton("");
			addPart.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					/*if(session == null || !session.canAddParts()){
						master.displayChildMessage("You do not have permission to add a part.");
					}else{*/
					
					AddInventoryPartView child = new AddInventoryPartView(master);
					JInternalFrame frame = new JInternalFrame(child.getTitle(), true, true, true, true );
					frame.add(child, BorderLayout.CENTER);
					frame.pack();
					//wimpy little cascade for new frame starting x and y
					frame.setLocation(newFrameX, newFrameY);
					newFrameX = (newFrameX) % desktop.getWidth(); 
					newFrameY = (newFrameY) % desktop.getHeight(); 
					desktop.add(frame);
					frame.setVisible(true);
					master.addPartFrame(frame);
					}
			});
			
			addProduct = new JButton("Add New Product");
			addProduct.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					/*if(session == null || !session.canAddParts()){
						master.displayChildMessage("You do not have permission to add a part.");
					}else{*/
					
					AddInventoryProductView child = new AddInventoryProductView(master);
					JInternalFrame frame = new JInternalFrame(child.getTitle(), true, true, true, true );
					frame.add(child, BorderLayout.CENTER);
					frame.pack();
					//wimpy little cascade for new frame starting x and y
					frame.setLocation(newFrameX, newFrameY);
					newFrameX = (newFrameX) % desktop.getWidth(); 
					newFrameY = (newFrameY) % desktop.getHeight(); 
					desktop.add(frame);
					frame.setVisible(true);
					master.addPartFrame(frame);
					}
			});
			
				//}
			
			inventoryArray=model.getInventoryArrayList();//gets ArrayList of names
			//System.out.println(inventoryArray.size());	
			inventory = new String[inventoryArray.size()];//creates new String array the size of ArrayList namesArray
			for(int i = 0; i < inventoryArray.size(); i++){
				inventory[i] = inventoryArray.get(i).getListText();
			}
				list = new JList(inventory);
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				list.addListSelectionListener(Controller);
				this.add(list, BorderLayout.NORTH);
				panel.add(addPart);
				panel.add(addProduct);
				this.add(panel, BorderLayout.SOUTH);
				
				this.setPreferredSize(new Dimension(300, 300));
				incrementTitle();
			}
		
		//set the title of the containing JInternalFrame
		private void setInternalFrameTitle(String t) {
			Container parent = this;
			//get climbing parent hierarchy until we find the JInnerFrame
			while(!(parent instanceof JInternalFrame) && parent != null) 
	            parent = parent.getParent();
			if(parent != null)
				((JInternalFrame) parent).setTitle(t);
		}
		
		private void incrementTitle() {
			//myTitle = "Product Templates";
			//update my title using getParent
			addPart.setText("Add New Part");
			//use getParent to find JInternalFrame container and set its title
			setInternalFrameTitle(myTitle);
		}

		public String getTitle(){
			return myTitle;
		}
		
		public int getSelectedIndex() {
			return list.getSelectedIndex();
		}
		
		public InventoryItem getSelectedInventory(){
			int i = getSelectedIndex();
			return inventoryArray.get(i);
		}
		
		public void setPanel(ArrayList parts){
			inventoryArray=parts;//gets ArrayList of names
			inventory = new String[inventoryArray.size()];//creates new String array the size of ArrayList namesArray
			inventory = inventoryArray.toArray(inventory);

			partsList = new JList(inventory);
			//list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			//list.addListSelectionListener(Controller);
			this.add(panel, BorderLayout.SOUTH);
		}
}