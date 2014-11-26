package ee.ut.math.tvt;

import static org.junit.Assert.assertEquals;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import org.junit.Before;
import org.junit.Test;

public class SoldItemTest {

	private SoldItem item;
	
	@Before
	public void setUp() {
		item = new SoldItem(new StockItem((long) 27, "Laua Viin", "vodka", 3.50), 2);
	}
	
	@Test
	public void testGetSum() {
		assertEquals(item.getSum(), item.getPrice() * item.getQuantity(), 0.0001);
	}
	
	@Test
	public void testGetSumWithZeroQuantity() {
		item.setQuantity(0);
		assertEquals(item.getSum(), 0.0, 0.0001);
	}
}
