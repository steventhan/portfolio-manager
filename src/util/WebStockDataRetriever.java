//TODO: change access back after testing
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

  private final WebStockDataRetriever instance = new WebStockDataRetriever();

  private WebStockDataRetriever() {

  }

  public WebStockDataRetriever getStockDataRetriever() {
    return this.instance;
  }

  public double getCurrentPrice(String stockSymbol) throws Exception {
    URL url = new URL("https://download.finance.yahoo.com/d/quotes.csv?" +
            "s=" + stockSymbol + "&f=l1&e=.csv");

    String output = new Scanner(url.openStream()).next();


    return Double.parseDouble(output);
  }

  public String getName(String stockSymbol) throws Exception {
    URL url = new URL("https://download.finance.yahoo.com/d/quotes.csv?" +
            "s=" + stockSymbol + "&f=n&e=.csv");

    String output = new Scanner(url.openStream()).next();


    return output;
  }

  //TODO: if from same as to date only one record returned
  public Map<Integer, PriceRecord> getHistoricalPrices(
          String stockSymbol,
          int fromDate, //TODO: be careful 04-Jun-17
          int fromMonth,
          int fromYear,
          int toDate,
          int toMonth,
          int toYear) //TODO: 2017 (maybe just 17)
          throws
          Exception {


    URL url = new URL("https://www.google" +
            ".com/finance/historical?output=csv&q=" + stockSymbol + "&startdate=" +
            fromMonth + "+" +
            fromDate + "+" +
            fromYear +
            "&enddate=" +
            toMonth + "+" +
            toDate + "+" +
            toYear);

    String output = "";
    Map<Integer, PriceRecord> prices = new TreeMap<Integer, PriceRecord>();
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

  //TODO: this is just a helper, shouldn't have to call directly
  public int toMonth(String month) {
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

  //TODO: this is just a helper, shouldn't have to call directly
  public Integer getDate(String date) {
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
