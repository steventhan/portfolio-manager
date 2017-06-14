import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import util.NewStockRetriever;
import util.WebRetrieverSingleton;
import java.util.HashMap;
import java.util.Map;

/**
 * JUnit tests for StockBasket.
 */
public class StockBasketTest {
  StockBasket sb1;
  StockBasket sb2;
  StockBasket sb3;
  StockBasket sb4;

  @Before
  public void setup() throws Exception {
    this.sb1 = new StockBasket();

    // using HashMap because, order doesn't matter
    Map<StockSingle, Integer> temp = new HashMap<>();
    temp.put(new StockSingle("MMM"), 3);
    temp.put(new StockSingle("ATVI"), 4);
    temp.put(new StockSingle("ADBE"), 5);
    temp.put(new StockSingle("A"), 6);
    temp.put(new StockSingle("VZ"), 7);
    temp.put(new StockSingle("UPS"), 8);

    this.sb2 = new StockBasket(temp);
    this.sb3 = new StockBasket();
    this.sb4 = new StockBasket();
  }

  @Test
  public void testConstructorLegal() {
  }

  @Test
  public void testConstructorIllegal() {

  }

  @Test
  public void testGetPriceOnDay() throws Exception {

  }

  @Test
  public void testTrendsUp() throws Exception {
    Assert.assertTrue(this.sb2.trendsUp("2017-06-13", "2017-06-12"));
  }

  @Test
  public void testTrendsUpIllegalDates() throws Exception {
    Assert.assertTrue(this.sb2.trendsUp("2017-06-13", "2017-06-12"));
  }

}