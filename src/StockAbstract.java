/**
 * Created by matthiasdenu on 6/13/2017.
 */
abstract class StockAbstract implements IStock {

  /**
   * Determines if a stock or basket trends up during a certain date range.
   * Find the line that joins the first data point to the last data point, and find its trend.
   *
   * @param fromDate start.
   * @param toDate end.
   * @return true if up trend and false otherwise.
   * @throws IllegalArgumentException if dates not valid.
   */
  @Override
  public boolean trendsUp(String fromDate, String toDate) throws Exception {
    return this.getPriceOnDay(fromDate) < this.getPriceOnDay(toDate);
  }

}
