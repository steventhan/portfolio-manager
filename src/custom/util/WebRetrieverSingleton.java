package custom.util;


import util.WebStockDataRetriever;

/**
 * Singleton web stock data retriever.
 */
public class WebRetrieverSingleton extends WebStockDataRetriever implements NewStockRetriever {
  private static WebRetrieverSingleton ourInstance = new WebRetrieverSingleton();
  public static WebRetrieverSingleton getInstance() {
    return ourInstance;
  }
  private WebRetrieverSingleton() {
  }
}
