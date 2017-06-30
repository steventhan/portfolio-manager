package view.trader;

import java.io.IOException;
import java.util.Map;

public interface TraderTextView {

  /**
   * Append the text the the output stream.
   *
   * @param text the text to be appended
   * @throws IOException when output stream cannot take in the text
   */
  void append(String text) throws IOException;

  /**
   * Sends all baskets details to output stresam.
   *
   * @param baskets the data to be sent
   * @throws Exception when the data cannot be sent
   */
  void printAllBaskets(Map<String, Map<String, Integer>> baskets) throws Exception;

  /**
   * Sends one basket detail to output stream.
   *
   * @param basket the basket data to be sent
   * @throws Exception when the data cannot be sent
   */
  void printBasket(Map<String, Integer> basket) throws Exception;

  /**
   * Sends the main menu data to output stream.
   *
   * @throws Exception the data cannot be sent
   */
  void printMenu() throws Exception;

}
