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

    g2d.setColor(color);

    for (String date : data.keySet()) {
      int y = this.getHeight() - 10
              - (int) Math.round((data.get(date) / this.highestPrice) * (this.getHeight() - 10));
      g2d.fillOval(Math.round(i), y, 5, 5);
      i += dateIncrement;
    }
  }
}
