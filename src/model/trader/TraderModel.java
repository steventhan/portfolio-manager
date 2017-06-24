package model.trader;

import java.util.Map;

/**
 * All methods offered by the trader model, listed in alphabetical order.
 */
public interface TraderModel {

  /**
   * Adds stock to a basket.
   * It is possible to remove stocks by sending a negative number. It is also possible to store a
   * negative number of stocks in a basket.
   *
   * @param name of basket.
   * @param symbol of stock.
   * @param numShare number of shares of stock to add to the basket.
   * @throws Exception described by its message.
   */
  void addStockToBasket(String name, String symbol, int numShare) throws Exception;

  /**
   * Creates a stock basket.
   * A previously created stock basket can be overwritten by creating a new stock basket of the
   * same name.
   *
   * @param name of basket.
   * @throws Exception described by its message.
   */
  void createStockBasket(String name) throws Exception;

  /**
   * Get the contents of a basket.
   *
   * @param sbName stock basket name.
   * @return representation of the stock baskets contents, i.e. the stock names and the shares per
   *         stock.
   */
  Map<String, Integer> getBasketContentByName(String sbName);

  /**
   * Returns a representation of all the baskets currently in the model.
   *
   * @return a representation of all the baskets currently in the model.
   */
  Map<String, Map<String, Integer>> getBaskets();

  /**
   * Returns highest price of all the stock entities of all the days in the range.
   *
   * @return highest price.
   */
  double getHighestPrice();

  /**
   * Returns a representation of the moving averages for each stock or basket present for each day
   * in the given range.
   *
   * @param fromDate date range start.
   * @param toDate date range end.
   * @param days number of days to average over.
   * @return representation of the moving averages for each stock or basket present for each day in
   *         the given range.
   * @throws Exception descrbided by its message.
   */
  Map<String, Map<String, Double>> getMovingAveragesForAll(String fromDate, String toDate, int days)
          throws Exception;

  /**
   * Returns a representation of the moving averages for a stock or basket present for each day
   * in the given range.
   *
   * @param stockOrBasketName stock or basket name.
   * @param fromDate date range start.
   * @param toDate date range end.
   * @param days number of days to average over.
   * @return a representation of the moving averages for a stock or basket present for each day
   * @throws Exception described by its message.
   */
  Map<String, Double> getMovingAveragesForOne(String stockOrBasketName, String fromDate,
                                              String toDate, int days) throws Exception;

  /**
   * Return the plot data for all stocks or baskets currently stored in the model.
   *
   * @param fromDate date range start.
   * @param toDate date range end.
   * @return the plot data for all stocks or baskets currently stored in the model.
   * @throws Exception described by its message.
   */
  Map<String, Map<String, Double>> getPlotData(String fromDate, String toDate) throws Exception;

  /**
   * Return the plot data for a stock or basket currently stored in the model.
   *
   * @param stockOrBasketName stock basket or name.
   * @param fromDate date range start.
   * @param toDate date range end.
   * @return the plot data for a stock or basket currently stored in the model.
   * @throws Exception described by its message.
   */
  Map<String, Double> getPlotDataForOne(String stockOrBasketName,
                                        String fromDate, String toDate) throws Exception;

  /**
   * Removes a stock entity from the current model, if it exists.
   */
  void remove(String name);

  /**
   * Returns true if the stock entity trends up, given the specified dates. This is a naive
   * algorithm that just looks at the difference between the values at the start and end dates.
   *
   * @param name stock entity name.
   * @param fromDate date range start.
   * @param toDate date range end.
   * @return true if stock entity trends up, otherwise false.
   * @throws Exception described by its message.
   */
  boolean trendsUp(String name, String fromDate, String toDate) throws Exception;

}