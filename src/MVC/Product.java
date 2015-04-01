package MVC;

import java.sql.Timestamp;

public class Product {
	
	private String num; 
	private String desc;
	private int id;
	private Timestamp time;
	
	public Product(String num, String desc, int id, Timestamp time){
		this.num = num;
		this.desc = desc;
		this.id = id;
		this.time = time;
	}
	
	public String getNum(){
		return this.num;
	}
	
	public String getDesc(){
		return this.desc;
	}
	
	public int getId(){
		return this.id;
	}
	
	public Timestamp getTime(){
		return this.time;
	}

}
