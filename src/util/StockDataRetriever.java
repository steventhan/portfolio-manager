package util;

import java.util.Map;

/**
 * This interface represents all the operations offered by a component that
 * can be used to get stock data.
 */
public interface StockDataRetriever {
  /**
   * Gets current price of a stock symbol.
   *
   * @param stockSymbol the stock symbol
   * @return the current price as double
   * @throws Exception when there's a problem with http request
   */
  double getCurrentPrice(String stockSymbol) throws Exception;

  /**
   * Gets the name of the name of company.
   *
   * @param stockSymbol stock symbol
   * @return the company Name as String
   * @throws Exception when there's a problem with the http request
   */
  String getName(String stockSymbol) throws Exception;

  /**
   * Gets a Map of Date and Price records.
   *
   * @param stockSymbol the stock symbol
   * @param fromDate    start date as int
   * @param fromMonth   start month as int
   * @param fromYear    start year as int
   * @param toDate      end date as int
   * @param toMonth     end month as int
   * @param toYear      end year as int
   * @return the data as Map
   * @throws Exception when there's a problem with http request
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
