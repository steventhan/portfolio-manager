package model.trader;

import java.util.List;
import java.util.Map;

/**
 * Created by steven on 18/06/2017.
 */
public interface TraderModel {
  List<String> getMenuOptions() throws Exception;

  void createStockBasket(String name) throws Exception;

  void createStockBasket(String name);
  void addStockToBasket(String name, String symbol, int numShare) throws Exception;
  void remove(String name);
  Map<String, Integer> getBasketContentByName(String sbName); //TODO: use for printBasket
  boolean trendsUp(String name, String fromDate, String toDate) throws Exception;
  Map<String, Map<String, Integer>> getAllBaskets();
}