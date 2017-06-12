import java.util.Map;

import util.PriceRecord;

/**
 * Created by matthiasdenu on 6/12/2017.
 */
public interface ISingleStockDataQuery extends IStockDataQuery {

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
   * Determines if there is a buying opportunity for a certain stock on a certain day.
   * @param symbol
   * @param date
   * @return
   */
  boolean isBuyingOpportunity(String symbol, Integer date);

}
