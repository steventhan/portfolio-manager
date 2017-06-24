package view.trader;

import java.util.Map;


/**
 * Created by matthiasdenu on 6/20/2017.
 */
public interface TraderGraphicalView {
  void setupPanel(double highestPrice, String fromDate, String toDate);
  void plotRecord(String name, Map<String, Double> data);
  void exit();
}
