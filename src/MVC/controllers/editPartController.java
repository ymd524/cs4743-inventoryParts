package MVC.controllers;

import java.awt.event.*;
import java.util.ArrayList;
import MVC.models.inventoryModel;
import MVC.views.editPartView;
import MVC.views.errorView;

public class editPartController implements ActionListener {
		public inventoryModel model;
		public editPartView view;
		public String currName;
		public errorView errorView;
		private String error;
		private String[] names;
		private ArrayList<String> namesArray = new ArrayList();
		
	public editPartController(inventoryModel model, editPartView view){
		this.model = model;
		this.view = view;
		this.setName();
	}

	@Override
	public void actionPerformed(ActionEvent e)     {
		String command = e.getActionCommand();//gets values from editPanel in editPartView
		if (command.equals("Cancel")) {
			view.closeWindow();
		} else if (command.equals("Save")){
			String name = view.getNameText();/*gets values submitted for currentObject update*/
			String num = view.getNumText();
			String ven = view.getVText();
			int Q = Integer.parseInt(view.getQText());
			String unit = view.getUnitText();
			String ext = view.getExtText();
			int q = Integer.parseInt(view.getQText());
			String location = view.getLocText();
			checkNull(name, num);
			checkQuantity(q);
			checkName(name);
			checkUnit(unit);
			checkLoc(location);
			if(model.getFlag() == 0){
				model.updatePart(num, name, ven, q, ext, unit, location);//values updates if necessary
				int index = model.getObjectIndex();
				model.changeNameInArray(index, name);
				model.resetList();//restarts the showPartsView for updated list values
				view.closeWindow();//close editPartView
			} else if(model.getFlag() == 1){
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
			if(names[i].equals(name) && !name.equals(currName)){
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
		if(q < 0){
			model.setFlag(1);
			error = "Part quantity must be greater than or equal to 0";
			model.setError(error);
		}
	}
	
	/*
	 * sets flag to 1 if name or num are empty or null, 
	 * adds error desc to errorArray
	 */
	private void checkNull(String name, String num) {
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
	
	private void setName(){
		currName = view.getCurrName();
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
	private void checkLoc(String location) {
		String nstr = "Unknown";
		String nullString = null;
		String emptyString = new String();
		
		if (location.equalsIgnoreCase(nstr)) {
			model.setFlag(1);
			error = "Part Location cannot be Unknown";
			model.setError(error);
		}
		if (location.equals(nullString) || location.equals(emptyString)) {
			model.setFlag(1);
			error = "Part Location is required";
			model.setError(error);
		}
		
	}
}
