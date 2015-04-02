package Controllers.Inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import MVC.MasterFrame;
import MVC.Part;
import Models.InventoryModel;
import Models.PartModel;
import Models.ProductTemplateModel;
import Views.Inventory.AddInventoryProductView;

public class AddInventoryProductController implements ActionListener{
	private AddInventoryProductView view;
	private MasterFrame master;
	private InventoryModel model;
	private PartModel partModel;
	private ProductTemplateModel productModel;
	public ArrayList productPartList = new ArrayList();
	private String productName;
	private String locationName;
	private String type = "part";
	private int locationId;
	private int productId;
	private int newQuantity;
	private int productQuantityInStock;
	private int qRequired;
	private int qInStock;
	private int partId;
	private int flag = 0;
	
	
	public AddInventoryProductController(AddInventoryProductView view, MasterFrame m){
		this.view = view;
		master = m;
		this.model = master.getInventoryModel();
		productModel = master.getProductTemplateModel();
		partModel = master.getPartModel();
	}
@Override
public void actionPerformed(ActionEvent e) {

		//get location name and id
		locationName  = view.getLocationValue();
		locationId = model.getLocationIdByName(locationName);
		
		//get product name, and id
		productName = view.getPartValue();
		productModel.setProductByNum(productName);
		productId = productModel.getCurrentProductObject().getId();	
		
		//get array of parts required for template
		productModel.setProductPartArray(productId);
		productPartList = productModel.getProductPartList();
		//System.out.println(productPartList.get(1));
		
		for (int i = 0; i < productPartList.size(); i++){
			//for each part in list, get partId
			partModel.setPartByName(productPartList.get(i).toString());
			partId = partModel.getCurrentPartObject().getId();
			//get quantity of this part required for this template
			qRequired = model.getRequiredQuantity(productId, partId);
			//get quantity of this part at specific location
			qInStock = model.getQuantityInStock(partId, locationId, type);
			//System.out.println("in stock:" + qInStock);
			//System.out.println("required is: " + qRequired);
			if(qInStock < qRequired){
				flag = 1;
			}
		}
		
		if(flag == 1){
			master.displayChildMessage("1 or more inventory parts do not have the minimum required amount to add this product");
			flag = 0;
		}else{
			for (int i = 0; i < productPartList.size(); i++){
				//for each part in list, get partId
				partModel.setPartByName(productPartList.get(i).toString());
				partId = partModel.getCurrentPartObject().getId();
				//get quantity of this part required for this template
				qRequired = model.getRequiredQuantity(productId, partId);
				//get quantity of this part at specific location
				qInStock = model.getQuantityInStock(partId, locationId, type);
				newQuantity = qInStock - qRequired;
				model.setNewInventoryQuantity(locationId, partId, type, newQuantity);
				//System.out.println("in stock:" + qInStock);
				//System.out.println("required is: " + qRequired);

			}
			
			
			productQuantityInStock = model.getQuantityInStock(productId, locationId, "product");
			if(productQuantityInStock == 0){
				productQuantityInStock += 1;
				model.addInventoryItem(productId, locationId, productQuantityInStock, "product");
			}else{

				productQuantityInStock += 1;
				model.setNewInventoryQuantity(locationId, productId, "product", productQuantityInStock);
				
			}
			master.displayChildMessage("Product add successful");
		}
		
		
		

}
}