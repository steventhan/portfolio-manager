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

}
