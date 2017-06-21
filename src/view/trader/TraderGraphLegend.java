package view.trader;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by steven on 21/06/2017.
 */
public class TraderGraphLegend extends BufferedImage {
  private int yInitial;

  public TraderGraphLegend(int width, int height) {
    super(width, height, BufferedImage.TYPE_3BYTE_BGR);
    this.yInitial = this.getHeight();
  }

  public void addLegendItem(String str, Color color) {
    Graphics2D g2d = this.createGraphics();
    this.yInitial = this.yInitial - 15;
    g2d.setColor(color);
    g2d.drawLine(0, this.yInitial - 5, 20, this.yInitial - 5);
    g2d.drawString(str,25, this.yInitial);
  }
}
