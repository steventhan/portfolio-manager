import util.PriceRecord;
import util.StockDataRetriever;
import util.WebStockDataRetriever;

import java.io.IOException;
import java.util.Map;

/**
 * Created by matthiasdenu on 6/12/2017.
 */
public class StockBasket implements IStock {
  //TODO: Ask about nested maps.

  private Map<StockSingle, Integer> basket;
  StockDataRetriever retriever;

  /**
   * Constructs an empty {@code StockBasket}.
   */
  public StockBasket() { }

  /**
   * Constructs a stock basket. Because implementations of StockDataRetriever are singleton,
   * instantiations of this class need only a reference to an implementation of StockDataRetriever.
   * No special access would be granted to the reference by virtue of it being hardcoded.
   * @param basket map with shares per symbol in basket.
   * @throws IllegalArgumentException if either argument is null or if stock symbol is invalid.
   * @throws IOException if there is an I/O exception thrown when fetching data.
   */
  public StockBasket(Map<StockSingle, Integer> basket, StockDataRetriever retriever)
          throws IllegalArgumentException, IOException {
    if ((basket == null) || (retriever == null)) throw new IllegalArgumentException();
    this.retriever = retriever;
    for (StockSingle ss : basket.keySet()) {
      try {
        if (retriever.getName(ss.toString()).equals("N/A"))
          throw new IllegalArgumentException("invalid stock symbol");
      } catch (IOException e) {
        throw new IOException("unknown I/O exception", e);
      }
    }
    this.basket = basket;
  }

  /**
   * Adds stock to the basket.
   * @param stock
   * @param shares
   * @throws IllegalArgumentException if either argument is null or if stock symbol is invalid.
   * @throws IOException if there is an I/O exception thrown when fetching data.
   */
  public void add(StockSingle stock, Integer shares)
          throws IllegalArgumentException, IOException {
    if ((stock == null) || (shares == null)) throw new IllegalArgumentException();
    if(retriever.getName(stock.toString()).equals("N/A")) throw new IllegalArgumentException();
    if (this.basket.containsKey(stock)) {
      this.basket.put(stock, shares + this.basket.get(stock)); }
    else {
      this.basket.put(stock, shares);
    }
  }

  @Override
  public double getPriceOnDay(String date) throws IllegalArgumentException {
    //TODO: Check the time and date, if current day, and markets open, return current price
    double res = 0.0;

    for(StockSingle s : this.basket.keySet()) {
      res += s.getPriceOnDay(date) * this.basket.get(s);
    }

    return res;
  }

  @Override
  public boolean trendsUp(String fromDate, String toDate) {
    //TODO: Check the time and date
    //TODO: Check for weekday before 4pm, use current price.
    return false;
  }

}
