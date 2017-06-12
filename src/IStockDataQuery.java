import util.PriceRecord;
import java.util.Map;

/**
 * Interface for all methods that a stock data query must implement.
 */
public interface IStockDataQuery {

  //TODO: Date format YYYYMMDD

  /**
   * Looks up the price of a stock on a certain day.
   * @param symbol
   * @param date
   * @return
   */
  PriceRecord getPriceOnDay(String symbol, Integer date);

  /**
   * Determines if there is a buying opportunity for a certain stock on a certain day.
   * @param symbol
   * @param date
   * @return
   */
  boolean isBuyingOpportunity(String symbol, Integer date);

  /**
   * Get historical (closing) prices for a stock for a certain date range.
   * Historical prices are available only for business days.
   * @param symbol
   * @param fromDate
   * @param toDate
   * @return
   */
  Map<Integer, PriceRecord> getClosingPrices(String symbol, Integer fromDate, Integer toDate);

  /**
   * Creates a basket of stocks comprising of shares of one or more stocks and then determine
   * its price on a certain date.
   * @return
   */
  //TODO: params here should echo params in Basket constructor (probably a map of symbols & shares)
  Map<String, Basket> makeBasketGetPriceOnDay();


  /**
   * Determines if a stock trends up during a certain date range.
   * @param symbol
   * @return
   */
  boolean trendsUp(String symbol);

  /**
   * Determines if a basket trends up during a certain date range.
   * @param basket
   * @return
   */
  boolean trendsUp(Basket basket);
}
