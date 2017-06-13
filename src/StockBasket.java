import util.PriceRecord;
import util.StockDataRetriever;
import util.WebStockDataRetriever;

import java.util.Map;

/**
 * Created by matthiasdenu on 6/12/2017.
 */
public class StockBasket implements IStock {
  //TODO: Ask about nested maps.

  private Map<StockSingle, Integer> basket;
  StockDataRetriever retriever = WebStockDataRetriever.getStockDataRetriever();

  /**
   * Constructs an empty {@code StockBasket}.
   */
  public StockBasket() { }

  /**
   * Constructs a stock basket.
   * @param basket map with shares per symbol in basket.
   */
  public StockBasket(Map<StockSingle, Integer> basket) {
    //TODO: take care of invalid stock symbols in StockSingle constructor and document it
    this.basket = basket;
  }

  /**
   * Adds stock to the basket
   * @param stock
   * @param shares
   * @throws IllegalArgumentException for null values.
   */
  public void add(StockSingle stock, Integer shares) throws Exception {
    //TODO: check for valid symbol
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
    //TODO: Check the time and date
    //TODO: Check for weekday before 4pm, use current price.
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
