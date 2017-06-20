package model.trader;

import java.util.List;

/**
 * Class to keep track of what stocks/baskets should be plotted. Iterate through this to plot each
 * item. Remove items when you no longer want them to be plotted.
 */
public class StockRecordsImpl {
  //TODO: pass StockRecordsImpl from model -> controller and possibly to view to plot
  List<StockBasket> baskets;
  List<StockSingle> stocks;
}
