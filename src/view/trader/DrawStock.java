package view.trader;

import java.awt.event.ActionEvent;

import javax.swing.*;

//TODO: Matthias fix this!

/**
 * Handles drawing a single stock.
 */
public class DrawStock extends JFrame {
  private DrawPanel drawPanel;

  public DrawStock() {
    super();
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    drawPanel = new DrawPanel();
    this.add(drawPanel);
    this.pack();
    this.setVisible(true);
  }

  public void step() {
    //TODO: change this
    drawPanel.increment();
    repaint();
  }

  public static void main(String[] args) {
    boolean quit = false;

    //TODO: find the largest date range and split the window according to that
    DrawStock mainWindow = new DrawStock();
    Timer timer = new Timer(1000, (ActionEvent e) -> mainWindow.step());
    timer.start();

  }

}

