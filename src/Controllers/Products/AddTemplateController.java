package Controllers.Products;

import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MVC.MasterFrame;
import Models.ProductTemplateModel;
import Views.Products.AddTemplateView;

public class AddTemplateController implements ListSelectionListener {
	
	private ProductTemplateModel model;
	private AddTemplateView view;
	private MasterFrame master;
	private ArrayList arrayList = new ArrayList();

	public AddTemplateController(ProductTemplateModel model, AddTemplateView view, MasterFrame m){
		this.model = model;
		this.view = view;
		master = m;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
	    if (!e.getValueIsAdjusting()){
	    	//arrayList.setListData(view.getSelectedValues());
	    
	    	Object[] command = view.getSelectedValues();
	    	System.out.println(command[1].toString());
	    	//product = model.getCurrentProductObject();
	    }
	}
	
}

