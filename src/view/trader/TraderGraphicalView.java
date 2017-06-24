package view.trader;

import java.util.Map;


/**
 * An interface for Trader Application's graphical view.
 */
public interface TraderGraphicalView {

  /**
   * Sets up the JFrame and JPanel that can be used to plot.
   *
   * @param highestPrice the higest price on the y-axis
   * @param fromDate the start date
   * @param toDate the end date
   */
  void setupPanel(double highestPrice, String fromDate, String toDate);

  /**
   * Plots a stock or basket onto the panel.
   *
   * @param name the name to be used in the legend
   * @param data the data to be plotted
   */
  void plotRecord(String name, Map<String, Double> data);

  /**
   * Disposes the JFrame and exit gracefully.
   */
  void exit();

}
