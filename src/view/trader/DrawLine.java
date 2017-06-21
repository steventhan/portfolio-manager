package view.trader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

/**
 * This class shows an example of creating a GUI using Java Swing. Feel free
 * to use and edit this code to suit your purpose
 *
 * It also breaks the tradition of putting main in a separate class by
 * putting main in this class itself, for brevity.
 */
public class DrawLine extends JFrame {
  private DrawPanel drawPanel;

  public DrawLine() {
    //call the constructor of JFrame, let it do what it does.
    super();
    //the X button should close this window, but not the entire program
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    //add the panel to this frame
    drawPanel = new DrawPanel();
    this.add(drawPanel);
    //resize this frame so that it is just big enough to hold the panel
    //the panel sets its own size by overriding getPreferredSize
    this.pack();
    //make the window visible. By default a window is invisible.
    this.setVisible(true);
  }

  public void incrementCount() {
    drawPanel.increment();
    repaint(); //refresh the screen
  }

  public static void main(String[] args) {
    DrawLine mainWindow = new DrawLine();

    Timer timer = new Timer(1000,
            (ActionEvent e) -> mainWindow.incrementCount());
    timer.start();
  }

}

class DrawPanel extends JPanel {

  private final static Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
          Color.BLUE, Color.MAGENTA, Color.PINK, Color.CYAN, Color.GRAY, Color.BLACK};

  private int colorIndex = 0;
  private int count;
  private List<ColorPoint> cpoints;

  public class ColorPoint extends Point {
    private Color color;

    ColorPoint(int x, int y, Color color) {
      super(x, y);
      this.color = color;
    }

    public Color getColor() {
      return new Color(this.color.getRGB());
    }

  }

  public DrawPanel() {
    super();
    //set background to white
    this.setBackground(Color.WHITE);
    count = 0;
    cpoints = new LinkedList<>();
  }

  public void increment() {
    count++;
    colorIndex = (1 + colorIndex) % colors.length;
    cpoints.add(new ColorPoint(this.getWidth() / 2, count * 10, colors[colorIndex]));
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(600, 600);
  }

  //TODO: make helpers
  //TODO: Grow legend taller with added symbols
  //TODO: Legend is 100px wide right now
  //TODO: String padding is 15px
  //TODO: pick unused colors for newly added symbols
  protected void paintComponent(Graphics g) {

    super.paintComponent(g); // Call the UI delegate's paint method

    Graphics2D g2d = (Graphics2D) g; // for convenience cast Graphics object as Graphics2D object

    // draw legend
    g2d.setColor(Color.BLACK);
    g2d.drawRect(50, 400, 100, 50);
    g2d.setColor(Color.BLACK);
    g2d.drawString("String 1", 100, 415);
    g2d.drawString("String 2", 100, 430);
    g2d.drawString("String 3", 100, 445);
    g2d.setColor(Color.RED);
    g2d.drawLine(60, 415, 75, 415);
    g2d.setColor(Color.ORANGE);
    g2d.drawLine(60, 430, 75, 430);
    g2d.setColor(Color.GREEN);
    g2d.drawLine(60, 445, 75, 445);

    // create a rotated transform and draw x-axis values
    Graphics2D gTransform = (Graphics2D) g;
    Font font = new Font(null, Font.PLAIN, 10);
    AffineTransform affineTransform = new AffineTransform();
    affineTransform.rotate(Math.toRadians(270), 0, 0);
    Font rotatedFont = font.deriveFont(affineTransform);
    gTransform.setFont(rotatedFont);


    //TODO this will probably be a list of records for stock prices
    for (int i = 0; i < cpoints.size(); i++) {
      ColorPoint p = cpoints.get(i);
      g2d.setColor(p.getColor());
      g2d.fillOval((int) p.getX(), (int) p.getY(), 10, 10);
      gTransform.drawString("A String", i * 10, 550);
    }
  }
}

