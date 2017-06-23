package model.trader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

import custom.util.StockRecord;
import custom.util.StockRecordImpl;

/**
 * Created by steven on 18/06/2017.
 */
public class TraderModelImpl implements TraderModel {
  private Map<String, StockRecord> records;

  //TraderModel.printStockRecords(myRecords);

  static final void printStockRecords(Map<String, StockRecord> records) {

  }

  public TraderModelImpl() {
    records = new HashMap<>();
  }

  @Override
  public Map<String, StockRecord> getRecords() {
    return this.records.keySet().stream().collect(Collectors.toMap(k -> k, records::get));
  }

  @Override
  public void createStockBasket(String name) throws Exception {
    //TODO: basket names cannot be stock symbols
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
  //TODO: extra feature decrement number of shares.
  public void addStockToBasket(String name, String symbol, int shares) throws Exception {
    StockRecord record = this.records.get(name);
    Objects.requireNonNull(record); //TODO: stock not found in the controller
    TreeMap<String, Integer> newShares = record.getStockShares();
    Integer tempInteger = (newShares.get(symbol) == null) ? 0 : newShares.get(symbol);
    newShares.put(symbol, shares + tempInteger);

    this.records.put(name, new StockRecordImpl(record.getSymbol(),
            newShares,
            record.getClosingPrices(),
            record.getFiftyDayAverages(),
            record.getTwoHundredDayAverages()));
  }

  @Override
  public Map<String, Integer> getBasketContentByName(String sbName) {
    //TODO: test this
    Map<String, Integer> contents = this.records.get(sbName).getStockShares();
    return contents.keySet().stream().collect(Collectors.toMap(k -> k, contents::get));
  }

  @Override
  public Map<String, Map<String, Double>> getPlotData(String fromDate, String toDate) {
    return null;
  }

  @Override
  public Map<String, Double> getPlotDataForOne(String stockOrBasketName, String fromDate, String toDate) throws Exception {
    return null;
  }

  @Override
  public boolean trendsUp(String name, String fromDate, String toDate) throws Exception {
    //TODO: test this

    try {
      StockSingle tempSingle = new StockSingleImpl(name);
      return tempSingle.trendsUp(fromDate, toDate);

    } catch (IllegalArgumentException e) {
      Map<String, Integer> contents = this.records.get(name).getStockShares();
      if (contents.size() == 0) {
        throw new IllegalArgumentException("There's nothing in the basket.");
      }
      Map<StockSingle, Integer> unboxedBasket = new HashMap<>();
      for (String key : contents.keySet()) {
        unboxedBasket.put(new StockSingleImpl(key), contents.get(key));
      }
      return new StockBasketImpl(unboxedBasket).trendsUp(fromDate, toDate);
    }
  }

  @Override
  public double getHighestPrice() {
    return 0;
  }

  @Override
  public String toString() {
    //TODO: pass sorted version of this.records
    return TraderModel.sPrintStockRecords(this.records);
  }
}