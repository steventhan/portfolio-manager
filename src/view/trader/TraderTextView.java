package view.trader;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;

/**
 * Created by steven on 17/06/2017.
 */
public class TraderTextView implements TraderView {
  private Appendable out;

  public TraderTextView(Appendable out) {
    this.out = out;
  }

  public void append(String text) throws IOException {
    this.out.append(text);
  }

  @Override
  public String getViewType() {
    return "text";
  }

  @Override
  public void showMainMenu(List<String> options) throws Exception {
    options.stream().forEach(o -> {
      try {
        this.out.append(o + "\n");
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
    });
    this.out.append("Select: ");
  }

  @Override
  public void showBasket(Map<String, Integer> basket) throws Exception {
    this.out.append(basket
            .entrySet()
            .stream()
            .map(e -> String.format("%s: %d share%s",
                    e.getKey(), e.getValue(), e.getValue() > 1 ? "s" : ""))
            .reduce("", (a, b) -> a + b)
    );
  }

  @Override
  public void exit() {
    System.exit(0);
  }
}