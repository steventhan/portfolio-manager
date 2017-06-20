import org.junit.Test;

import java.util.Map;

import model.trader.StockBasket;
import model.trader.StockBasketImpl;
import model.trader.StockSingleImpl;
import model.trader.TraderModel;
import model.trader.TraderModelImpl;

import static org.junit.Assert.*;

/**
 * Created by matthiasdenu on 6/20/2017.
 */
public class TraderModelImplTest {
  @Test
  public void createStockBasket() throws Exception {
  }

  @Test
  public void addStockToBasket() throws Exception {
  }

  @Test
  public void remove() throws Exception {
  }

  @Test
  public void getBasketContentByName() throws Exception {
    TraderModel md = new TraderModelImpl();
    Map<String, Integer> temp;

    md.createStockBasket("test sb");
    temp = md.getBasketContentByName("test sb");
    assertEquals(0, temp.size());

    md.addStockToBasket("test sb", "AMZN", 3);
    temp = md.getBasketContentByName("test sb");
    assertEquals(1, temp.size());
  }

  @Test
  public void trendsUp() throws Exception {
  }

  @Test
  public void getAllBaskets() throws Exception {
  }

  @Test
  public void testToString() throws Exception {


  }
}