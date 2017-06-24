import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import controller.trader.TraderControllerImpl;
import model.trader.TraderModelImpl;
import view.trader.TraderGraphicalViewImpl;
import view.trader.TraderTextViewImpl;
import view.trader.TraderViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by matthiasdenu on 6/23/2017.
 */
public class TraderAppTest {
  private String mainMenu;
  private String createPrompt;
  private String addPrompt;
  private String printPrompt;
  private StringBuffer out;
  private Reader in;

  @Before
  public void setUp() throws Exception {
    this.mainMenu = "c - Create new stock or basket\n"
            + "a - Add new share to a basket\n"
            + "p - Print the stock basket\n"
            + "t - Trend of stock\n"
            + "l - Plot\n"
            + "r - Remove stock entity by name\n"
            + "q - Quit\n"
            + "Select: ";

    this.createPrompt = "Enter stock symbol or basket name \n" +
            "(if the string is a valid \n" +
            "stock symbol, it's assumed to be stock, \n" +
            "otherwise a basket will be created): \n";

    this.printPrompt = "Enter basket name to print: ";
    this.addPrompt = "Enter basket name to add: Enter stock symbol: Amount: \n";
  }

  @Test
  public void testRunApp() throws Exception {
    in = new StringReader("q\n");
    out = new StringBuffer();

    new TraderControllerImpl(new TraderModelImpl(),
            new TraderViewImpl(new TraderGraphicalViewImpl(),
                    new TraderTextViewImpl(out)), in).start();

    assertEquals(mainMenu, out.toString());
  }


  @Test
  public void testCreateStock() throws Exception {

    in = new StringReader("c\naapl\nq\n");
    out = new StringBuffer();

    new TraderControllerImpl(new TraderModelImpl(),
            new TraderViewImpl(new TraderGraphicalViewImpl(),
                    new TraderTextViewImpl(out)), in).start();

    assertEquals(mainMenu + createPrompt + mainMenu, out.toString());
  }

  @Test
  public void testCreateBasket() throws Exception {
    in = new StringReader("c\nrandom\nq\n");
    out = new StringBuffer();

    new TraderControllerImpl(new TraderModelImpl(),
            new TraderViewImpl(new TraderGraphicalViewImpl(),
                    new TraderTextViewImpl(out)), in).start();

    assertEquals(mainMenu + createPrompt + mainMenu, out.toString());
  }

  @Test
  public void testCreateAndAdd() throws Exception {
    in = new StringReader("c\nrandom\na\nrandom\naapl\n10\nq\n");
    out = new StringBuffer();

    new TraderControllerImpl(new TraderModelImpl(),
            new TraderViewImpl(new TraderGraphicalViewImpl(),
                    new TraderTextViewImpl(out)), in).start();

    assertEquals(mainMenu + createPrompt + mainMenu + addPrompt + mainMenu, out.toString());
  }

  @Test
  public void testCreateBasketAddAndPrint() throws Exception {
    in = new StringReader("c\nrandom\na\nrandom\naapl\n10\np\nrandom\nq\n");
    out = new StringBuffer();
    String aapl = "aapl: 10 shares\n\n\n";

    new TraderControllerImpl(new TraderModelImpl(),
            new TraderViewImpl(new TraderGraphicalViewImpl(),
                    new TraderTextViewImpl(out)), in).start();

    assertEquals(mainMenu + createPrompt + mainMenu + addPrompt + mainMenu
            + printPrompt + aapl + mainMenu, out.toString());
  }

  @Test
  public void testCreateBasketAddAndPrintManyItems() throws Exception {
    in = new StringReader("c\nrandom\n" +
            "a\nrandom\naapl\n10\n" +
            "a\nrandom\namzn\n1\n" +
            "a\nrandom\namd\n15\n" +
            "p\nrandom\n" +
            "q\n");
    out = new StringBuffer();
    String amzn = "amzn: 1 share\n";
    String aapl = "aapl: 10 shares\n";
    String amd = "amd: 15 shares\n";

    new TraderControllerImpl(new TraderModelImpl(),
            new TraderViewImpl(new TraderGraphicalViewImpl(),
                    new TraderTextViewImpl(out)), in).start();

    assertTrue(out.toString().contains(amzn));
    assertTrue(out.toString().contains(aapl));
    assertTrue(out.toString().contains(amd));
  }

  @Test
  public void testAddBasketNotFound() throws Exception {
    in = new StringReader("c\nrandom\na\nsomething\nq\n");
    out = new StringBuffer();

    new TraderControllerImpl(new TraderModelImpl(),
            new TraderViewImpl(new TraderGraphicalViewImpl(),
                    new TraderTextViewImpl(out)), in).start();

    assertTrue(out.toString().contains("Basket not found.\n"));
  }

  @Test
  public void testPrintBasketNotFound() throws Exception {
    in = new StringReader("c\nrandom\np\nsomething\nq\n");
    out = new StringBuffer();

    new TraderControllerImpl(new TraderModelImpl(),
            new TraderViewImpl(new TraderGraphicalViewImpl(),
                    new TraderTextViewImpl(out)), in).start();

    assertTrue(out.toString().contains("Basket not found.\n"));
  }

  @Test
  public void testAddInvalidAmount() throws Exception {
    in = new StringReader("c\nrandom\na\nrandom\naapl\nsadf\nq\n");
    out = new StringBuffer();

    new TraderControllerImpl(new TraderModelImpl(),
            new TraderViewImpl(new TraderGraphicalViewImpl(),
                    new TraderTextViewImpl(out)), in).start();

    assertTrue(out.toString().contains("Invalid amount.\n"));
  }

  @Test
  public void testStockTrend() throws Exception {
    in = new StringReader("c\naapl\nt\naapl\n2015-01-08\n2015-02-05\nq\n");
    out = new StringBuffer();

    new TraderControllerImpl(new TraderModelImpl(),
            new TraderViewImpl(new TraderGraphicalViewImpl(),
                    new TraderTextViewImpl(out)), in).start();

    assertTrue(out.toString().contains("This basket or stock is trending up.\n"));
  }

  @Test
  public void testStockTrendInvalidDate() throws Exception {
    in = new StringReader("c\naapl\nt\naapl\n2015-01-10\n2015-02-03\nq\n");
    out = new StringBuffer();

    new TraderControllerImpl(new TraderModelImpl(),
            new TraderViewImpl(new TraderGraphicalViewImpl(),
                    new TraderTextViewImpl(out)), in).start();

    assertTrue(out.toString().contains("Check input date\n"));

    in = new StringReader("c\naapl\nt\naapl\n2015-01-08\n2015-01-01\nq\n");
    out = new StringBuffer();

    new TraderControllerImpl(new TraderModelImpl(),
            new TraderViewImpl(new TraderGraphicalViewImpl(),
                    new TraderTextViewImpl(out)), in).start();

    assertTrue(out.toString().contains("Start date has to be before end date"));

    in = new StringReader("c\naapl\nt\naapl\n2015-01-08\n2018-01-01\nq\n");
    out = new StringBuffer();

    new TraderControllerImpl(new TraderModelImpl(),
            new TraderViewImpl(new TraderGraphicalViewImpl(),
                    new TraderTextViewImpl(out)), in).start();

    assertTrue(out.toString().contains("Start date has to be before end date, and end date " +
            "cannot be in the future"));
  }
}

