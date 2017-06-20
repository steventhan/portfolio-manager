package view.trader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by matthiasdenu on 6/20/2017.
 */
public interface TraderTextView {
  void printBasket(Map<String, Integer> basket) throws Exception;
  void printMenu(List<String> options) throws Exception;
  void append(String text) throws IOException;
}
