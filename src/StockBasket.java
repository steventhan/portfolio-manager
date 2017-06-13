import util.PriceRecord;
import util.WebStockDataRetriever;

import java.util.Map;

/**
 * Created by matthiasdenu on 6/12/2017.
 */
public class StockBasket implements IStock {
  //TODO: Ask about nested maps.

  private Map<StockSingle, Integer> basket;

  /**
   * Constructs a stock basket.
   * @param basket map with shares per symbol in basket.
   */
  public StockBasket(Map<StockSingle, Integer> basket) {
    this.basket = basket;
  }

  @Override
  public double getPriceOnDay(String date) throws Exception {
    //TODO: Check the time and date
    //TODO: Check for weekday before 4pm, use current price.
    double res = 0.0;
    for(StockSingle s : this.basket.keySet()) {
      res += s.getPriceOnDay(date) * this.basket.get(s);
    }

    return 0.0;
  }

  @Override
  public boolean trendsUp(String fromDate, String toDate) {
    //TODO: Check the time and date
    //TODO: Check for weekday before 4pm, use current price.
    return false;
  }

}
