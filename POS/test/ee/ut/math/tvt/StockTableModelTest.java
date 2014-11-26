package ee.ut.math.tvt;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

public class StockTableModelTest {

	private StockTableModel model;
	private StockItem item;
	private StockItem item2;
	private StockItem item3;
	private static final Logger log = Logger.getLogger(StockTableModel.class);
	
	@Before
	public void setUp(){
		item = new StockItem(27L, "Laua Viin", "vodka", 3.50,6);
		item2 = new StockItem(564L, "Saulita", "Wine", 6.76, 9);
		item3 = new StockItem(68L, "Laua Viin", "vodka2", 5.32, 8);
		
	}
	
	@Test(expected= VerificationFailedException.class)
	public void testValidateNameUniqueness() throws VerificationFailedException {
		model = new StockTableModel();
		model.addItem(item);
		model.addItem(item3);
		assertEquals(2, (model.getItemByName(item.getName())).size());
	
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testHasEnoughInStock() {
		new SoldItem(item2, 2);
	}
	
	@Test
	public void testGetItemByIdWhenItemExists(){
		model = new StockTableModel();
		model.addItem(item);
		assertEquals(model.getItemById(item.getId()),item);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testGetItemByIdWhenThrowsException(){
		model = new StockTableModel();
		model.getItemById(-100);
	}

}
