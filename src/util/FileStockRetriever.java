package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

/**
 * Singleton data retriever to get stock data from a local file. This is class is primarily used for
 * testing.
 */
public class FileStockRetriever implements NewStockRetriever {

  private static final FileStockRetriever instance = new FileStockRetriever();
  private static final String filename = "MSFT2017-06-13T10_06_31_370.txt";

  private FileStockRetriever() {

  }

  public FileStockRetriever getFileStockRetriever() { return instance; }

  /**
   * Returns last closing price read from file.
   * @param stockSymbol symbol.
   * @return
   * @throws Exception
   */
  @Override
  public double getCurrentPrice(String stockSymbol) throws FileNotFoundException {
    //TODO: finish after storing a historical prices query to a file
    //TODO: figure out what WebRetrieverSingleton.getCurrentPrice() returns if stock symbol not found
    //TODO: figure out what WebRetrieverSingleton.getCurrentPrice() returns if price for requested day not found
    File file = new File(this.filename);
    Scanner sc = new Scanner(file).useDelimiter("\\n");
    String[] data;
    while(sc.hasNextLine()) {
      data = sc.next().split(",");

    }


    String output = "";

    return Double.parseDouble(output);
  }

  @Override
  public String getName(String stockSymbol) {
    return null;
  }

  @Override
  public Map<Integer, PriceRecord> getHistoricalPrices(String stockSymbol, int fromDate, int fromMonth, int fromYear, int toDate, int toMonth, int toYear) throws Exception {
    return null;
  }
}
