package Views.Products;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MVC.MasterFrame;
import MVC.Product;
import MVC.Session;
import Models.ProductTemplateModel;
import Views.ProductParts.ProductPartsView;

public class ProductDetailView extends JPanel {

	public ProductTemplateModel model;
	private MasterFrame master;
	private Product product;
	private String myTitle = "Product Detail";
	private JLabel id;
	private JLabel num;
	private JLabel desc;
	private JLabel time;
	private JButton edit = new JButton("Edit Template");
	private JButton delete = new JButton("Delete Template");
	private JButton parts = new JButton("Product Parts");
	private int newFrameX = 20, newFrameY = 20; //used to cascade or stagger starting x,y of JInternalFrames
	private JDesktopPane desktop;
	private Session session;
	
	public ProductDetailView(final ProductTemplateModel model, MasterFrame m){
		this.model = model;
		master = m;
		session = model.getSession();
		this.setLayout(new GridLayout(7,1));
		product = model.getCurrentProductObject();
		int ID = product.getId();
		desktop = master.getDesktop();
		id = new JLabel("ID #: " + product.getId());
		num = new JLabel("Product #: " + product.getNum());
		desc = new JLabel("Description: " + product.getDesc());
		time = new JLabel("Last Modified: " + product.getTime());

		
		this.add(id);
		this.add(num);
		this.add(desc);
		this.add(time);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(session == null || !session.canDeleteProductTemplates()){
					master.displayChildMessage("You do not have permission to delete product template.");
				}else{
				
					model.deleteProductById(product.getId());
					master.displayChildMessage("You just deleted part " + product.getNum() + ". Select Products > Show Templates in the menu bar to show updated list.");
				}
			}
		});
		
		parts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		    	ProductPartsView child = new ProductPartsView(model, master);
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
		
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		    	EditProductView child = new EditProductView(model, master);
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
		
		this.add(edit);
		this.add(delete);
		this.add(parts);
		}	
		
	
	
	public String getTitle(){
		return myTitle;
	}
	

}
