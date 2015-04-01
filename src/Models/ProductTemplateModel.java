package Models;

import java.util.ArrayList;

import MVC.Product;
import MVC.ProductPart;
import MVC.Session;
import MVC.User;

public class ProductTemplateModel {
	
	public ArrayList productArray;
	public ArrayList productPartList;
	public ArrayList partsList;
	public productGatewaySQL gateway = new productGatewaySQL("ymd524", "ymd524", "HRqEF9KWp7MFw04SR0zZ");
	public Product product;
	public ProductPart productPart;
	private int productId;
	private int partId;
	private int quantity;
	private User user;
	private Session session;
	
	public void deleteProductById(int id){
		gateway.deleteProductFromProductPartsById(id);
		gateway.deleteProductById(id);
	}
	
	public void deleteProductPartById(int id){
		gateway.deletePartFromProductPartsById(id);
	}
	
	public void addProduct(String num, String desc, ArrayList parts){
		productId = gateway.addProductGetId(num, desc);
		for(int i = 0; i< parts.size(); i++){
			partId = gateway.getPartId(parts.get(i).toString());
			gateway.addProductParts(partId, productId);
		}
	}
	
	public void addProductPart(int partId, int productId){
		gateway.addProductParts(partId, productId);
	}
	
	public void endSession(){
		this.session = null;
	}
	
	public int checkNum(String num){

		for(int i =0; i < productArray.size();i++){
			if(productArray.get(i).equals(num)){
				return 1;
			}
		}
		
		return 0;
	}

	public void updateProductDesc(int id, String desc){
		gateway.updateProductDesc(id, desc);
	}
	
	public void updateProductNum(int id, String num){
		gateway.updateProductNum(id, num);
	}
	
	public void updateProductPartQuantity(int productId, int partId, int q){

		gateway.updateProductPartQuantity(productId, partId, q);
	}
	
	/*
	 * setters
	 */
	
	public void setSession(Session session){
		this.session = session;
	}
	public void setProductArray(){
		productArray = gateway.getProductsArrayList();
	}

	public void setProductPartArray(int id){
		productPartList = gateway.getAllProductParts(id);
	}
	
	public void setProductByNum(String num){
		product = gateway.getProductObjectByNum(num);
	}

	public void setProductPartByName(String name){
		productPart = gateway.getProductPartObjectByName(name);
	}
	
	public void setPartsList(){
		partsList = gateway.getPartsArrayList();
	}
	
	/*
	 * getters
	 */
	
	public Session getSession(){
		return session;
	}
	
	public int getPartId(String name){
		return gateway.getPartId(name);
	}
	
	public ArrayList getPartsList(){
		return partsList;
	}
	
	public ArrayList getProductArraylist(){
		return productArray;
	}
	
	public Product getCurrentProductObject(){
		return product;
	}
	
	public ProductPart getCurrentProductPartObject(){
		return productPart;
	}
	
	public ArrayList getProductPartList(){
		return productPartList;
	}
	
	public User getUserObjectByEmail(String email){
		return gateway.getUserObject(email);
	}
	
	public int getQuantity(int productId, int partId){
		return gateway.getQuantity(productId, partId);
		
	}

}
