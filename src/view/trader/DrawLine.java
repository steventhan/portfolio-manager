package view.trader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
    repaint(); //refresh the scFeen
  }

  public static void main(String[] args) {
    DrawLine mainWindow = new DrawLine();

    Timer timer = new Timer(1000,
            (ActionEvent e) -> mainWindow.incrementCount());
    timer.start();
  }

}

class DrawPanel extends JPanel {

  private int count;
  private List<Point> points;

  public DrawPanel() {
    super();
    //set background to white
    this.setBackground(Color.WHITE);
    count = 0;
    points = new ArrayList<Point>();
  }

  public void increment() {
    count++;
    points.add(new Point((int) (Math.random() * this.getWidth()), (int) (Math.random() * this
            .getHeight())));
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(600, 600);
  }

  protected void paintComponent(Graphics g) {
    //call its default implementation, let it do its thing
    //one of the things its default implementation does it to clear the screen
    //thus everything in this method is drawn starting from a blank screen
    super.paintComponent(g);

    //the Graphics g object has already been set up. You can use this to draw
    //various shapes

    Graphics2D g2d = (Graphics2D) g;

    //set color to black
    g2d.setColor(Color.BLACK);
    //TODO: Grow legend taller with added symbols
    //TODO: Legend is 100px wide right now
    //TODO: String padding is 15px
//    g2d.drawLine(50, 400, 50, 450);
//    g2d.drawLine(50, 450, 150, 450);
//    g2d.drawLine(50, 400, 150, 400);
//    g2d.drawLine(150, 400, 150, 450);
    g2d.drawRect(50, 400, 100, 50);

    //write some text
    g2d.setColor(Color.BLACK);
    g2d.drawString("String 1", 100, 415);
    g2d.drawString("String 2", 100, 430);
    g2d.drawString("String 3", 100, 445);

    //TODO: pick unused colors for newly added symbols
    g2d.setColor(Color.RED);
    g2d.drawLine(60, 415, 75, 415);
    g2d.setColor(Color.ORANGE);
    g2d.drawLine(60, 430, 75, 430);
    g2d.setColor(Color.GREEN);
    g2d.drawLine(60, 445, 75, 445);

    g2d.setColor(Color.BLACK);
    for (Point p : points) {
      g2d.fillOval(p.x, p.y, 10, 10);
    }
  }
}

