package MVC;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Models.InventoryModel;
import Models.PartModel;
import Models.ProductTemplateModel;
import Views.Inventory.ShowInventoryView;
import Views.Login.LoginView;
import Views.Parts.ShowPartsView;
import Views.Products.ShowTemplatesView;



/*
 * MasterFrame : a little MDI skeleton that has communication from child to JInternalFrame 
 * 					and from child to the top-level JFrame (MasterFrame)  
 */
public class MasterFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JDesktopPane desktop;
	JMenuBar menuBar = new JMenuBar();
	JMenu menu;
	JMenu loginMenu;
	JMenu logoutMenu;
	JMenuItem menuItem;
	private JInternalFrame addPartFrame;
	private Session session;
	private PartModel partModel = new PartModel();
	private InventoryModel inventoryModel = new InventoryModel();
	private ProductTemplateModel productModel = new ProductTemplateModel();
	private int newFrameX = 0, newFrameY = 0; //used to cascade or stagger starting x,y of JInternalFrames
	public MasterFrame(String title) {
		super(title);
		
		//create menu for adding inner frames
		JMenu menu = new JMenu("File");
		JMenuItem menuItem = new JMenuItem("Quit");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MasterFrame.this.dispatchEvent(new WindowEvent(MasterFrame.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);
		
		menu = new JMenu("Parts");
		menuItem = new JMenuItem("Show Parts");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MasterFrame.this.resetModels();
				MasterFrame.this.setSession(MasterFrame.this.getSession());
				ShowPartsView child = new ShowPartsView(MasterFrame.this);
				JInternalFrame frame = new JInternalFrame(child.getTitle(), true, true, true, true );
				frame.add(child, BorderLayout.CENTER);
				frame.pack();
				//wimpy little cascade for new frame starting x and y
				frame.setLocation(newFrameX, newFrameY);
				newFrameX = (newFrameX + 10) % desktop.getWidth(); 
				newFrameY = (newFrameY + 10) % desktop.getHeight(); 
				desktop.add(frame);
				frame.setVisible(true);
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);
		
		menu = new JMenu("Inventory");
		menuItem = new JMenuItem("Show Inventory");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MasterFrame.this.resetModels();
				MasterFrame.this.setSession(MasterFrame.this.getSession());
				ShowInventoryView child = new ShowInventoryView(MasterFrame.this);
				JInternalFrame frame = new JInternalFrame(child.getTitle(), true, true, true, true );
				frame.add(child, BorderLayout.CENTER);
				frame.pack();
				//wimpy little cascade for new frame starting x and y
				frame.setLocation(newFrameX, newFrameY);
				newFrameX = (newFrameX + 10) % desktop.getWidth(); 
				newFrameY = (newFrameY + 10) % desktop.getHeight(); 
				desktop.add(frame);
				frame.setVisible(true);
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);

		
		menu = new JMenu("Products");
		menuItem = new JMenuItem("Show Templates");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MasterFrame.this.resetModels();
				MasterFrame.this.setSession(MasterFrame.this.getSession());
				ShowTemplatesView child = new ShowTemplatesView(MasterFrame.this);
				openMDIChild(child);
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);
		
		loginMenu = new JMenu("Login");
		menuItem = new JMenuItem("Sign In");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginView child = new LoginView(MasterFrame.this);
				JInternalFrame frame = new JInternalFrame(child.getTitle(), true, true, true, true );
				frame.add(child, BorderLayout.CENTER);
				frame.pack();
				//wimpy little cascade for new frame starting x and y
				frame.setLocation(newFrameX, newFrameY);
				newFrameX = (newFrameX + 10) % desktop.getWidth(); 
				newFrameY = (newFrameY + 10) % desktop.getHeight(); 
				desktop.add(frame);
				frame.setVisible(true);
			}
		});
		loginMenu.add(menuItem);
		menuBar.add(loginMenu);

		setJMenuBar(menuBar);
		   
		//create the MDI desktop
		desktop = new JDesktopPane();
		add(desktop);
	}
	
	public void resetModels(){
		partModel = new PartModel();
		productModel = new ProductTemplateModel();
		inventoryModel = new InventoryModel();
	}
	
	public void setSession(Session session){
		this.session = session;
		partModel.setSession(session);
		inventoryModel.setSession(session);
		productModel.setSession(session);
	}
	
	public void addSession(String name){
		menuBar.remove(loginMenu);
		logoutMenu = new JMenu(name);
		menuItem = new JMenuItem("Log Out");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				endSession();
			}
		});
		logoutMenu.add(menuItem);
		menuBar.add(logoutMenu);
	}
	
	public void endSession(){
		menuBar.remove(logoutMenu);
		productModel.endSession();
		inventoryModel.endSession();
		partModel.endSession();
		loginMenu = new JMenu("Login");
		menuItem = new JMenuItem("Sign In");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginView child = new LoginView(MasterFrame.this);
				JInternalFrame frame = new JInternalFrame(child.getTitle(), true, true, true, true );
				frame.add(child, BorderLayout.CENTER);
				frame.pack();
				//wimpy little cascade for new frame starting x and y
				frame.setLocation(newFrameX, newFrameY);
				newFrameX = (newFrameX + 10) % desktop.getWidth(); 
				newFrameY = (newFrameY + 10) % desktop.getHeight(); 
				desktop.add(frame);
				frame.setVisible(true);
			}
		});
		loginMenu.add(menuItem);
		menuBar.add(loginMenu);
	}
	
	public void addPartFrame(JInternalFrame frame){
		addPartFrame = frame;
	}
	
	public void closeAddPartFrame(){
		addPartFrame.dispose();
	}
	
	
	public Session getSession(){
		return this.session;
	}
	public PartModel getPartModel(){
		return partModel;
	}

	public InventoryModel getInventoryModel(){
		return inventoryModel;
	}
	
	public ProductTemplateModel getProductTemplateModel(){
		return productModel;
	}
	
	//create the child panel, insert it into a JInternalFrame and show it
	public void openMDIChild(ShowTemplatesView child) {
		JInternalFrame frame = new JInternalFrame(child.getTitle(), true, true, true, true );
		frame.add(child, BorderLayout.CENTER);
		frame.pack();
		//wimpy little cascade for new frame starting x and y
		frame.setLocation(newFrameX, newFrameY);
		newFrameX = (newFrameX + 10) % desktop.getWidth(); 
		newFrameY = (newFrameY + 10) % desktop.getHeight(); 
		desktop.add(frame);
		frame.setVisible(true);
	}
	
	public JDesktopPane getDesktop(){
		return desktop;
	}

	//display a child's message in a dialog centered on MDI frame
	public void displayChildMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	//creates and displays the JFrame
	public static void createAndShowGUI() {
		MasterFrame frame = new MasterFrame("Cabinetron");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setVisible(true);
	}

	//main: launch the JFrame on the EDT
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}


}
