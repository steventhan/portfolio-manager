package controller.trader;

import java.io.InputStreamReader;

import model.trader.TraderModel;
import view.trader.TraderView;

/**
 * An implementation of TraderController interface.
 */
public class TraderControllerImpl implements TraderController {
  private TraderModel model;
  private TraderView view;
  private Readable in;

  public TraderControllerImpl(TraderModel model, TraderView view) {
    this(model, view, new InputStreamReader(System.in));
  }

  public TraderControllerImpl(TraderModel model, TraderView view, Readable in) {
    this.model = model;
    this.view = view;
    this.in = in;
  }

  @Override
  public void start() throws Exception {

  }
}
