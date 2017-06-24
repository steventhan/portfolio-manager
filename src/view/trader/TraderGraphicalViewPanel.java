package view.trader;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map;

import javax.swing.JPanel;

/**
 * Created by steven on 21/06/2017.
 */
public class TraderGraphicalViewPanel extends JPanel {
  private TraderGraph graph;
  private TraderGraphLegend legend;

  private static final int width = 1100;
  private static final int height = 700;
  private static final int xOffset = 50;
  private static final int yOffset = 0;
  private static final Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
          Color.BLUE, Color.MAGENTA, Color.PINK, Color.CYAN, Color.GRAY};
  private int colorIndx;

  /**
   * Constructs the main panel for the graphical view.
   *
   * @param highestPrice used to scale window.
   */
  public TraderGraphicalViewPanel(double highestPrice) {
    super();
    this.graph = new TraderGraph(this.getPreferredSize().width - 200,
            this.getPreferredSize().height - 100, highestPrice);
    this.legend = new TraderGraphLegend(this.getPreferredSize().width
            - this.graph.getWidth() - this.xOffset - 10,
            this.graph.getHeight() - this.yOffset);
    this.colorIndx = 0;
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(width, height);
  }

  /**
   * Plots a record on the graphical view.
   * @param name name of record.
   * @param data record's data.
   */
  public void plotRecord(String name, Map<String, Double> data) {
    this.legend.addLegendItem(name, colors[this.colorIndx]);
    this.graph.plotRecord(data, colors[this.colorIndx]);
    this.colorIndx = (1 + this.colorIndx) % colors.length;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(this.graph, xOffset, yOffset, this);
    g.drawImage(this.legend, xOffset + this.graph.getWidth() + 10, yOffset, this);
  }
}
