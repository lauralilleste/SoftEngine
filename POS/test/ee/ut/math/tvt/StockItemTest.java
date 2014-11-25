package ee.ut.math.tvt;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockItemTest {
private StockItem item;

@Before
public void setUp() {
  item = new StockItem((long) 1, "Viru Valge", "vodka", 3.50); 
}




}
