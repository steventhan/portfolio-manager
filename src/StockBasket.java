import util.PriceRecord;

import java.util.Map;

/**
 * Created by matthiasdenu on 6/12/2017.
 */
public class StockBasket implements IStock {
  //TODO: Ask about nested maps.

  /**
   * Constructs a stock basket.
   * @param basket map with shares per symbol in basket.
   */
  public StockBasket(Map<String, Integer> basket) {

  }

  @Override
  public PriceRecord getPriceOnDay(String date) {
    return null;
  }

  @Override
  public boolean trendsUp(String fromDate, String toDate) {
    return false;
  }

}
