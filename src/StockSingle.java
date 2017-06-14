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
   * Constructs a new stock using the default retriever.
   *
   * @param symbol stock.
   * @throws Exception if no symbol.
   */
  public StockSingle(String symbol) throws Exception {
    this(symbol, WebRetrieverSingleton.getInstance());
  }

  /**
   * Constructs a new stock with the option to provide a retriever.
   * The retriever is of type NewStockRetriever.
   *
   * @param symbol stock.
   * @param retriever retriever to be used to retrieve stock price
   * @throws Exception if no symbol.
   */
  public StockSingle(String symbol, NewStockRetriever retriever) throws Exception {
    super();
    this.symbol = symbol;
    this.retriever = retriever;
    this.name = this.retriever.getName(symbol);
    if (this.name.equals("N/A")) {
      throw new IllegalArgumentException("invalid stock symbol");
    }
  }

  /**
   * Gets this Stock's symbol.
   *
   * @return the symbol as String
   */
  public String getSymbol() {
    return this.symbol;
  }

  /**
   * Gets company name.
   *
   * @return the company name as String
   */
  public String getName() {
    return this.name;
  }


  /**
   * Determines if this Stock equals to the given object.
   *
   * @return true if the other object is a StockSimple object, and has same symbol; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    return this == o || (o instanceof StockSingle
            && this.symbol.equals(((StockSingle) o).getSymbol()));
  }

  public double getPriceOnDay(CustomDate date) throws Exception {
    Map<Integer, PriceRecord> priceRecords;

    // If the date is today date then return current price
    if (date.equals(new CustomDate())) {
      return this.retriever.getCurrentPrice(this.symbol);
    }

    priceRecords = this.retriever.getHistoricalPrices(this.symbol, date.getDay(), date.getMonth(),
            date.getYear(), date.getDay(), date.getMonth(), date.getYear());

    PriceRecord result = priceRecords.get(date.toKeyInt());

    if (result != null) {
      return result.getClosePrice();
    }
    throw new StockPriceNotFound("Check input date");
  }

  /**
   * Looks up the price of a stock on a certain day.
   *
   * @param strDate date in YYYY-MM-DD format.
   * @return price record for that day.
   * @throws Exception if price not found.
   */
  @Override
  public double getPriceOnDay(String strDate) throws Exception {
    CustomDate date = new CustomDate(strDate);
    return this.getPriceOnDay(date);
  }

  /**
   * Gets this symbol's hashcode to help the map lookup operation within a StockBasket.
   *
   * @return this StockSingle's symbol's hashcode.
   */
  @Override
  public int hashCode() {
    return this.symbol.hashCode();
  }

  /**
   * Get historical (closing) prices for a stock for a certain date range.
   * Historical prices are available only for business days.
   *
   * @param fromDate start date.
   * @param toDate end date.
   * @return Map of dates and closing prices. Date format is YYYY-MM-DD
   * @throws Exception if dates not valid.
   */
  @Override
  public Map<String, Double> getClosingPrices(String fromDate, String toDate) throws Exception {
    super.verifyDateInOrder(fromDate, toDate);
    CustomDate from = new CustomDate(fromDate);
    CustomDate to = new CustomDate(toDate);

    Map<Integer, PriceRecord> priceRecords = this.retriever.getHistoricalPrices(this.symbol,
            from.getDay(), from.getMonth(), from.getYear(),
            to.getDay(), to.getMonth(), to.getYear());

    Map<String, Double> result = new TreeMap<>();

    for (Integer n : priceRecords.keySet()) {
      result.put(new CustomDate(String.valueOf(n)).toString(),
              priceRecords.get(n).getClosePrice());
    }
    return result;
  }

  private double getXDaysMovingAverage(CustomDate date, int days) throws Exception {
    CustomDate pastDate = date.getXDaysBeforeOrAfter(days * (-2));
    TreeMap<Integer, PriceRecord> priceRecords;
    double total = 0;
    int i = 0;

    priceRecords = (TreeMap) this.retriever.getHistoricalPrices(this.symbol, pastDate.getDay(),
            pastDate.getMonth(), pastDate.getYear(), date.getDay(),
            date.getMonth(), date.getYear());

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

  /**
   * Determines if there is a buying opportunity for a certain stock on a certain day.
   *
   * @param strDate format YYYY-MM-DD.
   * @return true if buying opportunity and false otherwise.
   */
  @Override
  public boolean isBuyingOpportunity(String strDate) throws Exception {
    return this.getXDaysMovingAverage(new CustomDate(strDate), 50)
            > this.getXDaysMovingAverage(new CustomDate(strDate), 200);
  }

  /**
   * Gets a String representation of StockSingle object.
   *
   * @return a String representation.
   */
  @Override
  public String toString() {
    return this.symbol;
  }
}
