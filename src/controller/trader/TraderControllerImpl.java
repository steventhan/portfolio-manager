package controller.trader;

import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

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

  @Override
  public void start() throws Exception {
    Scanner sc = new Scanner(this.in);
    Map<String, Integer> basket;

    while (true) {
      this.view.printMenu(this.model.getMenuOptions());
      switch (sc.nextLine()) {
        case "c":
          this.view.append("Enter stock basket name: ");
          try {
            this.model.createStockBasket(sc.nextLine());
          } catch (IllegalArgumentException e) {
            this.view.append(e.getMessage() + "\n");
          }
          break;

        case "a":
          this.view.append("Enter basket name to add: ");
          String basketName = sc.nextLine();

          this.view.append("Enter stock symbol: ");
          String symbol = sc.nextLine();
          this.view.append("Amount: ");
          int amount = Integer.valueOf(sc.nextLine());

          this.model.addStockToBasket(basketName, symbol, amount);
          break;

        case "p":
          this.view.append("Enter basket name to print: ");
          basket = this.model.getBasketContentByName(sc.nextLine());
          if (basket == null) {
            this.view.append("Basket not found\n");
            break;
          }
          this.view.printBasket(basket);
          this.view.append("\n");
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
