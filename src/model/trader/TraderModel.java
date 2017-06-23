package model.trader;

import java.util.List;
import java.util.Map;
//TODO: add creation dates for basket

/**
 * Created by steven on 18/06/2017.
 */
public interface TraderModel {
  void createStockBasket(String name) throws Exception;
  void addStockToBasket(String name, String symbol, int numShare) throws Exception;
  Map<String, Integer> getBasketContentByName(String sbName); //TODO: use for printBasket
  Map<String, Map<String, Double>> getPlotData(String fromDate, String toDate) throws Exception; //TODO: use for printBasket
  Map<String, Double> getPlotDataForOne(String stockOrBasketName,
                                        String fromDate, String toDate) throws Exception; //TODO: use for printBasket
  Map<String, Map<String, Double>> getMovingAveragesForAll(String fromDate, String toDate, int days)
          throws Exception;
  Map<String, Double> getMovingAveragesForOne(String stockOrBasketName, String fromDate,
                                              String toDate, int days) throws Exception; //TODO: use for printBasket
  boolean trendsUp(String name, String fromDate, String toDate) throws Exception;
  double getHighestPrice();
}