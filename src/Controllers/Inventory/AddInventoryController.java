package Controllers.Inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MVC.MasterFrame;
import Models.InventoryModel;
import Views.Inventory.AddInventoryPartView;

public class AddInventoryController implements ActionListener {
	private AddInventoryPartView view;
	private MasterFrame master;
	private InventoryModel model;
	private String partName;
	private String locationName;
	private String type = "part";
	private int locationId;
	private int partId;
	private int quantity;


	public AddInventoryController(AddInventoryPartView view, MasterFrame m){
		this.view = view;
		master = m;
		this.model = master.getInventoryModel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

			locationName  = view.getLocationValue();
			locationId = model.getLocationIdByName(locationName);
			partName = view.getPartValue();
			master.getPartModel().setPartByName(partName);
			partId = master.getPartModel().getCurrentPartObject().getId();
			quantity = view.getQText();
			model.addInventoryItem(partId, locationId, quantity, type);
			
			
	}
}
