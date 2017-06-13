import org.junit.Before;
import org.junit.Test;

import util.NewStockRetriever;
import util.WebRetrieverSingleton;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by matthiasdenu on 6/12/2017.
 */
public class StockBasketTest {

  NewStockRetriever retriever = WebRetrieverSingleton.getInstance();
  IStock emptyBasket;
  IStock fullBasket;

  @Before
  public void setup() throws Exception {
    emptyBasket = new StockBasket();

    // using HashMap because, order doesn't matter
    Map<StockSingle, Integer> temp = new HashMap<>();
    temp.put(new StockSingle("MMM"), 32800);
    temp.put(new StockSingle("ATVI"), 357800);
    temp.put(new StockSingle("ADBE"), 101300);
    temp.put(new StockSingle("A"), 26300);
    temp.put(new StockSingle("VZ"), 387500);
    temp.put(new StockSingle("UPS"), 57000);

    fullBasket = new StockBasket(temp);

  }



  @Test
  public void testConstructorLegal() {

  }

  @Test
  public void testConstructorIllegal() {

  }

  @Test
  public void testGetPriceOnDay() throws Exception {

    System.out.format("%f", fullBasket.getPriceOnDay("2017-06-13"));

    //TODO: 11:52am  2017-06-13 returns 0.0
  }

  @Test
  public void testTrendsUp() throws Exception {
  }

}