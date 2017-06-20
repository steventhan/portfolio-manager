package model.trader;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by steven on 18/06/2017.
 */
public class TraderModelImpl implements TraderModel {
  private Map<String, StockBasket> baskets;

  public TraderModelImpl() {
    this.baskets = new LinkedHashMap<>();
  }

  @Override
  public void createStockBasket(String sbName) {
    this.baskets.put(sbName, new StockBasket());
  }

  @Override
  public void addStockToBasket(String sbName, String symbol, int numShare) throws Exception {
    StockBasket basket = this.baskets.get(sbName);
    Objects.requireNonNull(basket);
    basket.add(new StockSingle(symbol), numShare);
  }

  @Override
  public Map<String, Integer> getBasketContentByName(String sbName) {
    StockBasket basket = this.baskets.get(sbName);
    return basket
            .keySet()
            .stream()
            .collect(Collectors.toMap(k -> k.getSymbol(), k -> basket.get(k)));
  }

  @Override
  public Map<String, Map<String, Integer>> getAllBaskets() {
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
    return new StockSingle(stockSymbol).getPriceOnDay(strDate);
  }
}