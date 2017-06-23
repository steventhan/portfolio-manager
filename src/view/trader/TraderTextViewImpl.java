package view.trader;

import java.io.IOException;
import java.util.Map;

/**
 * Created by steven on 17/06/2017.
 */
public class TraderTextViewImpl implements TraderTextView {
  private Appendable out;

  public TraderTextViewImpl(Appendable out) {
    this.out = out;
  }

  @Override
  public void append(String text) throws IOException {
    this.out.append(text);
  }

  @Override
  public void printMenu() throws Exception {
    this.out.append("c - Create new stock or basket\n");
    this.out.append("a - Add new share to a basket\n");
    this.out.append("p - Print the stock basket\n");
    this.out.append("t - Trend of stock\n");
    this.out.append("l - Plot\n");
    this.out.append("q - Quit\n");
    this.out.append("Select: ");
  }

  @Override
  public void printBasket(Map<String, Integer> basket) {
    basket.forEach((key, value) -> System.out.printf("%s: %d share%s\n",
            key, value, value > 1 ? "s" : ""));
  }

  public void printAllBaskets(Map<String, Map<String, Integer>> baskets) {
    baskets.keySet().forEach(k -> this.printBasket(baskets.get(k)));
  }

}