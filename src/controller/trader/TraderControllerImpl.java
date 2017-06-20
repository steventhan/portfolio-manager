package controller.trader;

import java.io.IOException;
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

  public TraderControllerImpl(TraderModel model, TraderView view) {
    this(model, view, new InputStreamReader(System.in));
  }

  public TraderControllerImpl(TraderModel model, TraderView view, Readable in) {
    this.model = model;
    this.view = view;
    this.in = in;
  }

  private void createNewStockBasket(Scanner sc) throws Exception {
    this.view.append("Enter stock basket name: ");
    try {
      this.model.createStockBasket(sc.nextLine());
    } catch (IllegalArgumentException e) {
      this.view.append(e.getMessage() + "\n");
    }
  }

  private void addNewStockToBasket(Scanner sc) throws Exception {
    this.view.append("Enter basket name to add: ");
    String basketName = sc.nextLine();

    this.view.append("Enter stock symbol: ");
    String symbol = sc.nextLine();
    this.view.append("Amount: ");
    int amount = Integer.valueOf(sc.nextLine());

    this.model.addStockToBasket(basketName, symbol, amount);
  }

  private void printStockBasket(Scanner sc) throws Exception {
    Map<String, Integer> basket;
    this.view.append("Enter basket name to print: ");
    basket = this.model.getBasketContentByName(sc.nextLine());
    if (basket == null) {
      this.view.append("Basket not found\n");
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
    } catch (StockPriceNotFound e) {
      this.view.append("Trend cannot be determined. Try again");
    } catch (IllegalArgumentException e) {
      this.view.append("Invalid input. Try again");
    }
    this.view.append("\n");
  }

  @Override
  public void start() throws Exception {
    Scanner sc = new Scanner(this.in);

    while (true) {
      this.view.printMenu();
      switch (sc.nextLine()) {
        case "c":
          this.createNewStockBasket(sc);
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

        case "g":
          this.start();
          break;

        case "q":
          this.view.exit();
      }
      this.view.append("\n");
    }
  }
}
