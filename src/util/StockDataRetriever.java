package util;

import java.util.Map;

/**
 * This interface represents all the operations offered by a component that
 * can be used to get stock data
 */
public interface StockDataRetriever {

  /**
   * Gets current price.
   * @param stockSymbol symbol.
   * @return price.
   * @throws Exception if there is one.
   */
  double getCurrentPrice(String stockSymbol) throws Exception;

  /**
   * Get company name.
   * @param stockSymbol symbol.
   * @return name.
   * @throws Exception if there is one.
   */
  String getName(String stockSymbol) throws Exception;

  /**
   * Gets Historical prices.
   * @param stockSymbol symbol.
   * @param fromDate .
   * @param fromMonth .
   * @param fromYear .
   * @param toDate .
   * @param toMonth .
   * @param toYear .
   * @return .
   * @throws Exception .
   */
  Map<Integer, PriceRecord> getHistoricalPrices(
          String stockSymbol,
          int fromDate,
          int fromMonth,
          int fromYear,
          int toDate,
          int toMonth,
          int toYear) throws Exception;

}
