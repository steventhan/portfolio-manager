package model.trader;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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

  /**
   * Get historical (closing) prices for a basket for a certain date range.
   * Historical prices are available only for business days.
   * If the start date is even before the Stock's earliest day,
   * it would return all entries it has.
   *
   * @param fromDate start date.
   * @param toDate   end date.
   * @return Map of dates and closing prices. Date format is YYYY-MM-DD
   * @throws Exception if dates not valid.
   */
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
   * Get moving averages for a stock for a certain date range.
   *
   * @param fromDate from date
   * @param toDate end date
   * @param days number of days moving averages
   * @return a Map&ltString, Double&gt with its keys as date, and values as moving averages
   *         on the date.
   * @throws Exception there's a problem communicating with server
   */
  @Override
  public Map<String, Double> getMovingAverages(String fromDate, String toDate, int days)
          throws Exception {
    Map<String, Double> movingAverages = new TreeMap<>();

    for (StockSingle s : this.basket.keySet()) {
      Map<String, Double> stockAverages = s.getMovingAverages(fromDate, toDate, days);
      int numShares = this.basket.get(s);
      for (String date : stockAverages.keySet()) {
        if (movingAverages.containsKey(date)) {
          movingAverages.put(date, movingAverages.get(date) + (stockAverages.get(date)
                  * numShares));
        } else {
          movingAverages.put(date, stockAverages.get(date) * numShares);
        }
      }
    }
    return movingAverages;
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
  @Override
  public int size() {
    return this.basket.size();
  }

  /**
   * Gets the Set of all StockSingle objects stored in the basket
   *
   * @return the them as Set&ltStockSingle&gt.
   */
  @Override
  public Set<StockSingle> keySet() {
    return this.basket.keySet();
  }

  /**
   * Gets basket content of a StockBasket object.
   *
   * @return the them as Map&ltStockSingle, Integer&gt.
   */
  @Override
  public Map<String, Integer> getStocks() {
    return this.basket.keySet().stream()
            .collect(Collectors.toMap(StockSingle::getSymbol, this.basket::get));
  }

  /**
   * Gets the quantity associated with the StockSingle object in a StockBasket.
   *
   * @param stock the StockSingle object for lookup
   * @return quanity as int
   */
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
