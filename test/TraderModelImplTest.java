import org.junit.Test;
import org.junit.Before;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import model.trader.TraderModel;
import model.trader.TraderModelImpl;
import view.trader.TraderView;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Class to test model.
 */
//TODO: test getRecords();
public class TraderModelImplTest {
  TraderModel model;

  @Before
  public void setup() throws Exception {
    model = new TraderModelImpl();
    model.createStockBasket("basket one");
    model.createStockBasket("basket two");
    model.createStockBasket("basket three");
    model.createStockBasket("basket four");
    model.createStockBasket("basket five");
    model.createStockBasket("basket six");

    model.addStockToBasket("basket one", "AAPL", 3);
    model.addStockToBasket("basket one", "WFC", 9);
    model.addStockToBasket("basket one", "UPS", 5);
    model.addStockToBasket("basket one", "AMZN", 3);

    model.addStockToBasket("basket two", "BAC", 6);
    model.addStockToBasket("basket two", "CHK", 15);
    model.addStockToBasket("basket two", "TWTR", 90);
    model.addStockToBasket("basket two", "INTC", 10);

    model.addStockToBasket("basket three", "INTC", 13);
    model.addStockToBasket("basket three", "MU", 15);
    model.addStockToBasket("basket three", "NVDA", 22);
    model.addStockToBasket("basket three", "SIRI", 8);

    model.addStockToBasket("basket four", "MSFT", 5);
    model.addStockToBasket("basket four", "CSCO", 5);
    model.addStockToBasket("basket four", "FB", 5);
    model.addStockToBasket("basket four", "ADBE", 5);

    model.addStockToBasket("basket six", "WFM", 10);

  }


  @Test
  public void createStockBasket() throws Exception {
    // new stock basket to new TraderModelImpl
    TraderModel newModel = new TraderModelImpl();
    assertEquals(0, newModel.getRecords().size());
    newModel.createStockBasket("test basket");
    assertEquals(1, newModel.getRecords().size());

    // TODO: decide how to handle duplicate basket creation (document it)
    // adding new basket with same name (Exception
    // newModel.createStockBasket("test basket");

    // adding new basket with different name
    newModel.createStockBasket("second test basket");
    assertEquals(2, newModel.getRecords().size());

    // new stock basket to pre-existing TraderModelImpl
    int originalSize = model.getRecords().size();
    model.createStockBasket("third test basket");
    assertEquals(originalSize + 1, model.getRecords().size());

    // TODO: test duplicates for pre-existing TraderModelImpl as well

    // System.out.println("createStockBasket()\n");
    // System.out.println(newModel.toString());
    // System.out.println(model.toString());
  }

  @Test
  public void addStockToBasket() throws Exception {
    // add stock to empty basket
    int origSz = model.getBasketContentByName("basket five").size();
    model.addStockToBasket("basket five", "HULU", 5);
    assertEquals(origSz + 1, model.getBasketContentByName("basket five").size());

    // add zero stock to empty basket
    //TODO: update so that stock needs nonzero value to be added to basket
    model.addStockToBasket("basket five", "PG", 0);
    assertEquals(origSz + 2, model.getBasketContentByName("basket five").size());

    // add stock to pre-existing stock
    //TODO: decide whether adding stock to pre-existing stock overwrites or adds to
    model.addStockToBasket("basket five", "HULU", 5);
    assertEquals(origSz + 2, model.getBasketContentByName("basket five").size());
    assertEquals((Integer) 10, model.getBasketContentByName("basket five").get("HULU"));

    // add zero stock to pre-existing stock
    model.addStockToBasket("basket five", "HULU", 0);
    assertEquals(origSz + 2, model.getBasketContentByName("basket five").size());
    assertEquals((Integer) 10, model.getBasketContentByName("basket five").get("HULU"));

    // add stock for invalid symbol
    //TODO: update so that invalid symbols can't be added
    model.addStockToBasket("basket five", "DICKS", 11);
    assertEquals(origSz + 3, model.getBasketContentByName("basket five").size());
    assertEquals((Integer) 10, model.getBasketContentByName("basket five").get("HULU"));

    // add stock to nonexistent basket
    //TODO: decide how to handle this null pointer exception
    // model.addStockToBasket("frmbl", "DKS", 11);
    // assertEquals(origSz + 4, model.getBasketContentByName("basket five").size());
    // assertEquals((Integer) 11, model.getBasketContentByName("basket five").get("DKS"));

    System.out.println(model.toString());
  }

  @Test
  public void remove() throws Exception {
    TraderModel md = new TraderModelImpl();
    Map<String, Integer> temp;

    md.createStockBasket("test sb");
    temp = md.getBasketContentByName("test sb");
    assertEquals(0, temp.size());

    md.addStockToBasket("test sb", "AMZN", 3);
    temp = md.getBasketContentByName("test sb");
    assertEquals(1, temp.size());

    md.addStockToBasket("test sb", "AAPL", 3);
    temp = md.getBasketContentByName("test sb");
    assertEquals(2, temp.size());

    md.addStockToBasket("test sb", "UPS", 3);
    temp = md.getBasketContentByName("test sb");
    assertEquals(3, temp.size());

    md.remove("test sb");
    temp = md.getBasketContentByName("test sb");
    assertEquals(0, temp.size());
  }

  @Test
  public void getBasketContentByName() throws Exception {
    Map<String, Integer> testMapOne = new TreeMap<>();
    testMapOne.put("BAC", 6);
    testMapOne.put("CHK", 15);
    testMapOne.put("TWTR", 90);
    testMapOne.put("INTC", 10);
    Map<String, Integer> emptySet = new LinkedHashMap<>();

    // get contents of non-empty basket
    assertEquals(testMapOne, model.getBasketContentByName("basket two"));
    // get contents of empty basket
    assertEquals(emptySet, model.getBasketContentByName("basket five"));
    //TODO: decide how to handle this null pointer exception
    // get contents of nonexistent basket
    // assertEquals(null, model.getBasketContentByName("frmbl"));
  }

  @Test
  public void trendsUp() throws Exception {
    //TODO: test this. just do math and check for correct resutl
  }

  @Test
  public void getRecords() throws Exception {
    //TODO: possibly return records sorted by name
    //TODO: make a big Map<String, Map<String, Integer>> to compare with and check
    System.out.println(TraderModel.sPrintStockRecords(model.getRecords()));
  }

  @Test
  public void testToString() throws Exception {
    System.out.println(model.toString());
  }
}