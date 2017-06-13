package util;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

/**
 * Singleton data retriever to get stock data from a local file. This is class is primarily used for
 * testing.
 */
public class FileStockDataRetriever implements StockDataRetriever {

  private static final FileStockDataRetriever instance = new FileStockDataRetriever();
  private String filename;


  private FileStockDataRetriever() {

  }

  public FileStockDataRetriever getFileStockDataRetriever() { return instance; }


  /**
   * Returns last closing price read from file.
   * @param stockSymbol symbol.
   * @return
   * @throws Exception
   */
  @Override
  public double getCurrentPrice(String stockSymbol) throws Exception {
    //TODO: finish after storing a historical prices query to a file
    File file = new File(this.filename);
    Scanner sc = new Scanner(file).useDelimiter(",");

    String output = "";

    return Double.parseDouble(output);
  }

  @Override
  public String getName(String stockSymbol) throws Exception {
    return null;
  }

  @Override
  public Map<Integer, PriceRecord> getHistoricalPrices(String stockSymbol, int fromDate, int fromMonth, int fromYear, int toDate, int toMonth, int toYear) throws Exception {
    return null;
  }
}
