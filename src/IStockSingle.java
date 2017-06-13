import java.util.Map;

/**
 * Created by matthiasdenu on 6/12/2017.
 */
public interface IStockSingle extends IStock {

  /**
   * Get historical (closing) prices for a stock for a certain date range.
   * Historical prices are available only for business days.
   * @param fromDate start date.
   * @param toDate end date.
   * @return Map of dates and closing prices. Date format is YYYY-MM-DD
   * @throws IllegalArgumentException if dates not valid.
   */
  Map<String, Double> getClosingPrices(String fromDate, String toDate)
          throws IllegalArgumentException;

  /**
   * Determines if there is a buying opportunity for a certain stock on a certain day.
   * @param date format YYYY-MM-DD.
   * @return true if buying opportunity and false otherwise.
   */
  boolean isBuyingOpportunity(String date) throws Exception;

}
