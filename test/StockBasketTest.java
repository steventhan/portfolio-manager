
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import custom.util.StockPriceNotFound;
import model.trader.IStockSingle;
import model.trader.StockBasket;
import model.trader.StockSingle;

/**
 * JUnit tests for StockBasket.
 */
public class StockBasketTest {
  StockBasket sb1;
  StockBasket sb2;
  StockBasket sb3;
  StockBasket sb4;

  /**
   * Creates stock baskets for testing purposes.
   */
  @Before
  public void setup() throws Exception {
    this.sb1 = new StockBasket();

    // using HashMap because, order doesn't matter
    Map<IStockSingle, Integer> temp = new HashMap<>();
    temp.put(new StockSingle("MMM"), 3);
    temp.put(new StockSingle("ATVI"), 4);
    temp.put(new StockSingle("ADBE"), 5);
    temp.put(new StockSingle("A"), 6);
    temp.put(new StockSingle("VZ"), 7);
    temp.put(new StockSingle("UPS"), 8);
    this.sb2 = new StockBasket(temp);

    this.sb3 = new StockBasket();
    this.sb3.add("AAPL", 3);
    this.sb3.add("MSFT", 5);
    this.sb3.add("FB", 7);
    this.sb3.add("GOOGL", 9);

    this.sb4 = new StockBasket();
    this.sb4.add("GE", 9);
    this.sb4.add("VZ", 20);
    this.sb4.add("DIS", 14);
  }

  @Test
  public void testConstructorLegal() {
    Assert.assertEquals(0, this.sb1.size());
    Assert.assertEquals(6, this.sb2.size());
    Assert.assertEquals(4, this.sb3.size());
    Assert.assertEquals(3, this.sb4.size());

    Assert.assertTrue(this.sb2.toString().contains("A: 6\n"));
    Assert.assertTrue(this.sb2.toString().contains("VZ: 7\n"));
    Assert.assertTrue(this.sb2.toString().contains("MMM: 3\n"));
    Assert.assertTrue(this.sb2.toString().contains("ADBE: 5\n"));

    Assert.assertTrue(this.sb3.toString().contains("AAPL: 3\n"));
    Assert.assertTrue(this.sb3.toString().contains("FB: 7\n"));
    Assert.assertTrue(this.sb3.toString().contains("MSFT: 5\n"));
    Assert.assertTrue(this.sb3.toString().contains("GOOGL: 9\n"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIllegal() {
    Map<IStockSingle, Integer> temp = null;
    //noinspection ConstantConditions
    new StockBasket(temp);
  }

  @Test
  public void testGetPriceOnDay() throws Exception {
    Assert.assertEquals(2917.09, this.sb2.getPriceOnDay("2017-04-07"), 0.01);
    Assert.assertEquals(2976.54, this.sb2.getPriceOnDay("2017-04-28"), 0.01);
    Assert.assertEquals(9361.4, this.sb3.getPriceOnDay("2017-03-21"), 0.01);
  }

  @Test
  public void testGetPriceOnIllegalDay() throws Exception {
    try {
      this.sb2.getPriceOnDay("1900-03-04");
      Assert.fail("Exception was not thrown for old date");
    } catch (StockPriceNotFound e) {
      // Pass test
    }

    try {
      this.sb3.getPriceOnDay("2018-02-03");
      Assert.fail("Exception was not thrown for future date");
    } catch (StockPriceNotFound e) {
      // Pass test
    }

    try {
      this.sb4.getPriceOnDay("2017-01-01");
      Assert.fail("Exception was not thrown for date without exchange activity");
    } catch (StockPriceNotFound e) {
      // Pass test
    }

    try {
      this.sb4.getPriceOnDay("2017-01-02");
      Assert.fail("Exception was not thrown for date without exchange activity");
    } catch (StockPriceNotFound e) {
      // Pass test
    }

    try {
      this.sb4.getPriceOnDay("2017-06-03");
      Assert.fail("Exception was not thrown for date without exchange activity");
    } catch (StockPriceNotFound e) {
      // Pass test
    }
  }

  @Test
  public void testTrendsUp() throws Exception {
    Assert.assertTrue(this.sb2.trendsUp("2017-06-12", "2017-06-13"));
    Assert.assertTrue(this.sb2.trendsUp("2017-04-07", "2017-04-28"));
    Assert.assertFalse(this.sb4.trendsUp("2016-07-18", "2016-10-14"));
  }

  @Test
  public void testTrendsUpIllegalDates() throws Exception {
    try {
      this.sb2.trendsUp("2017-06-13", "2017-06-12");
      Assert.fail("Exception was not thrown when from date is after to date");
    } catch (IllegalArgumentException e) {
      // Pass test
    }

    try {
      this.sb4.trendsUp("2017-06-12", "2017-06-12");
      Assert.fail("Exception was not thrown when from date same as to date");
    } catch (IllegalArgumentException e) {
      // Pass test
    }

    try {
      this.sb3.trendsUp("2017-05-05", "2017-06-11");
      Assert.fail("Exception was not thrown when to date is on weekend");
    } catch (Exception e) {
      // Pass test
    }

    try {
      this.sb3.trendsUp("2017-05-06", "2017-06-12");
      Assert.fail("Exception was not thrown when from date is on weekend");
    } catch (Exception e) {
      // Pass test
    }
  }

}