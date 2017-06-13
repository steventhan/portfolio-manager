import java.util.Map;

import util.NewStockRetriever;
import util.WebRetrieverSingleton;

/**
 * Class to represent a basket of stocks.
 */
public class StockBasket extends StockAbstract {

  private Map<StockSingle, Integer> basket;
  NewStockRetriever retriever;

  /**
   * Constructs an empty {@code StockBasket} with the default WebRetrieverSingleton.
   */
  public StockBasket() {
    this.retriever = WebRetrieverSingleton.getInstance();
  }

  /**
   * Constructs a stock basket.
   *
   * @param basket map with shares per symbol in basket.
   * @throws IllegalArgumentException if either argument is null.
   */
  public StockBasket(Map<StockSingle, Integer> basket)
          throws IllegalArgumentException {
    if (basket == null) throw new IllegalArgumentException();
    this.basket = basket;
    this.retriever = WebRetrieverSingleton.getInstance();
  }

  /**
   * Constructs an empty {@code StockBasket} with the specified implementation of NewStockRetriever.
   */
  public StockBasket(NewStockRetriever retriever) {
    this.retriever = retriever;
  }

  /**
   * Constructs an empty {@code StockBasket} with the specified implementation of NewStockRetriever.
   *
   * @param basket    stock basket.
   * @param retriever stock retriever.
   * @throws IllegalArgumentException if either argument is null.
   */
  public StockBasket(Map<StockSingle, Integer> basket, NewStockRetriever retriever)
          throws IllegalArgumentException {
    if ((basket == null) || (retriever == null)) throw new IllegalArgumentException();
    this.basket = basket;
    this.retriever = retriever;
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
  public double getPriceOnDay(String date) throws Exception {
    double res = 0.0;
    for(StockSingle s : this.basket.keySet()) {
      res += s.getPriceOnDay(date) * this.basket.get(s);
    }
    return res;
  }

}
