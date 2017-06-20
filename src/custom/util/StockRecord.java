package custom.util;

import java.util.Map;

/**
 * Created by matthiasdenu on 6/20/2017.
 */
public interface StockRecord {
  String getSymbol();

  Map<String, Double> getClosingPrices();

  Map<String, Integer> getStockShares();

  Map<String, Double> getFiftyDayAverages();

  Map<String, Double> getTwoHundredDayAverages();

}
