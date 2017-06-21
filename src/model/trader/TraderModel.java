package model.trader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import custom.util.StockRecord;

/**
 * Created by steven on 18/06/2017.
 */
public interface TraderModel {
  Map<String, StockRecord> getRecords();
  void createStockBasket(String name) throws Exception;
  void addStockToBasket(String name, String symbol, int numShare) throws Exception;
  void remove(String name);
  Map<String, Integer> getBasketContentByName(String sbName); //TODO: use for printBasket
  boolean trendsUp(String name, String fromDate, String toDate) throws Exception;

  /**
   * Stock records are closely related to TraderModels, that is why it was decided that the static
   * method to print a map of StockRecord should be here. It was decided that this method should be
   * static so that it did not require the instantiation of a new TraderModelImpl to print a
   * map of StockRecord. This is helpful because the transmission of data throughout our program is
   * essentially the transmission of maps of StockRecords throughout our program, so having a method
   * in the TraderModel interface that allows us to essentially print transmitted data is useful.
   *
   * @param records map of StockRecord to be printed.
   * @return String representation of map of StockRecord.
   */
  static String sPrintStockRecords(Map<String, StockRecord> records) {
    StringBuilder result = new StringBuilder();
    Iterator<String> cps;
    Iterator<String> fiftyDay;
    Iterator<String> two100Day;
    Iterator<String> shrs;
    String shareKey;

    for (String name : records.keySet()) {
      Map<String, Double> closingPrices = records.get(name).getClosingPrices();
      Map<String, Double> fiftyDayAvgs = records.get(name).getFiftyDayAverages();
      Map<String, Double> two100DayAvgs = records.get(name).getTwoHundredDayAverages();
      Map<String, Integer> shares = records.get(name).getStockShares();

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