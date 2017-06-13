import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import util.PriceRecord;
import util.StockDataRetriever;
import util.WebStockDataRetriever;

/**
 * Implementation of IStock
 */
public class StockSingle implements IStockSingle {
  private final String symbol; // stock symbol
  private final String name; // company name
  private final StockDataRetriever retriever;

  /**
   * Constructs a new stock.
   *
   * @param symbol stock.
   * @throws Exception if no symbol.
   */
  public StockSingle(String symbol) throws Exception {
    this.retriever = WebStockDataRetriever.getStockDataRetriever();
    this.symbol = symbol;
    this.name = this.retriever.getName(symbol);
    if (this.name.equals("N/A")) {
      throw new IllegalArgumentException("invalid stock symbol");
    }
  }

  /**
   * Constructs a new stock with the option to provide a retriever.
   * The retriever is of type StockDateRetriever.
   *
   * @param symbol stock.
   * @param retriever retriever to be used to retrieve stock price
   * @throws Exception if no symbol.
   */
  public StockSingle(String symbol, StockDataRetriever retriever) throws Exception {
    this.symbol = symbol;
    this.retriever = retriever;
    this.name = this.retriever.getName(symbol);
  }

  public String getSymbol() {
    return this.symbol;
  }

  public String getName() {
    return this.name;
  }


  @Override
  public boolean equals(Object o) {
    return this == o || (o instanceof StockSingle
            && this.symbol.equals(((StockSingle) o).getSymbol()));
  }

  @Override
  public double getPriceOnDay(String dateStr) throws Exception {
    CustomDate date = new CustomDate(dateStr);
    Map<Integer, PriceRecord> priceRecords;

    // If the date is today date then return current price
    if (date.equals(new CustomDate())) {
      return retriever.getCurrentPrice(this.symbol);
    }

    priceRecords = retriever.getHistoricalPrices(this.symbol, date.getDay(), date.getMonth(),
            date.getYear(), date.getDay(), date.getMonth(), date.getDay());

    PriceRecord result = priceRecords.get(date.toKeyInt());

    if (result != null) {
      return result.getClosePrice();
    }
    throw new StockPriceNotFound("Check input date");
  }

  @Override
  public int hashCode() {
    return this.symbol.hashCode();
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
    CustomDate from = new CustomDate(fromDate);
    CustomDate to = new CustomDate(toDate);

    return false;
  }

  @Override
  public Map<String, Double> getClosingPrices
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
