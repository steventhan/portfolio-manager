package custom.util;

import java.util.Map;
import java.util.TreeMap;

/**
 * Used to store information about a stock or a basket of stocks. This class is effectively a
 * substitute for a communication protocol between the model, view, and controller interfaces. A
 * list of stock records is created by the model and sent to the controller, where it is then
 * sent to the view to be rendered.
 */
public class StockRecordImpl implements StockRecord {
  private String symbol;
  private Map<String, Integer> stockShares; //TODO: make null for single stock and check later
  private Map<String, Double> closingPrices;
  private Map<String, Double> fiftyDayAverages;
  private Map<String, Double> twoHundredDayAverages;

  //TODO: scale view according to longest field among closingPrices, fiftyDayAverages, and twoHundredDayAverages

  public StockRecordImpl(String symbol,
                         Map<String, Integer> stockShares,
                         Map<String, Double> closingPrices,
                         Map<String, Double> fiftyDayAverages,
                         Map<String, Double> twoHundredDayAverages) {
    this.symbol = symbol;
    this.stockShares = stockShares;
    this.closingPrices = closingPrices;
    this.fiftyDayAverages = fiftyDayAverages;
    this.twoHundredDayAverages = twoHundredDayAverages;
  }

  @Override
  public String getSymbol() {
    return this.symbol;
  }

  @Override
  public Map<String, Double> getClosingPrices() {
    return copyMapStringDouble(this.closingPrices);
  }

  @Override
  public Map<String, Integer> getStockShares() {
    return copyMapStringInteger(this.stockShares);
  }

  @Override
  public Map<String, Double> getFiftyDayAverages() {
    return copyMapStringDouble(this.fiftyDayAverages);
  }

  @Override
  public Map<String, Double> getTwoHundredDayAverages() {
    return copyMapStringDouble(this.twoHundredDayAverages);
  }

  private TreeMap<String, Integer> copyMapStringInteger(Map<String, Integer> map) {
    TreeMap<String, Integer> result = new TreeMap<>();
    for (String key : map.keySet()) {
      result.put(key, map.get(key));
    }
    return result;
  }

  private TreeMap<String, Double> copyMapStringDouble(Map<String, Double> map) {
    TreeMap<String, Double> result = new TreeMap<>();
    for (String key : map.keySet()) {
      result.put(key, map.get(key));
    }
    return result;
  }
}
