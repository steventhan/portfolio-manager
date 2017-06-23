package model.trader;

import java.util.Map;

/**
 * All methods offered by the trader model, listed in alphabetical order.
 */
public interface TraderModel {

  void addStockToBasket(String name, String symbol, int numShare) throws Exception;

  void createStockBasket(String name) throws Exception;

  Map<String, Integer> getBasketContentByName(String sbName);

  Map<String, Map<String, Integer>> getBaskets();

  double getHighestPrice();

  Map<String, Map<String, Double>> getMovingAveragesForAll(String fromDate, String toDate, int days)
          throws Exception;

  Map<String, Double> getMovingAveragesForOne(String stockOrBasketName, String fromDate,
                                              String toDate, int days) throws Exception;

  Map<String, Map<String, Double>> getPlotData(String fromDate, String toDate) throws Exception;

  Map<String, Double> getPlotDataForOne(String stockOrBasketName,
                                        String fromDate, String toDate) throws Exception;

  //TODO do we want a remove() method

  boolean trendsUp(String name, String fromDate, String toDate) throws Exception;

}