package view.trader;

import java.awt.*;
import java.util.Map;

import javax.swing.*;

/**
 * Created by steven on 21/06/2017.
 */
public class TraderGraphicalViewPanel extends JPanel {
  private TraderGraph graph;
  private TraderGraphLegend legend;
  private double highestPrice;
  private String fromDate;
  private String toDate;

  private static final int width = 1100;
  private static final int height = 700;
  private static final int xOffset = 50;
  private static final int yOffset = 0;
  private final static Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
          Color.BLUE, Color.MAGENTA, Color.PINK, Color.CYAN, Color.GRAY};
  private int colorIndx;

  public TraderGraphicalViewPanel(double highestPrice, String fromDate, String toDate) {
    super();
    this.graph = new TraderGraph(this.getPreferredSize().width - 200,
            this.getPreferredSize().height - 100, highestPrice);
    this.legend = new TraderGraphLegend(this.getPreferredSize().width
            - this.graph.getWidth() - this.xOffset - 10,
            this.graph.getHeight() - this.yOffset);
    this.colorIndx = 0;
    this.highestPrice = highestPrice;
    this.fromDate = fromDate;
    this.toDate = toDate;
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(width, height);
  }

  public void plotRecord(String name, Map<String, Double> data) {
    this.legend.addLegendItem(name, colors[this.colorIndx]);
    this.graph.plotRecord(data, colors[this.colorIndx]);
    this.colorIndx = (1 + this.colorIndx) % colors.length;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawString(String.valueOf(this.highestPrice), 10, 20);
    g.drawString("0", 30, this.graph.getHeight() - 10);
    g.drawString(this.fromDate, xOffset - 20, this.graph.getHeight() + 20);
    g.drawString(this.toDate, xOffset + this.graph.getWidth()
            - 50, this.graph.getHeight() + 20);
    g.drawImage(this.graph, xOffset, yOffset, this);
    g.drawImage(this.legend, xOffset + this.graph.getWidth() + 10, yOffset, this);
  }
}
