package view.trader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Interface for View.
 */
public interface TraderView {
  String getViewType();
  void append(String text) throws IOException;
  void showMainMenu(List<String> options) throws Exception;
  void showBasket(Map<String, Integer> basket) throws Exception;
  void exit();
}