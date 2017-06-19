package model.trader;

import java.util.HashMap;
import java.util.Map;

import custom.util.CustomDate;

/**
 * Class to represent a basket of stocks.
 */
public class StockBasket extends StockAbstract {

  private Map<IStockSingle, Integer> basket;

  /**
   * Constructs an empty {@code StockBasket} with the default WebRetrieverSingleton.
   */
  public StockBasket() {
    this(new HashMap<>());
  }

  /**
   * Constructs a stock basket.
   *
   * @param basket map with shares per symbol in basket.
   * @throws IllegalArgumentException if either argument is null.
   */
  public StockBasket(Map<IStockSingle, Integer> basket) {
    if (basket == null) {
      throw new IllegalArgumentException();
    }
    this.basket = basket;
  }


  /**
   * Adds stock to the basket.
   *
   * @param stock  stock.
   * @param shares shares.
   * @throws IllegalArgumentException if either argument is null.
   */
  public void add(IStockSingle stock, Integer shares)
          throws IllegalArgumentException {
    if ((stock == null) || (shares == null)) {
      throw new IllegalArgumentException();
    }
    if (this.basket.containsKey(stock)) {
      this.basket.put(stock, shares + this.basket.get(stock));
    } else {
      this.basket.put(stock, shares);
    }
  }

  /**
   * Adds stock to the basket.
   *
   * @param stockSymbol stock symbol as String.
   * @param shares      shares.
   * @throws Exception IStockSingle object cannot be constructed.
   */
  public void add(String stockSymbol, Integer shares) throws Exception {
    this.add(new StockSingle(stockSymbol), shares);
  }

  /**
   * Determine the price of a basket on a certain date.
   *
   * @param date date in YYYY-MM-DD format.
   * @return price.
   * @throws IllegalArgumentException if
   */
  @Override
  public double getPriceOnDay(String date) throws Exception {
    double res = 0.0;
    CustomDate day = new CustomDate(date);
    for (IStockSingle s : this.basket.keySet()) {
      res += s.getPriceOnDay(day.toString()) * this.basket.get(s);
    }
    return res;
  }

  /**
   * Gets size of the StockBasket.
   *
   * @return size as int.
   */
  public int size() {
    return this.basket.size();
  }

  /**
   * Gets a String representation of the StockBasket.
   *
   * @return a String representation.
   */
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();

    for (IStockSingle s : this.basket.keySet()) {
      str.append(String.format("%s: %s\n", s.getSymbol(), this.basket.get(s)));
    }
    return str.toString();
  }
}
