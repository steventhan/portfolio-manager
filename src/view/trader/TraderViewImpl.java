package view.trader;

import java.io.IOException;
import java.util.Map;

/**
 * Implementation of the graphical view per the Assignment 6 instructions.
 */
public class TraderViewImpl implements TraderView {

  private TraderGraphicalView graphicalView;
  private TraderTextView textView;

  /**
   * Constructs a TraderViewImpl object given the TraderGraphicalView and TraderTextView objects.
   *
   * @param tgv the TraderGraphicalView to be used
   * @param ttv the TraderTextView to be used
   */
  public TraderViewImpl(TraderGraphicalView tgv, TraderTextView ttv) {
    this.graphicalView = tgv;
    this.textView = ttv;
  }

  /**
   * Plots a stock or basket onto the graphical view's panel.
   *
   * @param name the name to be used in the legend
   * @param data the data to be plotted
   */
  @Override
  public void plotRecord(String name, Map<String, Double> data) {
    this.graphicalView.plotRecord(name, data);
  }


  /**
   * Disposes the JFrame and exit gracefully.
   */
  @Override
  public void exit() {
    this.graphicalView.exit();
  }

  /**
   * Sends one basket detail to text view's output stream.
   *
   * @param basket the basket data to be sent
   * @throws Exception when the data cannot be sent
   */
  @Override
  public void printBasket(Map<String, Integer> basket) throws Exception {
    this.textView.printBasket(basket);
  }

  /**
   * Sends the main menu data to text view's output stream.
   *
   * @throws Exception the data cannot be sent
   */
  @Override
  public void printMenu() throws Exception {
    this.textView.printMenu();
  }

  /**
   * Appends text to the text view's output.
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

  /**
   * Sends all baskets details to text view's output stresam.
   *
   * @param baskets the data to be sent
   * @throws Exception when the data cannot be sent
   */
  @Override
  public void printAllBaskets(Map<String, Map<String, Integer>> baskets) throws Exception {
    this.textView.printAllBaskets(baskets);
  }

  /**
   * Sets up the JFrame and JPanel that can be used to plot.
   *
   * @param highestPrice the higest price on the y-axis
   * @param fromDate the start date
   * @param toDate the end date
   */
  @Override
  public void setupPanel(double highestPrice, String fromDate, String toDate) {
    this.graphicalView.setupPanel(highestPrice, fromDate, toDate);
  }
}
