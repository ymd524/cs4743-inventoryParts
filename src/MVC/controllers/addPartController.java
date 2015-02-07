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
			int q = view.getQuantity();
			String unit = view.getUnitQ();
			//checkNull(name, num);

			
			
			checkRequiredFields(name, num);
			checkQuantity(q);
			checkName(name);
			checkUnit(unit);
			if(model.getFlag() == 0){
				model.addPart(num, name, vendor, q, extNum, unit);//adds new object to ArrayList of objects and name to names ArrayList
				model.resetList();//restarts showPartsView for updated list values
				view.closeWindow();//closes addPartView
			}else if (model.getFlag() == 1){
				errorView = new errorView(model);//creates new errorView
				errorView.setSize(400, 300);/* starts new errorView*/
				errorView.setVisible(true);
			}
		}
	}	

	/*
	 *  gets name array and sets error flag to 1 if there is a match,
	 *  adds error desc to errorArray in model
	*/
	private void checkName(String name) {
		namesArray=model.getNameArray();//gets ArrayList of names
		names = new String[namesArray.size()];//creates new String array the size of ArrayList namesArray
		names = namesArray.toArray(names);//assigns values from arrayList to array
		
		for(int i=0; i<names.length;i++){
			if(names[i].equals(name)){
				model.setFlag(1);
				error = "Part name is not unique";
				model.setError(error);
				return;
			}
		}
	}

	/*
	 * sets flag to 1 if q is not > 0, adds error desc to errorArray
	 */
	private void checkQuantity(int q) {
		if(q <= 0){
			model.setFlag(1);
			error = "Part quantity must be greater than 0";
			model.setError(error);
		}
	}
	

	/*
	 * sets flag to 1 if name or num are empty or null, 
	 * adds error desc to errorArray
	 */
	private void checkRequiredFields(String name, String num) {
		String nullString = null;
		String emptyString = new String();
		
		if(name.equals(nullString) || name.equals(emptyString)){
			model.setFlag(1);
			error = "Part name is required";
			model.setError(error);
		}
		if(num.equals(nullString) || num.equals(emptyString)){
			model.setFlag(1);
			error = "Part number is required";
			model.setError(error);
		}
	}

	private void checkUnit(String unit) {
		String nstr = "Unknown";
		String nullString = null;
		String emptyString = new String();
		
		if (unit.equalsIgnoreCase(nstr)) {
			model.setFlag(1);
			error = "Unit of Quantity cannot be Unknown";
			model.setError(error);
		}
		if (unit.equals(nullString) || unit.equals(emptyString)) {
			model.setFlag(1);
			error = "Unit of Quantity is required";
			model.setError(error);
		}
		
	}
}
