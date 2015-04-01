package Views.Products;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import Controllers.Products.ShowTemplatesController;
import MVC.MasterFrame;
import MVC.Session;
import Models.ProductTemplateModel;



//ChildPanel : the GUI stuff that will display the inner frames
//				all it does is update its title when you press the button (wow)
public class ShowTemplatesView extends JPanel {
	private static final long serialVersionUID = 1L;
	private MasterFrame master;//container of inner frame parent
	private JDesktopPane desktop;
	private int counter = 0;//used for inner frame title
	private String myTitle = "Product Templates";
	private ProductTemplateModel model;
	private ArrayList<String> productArray = new ArrayList();
	private String[] products;
	private Session session;
	private int newFrameX = 20, newFrameY = 20; //used to cascade or stagger starting x,y of JInternalFrames
	JList list;
	JButton button;
	JLabel access = new JLabel("Access denied.");
	JLabel label = new JLabel("You do not have permissions to view this page.");
	
	public ShowTemplatesView(MasterFrame m) {
		master = m;
		this.model = master.getProductTemplateModel();
		this.session = model.getSession();
		if(session == null || !session.canViewProductTemplates()){
			this.setLayout(new GridLayout(3,1));
			this.add(access);
			this.add(label);
		}else{
			this.setLayout(new BorderLayout());
			ShowTemplatesController Controller = new ShowTemplatesController(model, this, master);
			desktop = master.getDesktop();
			model.setProductArray();
		
			button = new JButton("");
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(session == null || !session.canAddProductTemplates()){
						master.displayChildMessage("You do not have permission to add a product template.");
					}else{
					
					AddTemplateView child = new AddTemplateView(model, master);
					JInternalFrame frame = new JInternalFrame(child.getTitle(), true, true, true, true );
					frame.add(child, BorderLayout.CENTER);
					frame.pack();
					//wimpy little cascade for new frame starting x and y
					frame.setLocation(newFrameX, newFrameY);
					newFrameX = (newFrameX) % desktop.getWidth(); 
					newFrameY = (newFrameY) % desktop.getHeight(); 
					desktop.add(frame);
					frame.setVisible(true);
					}
				}
			});
			productArray=model.getProductArraylist();//gets ArrayList of names
			products = new String[productArray.size()];//creates new String array the size of ArrayList namesArray
			products = productArray.toArray(products);

			list = new JList(products);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.addListSelectionListener(Controller);
			this.add(list, BorderLayout.CENTER);
			this.add(button, BorderLayout.SOUTH);
		
			//give panel some room (pack makes things kind of cramped)
			this.setPreferredSize(new Dimension(300, 300));
		
			incrementTitle();
		}
	}
	
	//set the title of the containing JInternalFrame
	private void setInternalFrameTitle(String t) {
		Container parent = this;
		//get climbing parent hierarchy until we find the JInnerFrame
		while(!(parent instanceof JInternalFrame) && parent != null) 
            parent = parent.getParent();
		if(parent != null)
			((JInternalFrame) parent).setTitle(t);
	}
	
	private void incrementTitle() {
		myTitle = "Product Templates";
		//update my title using getParent
		button.setText("Add New Template");
		//use getParent to find JInternalFrame container and set its title
		setInternalFrameTitle(myTitle);
	}
	
	//useful for JInternalFrame starting with the child panel's title
	//child panel is instantiated before the JInternalFrame
	//so JInternalFrame calls this method to set its own title when it is created 
	public String getTitle() {
		return myTitle;
	}
	
	public String getSelectedValue() {
		return list.getSelectedValue().toString();
	}
}
