package model.trader;

import java.util.Map;
import java.util.Set;

/**
 * This class represents a stock basket.
 */
public interface StockBasket extends Stock {
  /**
   * Adds StockSingle object to basket.
   *
   * @param stock the StockSingle object.
   * @param shares number of shares to be added.
   * @throws IllegalArgumentException when StockSingle or shares is null
   */
  void add(StockSingle stock, Integer shares)
          throws IllegalArgumentException;

  /**
   * Adds stock to basket using stock symbol.
   *
   * @param stockSymbol the stock symbol into the basket.
   * @param shares number of shares to be added
   * @throws Exception the StockSingle object cannot be constructed from the given stock symbol
   */
  void add(String stockSymbol, Integer shares) throws Exception;

  /**
   * Get historical (closing) prices for a stock for a certain date range.
   * Historical prices are available only for business days.
   *
   * @param fromDate start date.
   * @param toDate end date.
   * @return Map of dates and closing prices. Date format is YYYY-MM-DD
   * @throws IllegalArgumentException if dates not valid.
   */
  Map<String, Double> getClosingPrices(String fromDate, String toDate) throws Exception;

  /**
   * Get moving averages for a basket for a certain date range.
   *
   * @param fromDate from date
   * @param toDate end date
   * @param days number of days moving averages
   * @return a Map&ltString, Double&gt with its keys as date, and values as moving averages
   *         on the date.
   * @throws Exception there's a problem communicating with server
   */
  Map<String, Double> getMovingAverages(String fromDate, String toDate, int days) throws Exception;

  /**
   * Gets how many unique stock symbols are there in the basket.
   *
   * @return the number of stock symbols as int.
   */
  int size();

  /**
   * Gets the quantity associated with the StockSingle object in a StockBasket.
   *
   * @param stock the StockSingle object for lookup
   * @return quanity as int
   */
  int get(StockSingle stock);

  /**
   * Gets the Set of all StockSingle objects stored in the basket
   *
   * @return the them as Set&ltStockSingle&gt.
   */
  Set<StockSingle> keySet();

  /**
   * Gets basket content of a StockBasket object.
   *
   * @return the them as Map&ltStockSingle, Integer&gt.
   */
  Map<String, Integer> getStocks();
}
