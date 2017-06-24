package util;

import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * This class represents a stock retriever module. It is a singleton, and so to
 * get the one (and only) object call getStockDataRetriever()
 */
public class WebStockDataRetriever implements StockDataRetriever {

  public WebStockDataRetriever() {
    // This is a singleton, no new object construction allowed.
  }

  /**
   * Gets current price of a stock symbol.
   *
   * @param stockSymbol the stock symbol
   * @return the current price as double
   * @throws Exception when there's a problem with http request
   */
  public double getCurrentPrice(String stockSymbol) throws Exception {
    URL url = new URL("https://download.finance.yahoo.com/d/quotes.csv?"
            + "s=" + stockSymbol + "&f=l1&e=.csv");

    String output = new Scanner(url.openStream()).next();


    return Double.parseDouble(output);
  }

  /**
   * Gets the name of the name of company.
   *
   * @param stockSymbol stock symbol
   * @return the company Name as String
   * @throws Exception when there's a problem with the http request
   */
  public String getName(String stockSymbol) throws Exception {
    URL url = new URL("https://download.finance.yahoo.com/d/quotes.csv?"
            + "s=" + stockSymbol + "&f=n&e=.csv");

    return new Scanner(url.openStream()).next();
  }

  /**
   * Gets a Map of Date and Price records.
   *
   * @param stockSymbol the stock symbol
   * @param fromDate    start date as int
   * @param fromMonth   start month as int
   * @param fromYear    start year as int
   * @param toDate      end date as int
   * @param toMonth     end month as int
   * @param toYear      end year as int
   * @return the data as Map
   * @throws Exception when there's a problem with http request
   */
  public Map<Integer, PriceRecord> getHistoricalPrices(
          String stockSymbol,
          int fromDate,
          int fromMonth,
          int fromYear,
          int toDate,
          int toMonth,
          int toYear)
          throws
          Exception {


    URL url = new URL("https://www.google"
            + ".com/finance/historical?output=csv&q=" + stockSymbol + "&startdate="
            + fromMonth + "+" + fromDate + "+" + fromYear + "&enddate=" + toMonth + "+"
            + toDate + "+" + toYear);

    String output = "";
    Map<Integer, PriceRecord> prices = new TreeMap<>();
    Scanner sc = new Scanner(url.openStream());
    //get first line of labels
    output = sc.next();

    while (sc.hasNext()) {
      output = sc.next();
      String[] data = output.split(",");

      PriceRecord record = new PriceRecord(
              Double.parseDouble(data[1]),
              Double.parseDouble(data[4]),
              Double.parseDouble(data[3]),
              Double.parseDouble(data[2])
      );
      //date is index 0
      Integer date = getDate(data[0]);
      prices.put(date, record);
    }
    return prices;
  }

  private int toMonth(String month) {
    switch (month) {
      case "Jan":
        return 1;
      case "Feb":
        return 2;
      case "Mar":
        return 3;
      case "Apr":
        return 4;
      case "May":
        return 5;
      case "Jun":
        return 6;
      case "Jul":
        return 7;
      case "Aug":
        return 8;
      case "Sep":
        return 9;
      case "Oct":
        return 10;
      case "Nov":
        return 11;
      case "Dec":
        return 12;
      default:
        return -1;
    }
  }

  private Integer getDate(String date) {
    String[] splitdate = date.split("-");
    int actualDate = Integer.parseInt(splitdate[0]);
    int actualYear = Integer.parseInt(splitdate[2]);
    int actualMonth = toMonth(splitdate[1]);
    if (actualYear <= LocalDate.now().getYear() % 100) {
      actualYear = LocalDate.now().getYear() / 100 * 100 + actualYear;
    } else {
      actualYear = (LocalDate.now().getYear() / 100 - 1) * 100 + actualYear;
    }
    return (actualYear * 100 + actualMonth) * 100 + actualDate;
  }

}
