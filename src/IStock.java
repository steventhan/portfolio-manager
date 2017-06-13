import util.PriceRecord;

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
  PriceRecord getPriceOnDay(String date) throws IllegalArgumentException;

  /**
   * Determines if a stock or basket trends up during a certain date range.
   * @param fromDate start date.
   * @param toDate end date.
   * @return true if up trend and false otherwise.
   * @throws IllegalArgumentException if dates not valid.
   */
  boolean trendsUp(String fromDate, String toDate) throws IllegalArgumentException;

}
