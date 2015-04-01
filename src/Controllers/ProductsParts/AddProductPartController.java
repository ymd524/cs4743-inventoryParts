package Controllers.ProductsParts;

import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MVC.MasterFrame;
import MVC.Product;
import Models.ProductTemplateModel;
import Views.ProductParts.AddProductPartView;
import Views.Products.AddTemplateView;

public class AddProductPartController implements ListSelectionListener {
	
	private ProductTemplateModel model;
	private AddProductPartView view;
	private MasterFrame master;
	private ArrayList arrayList = new ArrayList();
	private Product product;


	public AddProductPartController(ProductTemplateModel model, AddProductPartView view, MasterFrame m){
		this.model = model;
		this.view = view;
		master = m;
		arrayList = model.getProductPartList();
		product = model.getCurrentProductObject();
		
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
	    if (!e.getValueIsAdjusting()){
	    	String command = view.getSelectedValue();
	    	view.setFlag(0);
	    	for(int i = 0; i < arrayList.size(); i++){
	    		if(arrayList.get(i).equals(command)){
	    			view.setFlag(1);
	    			master.displayChildMessage("Part already added");
	    		}
	    	}
	    	
			//if(getNameText().equals(nullString) || getNameText().equals(emptyString)){
				//master.displayChildMessage("Part already added");
			//}
	    	//arrayList.setListData(view.getSelectedValues());
	    
	    	//Object[] command = view.getSelectedValues();
	    	//System.out.println(command[1].toString());
	    	//product = model.getCurrentProductObject();
	    }
	}
	
	
}

