import java.util.Map;

/**
 * Class to represent a basket of stocks.
 */
public class StockBasket implements IStock {

  private Map<StockSingle, Integer> basket;

  /**
   * Constructs an empty {@code StockBasket}.
   */
  public StockBasket() { }

  /**
   * Constructs a stock basket.
   * @param basket map with shares per symbol in basket.
   * @throws IllegalArgumentException if either argument is null.
   */
  public StockBasket(Map<StockSingle, Integer> basket)
          throws IllegalArgumentException {
    if (basket == null) throw new IllegalArgumentException();
    this.basket = basket;
  }

  /**
   * Adds stock to the basket.
   * @param stock stock.
   * @param shares shares.
   * @throws IllegalArgumentException if either argument is null.
   */
  public void add(StockSingle stock, Integer shares)
          throws IllegalArgumentException {
    if ((stock == null) || (shares == null)) throw new IllegalArgumentException();
    if (this.basket.containsKey(stock)) {
      this.basket.put(stock, shares + this.basket.get(stock)); }
    else {
      this.basket.put(stock, shares);
    }
  }

  /**
   * Determine the price of a basket on a certain date.
   * @param date date in YYYY-MM-DD format.
   * @return price.
   * @throws IllegalArgumentException if
   */
  @Override
  public double getPriceOnDay(String date) throws IllegalArgumentException {
    double res = 0.0;
    for(StockSingle s : this.basket.keySet()) {
      res += s.getPriceOnDay(date) * this.basket.get(s);
    }
    return res;
  }

  @Override
  public boolean trendsUp(String fromDate, String toDate) {
    double beforePrice = this.getPriceOnDay(fromDate);
    double afterPrice = this.getPriceOnDay(toDate);
    return beforePrice < afterPrice;
  }

}
