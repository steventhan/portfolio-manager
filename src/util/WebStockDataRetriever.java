package util;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.File;

/**
 * This class represents a stock retriever module. It is a singleton, and so to
 * get the one (and only) object call getStockDataRetriever().
 */
public class WebStockDataRetriever implements StockDataRetriever {

  private static final WebStockDataRetriever instance = new WebStockDataRetriever();

  /**
   * Private constructor.
   */
  private WebStockDataRetriever() {

  }

  /**
   * Returns WebStockDataRetriever singleton.
   * @return WebStockDataRetriever singleton.
   */
  public static WebStockDataRetriever getStockDataRetriever() {
    return instance;
  }

  public double getCurrentPrice(String stockSymbol) throws IOException {
    URL url = new URL("https://download.finance.yahoo.com/d/quotes.csv?" +
            "s=" + stockSymbol + "&f=l1&e=.csv");

    String output = new Scanner(url.openStream()).next();


    return Double.parseDouble(output);
  }

  public String getName(String stockSymbol) throws IOException {
    URL url = new URL("https://download.finance.yahoo.com/d/quotes.csv?" +
            "s=" + stockSymbol + "&f=n&e=.csv");

    String output = new Scanner(url.openStream()).next();

    return output;
  }

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

    // save a file for this query to refer back to later
    File outFile = new File(stockSymbol + "-"
            + LocalDateTime.now().toString().replaceAll("[:.]", "-") + ".txt");
    outFile.createNewFile();
    FileWriter outFileWriter = new FileWriter(outFile);

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
      outFileWriter.append(output).append("\n");
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
    outFileWriter.close();
    return prices;

  }

  /**
   * Converts string to month.
   * @param month .
   * @return .
   */
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

  /**
   * Get date.
   * @param date .
   * @return .
   */
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
