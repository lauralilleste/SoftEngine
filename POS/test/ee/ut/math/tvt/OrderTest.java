package ee.ut.math.tvt;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class OrderTest {
	
	private Order order;

	@Before
	public void setUp() throws Exception {
		order = new Order(new ArrayList<SoldItem>(),"26.11.2014","19:52:29");
	}

	@Test
	public void testAddSoldItem() {
		SoldItem soldItem = new SoldItem(new StockItem(435L, "Marjakook", "magustoit", 2.34),3);
		order.addSoldItem(soldItem);
	
	}
	

	

}
