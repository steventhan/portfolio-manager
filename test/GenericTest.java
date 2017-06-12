import org.junit.Test;

import util.StockDataRetriever;
import util.WebStockDataRetriever;

public class GenericTest {

  @Test
  public void testStockDataRetr() throws Exception {

    StockDataRetriever myRetriever = new WebStockDataRetriever();
    System.out.println(myRetriever.getDate("01-Nov-1993"));

    //Try a query
    IStockDataQuery myDataQuery = new StockDataQuery();
    System.out.println(myRetriever.getCurrentPrice("AAPL"));

//    myRetriever.getDate(""); // Don't use the result of this directly
//    System.out.println(myRetriever
//            .getHistoricalPrices("MSFT", 27, 8, 1990,
//                    1, 11, 1993));

    System.out.println(myRetriever
            .getHistoricalPrices("MSFT", 27, 8, 1990,
                    27, 8, 1990));

    System.out.println(myRetriever.getDate("08-Aug-1990"));

  }

}


