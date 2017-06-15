package util;

/**
 * This class represents the record of a price of a single stock item in a day.
 */
public class PriceRecord {
  private final double open;
  private final double close;
  private final double highest;
  private final double lowest;

  /**
   * Constructs a PriceRecord object.
   *
   * @param open    price at open
   * @param close   price at close
   * @param lowest  lowest price
   * @param highest highest price
   */
  public PriceRecord(double open, double close, double lowest, double highest) {
    this.open = open;
    this.close = close;
    this.highest = highest;
    this.lowest = lowest;
  }

  /**
   * Gets the open price.
   *
   * @return open price as double
   */
  public double getOpenPrice() {
    return open;
  }

  /**
   * Gets the close price.
   *
   * @return close price as double
   */
  public double getClosePrice() {
    return close;
  }

  /**
   * Gets the lowest price.
   *
   * @return lowest price as double
   */
  public double getLowestDayPrice() {
    return lowest;
  }

  /**
   * Gets the highest price.
   *
   * @return highest price as double
   */
  public double getHighestDayPrice() {
    return highest;
  }
}
