import java.util.Map;

import util.PriceRecord;
import util.StockDataRetriever;
import util.WebStockDataRetriever;

/**
 * Implementation of IStockDataQuery
 */
public class StockDataQuery implements IStockDataQuery {

  Map<String, Integer> basket;

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
  //TODO: this is a weighted average
  public PriceRecord getBasketPriceOnDay(Map<String, Integer> basket, Integer date) {
    return null;
  }

  @Override
  public boolean trendsUp(String symbol) {
    return false;
  }

  @Override
  public boolean trendsUp(Map<String, Integer> basket) {
    return false;
  }
}
