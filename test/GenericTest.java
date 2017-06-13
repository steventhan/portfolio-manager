import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import static util.WebStockDataRetriever.getStockDataRetriever;

public class GenericTest {

  @Test
  public void testStockDataRetriever() throws Exception {

//    System.out.println(myRetriever.getDate("01-Nov-1993"));
//    System.out.println(myRetriever.getDate("27-Aug-1990"));
//    System.out.println(myRetriever.getDate("08-Nov-1990"));
//    System.out.println(myRetriever.getCurrentPrice("AAPL"));
//    System.out.println(myRetriever.getCurrentPrice("MSFT"));
    System.out.println(getStockDataRetriever()
            .getHistoricalPrices("MSFT", 27, 8, 1990,
                    30, 8, 1990));

    System.out.println(LocalDate.now().getYear());
    System.out.println(Math.ceil(Math.log10((double) LocalDate.now().getYear())));
    System.out.println(Math.pow(10, 4));
    System.out.println(LocalDate.now().getMonth());
    System.out.println(LocalDate.now().getDayOfMonth());

    System.out.println(new SingleStockDataQuery("AAPL", 20170611, 20170611));
    System.out.println(new SingleStockDataQuery("AAPL", 20170608, 20170608));

  }

}


