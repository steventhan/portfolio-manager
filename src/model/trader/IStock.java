package model.trader;

/**
 * Interface for all methods that a stock must implement.
 */
public interface IStock {

  /**
   * Looks up the price of a stock on a certain day.
   *
   * @param strDate date in YYYY-MM-DD format.
   * @return price record for that day.
   * @throws Exception if price not found.
   */
  double getPriceOnDay(String strDate) throws Exception;

  /**
   * Determines if a stock or basket trends up during a certain date range.
   *
   * @param fromDate start date in YYYY-MM-DD format.
   * @param toDate end date in YYYY-MM-DD format.
   * @return true if up trend and false otherwise.
   * @throws Exception if dates not valid.
   */
  boolean trendsUp(String fromDate, String toDate) throws Exception;

}
