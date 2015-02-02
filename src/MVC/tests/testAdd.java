package MVC.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import MVC.controllers.addPartController;
import MVC.models.inventoryModel;
import MVC.views.addPartsView;

public class testAdd {
	
	inventoryModel model;
	addPartsView view;
	addPartController controller;
	
	@Before
	public void setUp(){
		model = new inventoryModel();
	}

	@Test
	public void testAddPart() {
		model.addPart("8976897sdsh7", "part1", "vendor1", 2);
		assertEquals(1,model.getObjectArray().size());
	}
	
	@Test
	public void testDeletePart() {
		model.addPart("8976897sdsh7", "part1", "vendor1", 5);
		model.addPart("H4G789KJM83S", "part2", "vendor2", 9);
		model.getObjectByInt(1);
		model.deletePart();
		assertEquals(1,model.getObjectArray().size());
	}

	@Test
	public void testUpdatePart() {
		model.addPart("8976897sdsh7", "part1", "vendor1", 5);
		model.getObjectByInt(0);
		model.updatePart("H4G789KJM83S", "part2", "vendor2", 9);
		assertEquals("part2", model.getCurrentObject().getName());
	}
}
