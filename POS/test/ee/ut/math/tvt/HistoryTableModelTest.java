package ee.ut.math.tvt;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.HistoryTableModel;

public class HistoryTableModelTest {

	private HistoryTableModel model;
	private Order order;
	
	@Before
	public void setUp(){
		model = new HistoryTableModel();
		order = new Order(new ArrayList<SoldItem>(), "26-11-14", "00:00:00");
	}
	
	@Test
	public void testAddOrder() {
		model.AddOrder(order);
		assertEquals(model.rows.contains(order), true);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetOrderWithInvalidRowNr() {
		model.getOrder(-1);
	}

	


}
