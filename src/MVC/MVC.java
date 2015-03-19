package MVC;

import java.awt.event.
*
;
import javax.swing.JFrame;

import MVC.controllers.menuController;
import MVC.controllers.showInventoryController;
import MVC.controllers.showPartsController;
import MVC.controllers.productControllers.showTemplatesController;
import MVC.models.inventoryModel;
import MVC.models.productModel;
import MVC.views.inventoryListView;
import MVC.views.showPartsView;
/*   
 * Assignment 2 by Richard & Zach
 * 
 * */
import MVC.views.productsViews.showTemplatesView;

public class MVC {
	

		public static void main(String[] args) {	
			
			inventoryModel model = new inventoryModel();/* create new model, view and controller */
			productModel proModel = new productModel();
			showPartsView partView = new showPartsView(model);
			showTemplatesView tempView = new showTemplatesView(proModel);
			inventoryListView inventoryView = new inventoryListView(model);
			showInventoryController inventoryController = new showInventoryController(inventoryView, model);
			showPartsController controller = new showPartsController(partView, model);
			showTemplatesController tempController = new showTemplatesController(tempView, proModel);
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
			
			tempView.registerListeners(tempController);
			tempView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//starts up inventoryView
			tempView.setSize(400,300);
			tempView.setLocation(400,0);
			tempView.setVisible(true);
		
		}
}
