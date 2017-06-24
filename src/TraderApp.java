import controller.trader.TraderControllerImpl;
import model.trader.TraderModelImpl;
import view.trader.TraderGraphicalViewImpl;
import view.trader.TraderTextViewImpl;
import view.trader.TraderViewImpl;

/**
 * Main class for the tader application.
 */
public class TraderApp {
  /**
   * Main method to start the trader application and hand control over to the controller.
   *
   * @param args unused.
   * @throws Exception described by its message.
   */
  public static void main(String[] args) throws Exception {
    new TraderControllerImpl(new TraderModelImpl(),
            new TraderViewImpl(new TraderGraphicalViewImpl(),
                    new TraderTextViewImpl(System.out))).start();
  }
}