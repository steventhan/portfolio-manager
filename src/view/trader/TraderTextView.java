package view.trader;

import java.io.IOException;
import java.util.Map;

public interface TraderTextView {
  void printBasket(Map<String, Integer> basket) throws Exception;
  void printMenu() throws Exception;
  void append(String text) throws IOException;
}
