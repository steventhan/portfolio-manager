import org.junit.Before;
import org.junit.Test;

import util.NewStockRetriever;
import util.WebRetrieverSingleton;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by matthiasdenu on 6/13/2017.
 */
public class StockSingleTest {

  private NewStockRetriever retriever;

  // intialize constants for testing
  private final IStockSingle verizon = new StockSingle("VZ");
  // 6/13/2017	5/5/2017	2/14/2017	12/25/2016	12/23/2016
  // 46.450001	  46.69	      48.27	      #N/A	     53.68

  private final IStockSingle UPS = new StockSingle("UPS");
  //  6/13/2017	5/5/2017	2/14/2017	12/25/2016	12/23/2016
  // 109.809998	  107.43	   108.99	      #N/A	    115.97

  private final CustomDate jun1317 = new CustomDate("2017-06-13");
  private final CustomDate cincoDeMayo = new CustomDate("2017-05-05");
  private final CustomDate valentines = new CustomDate("2017-02-14");
  private final CustomDate christmas = new CustomDate("2016-12-25");
  private final CustomDate dec2316 = new CustomDate("2016-12-23");

  public StockSingleTest() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    // reinitialize retriever
    this.retriever = WebRetrieverSingleton.getInstance();
  }

  @Test
  public void getPriceOnDay() throws Exception {
    assertEquals(46.450001, verizon.getPriceOnDay("2017-06-13"), 0.01);
    //TODO: 06/13/2017 @ 9:28 PM I ran this test to get an UnknownHostException.
    //TODO: see log-06132017


  }

  @Test
  public void getClosingPrices() throws Exception {
  }

  @Test
  public void isBuyingOpportunity() throws Exception {
  }


  @Test
  public void trendsUp() throws Exception {
  }

}