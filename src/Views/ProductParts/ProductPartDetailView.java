 package Views.ProductParts;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MVC.MasterFrame;
import MVC.Product;
import MVC.ProductPart;
import Models.ProductTemplateModel;

public class ProductPartDetailView extends JPanel {
	
	private ProductTemplateModel model;
	private ProductPart productPart;
	private Product product;
	private MasterFrame master;
	private JDesktopPane desktop;
	private String myTitle = "Part Detail"; 
	private JLabel qLabel;
	private JLabel nameLabel;
	private JLabel numLabel;
	private JLabel vendorLabel;
	private JLabel unitLabel;
	private JLabel extLabel;
	private JButton delete = new JButton("Delete Part from Template");
	private JButton edit = new JButton("Edit Quantity");
	private int newFrameX = 50, newFrameY = 50; //used to cascade or stagger starting x,y of JInternalFrames

	
	public ProductPartDetailView(final ProductTemplateModel model, MasterFrame m){
		this.model = model;
		master = m;
		this.setLayout(new GridLayout(4,3));
		desktop = master.getDesktop();
		productPart = model.getCurrentProductPartObject();
		product = model.getCurrentProductObject();
		
		qLabel = new JLabel("Quantity Required: " + model.getQuantity(product.getId(), productPart.getId()));
		nameLabel = new JLabel("Part Name: " + productPart.getPartName());
		numLabel = new JLabel("Part #: " + productPart.getPartNumber());
		vendorLabel = new JLabel("Vendor: " + productPart.getVendor());
		unitLabel = new JLabel("Unit: " + productPart.getUnit());
		extLabel = new JLabel("Ext Part #: " + productPart.getExt());
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.deleteProductPartById(productPart.getId());
				master.displayChildMessage("You just deleted part " + productPart.getPartName() + " from product template " + product.getNum()+ 
						". Close and reopen Product Parts List to see changes.");
			}
		});
		
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		    	EditProductPartView child = new EditProductPartView(model, master);
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
		
		
		this.add(nameLabel);
		this.add(numLabel);
		this.add(vendorLabel);
		this.add(extLabel);
		this.add(qLabel);
		this.add(unitLabel);
		this.add(edit);
		this.add(delete);
		
	}
	
	public String getTitle(){
		return myTitle;
	}
	

}
