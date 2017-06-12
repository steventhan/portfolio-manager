# neu-cs5004-hw5
## Assignment 5: Investing Application
:moneybag: :dollar: :pound: :euro: :yen: :money_with_wings:

### General Functions

We could possibly have a class called *SingleStockDataQuery*. A static member of that class would be the singleton *util.StockDataRetriever*. Members of *SingleStockDataQuery* could be like *String queryString*, *Map<Integer, PriceRecord> priceRecords*, *Integer startDate*, and *Integer endDate*.

* Look up the price of a stock on a certain day
    * Possibly a method *Integer getPriceOnDay()* that throws an exception of date is out of range.
* Determine if there is a buying opportunity for a certain stock on a certain day
    * Pick any day in the range for this query, and determine whether there is a buying opportunity
    * Should there be a default *SingleStockDataQuery* that works using the default investment strategy described in the instructions? Should we then have other classes with different investment strategies extend *SingleStockDataQuery*?
* Get historical (closing) prices for a stock for a certain date range. Historical prices are available only for business days.
* Create a basket of stocks comprising of shares of one or more stocks and then determine its price on a certain date.
    * Oou. How should we do this? Should another member be added to the *SingleStockDataQuery* class? Should the basket (wherever it is) contain just the symbols, or both the symbols and the price records at all times?
* Determine if a stock or a basket trends up during a certain date range.
    * *getTrend()* overloaded for basket parameters and stock parameters
* Try out different investment strategies: periodically investing a fixed amount of money, buying and selling to maintain a specific balance in a basket, etc.
    * Is this a required feature of the program?

### Other Notes

* Queries should work with anything that implements the *StockDataRetriever* interface.

### TODO

* Split workload with @steventhan
* Decide how to do the 5 things
* Implement SingleStockDataQuery like Expression from assignment 4, i.e. cannot change query once it is made, you would need to make a new query
* <del>Actually query something!</del>
* @steventhan to look at JUnit documentation about synchronicity
* <del>Make interface from model to controller</del> (IStockDataQuery)
* <del>Decide how to implement getPriceOnDay()</del>
* Complete the _Basket_ class

### Emoji Meanings
_*more than emoji one may be used at a time*_
#### Frequent
* :smile: progress made
* :heavy_check_mark: task completed
* :+1: working code
* :black_nib: documentation, style edits, and logistics
* :recycle: major refactoring
* :warning: untested code
* :exclamation: broken code
* :thought_balloon: brainstorming
#### Infrequent
* :scream: panic mode
* :godmode: god mode
* :zzz: going to bed
* :trollface: "I like my way better"





