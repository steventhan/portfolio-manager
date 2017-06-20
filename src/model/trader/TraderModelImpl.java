package model.trader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by steven on 18/06/2017.
 */
public class TraderModelImpl implements TraderModel {
  private Map<String, StockBasket> baskets;
  private List<StockSingle> stocks;

  public TraderModelImpl() {
    this.baskets = new LinkedHashMap<>();
    this.stocks = new LinkedList<>();
  }

  @Override
  public List<String> getMenuOptions() {
    return Arrays.asList("c - Create new stock basket", "a - Add new share to a basket",
            "p - Print the stock basket", "g - Graphical view",  "q - Quit");
  }

  @Override
  public void createStockBasket(String name) throws Exception {
    //TODO: basket names cannot be stock symbols
    try {
      new StockSingleImpl(name);
    } catch (IllegalArgumentException e) {
      this.baskets.put(name, new StockBasketImpl());
    }
    throw new IllegalArgumentException("Basket name cannot be same with a stock symbol");
  }

  @Override
  public void addStockToBasket(String name, String symbol, int numShare) throws Exception {
    StockBasket basket = this.baskets.get(name);
    Objects.requireNonNull(basket);
    basket.add(new StockSingleImpl(symbol), numShare);
  }

  @Override
  public void remove(String name) {
    if (baskets.keySet().contains(name)) {
      this.baskets.remove(name);
    } else {
      for (StockSingle s : this.stocks) {
        if (s.getSymbol().equals(name)) {
          this.stocks.remove(s);
          return;
        }
      }
    }
  }

  @Override
  public Map<String, Integer> getBasketContentByName(String sbName) {
    StockBasket basket = this.baskets.get(sbName);
    //TODO: why not return a new basket here?
    return basket
            .keySet()
            .stream()
            .collect(Collectors.toMap(StockSingle::getSymbol, basket::get));
  }

  @Override
  public boolean trendsUp(String name, String fromDate, String toDate) throws Exception {
    //TODO: either check date format or create custom date
    if (baskets.keySet().contains(name)) {
      return this.baskets.get(name).trendsUp(fromDate, toDate);
    } else {
      for (StockSingle s : this.stocks) {
        if (s.getSymbol().equals(name)) {
          return s.trendsUp(fromDate, toDate);
        }
      }
    }
    //TODO: maybe throw exception if name not contained in this.baskets or this.stocks
    return false;
  }

  @Override
  public Map<String, Map<String, Integer>> getAllBaskets() {
    //TODO: is this prefferred over Map<String, StockBasket> ?
    Map<String, Map<String, Integer>> result = new LinkedHashMap<>();
    this.baskets.keySet().stream().map(k -> {
      StockBasket basket = this.baskets.get(k);
      Map<String, Integer> basketContent = new HashMap<>();
      basket.keySet().stream().map(key -> basketContent.put(key.getSymbol(), basket.get(key)));
      return result.put(k, basketContent);
    });

    return result;
  }

  @Override
  public double getPriceOnDay(String stockSymbol, String strDate) throws Exception {
    return new StockSingleImpl(stockSymbol).getPriceOnDay(strDate);
  }

}