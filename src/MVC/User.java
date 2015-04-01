package MVC;

public class User {
	
	private String fullName;
	private String email;
	private String role;
	private int id;
	private String pw;
	
	public User(String fullName, String email, String role, int id, String pw){
		this.fullName = fullName;
		this.email = email;
		this.role = role;
		this.id = id;
		this.pw = pw;
		
	}

	public String getName(){
		return this.fullName;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getRole(){
		return this.role;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getPw(){
		return this.pw;
	}
	
}
