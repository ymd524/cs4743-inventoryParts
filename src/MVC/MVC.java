package MVC;


import javax.swing.JFrame;

import MVC.controllers.menuController;
import MVC.controllers.showInventoryController;
import MVC.controllers.showPartsController;
import MVC.models.inventoryModel;
import MVC.views.inventoryListView;
import MVC.views.showPartsView;
/*   
 * Assignment 2 by Richard & Zach
 * 
 * */

public class MVC {
	

		public static void main(String[] args) {	
			
			inventoryModel model = new inventoryModel();/* create new model, view and controller */
			showPartsView partView = new showPartsView(model);
			inventoryListView inventoryView = new inventoryListView(model);
			showInventoryController inventoryController = new showInventoryController(inventoryView, model);
			showPartsController controller = new showPartsController(partView, model);
			menuController menuController = new menuController(model, partView);
			
			partView.registerListeners(controller, menuController);//register controllers as listeners
			partView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//starts up showPartsView
			partView.setSize(400, 300);
			partView.setVisible(true);		
			
			inventoryView.registerListeners(inventoryController, menuController);//register controllers as listeners
			inventoryView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//starts up inventoryView
			inventoryView.setSize(400, 300);
			inventoryView.setLocation(400, 0);
			inventoryView.setVisible(true);	
		
		}
}
