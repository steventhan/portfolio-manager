import java.util.Map;

import util.PriceRecord;

/**
 * Created by matthiasdenu on 6/12/2017.
 */
public class BasketStockDataQuery implements IBasketStockDataQuery {

  //TODO: Ask about nested maps.

  public BasketStockDataQuery() {

  }

  @Override
  public PriceRecord getPriceOnDay(Integer date) {
    return null;
  }

  @Override
  public boolean trendsUp(Integer fromDate, Integer toDate) {
    return false;
  }

}
