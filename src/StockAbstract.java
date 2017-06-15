import custom.util.CustomDate;

/**
 * This class represents an abstract stock which implements operations shared between any stock
 * classes.
 */
abstract class StockAbstract implements IStock {

  /**
   * Verifies the start date is before the end date, and the end date is not in the future.
   *
   * @param fromDate the start date.
   * @param toDate   the end date.
   * @throws IllegalArgumentException when the start date is after the end date or the end date is
   *                                  in the future.
   */
  protected void verifyDateInOrder(String fromDate, String toDate) throws IllegalArgumentException {
    if (new CustomDate(fromDate).compareTo(new CustomDate(toDate)) >= 0
            || new CustomDate(toDate).compareTo(new CustomDate()) > 0) {
      throw new IllegalArgumentException("Start date has to be before end date, " +
              "and end date cannot be in the future");
    }
  }

  /**
   * Determines if a stock or basket trends up during a certain date range.
   * Find the line that joins the first data point to the last data point, and find its trend.
   *
   * @param fromDate start.
   * @param toDate   end.
   * @return true if up trend and false otherwise.
   * @throws Exception if dates not valid.
   */
  @Override
  public boolean trendsUp(String fromDate, String toDate) throws Exception {
    this.verifyDateInOrder(fromDate, toDate);
    return this.getPriceOnDay(fromDate) < this.getPriceOnDay(toDate);
  }
}
