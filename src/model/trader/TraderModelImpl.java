package model.trader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import custom.util.StockRecord;
import custom.util.StockRecordImpl;

/**
 * Created by steven on 18/06/2017.
 */
public class TraderModelImpl implements TraderModel {
  private Map<String, StockRecord> records;

  public TraderModelImpl() {
    records = new HashMap<>();
  }

  @Override
  public List<String> getMenuOptions() {
    return Arrays.asList("c - Create new stock basket",
            "a - Add new share to a basket",
            "p - Print the stock basket",
            "t - Trend of stock",
            "g - Graphical view",
            "p - Plot",
            "q - Quit");
  }

  @Override
  public void createStockBasket(String name) throws Exception {
    //TODO: basket names cannot be stock symbols
    StockBasket tempBasket = new StockBasketImpl();
    TreeMap<String, Integer> tempShares = new TreeMap<>();
    TreeMap<String, Double> tempPrices = new TreeMap<>();
    TreeMap<String, Double> temp50Avgs = new TreeMap<>();
    TreeMap<String, Double> temp200Avgs = new TreeMap<>();

    StockRecord tempRecord = new StockRecordImpl(name,
            tempShares,
            tempPrices,
            temp50Avgs,
            temp200Avgs);

    records.put(name, tempRecord);
  }

  @Override
  public void addStockToBasket(String name, String symbol, int shares) throws Exception {
    //TODO: test this
    StockRecord temp = this.records.get(name);
    Map<String, Integer> tempShares = temp.getStockShares();
    Integer tempInteger = (tempShares.get(symbol) == null) ? 0 : tempShares.get(symbol);
    tempShares.put(symbol, shares + tempInteger);
  }

  @Override
  public void remove(String name) {
    this.records.remove(name);
  }

  @Override
  public Map<String, Integer> getBasketContentByName(String sbName) {
    //TODO: test this
    Map<String, Integer> contents = this.records.get(sbName).getStockShares();
    return contents.keySet().stream().collect(Collectors.toMap(k -> k, contents::get));
  }

  @Override
  public boolean trendsUp(String name, String fromDate, String toDate) throws Exception {
    //TODO: test this

    try {
      StockSingle tempSingle = new StockSingleImpl(name);
      return tempSingle.trendsUp(fromDate, toDate);

    } catch (IllegalArgumentException e) {
      Map<String, Integer> contents = this.records.get(name).getStockShares();
      Map<StockSingle, Integer> unboxedBasket = new HashMap<>();
      for (String key : contents.keySet()) {
        unboxedBasket.put(new StockSingleImpl(key), contents.get(key));
      }
      return new StockBasketImpl(unboxedBasket).trendsUp(fromDate, toDate);

    }
  }

  @Override
  public Map<String, Map<String, Integer>> getAllBaskets() {
    Map<String, Map<String, Integer>> result = new LinkedHashMap<>();
    return this.records.keySet().stream()
            .collect(Collectors.toMap(k -> k, k -> this.records.get(k).getStockShares()));
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    Iterator<String> cps;
    Iterator<String> fiftyDay;
    Iterator<String> two100Day;
    Iterator<String> shrs;
    String shareKey;

    for (String name : this.records.keySet()) {
      Map<String, Double> closingPrices = this.records.get(name).getClosingPrices();
      Map<String, Double> fiftyDayAvgs = this.records.get(name).getFiftyDayAverages();
      Map<String, Double> two100DayAvgs = this.records.get(name).getTwoHundredDayAverages();
      Map<String, Integer> shares = this.records.get(name).getStockShares();

      cps = closingPrices.keySet().iterator();
      fiftyDay = fiftyDayAvgs.keySet().iterator();
      two100Day = two100DayAvgs.keySet().iterator();

      for (int i = 0; i < 30 - (name.length() / 2); i++) {
        result.append(" ");
      } // center record name
      result.append(name).append("\n");
      while (cps.hasNext() || fiftyDay.hasNext() || two100Day.hasNext()) {
        result.append(String.format("%19s %19s %19s\n",
                (cps.hasNext() ? closingPrices.get(cps.next()) : ""),
                (fiftyDay.hasNext() ? fiftyDayAvgs.get(fiftyDay.next()) : ""),
                (two100Day.hasNext() ? two100DayAvgs.get(two100Day.next()) : "")));
      }

      result.append("\n");
      result.append("stock shares\n\n");
      shrs = shares.keySet().iterator();

      while (shrs.hasNext()) {
        shareKey = shrs.next();
        result.append(String.format("%s, %d\n", shareKey, shares.get(shareKey)));
      }
      result.append("\n").append("\n");
    }

    return result.toString();
  }
}