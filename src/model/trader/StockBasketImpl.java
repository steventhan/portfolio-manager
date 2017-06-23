package model.trader;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import custom.util.CustomDate;

/**
 * Class to represent a basket of stocks.
 */
public class StockBasketImpl extends StockAbstract implements StockBasket {

  private Map<StockSingle, Integer> basket;

  /**
   * Constructs an empty {@code StockBasketImpl} with the default WebRetrieverSingleton.
   */
  public StockBasketImpl() {
    this(new HashMap<>());
  }

  /**
   * Constructs a stock basket.
   *
   * @param basket map with shares per symbol in basket.
   * @throws IllegalArgumentException if either argument is null.
   */
  public StockBasketImpl(Map<StockSingle, Integer> basket) {
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
  public void add(StockSingle stock, Integer shares)
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
   * @throws Exception StockSingle object cannot be constructed.
   */
  public void add(String stockSymbol, Integer shares) throws Exception {
    this.add(new StockSingleImpl(stockSymbol), shares);
  }

  @Override
  public Map<String, Double> getClosingPrices(String fromDate, String toDate) throws Exception {
    Map<String, Double> basketPrices = new TreeMap<>();
    for (StockSingle s : this.basket.keySet()) {
      Map<String, Double> stockPrices = s.getClosingPrices(fromDate, toDate);
      int numShares = this.basket.get(s);
      for (String date : stockPrices.keySet()) {
        if (basketPrices.containsKey(date)) {
          basketPrices.put(date, basketPrices.get(date) + (stockPrices.get(date) * numShares));
        } else {
          basketPrices.put(date, stockPrices.get(date) * numShares);
        }
      }
    }
    return basketPrices;
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
    for (StockSingle s : this.basket.keySet()) {
      res += s.getPriceOnDay(day.toString()) * this.basket.get(s);
    }
    return res;
  }

  /**
   * Gets size of the StockBasketImpl.
   *
   * @return size as int.
   */
  public int size() {
    return this.basket.size();
  }

  public Set<StockSingle> keySet() {
    return this.basket.keySet();
  }

  @Override
  public Map<String, Integer> getStocks() {
    return this.basket.keySet().stream()
            .collect(Collectors.toMap(StockSingle::getSymbol, this.basket::get));
  }

  public int get(StockSingle stock) {
    return this.basket.get(stock);
  }

  /**
   * Gets a String representation of the StockBasketImpl.
   *
   * @return a String representation.
   */
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();

    for (StockSingle s : this.basket.keySet()) {
      str.append(String.format("%s: %s\n", s.getSymbol(), this.basket.get(s)));
    }
    return str.toString();
  }
}
