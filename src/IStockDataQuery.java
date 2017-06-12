import util.PriceRecord;
import java.util.Map;

/**
 * Interface for all methods that a stock data query must implement.
 */
public interface IStockDataQuery {

  /**
   * Looks up the price of a stock on a certain day.
   * @param date
   * @return
   */
  PriceRecord getPriceOnDay(Integer date);

  /**
   * Determines if a stock or basket trends up during a certain date range.
   * @param fromDate
   * @param toDate
   * @return
   */
  boolean trendsUp(Integer fromDate, Integer toDate);




}
