import java.util.Map;

import util.PriceRecord;
import util.WebStockDataRetriever;

/**
 * Implementation of IStock
 */
public class StockSingle implements IStockSingle {

  //TODO: K.I.S.S.
  private final String symbol; // stock symbol
  private final String name; // company name
  private final WebStockDataRetriever retriever;

  /**
   * Constructs a new stock.
   *
   * @param symbol stock.
   * @throws Exception if no symbol.
   */
  public StockSingle(String symbol) throws Exception {
    this.symbol = symbol;
    this.retriever = WebStockDataRetriever.getStockDataRetriever();
    this.name = this.retriever.getName(symbol);
  }

  @Override
  public PriceRecord getPriceOnDay(String date) throws IllegalArgumentException {
    return null;
  }

  /**
   * Determines if a stock or basket trends up during a certain date range.
   * Find the line that joins the first data point to the last data point, and find its trend.
   * @param fromDate
   * @param toDate
   * @return
   * @throws IllegalArgumentException if dates not valid.
   */
  @Override
  public boolean trendsUp(String fromDate, String toDate) throws IllegalArgumentException {
    return false;
  }

  @Override
  public Map<String, PriceRecord> getClosingPrices
          (String fromDate, String toDate) throws IllegalArgumentException {
    return null;
  }

  @Override
  public boolean isBuyingOpportunity(String date) throws IllegalArgumentException {
    return false;
  }

  @Override
  public String toString() {
    return this.symbol;
  }
}
