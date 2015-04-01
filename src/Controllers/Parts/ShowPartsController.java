package Controllers.Parts;

import java.awt.BorderLayout;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MVC.MasterFrame;
import MVC.Part;
import MVC.ProductPart;
import Models.PartModel;
import Views.Parts.PartDetailView;
import Views.Parts.ShowPartsView;
import Views.ProductParts.ProductPartDetailView;

public class ShowPartsController implements ListSelectionListener{
	
	private ShowPartsView view;
	private MasterFrame master;
	private PartModel model;
	private JDesktopPane desktop;
	private int newFrameX = 40, newFrameY = 40;
	private Part part;
	
	
	public ShowPartsController(ShowPartsView view, MasterFrame m){
		master = m;
		model = master.getPartModel();
		this.view = view;
		desktop = master.getDesktop();
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()){
			String command = view.getSelectedValue();
			model.setPartByName(command);
			part = model.getCurrentPartObject();
			//System.out.println(productPart.getPartNumber());
    	
    	
			PartDetailView child = new PartDetailView(master);
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
