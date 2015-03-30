package MVC.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;

import MVC.views.productsViews.productErrorView;
import MVC.views.productsViews.productPartsListView;

public class productModel {
	//public productGatewaySQL gateway = new productGatewaySQL("ymd524", "ymd524", "HRqEF9KWp7MFw04SR0zZ");
	public productGatewaySQL gateway = new productGatewaySQL("lop343", "lop343", "dragon91z");
	public productPartsListView view;
	public ArrayList<String> productArrayList = new ArrayList();
	public ArrayList<String> productPartsArrayList = new ArrayList();
	public ArrayList<String> nameArrayList = new ArrayList();
	public ArrayList<Integer> productPartIdsList = new ArrayList();
	public ArrayList<String> productD = new ArrayList();
	public productErrorView errorView;
	public ResultSet results;
	public ResultSet results2;
	public String listText;
	public String productNum;
	public String productDesc;
	public String quantity;
	public String currentNum = null;
	public String proDesc;
	public int partId;
	public int id;
	public int q;
	public int flag = 0;
	public Date dateAdded; 
	public Date dateModified; 
	private String[] errorArray = new String[10];
	public String partName;
	public String partNum;
	public String partVendor;
	public String partUnit;
	public String partExt;
	
	public void addProduct(String num, String desc){
		checkNum(num);
		checkDesc(desc);
		
		this.dateAdded = new Date();
		this.dateModified = new Date();
		
		if(flag == 0){
			gateway.addProduct(num, desc);
		}else{
			errorView = new productErrorView(this);
			//errorView.registerListeners(tempController);
			errorView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//starts up inventoryView
			errorView.setSize(400,300);
			errorView.setLocation(400,0);
			errorView.setVisible(true);
		}
	}
	

	
	public int setPartByName(String name){
		int id = 0;
		results = null;
		try{
			results = gateway.getPartByName(name);
			setPartName(name);
			setPartNum(results.getString("partNumber"));
			setPartVendor(results.getString("vendor"));
			setPartUnit(results.getString("unit"));
			setPartExt(results.getString("extPartNumber"));

		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return id;
	}
	
	public void setPartName(String name){
		this.partName = name;
	}
	
	public void setPartNum(String num){
		this.partNum = num;
	}
	
	public void setPartVendor(String ven){
		this.partVendor = ven;
	}
	
	public void setPartUnit(String unit){
		this.partUnit = unit;
	}
	
	public void setPartExt(String ext){
		this.partExt = ext;
	}
	
	public String getPartName(){
		return partName;
	}
	
	public String getPartNum(){
		return partNum;
	}
	
	public String getPartVendor(){
		return partVendor;
	}
	
	public String getPartUnit(){
		return partUnit;
	}
	
	public String getPartExt(){
		return partExt;
	}
	
	public void updateProduct(String num, String desc){
		currentNum = num;
		checkNum(num);
		checkDesc(desc);

		this.dateModified = new Date();
		
		if(flag == 0){
			gateway.updateProduct(num, desc);
		}else{
			
		}
		currentNum = null;
	}

	public void checkNum(String num){
		String nullString = null;
		nameArrayList = new ArrayList();
		if(num.length() > 20){
			setError("Part Number exceeds 20 characters");
			flag += 1;
		}
		
		if(num.charAt(0) != 'A'){
			setError("Part Number does not start with the letter 'A' ");
			flag += 1;
		}
		
		results = null;
		try{
			results = gateway.getAllProducts();
			productNum = results.getString("productNum");
			nameArrayList.add(productNum);
			while(results.next()){
				productNum = results.getString("productNum");
				nameArrayList.add(productNum);
			}
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		
		if(nameArrayList.contains(num)){
			//if(currentNum.equals(null)){
				setError("Part Number is not unique");
				flag += 1;
			//}
		}
	}
	
	public void setError(String errorMessage){
		errorArray[flag] = errorMessage;
	}
	
	public void resetErrors(){
		errorArray = new String[10];
		flag = 0;
	}
	
	public void checkDesc(String desc){
		if(desc.length() > 255){
			setError("Desription exceeds 255 characters");
			flag += 1;
		}
	}
	
	public ArrayList<String> getAllProductPartsList(){
		results = null;
		productPartsArrayList = new ArrayList();
		try{
			results = gateway.getAllProductParts(id);
			partId = results.getInt("partId");
			String partName = gateway.getPartNameById(partId);
			q = results.getInt("quantity");
			quantity = new Integer(q).toString();
			listText = toString(partName, quantity);
			productPartsArrayList.add(listText);
			
			while(results.next()){
				partId = results.getInt("partId");
				partName = gateway.getPartNameById(partId);
				q = results.getInt("quantity");
				quantity = new Integer(q).toString();
				listText = toString(partName, quantity);
				productPartsArrayList.add(listText);
			}
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return productPartsArrayList;
	}
	
	public ArrayList<String> getAllProductParts(){
		results = null;
		productPartsArrayList = new ArrayList();
		try{
			results = gateway.getAllProductParts(id);
			partId = results.getInt("partId");
			String partName = gateway.getPartNameById(partId);
			productPartsArrayList.add(partName);
			
			while(results.next()){
				partId = results.getInt("partId");
				partName = gateway.getPartNameById(partId);
				productPartsArrayList.add(partName);
			}
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return productPartsArrayList;
	}
	
	/*public ArrayList<Integer> getAllProductPartIds(){
		results = null;
		productPartIdsList.clear();
		try{
			results = gateway.getAllProductParts(id);
			partId = results.getInt("partId");
			productPartIdsList.add(partId);
			while(results.next()){
				partId = results.getInt("partId");
				productPartIdsList.add(partId);
			}
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return productPartIdsList;
	}*/
	public void closeView(productPartsListView view){
		this.view = view;
		this.view.closeWindow();
	}
	
	public ArrayList<String> getAllProducts(){
		results = null;
		productArrayList.clear();
		try{
			results = gateway.getAllProducts();
			productNum = results.getString("productNum");
			productDesc = results.getString("productDesc");
			listText = toString(productNum, productDesc);
			productArrayList.add(listText);
			
			while(results.next()){
				productNum = results.getString("productNum");
				productDesc = results.getString("productDesc");
				listText = toString(productNum, productDesc);
				productArrayList.add(listText);
			}
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return productArrayList;
	}
	
	public ArrayList<String> getAllParts(){
		results = null;
		try{
			results = gateway.getAllParts();
			partName = results.getString("partName");
			nameArrayList.add(partName);
			
			while(results.next()){
				partName = results.getString("partName");
				nameArrayList.add(partName);
			}
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return nameArrayList;
	}
	
	public void getProductsDescription(){
		results2 = null;
		//productD.clear();
		productD = new ArrayList();
		try{
			results2 = gateway.getAllProducts();
			productD.add(results2.getString("productDesc"));
			while(results2.next()){
				productD.add(results2.getString("productDesc"));
			}
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		//return productD;
	}
	
	public void addPartToProduct(String name, int q){
		results = null;
		try{
			results = gateway.getPartByName(name);
			int partId = results.getInt("id");
			int productId = getId();
			gateway.addProductPart(partId, productId , q);
			
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void deletePartFromProduct(String name){
			results = null;
			try{
				results = gateway.getPartByName(name);
				int partId = results.getInt("id");

			}catch(SQLException e){
				throw new RuntimeException(e.getMessage());
			}
			gateway.deleteProductPart(partId, this.id);
	}
	

	
	public String toString(String proNum, String proDesc ){
		listText = proNum;
		listText += " ";
		listText += proDesc;
		return listText;
	}
	
	/*gets and assigns values of currentObject*/
	public void setProductNumber(String num){
		productNum = num;
	}
	public void setProductDesc(String desc){
		productDesc = desc;
	}
	public void setDateAdded(Date date){
		dateAdded = date;
	}
	public void setDateModified(Date date){
		dateModified = date;
	}
	public void setId(int id){
		this.id = id;
	}
	
	public int setProductByNum(String num){
		int id = 0;
		results = null;
		try{
			results = gateway.getProductByNum(num);
			setProductNumber(num);
			setId(results.getInt("id"));
			setProductDesc(results.getString("productDesc"));
			//setDateAdded(results.getDate("dateAdded"));
			setDateModified(results.getDate("lastModified"));

		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return id;
	}
	
	public int getProductId(String number) {
		int id=0;
		try{
			results = gateway.getProductByNum(number);
			id = results.getInt("id");
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return id;
	}
	
	public int getProductIdByDesc(String desc) {
		int id=0;
		try{
			results = gateway.getProductByDesc(desc);
			id = results.getInt("id");
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return id;
	}
	
	public String getProductDescById(int id) {
		String str = gateway.getProductByDesc(id);
		return str;
	}
	
	/*gets and assigns values of currentObject*/
	
	public ArrayList getProductArray(){
		return productArrayList;
	}
	
	public ArrayList getProductPartsArray(){
		return productPartsArrayList;
	}
	public ArrayList getProductDescArray() {
		return productD;
	}
	
	public String getProductNumber(){
		return productNum;
	}
	public String getProductDesc(){
		return productDesc;
	}
	public Date getDateAdded(){
		return dateAdded;
	}
	public Date getDateModified(){
		return dateModified;
	}
	
	public String[] getErrorArray(){
		return errorArray;
	}
	
	public int getId(){
		return this.id;
	}
	
}
