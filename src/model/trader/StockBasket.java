package model.trader;

import java.util.Map;
import java.util.Set;

/**
 * Created by matthiasdenu on 6/20/2017.
 */
//TODO add stock basket date
public interface StockBasket extends Stock {
  void add(StockSingle stock, Integer shares)
          throws IllegalArgumentException;

  void add(String stockSymbol, Integer shares) throws Exception;

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

  int size();

  int get(StockSingle stock);

  Set<StockSingle> keySet();

  Map<String, Integer> getStocks();
}
