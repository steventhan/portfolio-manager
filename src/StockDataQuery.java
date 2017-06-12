import java.util.Map;

import util.PriceRecord;
import util.StockDataRetriever;
import util.WebStockDataRetriever;

/**
 * Implementation of IStockDataQuery
 */
public class StockDataQuery implements IStockDataQuery {
  private static StockDataRetriever dataRetriever;
  Map<Integer, PriceRecord> prices;

  //TODO: K.I.S.S.
  public StockDataQuery() {
    dataRetriever = new WebStockDataRetriever();
  }

  public PriceRecord getPriceOnDay() {
    return null;
  }

}
