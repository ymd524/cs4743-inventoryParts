package Controllers.Products;

import java.awt.BorderLayout;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MVC.MasterFrame;
import MVC.Product;
import Models.ProductTemplateModel;
import Views.Products.ProductDetailView;
import Views.Products.ShowTemplatesView;

public class ShowTemplatesController implements ListSelectionListener{
	
	private ProductTemplateModel model;
	private ShowTemplatesView view;
	private MasterFrame master;
	private Product product;
	private int newFrameX = 30, newFrameY = 30; //used to cascade or stagger starting x,y of JInternalFrames
	private JDesktopPane desktop;
	public ShowTemplatesController(ProductTemplateModel model, ShowTemplatesView view, MasterFrame m){
		this.model = model;
		this.view = view;
		master = m;
		desktop = master.getDesktop();
		
	}

	@Override
	 public void valueChanged(ListSelectionEvent e) {
	    if (!e.getValueIsAdjusting()){
	    	String command = view.getSelectedValue();
	    	model.setProductByNum(command);
	    	product = model.getCurrentProductObject();

	    	
				ProductDetailView child = new ProductDetailView(model, master);
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
}