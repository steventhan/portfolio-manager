package view.trader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by matthiasdenu on 6/20/2017.
 */
public class TraderViewImpl implements TraderView {

  //TODO: use composition and delegation to implement all methods in TraderView interface
  private TraderGraphicalView graphicalView;
  private TraderTextView textView;

  public TraderViewImpl() {
    graphicalView = new TraderGraphicalViewImpl();
    textView = new TraderTextViewImpl(System.out);
  }

  //TODO: document that append is used to send warnings to user
  //TODO: add overrides

  @Override
  public void plotRecord(String name, Map<String, Double> data) {
    this.graphicalView.plotRecord(name, data);
  }

  @Override
  public void exit() {
    this.graphicalView.exit();
  }

  @Override
  public void printBasket(Map<String, Integer> basket) throws Exception {
    this.textView.printBasket(basket);
  }

  @Override
  public void printMenu(List<String> options) throws Exception {
    this.textView.printMenu(options);
  }

  @Override
  public void append(String text) throws IOException {
    this.textView.append(text);
  }

}
