package view.trader;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;

/**
 * Created by matthiasdenu on 6/20/2017.
 */
public class TraderViewImpl implements TraderView {

  //TODO: use composition and delegation to implement all methods in TraderView interface
  private ITraderGraphicalView graphicalView;
  private ITraderTextView textView;

  public TraderViewImpl() {
    graphicalView = new TraderGraphicalView();
    textView = new TraderTextView(System.out);
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
  public void printRecord(Map<String, Integer> basket) throws Exception {
    this.textView.printRecord(basket);
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
