package view.trader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Created by steven on 21/06/2017.
 */
public class TraderGraph extends BufferedImage {

  private double highestPrice;

  public TraderGraph(int width, int height, double highestPrice) {
    super(width, height, BufferedImage.TYPE_3BYTE_BGR);
    this.highestPrice = highestPrice;
  }

  public void plotRecord(Map<String, Double> data, Color color) {
    Graphics2D g2d = this.createGraphics();
    float dateIncrement = ((float) this.getWidth()) / data.keySet().size();
    float i = 0;
    int prevX = 0;
    int prevY = 0;
    int currentX = 0;
    int currentY = 0;

    g2d.setColor(color);

    for (String date : data.keySet()) {
      currentX = Math.round(i);
      currentY = this.getHeight() - 10
              - (int) Math.round((data.get(date) / (this.highestPrice + 10))
              * (this.getHeight() - 10));
      g2d.fillOval(currentX, currentY, 5, 5);

      if (currentX != 0 && currentY != 0) {
        g2d.drawLine(prevX, prevY, currentX, currentY);
      }
      prevX = currentX;
      prevY = currentY;
      i += dateIncrement;
    }
  }
}
