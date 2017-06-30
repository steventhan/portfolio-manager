package view.trader;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * The legend to be displayed with the graph.
 */
public class TraderGraphLegend extends BufferedImage {
  private int yInitial;

  /**
   * Constructs a TraderGraphLegend object.
   *
   * @param width the width of the legend
   * @param height the height of the legend
   */
  public TraderGraphLegend(int width, int height) {
    super(width, height, BufferedImage.TYPE_3BYTE_BGR);
    this.yInitial = this.getHeight();
  }

  /**
   * Adds an item to the legend.
   *
   * @param str   information about the item.
   * @param color color of string.
   */
  public void addLegendItem(String str, Color color) {
    Graphics2D g2d = this.createGraphics();
    this.yInitial = this.yInitial - 15;
    g2d.setColor(color);
    g2d.drawLine(0, this.yInitial - 5, 20, this.yInitial - 5);
    g2d.drawString(str,25, this.yInitial);
  }
}
