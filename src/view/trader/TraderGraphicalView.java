package view.trader;

import java.util.Map;

/**
 * Graphical view for the stock trader application.
 */
public class TraderGraphicalView implements ITraderGraphicalView {

  private DrawStock stockDrawer;

  @Override
  public void plotRecord(String name, Map<String, Double> data) {
    //TODO: put logic for calculating scale in the view
  }

  public void exit() {
    System.exit(0);
  }


}
