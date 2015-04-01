package Views.Parts;

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

import Controllers.Parts.ShowPartsController;
import Controllers.Products.ShowTemplatesController;
import MVC.MasterFrame;
import MVC.Session;
import Models.PartModel;
import Views.Products.AddTemplateView;

public class ShowPartsView extends JPanel{
	
	private MasterFrame master;
	private PartModel model;
	private String myTitle = "Parts List";
	private Session session;
	private JDesktopPane desktop;
	private ArrayList<String> partArray = new ArrayList();
	private int newFrameX = 30, newFrameY = 30; //used to cascade or stagger starting x,y of JInternalFrames
	private String[] parts;
	private JList list;
	JLabel access = new JLabel("Access denied.");
	JLabel label = new JLabel("You do not have permissions to view this page.");
	JButton button;
	
	public ShowPartsView(MasterFrame m){
		master = m;
		model = master.getPartModel();
		session = model.getSession();
		
		/*if(session == null || !session.canViewParts()){
			this.setLayout(new GridLayout(3,1));
			this.add(access);
			this.add(label);
		}else{*/
			this.setLayout(new BorderLayout());
			ShowPartsController Controller = new ShowPartsController(this, master);
			desktop = master.getDesktop();
			model.setPartArrayList();
			
			button = new JButton("");
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					/*if(session == null || !session.canAddParts()){
						master.displayChildMessage("You do not have permission to add a part.");
					}else{*/
					
					AddPartView child = new AddPartView(master);
					JInternalFrame frame = new JInternalFrame(child.getTitle(), true, true, true, true );
					frame.add(child, BorderLayout.CENTER);
					frame.pack();
					//wimpy little cascade for new frame starting x and y
					frame.setLocation(newFrameX, newFrameY);
					newFrameX = (newFrameX) % desktop.getWidth(); 
					newFrameY = (newFrameY) % desktop.getHeight(); 
					desktop.add(frame);
					frame.setVisible(true);
					master.addPartFrame(frame);
					}
				//}
			});
			
			partArray=model.getPartArrayList();//gets ArrayList of names
			parts = new String[partArray.size()];//creates new String array the size of ArrayList namesArray
			parts = partArray.toArray(parts);

			list = new JList(parts);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.addListSelectionListener(Controller);
			this.add(list, BorderLayout.CENTER);
			this.add(button, BorderLayout.SOUTH);
			
			this.setPreferredSize(new Dimension(300, 300));
			incrementTitle();
		}
	//}
	
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
		//myTitle = "Product Templates";
		//update my title using getParent
		button.setText("Add New Part");
		//use getParent to find JInternalFrame container and set its title
		setInternalFrameTitle(myTitle);
	}

	public String getTitle(){
		return myTitle;
	}
	
	public String getSelectedValue() {
		return list.getSelectedValue().toString();
	}
}
