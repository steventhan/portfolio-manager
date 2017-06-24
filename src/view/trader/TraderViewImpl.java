package view.trader;

import java.io.IOException;
import java.util.Map;

/**
 //TODO: update this description
 * A user is able to open a window and view plotted graphs of the prices of one or more baskets or
 * stocks created in the model. Specifically, the window shows the plots of the prices vs. dates in
 * a specified date range.
 *
 * PUT MORE DETAILS ABOUT THE IMPLEMENTATION OF THE DRAWING HERE
 *
 * The user is able to plot the closing prices of one or more stocks/baskets simultaneously.
 *
 * The user is able to plot the 50-day and/or 200-day averages of one or more stocks/baskets along
 * with the plot of their closing prices.
 *
 * The user is able to simultaneously plot the closing prices, the 50-day averages,
 * and the 200-day averages(or any combination of those things) for any stock or basket created in
 * the model.
 *
 * The graphical view has a set number of distinct colors for plots, so plot colors will repeat
 * after a certain number of plots have been added to the view. This should not be a problem if the
 * user is only planning to add less than 10 distinct plots. But if there are a high number of plots
 * and colors are repeated, the legend will help the user.
 *
 * The legend shows the color of the plot line for a stock symbol or basket along with a descriptive
 * string.
 *
 * A user is able to create a blank graphical view.
 * A user is able to add or remove plots to the graphical view.
 * Once shown, the graphical view will be visible unless the window is closed.
 * Any changes to what the graphical view shows should cause it to refresh automatically to show the
 * updated plots.
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
  public void setupPanel(double highestPrice, String fromDate, String toDate) {
    this.graphicalView.setupPanel(highestPrice, fromDate, toDate);
  }
}
