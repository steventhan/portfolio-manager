import java.util.Map;
import java.util.TreeMap;

import util.NewStockRetriever;
import util.WebRetrieverSingleton;

import util.PriceRecord;

/**
 * Implementation of IStock
 */
public class StockSingle extends StockAbstract implements IStockSingle {
  private final String symbol; // stock symbol
  private final String name; // company name
  private final NewStockRetriever retriever;

  /**
   * Constructs a new stock.
   *
   * @param symbol stock.
   * @throws Exception if no symbol.
   */
  public StockSingle(String symbol) throws Exception {
    this.retriever = WebRetrieverSingleton.getInstance();
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
  public StockSingle(String symbol, NewStockRetriever retriever) throws Exception {
    this.symbol = symbol;
    this.retriever = retriever;
    this.name = this.retriever.getName(symbol);
    if (this.name.equals("N/A")) {
      throw new IllegalArgumentException("invalid stock symbol");
    }
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
            date.getYear(), date.getDay(), date.getMonth(), date.getYear());

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

  @Override
  public Map<String, Double> getClosingPrices
          (String fromDate, String toDate) throws IllegalArgumentException {
    CustomDate from = new CustomDate(fromDate);
    CustomDate to = new CustomDate(toDate);
    return null;
  }

  private double getXDaysMovingAverage(CustomDate date, int days) throws Exception {
    CustomDate pastDate = date.getXDaysBeforeOrAfter(days * (-2));
    TreeMap<Integer, PriceRecord> priceRecords;
    double total = 0;
    int i = 0;

    priceRecords = (TreeMap) retriever.getHistoricalPrices(this.symbol, pastDate.getDay(), pastDate.getMonth(),
            pastDate.getYear(), date.getDay(), date.getMonth(), date.getYear());

    for (Integer n : priceRecords.descendingKeySet()) {
      if (i >= days) {
        break;
      }
      total += priceRecords.get(n).getClosePrice();
      i++;
    }

    if (total == 0) {
      throw new StockPriceNotFound("Stock price not found");
    }
    return total / days;
  }

  @Override
  public boolean isBuyingOpportunity(String date) throws Exception {
    return this.getXDaysMovingAverage(new CustomDate(date), 50)
            > this.getXDaysMovingAverage(new CustomDate(date), 200);
  }

  @Override
  public String toString() {
    return this.symbol;
  }
}
