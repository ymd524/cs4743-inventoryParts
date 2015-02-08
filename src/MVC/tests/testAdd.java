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
		model.addPart("8976897sdsh7", "part1", "vendor1", 2, "khjdf797", "pieces", "Facility 4");
		assertEquals(1,model.getObjectArray().size());
	}
	
	@Test
	public void testDeletePart() {

		model.addPart("8976897sdsh7", "part1", "vendor1", 5,"jkhg876hj", "Pieces", "Facility 1");
		model.addPart("H4G789KJM83S", "part2", "vendor2", 9, "jhkgj6576jh", "Unit", "Facility 2");
		model.getObjectByInt(1);
		model.deletePart();
		assertEquals(1,model.getObjectArray().size());
	}

	@Test
	public void testUpdatePart() {

		model.addPart("8976897sdsh7", "part1", "vendor1", 5, "ljkh76dfsdf8", "sets", "Facility 1");
		model.getObjectByInt(0);
		model.updatePart("H4G789KJM83S", "part2", "vendor2", 9,"lkhfau6987kj", "meters", "Facility 2");
		assertEquals("part2", model.getCurrentObject().getName());
	}
	
	@Test
	public void testPartId() {
		model.addPart("8976897sdsh7", "part1", "vendor1", 5, "kjhdsf76hv765", "sets" , "Facility 3");
		model.addPart("8976897sdsh7", "part1", "vendor1", 5, "987jhg765gf54h", "meters", "Facility 2");
		model.getObjectByInt(1);
		assertEquals(2, model.getCurrentObject().getId());
	}
}
