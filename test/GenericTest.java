import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import util.StockDataRetriever;
import util.WebStockDataRetriever;

public class GenericTest {

  @Test
  public void testUnfamiliarAPI() {
    System.out.println(LocalDateTime.now().toString().replaceAll("[:.]", "_"));
    System.out.println(LocalDate.now().getYear() % 100);
    System.out.println(LocalDate.now().getMonth()); //TODO: reuse toMonth()
    System.out.println(LocalDate.now().getDayOfMonth());

//    System.out.println(LocalDate.now());
//    System.out.println(LocalDateTime.now());
//    System.out.println(LocalDate.now().getYear());
//    System.out.println(Math.ceil(Math.log10((double) LocalDate.now().getYear())));
//    System.out.println(Math.pow(10, 4));
  }

  @Test
  public void testStockDataRetriever() throws Exception {
    StockDataRetriever myRetriever = WebStockDataRetriever.getStockDataRetriever();

    System.out.println(myRetriever.getName("AAPL"));
    System.out.println(myRetriever.getCurrentPrice("AAPL"));

    System.out.println(myRetriever.getName("MSFT"));
    System.out.println(myRetriever.getCurrentPrice("MSFT"));

    System.out.println(myRetriever
            .getHistoricalPrices("AAPL", 8, 6, 2017,
                    6, 13, 2017));
    System.out.println(myRetriever
            .getHistoricalPrices("MSFT", 8, 6, 2017,
                    6, 13, 2017));

    System.out.println(myRetriever.getName("FRMBL"));
    System.out.println(myRetriever.getName("MMM"));
    System.out.println(myRetriever.getName("ZOMM"));
    System.out.println(myRetriever.getName("MYE"));
    System.out.println(myRetriever.getName("PWC"));
    System.out.println(myRetriever.getName("NRG"));
    System.out.println(myRetriever.getName("GE"));
    System.out.println(myRetriever.getName("ACME"));

  }

  @Test
  public void testStockBasket() {
    //m.contains(k) ?

  }

}


