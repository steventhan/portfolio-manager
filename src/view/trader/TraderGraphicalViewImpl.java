package view.trader;

import java.util.Map;
import javax.swing.*;

/**
 * Graphical view for the stock trader application.
 */
public class TraderGraphicalViewImpl implements TraderGraphicalView {
  private TraderGraphicalViewPanel graphPanel;
  private JFrame frame;

  @Override
  public void setupPanel(double highestPrice, String fromDate, String toDate) {
    if (this.frame == null) {
      this.frame = new JFrame();
      this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.frame.setVisible(true);
    }
    if (this.graphPanel != null) {
      this.frame.remove(this.graphPanel);
    }
    this.graphPanel = new TraderGraphicalViewPanel(highestPrice, fromDate, toDate);
    this.frame.add(this.graphPanel);
    this.frame.pack();
    this.frame.revalidate();
    this.frame.repaint();

  }

  @Override
  public void plotRecord(String name, Map<String, Double> data) {
    //TODO: put logic for calculating scale in the view
    this.graphPanel.plotRecord(name, data);
    this.frame.pack();
    this.frame.revalidate();
    this.frame.repaint();
  }

  public void exit() {
    if (this.frame != null) {
      this.frame.dispose();
    }
  }
}
