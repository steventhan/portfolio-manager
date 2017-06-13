/**
 * This class represents an exception that can be thrown when a Stock's price cannot be found.
 */
public class StockPriceNotFound extends Exception {
  public StockPriceNotFound(String msg) {
    super(msg);
  }
}