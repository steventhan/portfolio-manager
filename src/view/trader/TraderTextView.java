package view.trader;

import java.io.IOException;
import java.util.Map;

public interface TraderTextView {

  void append(String text) throws IOException;

  void printAllBaskets(Map<String, Map<String, Integer>> baskets) throws Exception;

  void printBasket(Map<String, Integer> basket) throws Exception;

  void printMenu() throws Exception;

}
