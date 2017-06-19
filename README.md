# neu-cs5004-hw6
## Assignment 6: Views for the Stocks Application

### Assignment Requirements
3 Graphical view

* In the graphical view, a user should be able to open a window and view plotted graphs of the prices of one or more baskets or stocks created in the model. Specifically the window shows the plots of the prices vs. dates in a specified date range. While you are free to choose the details of drawing, you must have the following:
   * ability to plot the closing prices of one or more stocks/baskets simultaneously.
   * ability to plot the 50-day and/or 200-day averages of one or more stocks/baskets with the plot of their closing prices.
   * plots should have a different color.
   * should be a legend showing the meaning of each color (stock ticker symbol and basket name with quantity that is plotted are enough).
     * _the legend may need to be another panel_

Add option(s) to the text view to create and customize this graphical view. A user should be able to create a blank graphical view, add or remove plots to it (e.g. add a new ticker symbol) in a suitable manner. Once shown, the graphical view will be visible unless the window is closed. Any changes to what the graphical view shows should cause it to refresh automatically to show the updated plots.

# neu-cs5004-hw5
## Assignment 5: Investing Application
:moneybag: :dollar: :pound: :euro: :yen: :money_with_wings: :yen: :euro: :pound: :dollar: :moneybag:

### Assignment Requirements

* Look up the price of a stock or basket on a certain day
* Determine if there is a buying opportunity for a certain stock on a certain day
* Get historical (closing) prices for a stock for a certain date range.
    * Historical prices are available only for business days.
* Create a basket of stocks comprising of shares of one or more stocks.
    * Determine its price on a certain date.
* Determine if a stock or a basket trends up during a certain date range.

### FYI

* *IStock* is the super interface for *IStockSingle* and *IBasketStockDataRecord*.
* Queries should work with anything that implements the *NewStockRetriever* interface.

### Optional

* Be able to pass different NewStockRetrievers to implementations of IStocks
* In the interest of implementing a Map<Strings, Stocks> in the controller, override the hasCode() and equals() methods later.
* Try out different investment strategies: periodically investing a fixed amount of money, buying and selling to maintain a specific balance in a basket, etc.
* Graph the outputs
* Interactivity

### TODO

* Resolve internal TODO list items
* Resolve all failing tests
* Review documentation. Document all methods that require documentation.
* Change access to methods once testing is finished
* @steventhan to write StockSingle class
* @steventhan to write test for Basket class
* @matthiasdenu to write Basket class
* @matthiasdenu to write test for Stock class
* @steventhan to look at java.util.Date class
* <del>Decide how to do the 5 things</del>
* <del>Implement StockSingle like Expression from assignment 4, i.e. cannot change query once it is made, you would need to make a new query</del>
* <del>Actually query something!</del>
* <del>Make interface from model to controller</del> (IStock)
* <del>Decide how to implement getPriceOnDay()</del>

### Emoji Meanings
_more than emoji one may be used at a time_
#### Frequent
* :construction: work in progress
* :heavy_check_mark: task completed
* :smile: working code
* :black_nib: documentation, style edits, and logistics
* :thought_balloon: brainstorming
* :recycle: major refactoring
* :warning: untested code
* :exclamation: broken code
#### Infrequent
* :scream: panic mode
* :godmode: god mode
* :zzz: going to bed
* :trollface: "I like my way better"





