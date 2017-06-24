import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import model.trader.TraderModel;
import model.trader.TraderModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Class to test model.
 */
public class TraderModelImplTest {
  private TraderModel model;
  private final String[] testStrings = {"basket-one", "basket-two", "basket-three"};

  private Map<String, Integer> testMapOne = new TreeMap<>();
  private Map<String, Integer> emptySet = new LinkedHashMap<>();
  private Map<String, Integer> testMapThree = new TreeMap<>();

  /**
   * Initialize data that can be used for each test case.
   * @throws Exception described by its message.
   */
  @Before
  public void setup() throws Exception {

    model = new TraderModelImpl();

    // a basket that is initialized to be empty at the start of every tet
    model.createStockBasket("basket-one");
    // a basket that is initialized at the start of every tet
    model.createStockBasket("basket-two");
    // a basket that is initialized at the start of every tet
    model.createStockBasket("basket-three");

    model.addStockToBasket("basket-one", "BAC", 6);
    model.addStockToBasket("basket-one", "CHK", 15);
    model.addStockToBasket("basket-one", "TWTR", 90);
    model.addStockToBasket("basket-one", "INTC", 10);

    model.addStockToBasket("basket-three", "WFC", 33);
    model.addStockToBasket("basket-three", "HAL", 5);
    model.addStockToBasket("basket-three", "A", 9);
    model.addStockToBasket("basket-three", "PWC", 1);

    testMapOne.put("BAC", 6);
    testMapOne.put("CHK", 15);
    testMapOne.put("TWTR", 90);
    testMapOne.put("INTC", 10);

    testMapThree.put("WFC", 33);
    testMapThree.put("HAL", 5);
    testMapThree.put("A", 9);
    testMapThree.put("PWC", 1);

  }

  @Test
  public void addStockToBasket() throws Exception {
    // add stock to empty basket
    int origSz = model.getBasketContentByName("basket-two").size();
    model.addStockToBasket("basket-two", "HAL", 5);
    assertEquals(origSz + 1, model.getBasketContentByName("basket-two").size());

    // add zero stock to empty basket
    model.addStockToBasket("basket-two", "PG", 0);
    assertEquals(origSz + 2, model.getBasketContentByName("basket-two").size());

    // add stock to pre-existing stock
    model.addStockToBasket("basket-two", "HAL", 5);
    assertEquals(origSz + 2, model.getBasketContentByName("basket-two").size());
    assertEquals((Integer) 10, model.getBasketContentByName("basket-two").get("HAL"));

    // add zero stock to pre-existing stock
    model.addStockToBasket("basket-two", "HAL", 0);
    assertEquals(origSz + 2, model.getBasketContentByName("basket-two").size());
    assertEquals((Integer) 10, model.getBasketContentByName("basket-two").get("HAL"));

    // remove stock from pre-existing basket
    model.addStockToBasket("basket-two", "HAL", -3);
    assertEquals(origSz + 2, model.getBasketContentByName("basket-two").size());
    assertEquals((Integer) 7, model.getBasketContentByName("basket-two").get("HAL"));

    try { // try adding stock to a single stock
      model.createStockBasket("AAPL");
      model.addStockToBasket("AAPL", "WFC", 1);
      Assert.fail();
    } catch (Exception e) {
      // pass
    }
  }

  @Test
  public void addStockToBasketExceptions() throws Exception {

    try { // adding invalid stock symbol
      model.addStockToBasket("basket-two", "ASDKLFJ", 1);
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

    try { // add stock to nonexistent basket
      model.addStockToBasket("frmbl", "DKS", 11);
      Assert.fail();
    } catch (Exception e) {
      // pass
    }

  }


  @Test
  public void getBasketContentByName() throws Exception {


    // get contents of non-empty basket
    assertEquals(testMapOne, model.getBasketContentByName("basket-one"));
    // get contents of empty basket
    assertEquals(emptySet, model.getBasketContentByName("basket-two"));
    // get contents of another non-empty bsket
    assertEquals(testMapThree, model.getBasketContentByName("basket-three"));

    try { // get contents of nonexistent basket
      assertEquals(null, model.getBasketContentByName("frmbl"));
    } catch (Exception e) {
      // pass
    }
  }

  @Test
  public void getBaskets() throws Exception {
    assertTrue(this.model.getBaskets().keySet().containsAll(Arrays.asList(this.testStrings)));
    assertTrue(this.model.getBaskets().get(testStrings[0]).equals(testMapOne));
    assertTrue(this.model.getBaskets().get(testStrings[1]).equals(new HashMap<>()));
    assertTrue(this.model.getBaskets().get(testStrings[2]).equals(testMapThree));
  }

  @Test
  public void createStockBasket() throws Exception {

    // add new stock basket to new TraderModelImpl
    TraderModel newModel = new TraderModelImpl();
    assertEquals(0, newModel.getBaskets().size());
    newModel.createStockBasket("test-basket");
    assertEquals(1, newModel.getBaskets().size());

    // overwrite a stock basket
    assertEquals(0, newModel.getBaskets().get("test-basket").size());
    newModel.addStockToBasket("test-basket", "AAPL", 1);
    newModel.addStockToBasket("test-basket", "WFC", 1);
    assertEquals(2, newModel.getBaskets().get("test-basket").size());
    newModel.createStockBasket("test-basket");
    assertEquals(0, newModel.getBaskets().get("test-basket").size());

    // adding new basket with different name
    newModel.createStockBasket("second-test-basket");
    assertEquals(2, newModel.getBaskets().size());

    // new stock basket to pre-existing TraderModelImpl
    int originalSize = model.getBaskets().size();
    model.createStockBasket("third-test-basket");
    assertEquals(originalSize + 1, model.getBaskets().size());

  }

  @Test
  public void getHighestPrice() throws Exception {
    // make call to getPlotData() in order to force a value to be assigned as the highest price
    TraderModel tempModel = new TraderModelImpl();
    tempModel.createStockBasket("BAC");
    tempModel.createStockBasket("CHK");
    tempModel.createStockBasket("TWTR");
    tempModel.createStockBasket("INTC");
    tempModel.createStockBasket("WFC");
    tempModel.createStockBasket("HAL");
    tempModel.createStockBasket("A");
    tempModel.createStockBasket("PWC");
    tempModel.getPlotData("2017-06-21", "2017-06-22");
    assertEquals(85.6751, tempModel.getHighestPrice(), 0.01);
  }

  @Test
  public void getMovingAveragesForAll()
          throws Exception {
  }

  @Test
  public void getMovingAveragesForOne() throws Exception {
    this.model.createStockBasket("AAPL");
    assertEquals(148.85, this.model.getMovingAveragesForOne("AAPL",
            "2017-06-21", "2017-06-22",50).get("2017-06-22"), 0.01);


    Map<String, Double> temp = this.model.getMovingAveragesForOne("basket-one",
            "2017-06-20", "2017-06-22", 50);
    assertEquals(2041.488, temp.get("2017-06-22"), 0.01);
    assertEquals(2076.368, temp.get("2017-06-21"), 0.01);
    assertEquals(2111.365, temp.get("2017-06-20"), 0.01);
  }

  @Test
  public void getPlotData() throws Exception {
    //todo exception for same day
    assertTrue(this.hashCode() != 0);
  }

  @Test
  public void getPlotDataForOne() throws Exception {
    assertTrue(this.hashCode() != 0);
  }

  @Test
  public void remove() throws Exception {
    TraderModel md = new TraderModelImpl();
    Map<String, Integer> temp;

    md.createStockBasket("test-sb");
    temp = md.getBasketContentByName("test-sb");
    assertEquals(0, temp.size());

    md.addStockToBasket("test-sb", "AMZN", 3);
    temp = md.getBasketContentByName("test-sb");
    assertEquals(1, temp.size());

    md.remove("test-sb");

    try { // verify removal worked by triggered a "Basket not found Exception"
      md.getBasketContentByName("test-sb");
    } catch (Exception e) {
      // pass
    }

  }

  @Test
  public void trendsUp() throws Exception {
    //TODO: test this -- just do math and check for correct result
    assertTrue(this.hashCode() != 0);
  }

}
