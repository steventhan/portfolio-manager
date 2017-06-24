package view.trader;

import java.io.IOException;
import java.util.Map;

/**
 * Implementation of the graphical view per the Assignment 6 instructions.
 */
public class TraderViewImpl implements TraderView {

  //TODO: use composition and delegation to implement all methods in TraderView interface
  private TraderGraphicalView graphicalView;
  private TraderTextView textView;

  public TraderViewImpl(TraderGraphicalView tgv, TraderTextView ttv) {
    this.graphicalView = tgv;
    this.textView = ttv;
  }

  @Override
  public void plotRecord(String name, Map<String, Double> data) {
    this.graphicalView.plotRecord(name, data);
  }

  @Override
  public void exit() {
    this.graphicalView.exit();
  }

  @Override
  public void printBasket(Map<String, Integer> basket) throws Exception {
    this.textView.printBasket(basket);
  }

  @Override
  public void printMenu() throws Exception {
    this.textView.printMenu();
  }

  /**
   * Appends text to the output.
   * This method is used for displaying very simple messages in the output, like true/false or
   * warnings for the user.
   *
   * @param text to display.
   * @throws IOException if there is one.
   */
  @Override
  public void append(String text) throws IOException {
    this.textView.append(text);
  }

  @Override
  public void printAllBaskets(Map<String, Map<String, Integer>> baskets) throws Exception {
    this.textView.printAllBaskets(baskets);
  }

  @Override
  public void setupPanel(double highestPrice) {
    this.graphicalView.setupPanel(highestPrice);
  }
}
