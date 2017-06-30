package model.trader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import custom.util.CustomDate;
import custom.util.NewStockRetriever;
import custom.util.StockPriceNotFound;
import custom.util.WebRetrieverSingleton;
import util.PriceRecord;

/**
 * Implementation of Stock.
 */
public class StockSingleImpl extends StockAbstract implements StockSingle {
  private final String symbol; // stock symbol
  private final String name; // company name
  private final NewStockRetriever retriever;

  /**
   * Constructs a new stock using the default retriever.
   *
   * @param symbol stock.
   * @throws Exception if no symbol.
   */
  public StockSingleImpl(String symbol) throws Exception {
    this(symbol, WebRetrieverSingleton.getInstance());
  }

  /**
   * Constructs a new stock with the option to provide a retriever.
   * The retriever is of type NewStockRetriever.
   *
   * @param symbol    stock.
   * @param retriever retriever to be used to retrieve stock price
   * @throws Exception if no symbol.
   */
  public StockSingleImpl(String symbol, NewStockRetriever retriever) throws Exception {
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
   * Determines if this Stock sameDate to the given object.
   *
   * @return true if the other object is a StockSimple object, and has same symbol; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    return this == o || (o instanceof StockSingleImpl
            && this.symbol.equals(((StockSingleImpl) o).getSymbol()));
  }

  /**
   * Gets price on a day.
   *
   * @param date day.
   * @return price.
   * @throws Exception if price not found.
   */
  public double getPriceOnDay(CustomDate date) throws Exception {
    Map<Integer, PriceRecord> priceRecords;

    // If the date is today date then return current price
    if (date.sameDate(new CustomDate())) {
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
   * Gets this symbol's hashcode to help the map lookup operation within a StockBasketImpl.
   *
   * @return this StockSingleImpl's symbol's hashcode.
   */
  @Override
  public int hashCode() {
    return this.symbol.hashCode();
  }

  /**
   * Get historical (closing) prices for a stock for a certain date range.
   * Historical prices are available only for business days.
   * If the start date is even before the Stock's earliest day,
   * it would return all entries it has.
   *
   * @param fromDate start date.
   * @param toDate   end date.
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

  /**
   * Returns a representation of the moving averages for each day in the given range.
   *
   * @param fromDate date range start.
   * @param toDate   date range end.
   * @param days     number of days to average over.
   * @return a representation of the moving averages for a stock or basket present for each day
   * @throws Exception described by its message.
   */
  @Override
  public Map<String, Double> getMovingAverages(String fromDate, String toDate, int days)
          throws Exception {
    return this.getMovingAverages(new CustomDate(fromDate), new CustomDate(toDate), days);
  }

  /**
   * Returns a representation of the moving averages for each day in the given range.
   *
   * @param fromDate date range start.
   * @param toDate   date range end.
   * @param days     number of days to average over.
   * @return a representation of the moving averages for a stock or basket present for each day
   * @throws Exception described by its message.
   */
  public Map<String, Double> getMovingAverages(CustomDate fromDate, CustomDate toDate, int days)
          throws Exception {

    CustomDate pastDate = fromDate.getXDaysBeforeOrAfter(-(days * 2));
    Map<String, Double> result = new TreeMap<>();
    TreeMap<Integer, PriceRecord> priceRecords;

    priceRecords = (TreeMap<Integer, PriceRecord>) this.retriever.getHistoricalPrices(this.symbol,
            pastDate.getDay(), pastDate.getMonth(), pastDate.getYear(),
            toDate.getDay(), toDate.getMonth(), toDate.getYear());

    List<Integer> keySet = new ArrayList<>(priceRecords.descendingKeySet());
    int i = 0;

    while (toDate.compareTo(fromDate) >= 0) {
      if (priceRecords.get(toDate.toKeyInt()) != null) {
        double total = 0;
        int currentDay = keySet.get(i);
        for (int j = i; j < (days + i); j++) {
          total += priceRecords.get(keySet.get(j)).getClosePrice();
        }
        result.put(new CustomDate(String.valueOf(currentDay)).toString(), total / days);
        i++;
      }
      toDate = toDate.getXDaysBeforeOrAfter(-1);
    }

    return result;
  }

  /**
   * Determines if there is a buying opportunity for a certain stock on a certain day.
   *
   * @param strDate format YYYY-MM-DD.
   * @return true if buying opportunity and false otherwise.
   */
  @Override
  public boolean isBuyingOpportunity(String strDate) throws Exception {
    return this.getMovingAverages(strDate, strDate, 50).get(strDate)
            > this.getMovingAverages(strDate, strDate, 200).get(strDate);
  }

  /**
   * Gets a String representation of StockSingleImpl object.
   *
   * @return a String representation.
   */
  @Override
  public String toString() {
    return this.symbol;
  }
}
