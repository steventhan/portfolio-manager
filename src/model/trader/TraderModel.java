package model.trader;

import java.util.Map;

/**
 * Created by steven on 18/06/2017.
 */
public interface TraderModel {
  void createStockBasket(String sbName);
  void addStockToBasket(String sbName, String symbol, int numShare) throws Exception;
  Map<String, Integer> getBasketByName(String sbName);
  Map<String, Map<String, Integer>> getAllBaskets();
  double getPriceOnDay(String stockSymbol, String strDate) throws Exception;
}