package Controllers.ProductsParts;

import java.awt.BorderLayout;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MVC.MasterFrame;
import MVC.ProductPart;
import Models.ProductTemplateModel;
import Views.ProductParts.ProductPartDetailView;
import Views.ProductParts.ProductPartsView;
import Views.Products.ProductDetailView;

public class ProductPartsController implements ListSelectionListener {
	private ProductTemplateModel model;
	private ProductPartsView view; 
	private MasterFrame master;
	private JDesktopPane desktop;
	private int newFrameX = 40, newFrameY = 40;
	private ProductPart productPart;
	
	public ProductPartsController(ProductTemplateModel model, ProductPartsView view, MasterFrame m){
		this.model = model;
		this.view = view;
		master = m;
		desktop = master.getDesktop();
	}

	@Override
	 public void valueChanged(ListSelectionEvent e) {
	    if (!e.getValueIsAdjusting()){
	    	String command = view.getSelectedValue();
	    	model.setProductPartByName(command);
	    	productPart = model.getCurrentProductPartObject();
	    	//System.out.println(productPart.getPartNumber());
	    	
	    	
	    	ProductPartDetailView child = new ProductPartDetailView(model, master);
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
