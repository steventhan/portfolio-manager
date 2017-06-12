import org.junit.Test;

import util.StockDataRetriever;
import util.WebStockDataRetriever;

public class GenericTest {

  @Test
  public void testStockDataRetr() throws Exception {

    StockDataRetriever myRetriever = new WebStockDataRetriever();
//    System.out.println(myRetriever.getDate("01-Nov-1993"));
//    System.out.println(myRetriever.getDate("27-Aug-1990"));
//    System.out.println(myRetriever.getDate("08-Nov-1990"));
//    System.out.println(myRetriever.getCurrentPrice("AAPL"));
//    System.out.println(myRetriever.getCurrentPrice("MSFT"));
    System.out.println(myRetriever
            .getHistoricalPrices("MSFT", 27, 8, 1990,
                    30, 8, 1990));

    //Try a query
    IStockDataQuery myDataQuery = new StockDataQuery();




  }

}


