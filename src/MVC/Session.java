package MVC;

public class Session {

	private User user;
	private boolean canViewProductTemplates;
	private boolean canAddProductTemplates;
	private boolean canDeleteProductTemplates;
	private boolean canCreateProducts;
	private boolean canViewInventory;
	private boolean canAddInventory;
	private boolean canViewParts;
	private boolean canAddParts;
	private boolean canDeleteParts;
	private boolean canDeleteInventory;
	private String fullName;
	private String email;
	private String role;
	private int id;
	
	public Session(User user){
		this.fullName = user.getName();
		this.email = user.getEmail();
		this.role = user.getRole();
		this.id = user.getId();
		
		if(role.equals("Production Manager")){
			canViewProductTemplates = true;
			canAddProductTemplates = true;
			canDeleteProductTemplates = true;
			canCreateProducts = true;
			canViewInventory = true;
			canAddInventory = false;
			canViewParts = true;
			canAddParts  = false;
			canDeleteParts = false;
			canDeleteInventory = false;
			
		}else if(role.equals("Inventory Manager")){
			
			canViewProductTemplates = false;
			canAddProductTemplates = false;
			canDeleteProductTemplates = false;
			canCreateProducts = false;
			canViewInventory = true;
			canAddInventory = true;
			canViewParts = true;
			canAddParts  = true;
			canDeleteParts = false;
			canDeleteInventory = false;
			
		}else if(role.equals("Admin")){
			
			canViewProductTemplates = true;
			canAddProductTemplates = true;
			canDeleteProductTemplates = true;
			canCreateProducts = true;
			canViewInventory = true;
			canAddInventory = true;
			canViewParts = true;
			canAddParts  = true;
			canDeleteParts = true;
			canDeleteInventory = true;
		}
		
	}
	
	public boolean canViewProductTemplates(){
		return canViewProductTemplates;
	}
	
	public boolean canAddProductTemplates(){
		return canAddProductTemplates;
	}
	
	public boolean canDeleteProductTemplates(){
		return canDeleteProductTemplates;
	}
	
	public boolean canCreateProducts(){
		return canCreateProducts;
	}
	
	public boolean canViewInventory(){
		return canViewInventory;
	}
	public boolean canAddInventory(){
		return canAddInventory;
	}
	
	public boolean canViewParts(){
		return canViewParts;
	}
	
	public boolean canAddParts(){
		return canAddParts;
	}
	
	public boolean canDeleteParts(){
		return canDeleteParts;
	}
	public boolean canDeleteInventory(){
		return canDeleteInventory;
	}
	
	
	
	
	
	
}
