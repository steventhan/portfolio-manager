/**
 * Interface for all methods that a stock data query must implement.
 */
public interface IStock {

  /**
   * Looks up the price of a stock on a certain day.
   * @param date date in YYYY-MM-DD format.
   * @return price record for that day.
   * @throws IllegalArgumentException if price not found.
   */
  double getPriceOnDay(String date) throws Exception;

  /**
   * Determines if a stock or basket trends up during a certain date range.
   * @param fromDate start date.
   * @param toDate end date.
   * @return true if up trend and false otherwise.
   * @throws IllegalArgumentException if dates not valid.
   */
  boolean trendsUp(String fromDate, String toDate) throws Exception;

}
