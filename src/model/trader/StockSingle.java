package model.trader;

import java.util.Map;

/**
 * Interface for methods common to a single stock.
 */
public interface StockSingle extends Stock {

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
   * Get moving averages for a stock for a certain date range.
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
   * Determines if there is a buying opportunity for a certain stock on a certain day.
   *
   * @param date format YYYY-MM-DD.
   * @return true if buying opportunity and false otherwise.
   */
  boolean isBuyingOpportunity(String date) throws Exception;

  /**
   * Gets this Stock's symbol.
   *
   * @return the symbol as String
   */
  String getSymbol();

  /**
   * Gets company name.
   *
   * @return the company name as String
   */
  String getName();

}
