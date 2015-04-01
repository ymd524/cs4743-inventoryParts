package Views.ProductParts;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import Controllers.ProductsParts.ProductPartsController;
import MVC.MasterFrame;
import MVC.Product;
import Models.ProductTemplateModel;

public class ProductPartsView extends JPanel{
	private MasterFrame master;
	private ProductTemplateModel model;
	private JDesktopPane desktop;
	private Product product;
	private ArrayList<String> partArray = new ArrayList();
	private String[] parts;
	private String myTitle = "Product Parts";
	private int newFrameX = 50, newFrameY = 50; //used to cascade or stagger starting x,y of JInternalFrames
	JButton add = new JButton("Add Part to Template");
	JList list = new JList();
	
	public ProductPartsView(final ProductTemplateModel model, MasterFrame m){
		master = m;
		this.model = model;
		product = model.getCurrentProductObject();
		ProductPartsController Controller = new ProductPartsController(model, this, master);
		this.setLayout(new BorderLayout());
		desktop = master.getDesktop();
		model.setProductPartArray(product.getId());
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		    	AddProductPartView child = new AddProductPartView(model, master);
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
		});
		
		if(model.getProductPartList().isEmpty()){
			master.displayChildMessage("This template contains no parts");
		}else{
			
		
		partArray = model.getProductPartList();//gets ArrayList of names
		parts = new String[partArray.size()];//creates new String array the size of ArrayList namesArray
		parts = partArray.toArray(parts);

		list = new JList(parts);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(Controller);
		this.add(list, BorderLayout.CENTER);
		this.add(add, BorderLayout.SOUTH);
		}
		}
	
	public String getTitle(){
		return myTitle;
	}

	public String getSelectedValue(){
		return list.getSelectedValue().toString();
	}
}
