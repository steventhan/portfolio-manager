package model.trader;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by steven on 18/06/2017.
 */
public class TraderModelImpl2 implements TraderModel {
  private Map<String, StockBasket> records;

  public TraderModelImpl2() {
    this.records = new HashMap<>();
  }

  private boolean basketExist(String sbName) {
    StockBasket basket = this.records.get(sbName);
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
    try {
      StockBasket newBasket = new StockBasketImpl();
      newBasket.add(sbName, 1); // Stock is added to basket only when symbol is valid
      this.records.put(sbName, newBasket);
    } catch (IllegalArgumentException e) {
      // Exception is thrown when there's no matching symbol, meaning sbName can be basket name
      this.records.put(sbName, new StockBasketImpl());
    }
  }

  @Override
  public void addStockToBasket(String sbName, String symbol, int numShare) throws Exception {
    StockBasket basket = this.records.get(sbName);
    if (!this.basketExist(sbName)) {
      throw new IllegalArgumentException("Basket not found.");
    }
    basket.add(new StockSingleImpl(symbol), numShare);
  }

  @Override
  public Map<String, Integer> getBasketContentByName(String sbName) {
    StockBasket basket = this.records.get(sbName);
    if (!this.basketExist(sbName)) {
      throw new IllegalArgumentException("Basket not found.");
    }
    return basket
            .keySet()
            .stream()
            .collect(Collectors.toMap(k -> k.getSymbol(), k -> basket.get(k)));
  }

  @Override
  public Map<String, Map<String, Double>> getPlotData(String fromDate, String toDate)
          throws Exception {

    Map<String, Map<String, Double>> data = new TreeMap<>();

    for (String name : this.records.keySet()) {
      data.put(name, this.records.get(name).getClosingPrices(fromDate, toDate));
    }
    return data;
  }

  @Override
  public Map<String, Double> getPlotDataForOne(String symbolOrBasketName,
                                               String fromDate, String toDate) throws Exception {

    if (this.basketExist(symbolOrBasketName)) {
      return this.records.get(symbolOrBasketName).getClosingPrices(fromDate, toDate);
    }
    return new StockSingleImpl(symbolOrBasketName).getClosingPrices(fromDate, toDate);

  }

  @Override
  public boolean trendsUp(String name, String fromDate, String toDate) throws Exception {
    StockBasket basket = this.records.get(name);

    if (basket.keySet().size() == 0) {
      throw new IllegalArgumentException("Basket is empty.");
    }

    if (basket == null) {
      throw new IllegalArgumentException("Basket or stock not found.");
    }

    try {
      return new StockSingleImpl(name).trendsUp(fromDate, toDate);
    } catch (IllegalArgumentException e) {
      return basket.trendsUp(fromDate, toDate);
    }
  }
}