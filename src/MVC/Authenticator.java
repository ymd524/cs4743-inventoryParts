package MVC;

import Models.ProductTemplateModel;

public class Authenticator {
	
	private String email;
	private String password;
	private String name;
	private String role;
	private String pw;
	private int userId;
	private boolean emailPassed;
	private boolean passwordPassed;
	private boolean status;
	private User user;
	private Session session;
	private ProductTemplateModel model;
	
	public Authenticator(User user, String mail, String password){
		this.model = model;
		this.email = user.getEmail();
		this.name = user.getName();
		this.userId = user.getId();
		this.role= user.getRole();
		this.pw = user.getPw();
		
		if(!mail.equals(this.email)){
			setStatus(false);
		}else{
			if(!password.equals(this.pw)){
				setStatus(false);
			}else{
				setStatus(true);
			}
		}
		
		
	}
	
	public void setStatus(boolean b){
		status = b;
	}	
	public boolean getStatus(){
		return status;
	}
	
	public void setSession(User user){
		session = new Session(user);
	}
	
	public Session getSession(){
		return session;
	}

}
