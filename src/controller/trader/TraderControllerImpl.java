package controller.trader;

import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

import custom.util.StockPriceNotFound;
import model.trader.TraderModel;
import view.trader.TraderView;

/**
 * An implementation of TraderController interface.
 */
public class TraderControllerImpl implements TraderController {
  private final TraderModel model;
  private final TraderView view;
  private final Readable in;
  private double currentGraphHighestPrice;
  private String fromDate;
  private String toDate;

  public TraderControllerImpl(TraderModel model, TraderView view) {
    this(model, view, new InputStreamReader(System.in));
  }

  public TraderControllerImpl(TraderModel model, TraderView view, Readable in) {
    this.model = model;
    this.view = view;
    this.in = in;
    this.currentGraphHighestPrice = 0;
    this.fromDate = null;
    this.toDate = null;
  }

  private void createNewStockOrBasket(Scanner sc) throws Exception {
    this.view.append("Enter stock symbol or basket name \n" +
            "(if the string is a valid \n" +
            "stock symbol, it's assumed to be stock, \n" +
            "otherwise a basket will be created): ");
    String basketOrStockName = sc.nextLine();
    try {
      this.model.createStockBasket(basketOrStockName);
    } catch (IllegalArgumentException e) {
      this.view.append(e.getMessage() + "\n");
    }

    boolean stockCreated = false;
    try {
      this.model.getBasketContentByName(basketOrStockName);
    } catch (IllegalArgumentException e) {
      stockCreated = true;
    }

    if (this.fromDate != null && this.toDate != null && stockCreated) {
      this.plotOneAddionalRecord(basketOrStockName);
    }
  }

  private void addNewStockToBasket(Scanner sc) throws Exception {
    this.view.append("Enter basket name to add: ");
    String basketName = sc.nextLine();
    try {
      this.model.getBasketContentByName(basketName);
    } catch (IllegalArgumentException e) {
      this.view.append(e.getMessage() + "\n");
      return;
    }

    this.view.append("Enter stock symbol: ");
    String symbol = sc.nextLine();
    this.view.append("Amount: ");
    int amount = Integer.valueOf(sc.nextLine());

    this.model.addStockToBasket(basketName, symbol, amount);
    if (this.fromDate != null && this.toDate != null) {
      this.plotOneAddionalRecord(basketName);
    }
  }

  private void printStockBasket(Scanner sc) throws Exception {
    Map<String, Integer> basket;
    this.view.append("Enter basket name to print: ");
    try {
      basket = this.model.getBasketContentByName(sc.nextLine());
    } catch (IllegalArgumentException e) {
      this.view.append(e.getMessage() + "\n");
      return;
    }
    this.view.printBasket(basket);
    this.view.append("\n");
  }

  private void stockOrBasketTrendsUp(Scanner sc) throws Exception {
    this.view.append("Enter basket name or stock symbol: ");
    String symbolOrBasketName = sc.nextLine();
    this.view.append("From (yyyy-mm-dd): ");
    String from = sc.nextLine();
    this.view.append("To (yyyy-mm-dd): ");
    String to = sc.nextLine();
    try {
      boolean trendingUp = this.model.trendsUp(symbolOrBasketName, from, to);
      this.view.append(String.format("This basket or stock is trending %s.", trendingUp
              ? "up" : "down"));
    } catch (StockPriceNotFound | IllegalArgumentException e) {
      this.view.append(e.getMessage());
    }
    this.view.append("\n");
  }

  private void plotOneAddionalRecord(String basketOrStockName) throws Exception {
    Map<String, Double> priceData;
    Map<String, Double> avg50;
    Map<String, Double> avg200;
    priceData = this.model.getPlotDataForOne(basketOrStockName, this.fromDate, this.toDate);
    avg50 = this.model.getMovingAveragesForOne(basketOrStockName, this.fromDate, this.toDate, 50);
    avg200 = this.model.getMovingAveragesForOne(basketOrStockName, this.fromDate, this.toDate, 200);

    if (Double.compare(this.model.getHighestPrice(), this.currentGraphHighestPrice) == 0) {
      this.view.plotRecord(basketOrStockName, priceData);
      this.view.plotRecord(basketOrStockName + "50", avg50);
      this.view.plotRecord(basketOrStockName + "200", avg200);
    } else {
      this.plotEverything();
    }
  }

  private void plotEverything() throws Exception {
    Map<String,Map<String, Double>> priceData;
    Map<String,Map<String, Double>> avg50;
    Map<String,Map<String, Double>> avg200;
    priceData = this.model.getPlotData(this.fromDate, this.toDate);
    avg50 = this.model.getMovingAveragesForAll(this.fromDate, this.toDate, 50);
    avg200 = this.model.getMovingAveragesForAll(this.fromDate, this.toDate, 200);
    this.currentGraphHighestPrice = this.model.getHighestPrice();
    this.view.setupPanel(this.currentGraphHighestPrice);

    for (String k : priceData.keySet()) {
      this.view.plotRecord(k, priceData.get(k));
      this.view.plotRecord(k + "50", avg50.get(k));
      this.view.plotRecord(k + "200", avg200.get(k));
    }
  }

  private void plot(Scanner sc) throws Exception {
    this.view.append("From (yyyy-mm-dd): ");
    this.fromDate = sc.nextLine();
    this.view.append("To (yyyy-mm-dd): ");
    this.toDate = sc.nextLine();
    this.plotEverything();
  }
  @Override
  public void start() throws Exception {
    Scanner sc = new Scanner(this.in);

    while (true) {
      this.view.printMenu();
      switch (sc.nextLine()) {
        case "c":
          this.createNewStockOrBasket(sc);
          break;

        case "a":
          this.addNewStockToBasket(sc);
          break;

        case "p":
          this.printStockBasket(sc);
          break;

        case "t":
          this.stockOrBasketTrendsUp(sc);
          break;

        case "l":
          this.plot(sc);
          break;

        case "q":
          this.view.exit();
      }
      this.view.append("\n");
    }
  }
}
