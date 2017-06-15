package custom.util;

import util.WebStockDataRetriever;

/**
 * Singleton web stock data retriever.
 */
public class WebRetrieverSingleton extends WebStockDataRetriever implements NewStockRetriever {
  private static WebRetrieverSingleton ourInstance = new WebRetrieverSingleton();

  /**
   * Gets the current running (and only) instance of WebRetrieverSingleton.
   *
   * @return the current running WebRetriever
   */
  public static WebRetrieverSingleton getInstance() {
    return ourInstance;
  }
  private WebRetrieverSingleton() {
  }
}
