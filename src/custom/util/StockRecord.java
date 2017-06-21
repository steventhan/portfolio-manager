package custom.util;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by matthiasdenu on 6/20/2017.
 */
public interface StockRecord {
  String getSymbol();

  TreeMap<String, Double> getClosingPrices();

  TreeMap<String, Integer> getStockShares();

  TreeMap<String, Double> getFiftyDayAverages();

  TreeMap<String, Double> getTwoHundredDayAverages();

}
