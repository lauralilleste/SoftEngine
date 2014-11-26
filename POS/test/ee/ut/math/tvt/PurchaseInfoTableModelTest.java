package ee.ut.math.tvt;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

public class PurchaseInfoTableModelTest {
	

	private PurchaseInfoTableModel model;
	private StockItem item;
	private StockItem item2;
	private SoldItem sitem;
	private SoldItem sitem2;

	@Before
	public void setUp() throws Exception {
		model = new PurchaseInfoTableModel();
		item = new StockItem(27L, "Laua Viin", "vodka", 3.50,69);
		item2 = new StockItem(200l, "Saulita", "wine", 3.25, 1);
		sitem = new SoldItem(item, 5);
		sitem2 = new SoldItem(item2, 1);
	}
	
	@Test
	public void testSoldAddItem() {
          model.addItem(sitem, null);
          assertTrue(model.getTableRows().contains(sitem)); 
	}


	@Test
	public void testGetSumWithNoItems(){
		model = new PurchaseInfoTableModel();
		assertEquals(model.setPurchaseSum(), 0);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetSumWithOneItem(){
		model = new PurchaseInfoTableModel();
		model.addItem(sitem, null);
		assertEquals(model.getPurchaseSum(), 0.0, 0.0001);
	}
	@Test
	public void testGetSumWithMultipleItems(){
		model = new PurchaseInfoTableModel();
		model.addItem(sitem, null);
		model.addItem(sitem2, null);
		assertEquals(model.getPurchaseSum(),(10+28));
	}
	
	

}
