import java.util.Map;

import util.PriceRecord;
import util.StockDataRetriever;
import util.WebStockDataRetriever;

/**
 * Implementation of IStockDataQuery
 */
public class SingleStockDataQuery implements ISingleStockDataQuery {

  //TODO: K.I.S.S.

  Map<Integer, PriceRecord> prices;

  /**
   * Retrieves {@code PriceRecord} for each day in the given range. The {@code fromDate} and
   * {@code toDate} may be the same. The date format is YYYYMMDD. A new instance of this class must
   * be instantiated for each new stock data query.
   *
   * @param symbol Stock sybmol.
   * @param fromDate from date, inclusive.
   * @param toDate to date, inclusive.
   * @throws IllegalArgumentException if no data available for dates.
   */
  public SingleStockDataQuery(String symbol, Integer fromDate, Integer toDate)
          throws Exception {
    this.prices = WebStockDataRetriever.getStockDataRetriever()
            .getHistoricalPrices(symbol, fromDate % 100,
                    (fromDate / 100) % 100,
                    (fromDate / 10000) % 10000,
                    toDate % 100,
                    (toDate / 100) % 100,
                    (toDate / 10000) % 10000);
  }

  @Override
  public PriceRecord getPriceOnDay(Integer date) throws IllegalArgumentException {
    return null;
  }

  @Override
  public boolean trendsUp(Integer fromDate, Integer toDate) throws IllegalArgumentException {
    return false;
  }

  @Override
  public Map<Integer, PriceRecord> getClosingPrices
          (String symbol, Integer fromDate, Integer toDate) throws IllegalArgumentException {
    return null;
  }

  @Override
  public boolean isBuyingOpportunity(String symbol, Integer date) throws IllegalArgumentException {
    return false;
  }

  @Override
  public String toString() {
    return this.prices.toString();
  }
}
