import controller.trader.TraderControllerImpl;
import model.trader.TraderModelImpl;
import view.trader.TraderTextView;

/**
 * Created by steven on 18/06/2017.
 */
public class TraderApp {
  public static void main(String[] args) throws Exception {
    new TraderControllerImpl(new TraderModelImpl(), new TraderTextView(System.out)).start();
  }
}