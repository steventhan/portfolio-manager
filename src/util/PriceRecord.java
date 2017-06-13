package util;

/**
 * This class represents the record of a price of a single stock item in a day
 */
public class PriceRecord {
  private final double open,close,highest,lowest;

  public PriceRecord(double open,double close,double lowest,double highest) {
    this.open = open;
    this.close = close;
    this.highest = highest;
    this.lowest = lowest;
  }

  public double getOpenPrice() { return open;}
  public double getClosePrice() { return close;}
  public double getLowestDayPrice() {return lowest;}
  public double getHighestDayPrice() {return highest;}

  @Override
  public String toString() {
    return String.format("{open: %f, close: %f, highest: %f, lowest: %f}",
            open, close, highest, lowest);
  }
}

