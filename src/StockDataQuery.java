import java.util.Map;

import util.PriceRecord;
import util.StockDataRetriever;
import util.WebStockDataRetriever;

/**
 * Implementation of IStockDataQuery
 */
public class StockDataQuery implements IStockDataQuery {

  private static StockDataRetriever dataRetriever = new WebStockDataRetriever();
  Map<Integer, PriceRecord> prices;

  //TODO: K.I.S.S.
  public StockDataQuery() {
  }

  @Override
  public PriceRecord getPriceOnDay(String symbol, Integer date) {
    return null;
  }

  @Override
  public boolean isBuyingOpportunity(String symbol, Integer date) {
    return false;
  }

  @Override
  public Map<Integer, PriceRecord> getClosingPrices(String symbol, Integer fromDate, Integer toDate) {
    return null;
  }

  @Override
  public Map<String, Basket> makeBasketGetPriceOnDay() {
    return null;
  }

  @Override
  public boolean trendsUp(String symbol) {
    return false;
  }

  @Override
  public boolean trendsUp(Basket basket) {
    return false;
  }
}
