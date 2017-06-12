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

* *IStockDataQuery* is the super interface for *ISingleStockDataQuery* and *IBasketStockDataQuery*.
* Queries should work with anything that implements the *StockDataRetriever* interface.

### Optional

* Try out different investment strategies: periodically investing a fixed amount of money, buying and selling to maintain a specific balance in a basket, etc.
* Graph the outputs
* Interactivity

### TODO

* Split workload with @steventhan
* @steventhan to look at JUnit documentation about synchronicity
* Complete the _Basket_ class
* <del>Decide how to do the 5 things</del>
* <del>Implement SingleStockDataQuery like Expression from assignment 4, i.e. cannot change query once it is made, you would need to make a new query</del>
* <del>Actually query something!</del>
* <del>Make interface from model to controller</del> (IStockDataQuery)
* <del>Decide how to implement getPriceOnDay()</del>

### Emoji Meanings
_more than emoji one may be used at a time_
#### Frequent
* :smile: progress made
* :heavy_check_mark: task completed
* :+1: working code
* :black_nib: documentation, style edits, and logistics
* :recycle: major refactoring
* :thought_balloon: brainstorming
* :warning: untested code
* :exclamation: broken code
#### Infrequent
* :scream: panic mode
* :godmode: god mode
* :zzz: going to bed
* :trollface: "I like my way better"





