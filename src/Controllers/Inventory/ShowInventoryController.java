package Controllers.Inventory;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MVC.MasterFrame;
import MVC.Part;
import Models.InventoryItem;
import Models.InventoryModel;
import Views.Inventory.AddInventoryProductView;
import Views.Inventory.InventoryDetailView;
import Views.Inventory.ShowInventoryView;

public class ShowInventoryController implements ListSelectionListener {

		private ShowInventoryView view;
		private MasterFrame master;
		private InventoryModel model;
		private InventoryItem item;
		private ArrayList<String> partsArray = new ArrayList();
		private ArrayList<Integer> partsIds = new ArrayList();
		private ArrayList<Part> partObjects = new ArrayList();
		private int newFrameX = 40, newFrameY = 40; //used to cascade or stagger starting x,y of JInternalFrames
		private JDesktopPane desktop;
		
		private int locationId;
		
		public ShowInventoryController(ShowInventoryView view, MasterFrame m){
			this.view = view;
			master = m;
			model = master.getInventoryModel();
			desktop = master.getDesktop();
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()){
				InventoryItem item = view.getSelectedInventory();
				model.setInventoryItem(item);
				InventoryDetailView child = new InventoryDetailView(master);
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
		}
}
