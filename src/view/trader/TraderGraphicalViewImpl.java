package view.trader;


import java.awt.*;
import java.util.Map;

import javax.swing.*;

/**
 * Graphical view for the stock trader application.
 */
public class TraderGraphicalViewImpl extends JFrame implements TraderGraphicalView {
  private TraderGraphicalViewPanel graphPanel;

  public TraderGraphicalViewImpl() {
    super();
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);
  }

  public void setupPanel() {
    if (this.graphPanel != null) {
      this.remove(this.graphPanel);
    }
    this.graphPanel = new TraderGraphicalViewPanel();
    this.add(graphPanel);
    this.pack();
    this.revalidate();
    this.repaint();
  }

  @Override
  public void plotRecord(String name, Map<String, Double> data) {
    //TODO: put logic for calculating scale in the view
    this.graphPanel.plotRecord(name, data);
    this.pack();
    this.revalidate();
    this.repaint();
  }

  public void exit() {
    System.exit(0);
  }
}
