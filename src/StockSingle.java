import java.io.IOException;
import java.util.Map;
import java.util.Objects;

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
  public StockSingle(String symbol) throws IOException, IllegalArgumentException {
    this.retriever = WebStockDataRetriever.getStockDataRetriever();
    this.symbol = symbol;
    String temp;

    try {
      temp = this.retriever.getName(symbol);
      if (temp.equals("N/A")) throw new IllegalArgumentException("invalid stock symbol");
      this.name = temp;
    } catch (IOException e) {
      throw new IOException("unkown I/O exception", e);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof StockSingle)) return false;
    return this.symbol.equals(((StockSingle) o).getSymbol());
  }

  @Override
  public double getPriceOnDay(String date) throws IllegalArgumentException {
    //TODO: if current day call retriever.getCurrentPrice();
    return 0;
  }

  public String getSymbol() {
    return this.symbol;
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
