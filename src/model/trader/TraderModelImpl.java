package model.trader;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by steven on 18/06/2017.
 */
public class TraderModelImpl implements TraderModel {
  private Map<String, StockBasket> stockBasketMap;
  private double highestPrice; // This field is to determine graph vertical scaling.

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

  @Override
  public void addStockToBasket(String sbName, String symbol, int numShare) throws Exception {
    StockBasket basket = this.stockBasketMap.get(sbName);
    if (!this.basketExist(sbName)) {
      throw new IllegalArgumentException("Basket not found.");
    }
    basket.add(new StockSingleImpl(symbol), numShare);
  }

  @Override
  public Map<String, Map<String, Integer>> getBaskets() {
    return this.stockBasketMap.keySet().stream() // stream the stockBasketMap
            .collect(Collectors.toMap(k -> k, k -> this.stockBasketMap.get(k).getStocks()));
  }

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

  @Override
  public double getHighestPrice() {
    return this.highestPrice;
  }

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

  @Override
  public void remove(String name) {
    try {
      this.stockBasketMap.remove(name);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

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