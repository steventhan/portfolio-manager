import java.util.Map;

import util.PriceRecord;

/**
 * Created by matthiasdenu on 6/12/2017.
 */
public interface IBasketStockDataQuery extends IStockDataQuery {

  /**
   * Creates a basket of stocks comprising of shares of one or more stocks and then determine
   * its price on a certain date.
   * @return
   */
  //TODO: params here should echo params in Basket constructor (probably a map of symbols & shares)
  PriceRecord getBasketPriceOnDay(Map<String, Integer> basket, Integer date);

}
