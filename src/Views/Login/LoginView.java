package Views.Login;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MVC.Authenticator;
import MVC.MasterFrame;
import MVC.Session;
import MVC.User;
import Models.ProductTemplateModel;
import Views.Products.AddTemplateView;

public class LoginView extends JPanel {

	private MasterFrame master;
	private ProductTemplateModel model;
	private User user;
	private Session session;
	private String myTitle = "Sign In";
	JButton submit = new JButton("Submit");
	JLabel email = new JLabel("Email: ");
	JLabel password = new JLabel("Password: ");
	JTextField emailText= new JTextField(20);
	JTextField passwordText = new JTextField(20);
	
	
	public LoginView(MasterFrame m){
		master = m;
		this.model = master.getProductTemplateModel();
		this.setLayout(new GridLayout(5,1));
		
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					user = model.getUserObjectByEmail(getEmail());
					if(user == null){
						master.displayChildMessage("Incorrect Email");
					}else{
						Authenticator obj = new Authenticator(user, getEmail(), getPassword());
						boolean access = obj.getStatus();
						if(access == false){
							master.displayChildMessage("Login Credentials Not Valid");
						}else{
							obj.setSession(user);
							session = obj.getSession();
							master.setSession(session);
							master.addSession(user.getName());
							master.displayChildMessage("Log in successful!");
						}
					}	
			}
		});
		
		this.add(email);
		this.add(emailText);
		this.add(password);
		this.add(passwordText);
		this.add(submit);
		
		
		
	}
	
	public String getTitle(){
		return myTitle;
	}
	
	public String getEmail(){
		return emailText.getText();
	}
	
	public String getPassword(){
		return passwordText.getText();
	}
}
