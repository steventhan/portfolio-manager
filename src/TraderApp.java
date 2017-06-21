import controller.trader.TraderControllerImpl;
import model.trader.TraderModelImpl;
import model.trader.TraderModelImpl2;
import view.trader.TraderGraphicalViewImpl;
import view.trader.TraderTextViewImpl;
import view.trader.TraderViewImpl;

/**
 * Created by steven on 18/06/2017.
 */
public class TraderApp {
  public static void main(String[] args) throws Exception {
    new TraderControllerImpl(new TraderModelImpl2(),
            new TraderViewImpl(new TraderGraphicalViewImpl(),
                    new TraderTextViewImpl(System.out))).start();
  }
}