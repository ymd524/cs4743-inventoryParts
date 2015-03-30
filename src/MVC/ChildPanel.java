package MVC;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//ChildPanel : the GUI stuff that will display the inner frames
//				all it does is update its title when you press the button (wow)
public class ChildPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private MasterFrame master;//container of inner frame parent
	private int counter = 0;//used for inner frame title
	private String myTitle;
	JButton button;
	
	public ChildPanel(MasterFrame m) {
		master = m;
		
		this.setLayout(new BorderLayout());
		
		button = new JButton("Increment Title");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//update title
				incrementTitle();
				//tell master to display a message
				master.displayChildMessage("Child title changed to " + myTitle);
			}
		});
		
		JPanel emptyPanel = new JPanel();
		emptyPanel.add(new JLabel(""));
		this.add(emptyPanel, BorderLayout.CENTER);
		this.add(button, BorderLayout.SOUTH);
		
		//give panel some room (pack makes things kind of cramped)
		this.setPreferredSize(new Dimension(300, 300));
		
		incrementTitle();
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
	
	//
	private void incrementTitle() {
		myTitle = "Show Product Templates";
		//update my title using getParent
		button.setText("Add new template");
		counter++;
		//use getParent to find JInternalFrame container and set its title
		setInternalFrameTitle(myTitle);
	}
	
	//useful for JInternalFrame starting with the child panel's title
	//child panel is instantiated before the JInternalFrame
	//so JInternalFrame calls this method to set its own title when it is created 
	public String getTitle() {
		return myTitle;
	}
}
