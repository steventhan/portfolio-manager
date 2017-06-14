import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import util.NewStockRetriever;
import util.WebRetrieverSingleton;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by matthiasdenu on 6/13/2017.
 */
public class StockSingleTest {

  public StockSingleTest() throws Exception {
  }

  private NewStockRetriever retriever;

  // intialize constants for testing
  private final IStockSingle verizon = new StockSingle("VZ");
  // 6/13/2017	5/5/2017	2/14/2017	12/25/2016	12/23/2016
  // 46.450001	  46.69	      48.27	      #N/A	     53.68
  private final IStockSingle UPS = new StockSingle("UPS");
  //  6/13/2017	5/5/2017	2/14/2017	12/25/2016	12/23/2016
  // 109.809998	  107.43	   108.99	      #N/A	    115.97
  private final CustomDate today = new CustomDate();
  private final CustomDate cincoDeMayo = new CustomDate("2017-05-05");
  // VZ $46.69, $48.7546, $50.5683
  // UPS $107.43, $106.3434, $109.92555
  private final CustomDate valentines = new CustomDate("2017-02-14");
  // VZ $48.27, $51.3636, $51.69135
  // UPS $108.99, $114.1286, $109.53035
  private final CustomDate christmas = new CustomDate("2016-12-25");
  // VZ n/a
  // UPS n/a
  private final CustomDate dec2316 = new CustomDate("2016-12-23");
  // VZ $53.68, $49.603, $51.9146
  // UPS $115.97, $113.1618, $108.22505
  private final CustomDate blackThursday = new CustomDate("1929-10-04");
  private final CustomDate firstContact = new CustomDate("2063-04-05");

  @Before
  public void setUp() throws Exception {
    // reinitialize retriever
    this.retriever = WebRetrieverSingleton.getInstance();
  }

  @Test
  public void testConstructor() throws Exception {
    // legal constructor arguments
    new StockSingle("A");
    new StockSingle("A", this.retriever);

    // illegal constructor arguments

    try { // empty string
      new StockSingle("");
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try { // null symbol
      new StockSingle(null);
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try { // null symbol
      new StockSingle(null, this.retriever);
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try { // no such symbol
      new StockSingle("AAA");
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try { // null retriever
      new StockSingle("A", null);
      Assert.fail();
    } catch (Exception e) {
      // pass
    }
  }

  @Test
  public void testGetPriceOnDayExceptions() throws Exception {
    // nulls, empty, wrong format, old, future, holiday

    try { // null
      assertEquals(0.0, verizon.getPriceOnDay(null), 0.001);
      Assert.fail();
    } catch (Exception e) {
      // pass
    }


    try { // empty string
      assertEquals(0.0, verizon.getPriceOnDay(""), 0.001);
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try { // wrong format
      assertEquals(0.0, verizon.getPriceOnDay("09-06-2000"), 0.001);
      Assert.fail();
    } catch (Exception e) {
      // pass
    }


    try { // holiday
      assertEquals(0.0, verizon.getPriceOnDay(christmas.toString()), 0.001);
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try { // holiday
      assertEquals(0.0, UPS.getPriceOnDay(christmas.toString()), 0.001);
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try { // really old date
      assertEquals(0.0, UPS.getPriceOnDay(blackThursday.toString()), 0.001);
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try { // future date
      assertEquals(0.0, UPS.getPriceOnDay(firstContact.toString()), 0.001);
      Assert.fail();
    } catch (Exception e) {
      // pass
    }


  }

  @Test
  public void testGetPriceOnDay() throws Exception {
    assertEquals(46.46, verizon.getPriceOnDay("2017-06-13"), 0.001);
    assertTrue(Double.isFinite(verizon.getPriceOnDay("2017-06-14")));

    // expected values retrieved from www.nyse.com
    assertTrue(Double.isFinite(verizon.getPriceOnDay(today.toString())));
    assertEquals(46.69, verizon.getPriceOnDay(cincoDeMayo.toString()), 0.001);
    assertEquals(48.27, verizon.getPriceOnDay(valentines.toString()), 0.001);
    assertEquals(53.68, verizon.getPriceOnDay(dec2316.toString()), 0.001);

    assertTrue(Double.isFinite(UPS.getPriceOnDay(today.toString())));
    assertEquals(107.43, UPS.getPriceOnDay(cincoDeMayo.toString()), 0.001);
    assertEquals(108.99, UPS.getPriceOnDay(valentines.toString()), 0.001);
    assertEquals(115.97, UPS.getPriceOnDay(dec2316.toString()), 0.001);
  }

  @Test
  public void getClosingPricesExceptions() throws Exception {
    // null, empty, past, future, wrong format

    Map<String, Double> actualPricesVZ = verizon.getClosingPrices("2017-05-11", "2017-06-12");
    Map<String, Double> actualPricesUPS = UPS.getClosingPrices("2017-05-11", "2017-06-12");

  }

  @Test
  public void getClosingPrices() throws Exception {
    Map<String, Double> expectedPricesVZ = new HashMap<>();
    expectedPricesVZ.put("6/12/2017", 47.19);
    expectedPricesVZ.put("6/9/2017", 46.72);
    expectedPricesVZ.put("6/8/2017", 46.19);
    expectedPricesVZ.put("6/7/2017", 46.5);
    expectedPricesVZ.put("6/6/2017", 46.44);
    expectedPricesVZ.put("6/5/2017", 46.37);
    expectedPricesVZ.put("6/2/2017", 46.44);
    expectedPricesVZ.put("6/1/2017", 46.51);
    expectedPricesVZ.put("5/31/2017", 46.64);
    expectedPricesVZ.put("5/30/2017", 46.2);
    expectedPricesVZ.put("5/26/2017", 45.32);
    expectedPricesVZ.put("5/25/2017", 45.31);
    expectedPricesVZ.put("5/24/2017", 45.04);
    expectedPricesVZ.put("5/23/2017", 45.48);
    expectedPricesVZ.put("5/22/2017", 45.48);
    expectedPricesVZ.put("5/19/2017", 45.42);
    expectedPricesVZ.put("5/18/2017", 45.04);
    expectedPricesVZ.put("5/17/2017", 44.48);
    expectedPricesVZ.put("5/16/2017", 45.31);
    expectedPricesVZ.put("5/15/2017", 45.38);
    expectedPricesVZ.put("5/12/2017", 45.84);
    expectedPricesVZ.put("5/11/2017", 46.02);

    Map<String, Double> expectedPricesUPS = new HashMap<>();
    expectedPricesUPS.put("6/12/2017", 108.94);
    expectedPricesUPS.put("6/9/2017", 107.03);
    expectedPricesUPS.put("6/8/2017", 106.75);
    expectedPricesUPS.put("6/7/2017", 106.68);
    expectedPricesUPS.put("6/6/2017", 107.43);
    expectedPricesUPS.put("6/5/2017", 107.95);
    expectedPricesUPS.put("6/2/2017", 107.67);
    expectedPricesUPS.put("6/1/2017", 107.66);
    expectedPricesUPS.put("5/31/2017", 105.97);
    expectedPricesUPS.put("5/30/2017", 106.81);
    expectedPricesUPS.put("5/26/2017", 105.87);
    expectedPricesUPS.put("5/25/2017", 105.94);
    expectedPricesUPS.put("5/24/2017", 104.72);
    expectedPricesUPS.put("5/23/2017", 104.2);
    expectedPricesUPS.put("5/22/2017", 103.78);
    expectedPricesUPS.put("5/19/2017", 103.42);
    expectedPricesUPS.put("5/18/2017", 103.19);
    expectedPricesUPS.put("5/17/2017", 102.87);
    expectedPricesUPS.put("5/16/2017", 104.5);
    expectedPricesUPS.put("5/15/2017", 105.29);
    expectedPricesUPS.put("5/12/2017", 104.13);
    expectedPricesUPS.put("5/11/2017", 104.15);

    Map<String, Double> actualPricesVZ = verizon.getClosingPrices("2017-05-11", "2017-06-12");
    Map<String, Double> actualPricesUPS = UPS.getClosingPrices("2017-05-11", "2017-06-12");

    //TODO: fix whatever issue is going on here with HashCode (docs say it returns Map<K, V>)
    for (String k : expectedPricesVZ.keySet()) {
      assertEquals(expectedPricesVZ.get(k),
              actualPricesVZ.get(k), 0.001);
    }

    for (String k : expectedPricesUPS.keySet()) {
      assertEquals(expectedPricesUPS.get(k),
              actualPricesUPS.get(k), 0.001);
    }

    actualPricesVZ = verizon.getClosingPrices(dec2316.toString(), dec2316.toString());
    assertEquals(actualPricesVZ.get(dec2316.toString()),
            verizon.getPriceOnDay(dec2316.toString()), 0.001);

    actualPricesUPS = UPS.getClosingPrices(dec2316.toString(), dec2316.toString());
    assertEquals(actualPricesUPS.get(dec2316.toString()),
            UPS.getPriceOnDay(dec2316.toString()), 0.001);
  }

  @Test
  public void isBuyingOppExceptions() throws Exception {

    //TODO: wrong format date exception (i.e. start using CustomDate as an intermediary)
    //TODO: use CustomDate.toString();

    try {
      assertFalse(UPS.isBuyingOpportunity(null));
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try {
      assertFalse(verizon.isBuyingOpportunity(null));
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try {
      assertFalse(UPS.isBuyingOpportunity(""));
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try {
      assertFalse(verizon.isBuyingOpportunity(""));
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try {
      //TODO: need to document what this returns. This should be an exception if getPriceOnDay()
      //TODO: throws an exception
      assertFalse(UPS.isBuyingOpportunity(blackThursday.toString()));
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try {
      assertFalse(verizon.isBuyingOpportunity(blackThursday.toString()));
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try {
      assertFalse(UPS.isBuyingOpportunity(firstContact.toString()));
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try {
      assertFalse(verizon.isBuyingOpportunity(firstContact.toString()));
      Assert.fail();
    } catch (Exception e) {
      // pass
    }
  }

  @Test
  public void isBuyingOpportunity() throws Exception {
    assertTrue(UPS.isBuyingOpportunity("2017-03-14"));
    assertFalse(UPS.isBuyingOpportunity("2017-03-15"));
    assertFalse(UPS.isBuyingOpportunity("03-14-2017"));
    assertTrue(verizon.isBuyingOpportunity("2016-10-20"));
    assertFalse(verizon.isBuyingOpportunity("2016-10-21"));
    assertFalse(verizon.isBuyingOpportunity("10-20-2016"));
  }

  @Test
  public void trendsUpExceptions() throws Exception {

    try {
      // nulls
      assertTrue(verizon.trendsUp(null, "2017-06-12"));
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try {
      // nulls
      assertTrue(verizon.trendsUp("2017-06-09", null));
      Assert.fail();
    } catch (Exception e) {
      // pass
    }


    try {
      // empty string
      assertTrue(verizon.trendsUp("", "2017-06-09"));
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try {
      // empty string
      assertTrue(verizon.trendsUp("2017-06-09", ""));
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try {
      // wrong order
      //TODO: document how this works or throw exception
      assertTrue(verizon.trendsUp("2017-06-12", "2017-06-09"));
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try {
      // wrong format
      //TODO: we should be able to throw an exception here
      assertTrue(verizon.trendsUp("09-06-2017", "08-10-2016"));
      Assert.fail();
    } catch (Exception e) {
      // pass
    }


    try {
      // really old date
      assertTrue(verizon.trendsUp(blackThursday.toString(), "2017-06-12"));
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try {
      // future date
      assertTrue(verizon.trendsUp("2017-06-12", firstContact.toString()));
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

  }

  @Test
  public void trendsUp() throws Exception {
    assertTrue(verizon.trendsUp("2017-06-09", "2017-06-12"));
    assertFalse(verizon.trendsUp("2017-04-20", "2017-06-12"));
    assertFalse(verizon.trendsUp("2017-06-12", "2017-06-12"));


    assertTrue(UPS.trendsUp("2017-04-20", "2017-06-12"));
    assertFalse(UPS.trendsUp("2017-05-30", "2017-05-31"));
    assertTrue(UPS.trendsUp("2017-06-09", "2017-06-12"));
    assertFalse(UPS.trendsUp("2017-06-12", "2017-06-12"));

    try {
      //TODO: I'm getting a StockPriceNotFound exception here.
      verizon.trendsUp("2017-01-02", today.toString());
      verizon.trendsUp(today.toString(), today.toString());
      UPS.trendsUp("2017-01-02", today.toString());
      UPS.trendsUp(today.toString(), today.toString());
    } catch (Exception e) {
      Assert.fail();
    }
  }

}