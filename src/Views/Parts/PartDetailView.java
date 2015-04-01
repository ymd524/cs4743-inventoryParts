package Views.Parts;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MVC.MasterFrame;
import MVC.Part;
import MVC.Session;
import Models.PartModel;
import Views.Inventory.AddInventoryPartView;

public class PartDetailView extends JPanel{
	
	private MasterFrame master;
	private PartModel model;
	private Session session;
	private Part part;
	private JDesktopPane desktop;
	private String myTitle = "Part Detail";
	private int partId;
	private JLabel id;
	private JLabel name;
	private JLabel num;
	private JLabel vendor;
	private JLabel unit;
	private JLabel ext;
	private JButton edit = new JButton("Edit Template");
	private JButton delete = new JButton("Delete Template");
	
	
	
	public PartDetailView(MasterFrame m){
		master = m;
		this.model = master.getPartModel();
		session = model.getSession();
		this.setLayout(new GridLayout(4,2));
		part = model.getCurrentPartObject();
		partId = part.getId();
		desktop = master.getDesktop();
		id = new JLabel("ID #: " + part.getId());
		name = new JLabel("Part Name: " + part.getName());
		num = new JLabel("Part #: " + part.getNum());
		vendor = new JLabel("Vendor: " + part.getVendor());
		unit = new JLabel("Unit: " + part.getUnit());
		ext = new JLabel("Ext #: " + part.getExt());	
		
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*if(session == null || !session.canDeleteParts()){
					master.displayChildMessage("You do not have permission to Delete a part.");
				}else{*/
				System.out.println(part.getId());
				model.deletePartById(part.getId());
				master.displayChildMessage("Part Deleted Successfully");
				//master.closeAddPartFrame();
				}
		});

		
		this.add(id);
		this.add(name);
		this.add(num);
		this.add(vendor);
		this.add(unit);
		this.add(ext);
		this.add(edit);
		this.add(delete);
		
		this.setPreferredSize(new Dimension(300, 300));
	}
	
	public String getTitle(){
		return myTitle;
	}

}
