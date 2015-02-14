package MVC.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import MVC.models.inventoryModel;
import MVC.views.addPartsView;
import MVC.views.errorView;
import MVC.views.showPartsView;

public class addPartController implements ActionListener{
	private addPartsView view;
	private inventoryModel model;
	//private showPartsView showView;
	private String[] names;
	private String error;
	private ArrayList<String> namesArray = new ArrayList();
	private errorView errorView;

	
	public addPartController(inventoryModel model, addPartsView view){
		this.view = view;
		this.model = model;		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();//gets command value from buttonPanel in addPartView
		if (command.equals("Cancel")) {
			view.closeWindow();//closes addPartView
		}else if (command.equals("Add Part")){
			String name = view.getName();/*sets values from addPartView's text fields*/
			String num = view.getNum();
			String vendor = view.getVendor();
			String extNum = view.getExtNum();
			//int q = view.getQuantity();
			String unit = view.getUnitQ();
			//String location = view.getLoc();
			//checkNull(name, num);
			model.checkRequiredFields(name, num);
			model.checkNameLength(name);
			model.checkNumLength(num);
			model.checkVendorLength(vendor);
			model.checkExtLength(extNum);
			model.checkName(name);
			model.checkUnit(unit);
			//checkQuantity(q);
			//checkLoc(location);
			if(model.getFlag() == 0){
				model.addPart(num, name, vendor,extNum, unit);//adds new object to ArrayList of objects and name to names ArrayList
				model.resetList();//restarts showPartsView for updated list values
				view.closeWindow();//closes addPartView
			}else if (model.getFlag() == 1){
				errorView = new errorView(model);//creates new errorView
				errorView.setSize(400, 300);/* starts new errorView*/
				errorView.setVisible(true);
			}
		}
	}	


}
