package MVC;

import java.awt.event.*;

import javax.swing.Timer;
import javax.swing.JFrame;

import MVC.controllers.menuController;
import MVC.controllers.showPartsController;
import MVC.models.inventoryModel;
import MVC.views.showPartsView;
/*   
 * Assignment 2 by Richard & Zach
 * 
 * */

public class MVC {
	

		public static void main(String[] args) {
			
			inventoryModel model = new inventoryModel();/* create new model, view and controller */
			showPartsView view = new showPartsView(model);
			showPartsController controller = new showPartsController(view, model);
			menuController menuController = new menuController(model, view);
			
			view.registerListeners(controller, menuController);//register controllers as listeners
			view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//starts up showPartsView
			view.setSize(400, 300);
			view.setVisible(true);			
		
		}
}
