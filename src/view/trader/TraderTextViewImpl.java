package view.trader;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Map;

/**
 * Created by steven on 17/06/2017.
 */
public class TraderTextViewImpl implements TraderTextView {
  private final Appendable out;

  /**
   * Instantiates a trader text view.
   *
   * @param out output for view.
   */
  public TraderTextViewImpl(Appendable out) {
    this.out = out;
  }

  /**
   * Appends text to the output.
   *
   * @param text to be appended.
   * @throws IOException described by its message.
   */
  @Override
  public void append(String text) throws IOException {
    this.out.append(text);
  }

  /**
   * Prints the menu for the user.
   *
   * @throws Exception described by its message.
   */
  @Override
  public void printMenu() throws Exception {
    this.out.append("c - Create new stock or basket\n");
    this.out.append("a - Add new share to a basket\n");
    this.out.append("p - Print the stock basket\n");
    this.out.append("t - Trend of stock\n");
    this.out.append("l - Plot\n");
    this.out.append("r - Remove stock entity by name\n");
    this.out.append("q - Quit\n");
    this.out.append("Select: ");
  }

  /**
   * Prints a representation of a stock basket.
   *
   * @param basket to be printed.
   * @throws IOException described by its message.
   */
  @Override
  public void printBasket(Map<String, Integer> basket) throws IOException {
    basket.forEach((key, value) -> {
      try {
        this.out.append(String.format("%s: %d share%s\n", key, value, value > 1 ? "s" : ""));
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
    });
  }

  /**
   * Prints all baskets passed to it.
   *
   * @param baskets to be printed.
   * @throws Exception described by its message.
   */
  public void printAllBaskets(Map<String, Map<String, Integer>> baskets) throws Exception {
    baskets.keySet().forEach(k -> {
      try {
        this.printBasket(baskets.get(k));
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
    });
  }
}