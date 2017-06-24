package model.trader;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * An implementation of TraderModel.
 */
public class TraderModelImpl implements TraderModel {
  private Map<String, StockBasket> stockBasketMap;
  private double highestPrice; // This field is to determine graph vertical scaling.

  /**
   * Constructs a TraderModelImpl object.
   */
  public TraderModelImpl() {
    this.stockBasketMap = new HashMap<>();
    this.highestPrice = 0;
  }

  private boolean basketExist(String sbName) {
    StockBasket basket = this.stockBasketMap.get(sbName);
    if (basket == null) {
      return false;
    }
    if (basket.keySet().size() == 1) {
      for (StockSingle k : basket.keySet()) {
        return !k.getSymbol().equals(sbName);
      }
    }
    return true;
  }

  /**
   * Creates a stock basket.
   * A previously created stock basket can be overwritten by creating a new stock basket of the
   * same name.
   *
   * @param sbName of basket.
   * @throws Exception described by its message.
   */
  @Override
  public void createStockBasket(String sbName) throws Exception {
    // throws exception if sbName has spaces because the URL gets messed up
    for (char c : sbName.toCharArray()) {
      if (Character.isSpaceChar(c)) {
        throw new IllegalArgumentException("Name must not contain spaces");
      }
    }

    try {
      StockBasket newBasket = new StockBasketImpl();
      newBasket.add(sbName, 1); // Stock is added to basket only when symbol is valid
      this.stockBasketMap.put(sbName, newBasket);
    } catch (IllegalArgumentException e) {
      // Exception is thrown when there's no matching symbol, meaning sbName can be basket name
      this.stockBasketMap.put(sbName, new StockBasketImpl());
    }
  }

  /**
   * Adds stock to a basket.
   * It is possible to remove stocks by sending a negative number. It is also possible to store a
   * negative number of stocks in a basket.
   *
   * @param sbName of basket.
   * @param symbol of stock.
   * @param numShare number of shares of stock to add to the basket.
   * @throws Exception described by its message.
   */
  @Override
  public void addStockToBasket(String sbName, String symbol, int numShare) throws Exception {
    StockBasket basket = this.stockBasketMap.get(sbName);
    if (!this.basketExist(sbName)) {
      throw new IllegalArgumentException("Basket not found.");
    }
    basket.add(new StockSingleImpl(symbol), numShare);
  }

  /**
   * Returns a representation of all the baskets currently in the model.
   *
   * @return a representation of all the baskets currently in the model.
   */
  @Override
  public Map<String, Map<String, Integer>> getBaskets() {
    return this.stockBasketMap.keySet().stream() // stream the stockBasketMap
            .collect(Collectors.toMap(k -> k, k -> this.stockBasketMap.get(k).getStocks()));
  }

  /**
   * Get the contents of a basket.
   *
   * @param sbName stock basket name.
   * @return representation of the stock baskets contents, i.e. the stock names and the shares per
   *         stock.
   */
  @Override
  public Map<String, Integer> getBasketContentByName(String sbName) {
    StockBasket basket = this.stockBasketMap.get(sbName);
    if (!this.basketExist(sbName)) {
      throw new IllegalArgumentException("Basket not found.");
    }
    return basket
            .keySet()
            .stream()
            .collect(Collectors.toMap(StockSingle::getSymbol, basket::get));
  }

  /**
   * Return the plot data for all stocks or baskets currently stored in the model.
   *
   * @param fromDate date range start.
   * @param toDate date range end.
   * @return the plot data for all stocks or baskets currently stored in the model.
   * @throws Exception described by its message.
   */
  @Override
  public Map<String, Map<String, Double>> getPlotData(String fromDate, String toDate)
          throws Exception {

    Map<String, Map<String, Double>> data = new TreeMap<>();
    Map<String, Double> closingPrices;
    this.highestPrice = 0;

    for (String name : this.stockBasketMap.keySet()) {
      closingPrices = this.stockBasketMap.get(name).getClosingPrices(fromDate, toDate);
      closingPrices
              .entrySet()
              .stream()
              .forEach(e -> this.highestPrice = e.getValue() > this.highestPrice
                      ? e.getValue() : this.highestPrice);

      data.put(name, closingPrices);
    }
    return data;
  }

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
  @Override
  public Map<String, Map<String, Double>> getMovingAveragesForAll(String fromDate,
                                                                  String toDate, int days)
          throws Exception {

    Map<String, Map<String, Double>> data = new TreeMap<>();
    Map<String, Double> averages;

    for (String name : this.stockBasketMap.keySet()) {
      averages = this.stockBasketMap.get(name).getMovingAverages(fromDate, toDate, days);
      data.put(name, averages);
    }
    return data;
  }


  /**
   * Returns a representation of the moving averages for a stock or basket present for each day
   * in the given range.
   *
   * @param symbolOrBasketName stock or basket name.
   * @param fromDate date range start.
   * @param toDate date range end.
   * @param days number of days to average over.
   * @return a representation of the moving averages for a stock or basket present for each day
   * @throws Exception described by its message.
   */
  @Override
  public Map<String, Double> getMovingAveragesForOne(String symbolOrBasketName, String fromDate,
                                                     String toDate, int days)
          throws Exception {
    Map<String, Double> data;
    data = this.basketExist(symbolOrBasketName)
            ? this.stockBasketMap.get(symbolOrBasketName).getMovingAverages(fromDate, toDate, days)
            : new StockSingleImpl(symbolOrBasketName).getMovingAverages(fromDate, toDate, days);

    data.entrySet()
            .stream()
            .forEach(e -> this.highestPrice = e.getValue() > this.highestPrice
                    ? e.getValue() : this.highestPrice);

    return data;

  }

  /**
   * Returns highest price of all the stock entities of all the days in the range.
   *
   * @return highest price.
   */
  @Override
  public double getHighestPrice() {
    return this.highestPrice;
  }

  /**
   * Return the plot data for a stock or basket currently stored in the model.
   *
   * @param symbolOrBasketName stock basket or name.
   * @param fromDate date range start.
   * @param toDate date range end.
   * @return the plot data for a stock or basket currently stored in the model.
   * @throws Exception described by its message.
   */
  @Override
  public Map<String, Double> getPlotDataForOne(String symbolOrBasketName,
                                               String fromDate, String toDate) throws Exception {
    Map<String, Double> data;

    data = this.basketExist(symbolOrBasketName) ?
            this.stockBasketMap.get(symbolOrBasketName).getClosingPrices(fromDate, toDate)
            : new StockSingleImpl(symbolOrBasketName).getClosingPrices(fromDate, toDate);

    data.entrySet()
            .stream()
            .forEach(e -> this.highestPrice = e.getValue() > this.highestPrice ?
                    e.getValue() : this.highestPrice);

    return data;
  }

  /**
   * Removes a stock entity from the current model, if it exists.
   */
  @Override
  public void remove(String name) {
    try {
      this.stockBasketMap.remove(name);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

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
  @Override
  public boolean trendsUp(String name, String fromDate, String toDate) throws Exception {
    StockBasket basket = this.stockBasketMap.get(name);

    if (basket == null) {
      throw new IllegalArgumentException("Basket or stock not found.");
    }

    if (basket.keySet().size() == 0) {
      throw new IllegalArgumentException("Basket is empty.");
    }

    try {
      return new StockSingleImpl(name).trendsUp(fromDate, toDate);
    } catch (IllegalArgumentException e) {
      return basket.trendsUp(fromDate, toDate);
    }
  }

}